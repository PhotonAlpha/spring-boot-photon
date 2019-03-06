/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-17 19:13
 **/
@Component
public class JwtTokenUtils {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_AUDIENCE = "aud";
    private static final String CLAIM_KEY_CREATED = "iat";

    private static final String AUDIENCE_UNKNOWN = "unknown";
    private static final String AUDIENCE_WEB = "web";
    private static final String AUDIENCE_MOBILE = "mobile";
    private static final String AUDIENCE_TABLET = "tablet";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.shortexpiration}")
    private Long shortExpiration;

    public <T>T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String getAudienceFromToken(String token) {
        return getClaimFromToken(token, Claims::getAudience);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(now());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private String generateAudience(Device device) {
        String audience = AUDIENCE_UNKNOWN;
        if (device.isNormal()) {
            audience = AUDIENCE_WEB;
        } else if (device.isTablet()) {
            audience = AUDIENCE_TABLET;
        } else if (device.isMobile()) {
            audience = AUDIENCE_MOBILE;
        }
        return audience;
    }

    private Boolean ignoreTokenExpiration(String token) {
        String audience = getAudienceFromToken(token);
        return (AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience));
    }

    public String generateTempToken(String code, Device device) {
        Map<String, Object> claims = new HashMap<>();

        final Date createdDate = now();
        final LocalDateTime localTime = createdDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime expirationTime = localTime.plusSeconds(shortExpiration);
        final Date expirationDate = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(code)
                .setAudience(generateAudience(device))
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String generateToken(UserDetails userDetails, Device device) {
        Map<String, Object> claims = new HashMap<>();
        final List<String> roles = userDetails.getAuthorities().stream().map(auth -> ((GrantedAuthority) auth).getAuthority())
                .collect(Collectors.toList());
        claims.put("role", roles);
        return doGenerateToken(claims, userDetails.getUsername(), generateAudience(device));
    }
    public String generateRefreshToken(UserDetails userDetails, Device device) {
        Map<String, Object> claims = new HashMap<>();
        final Date createdDate = now();
        final LocalDateTime localTime = createdDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime expirationTime = localTime.plusSeconds(expiration * 5);
        final Date expirationDate = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setAudience(generateAudience(device))
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, String audience) {
        final Date createdDate = now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setAudience(audience)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        // final Date createDate = getIssuedAtDateFromToken(token);
        return (username.equals(user.getUsername())
            && !isTokenExpired(token));
            // && !isCreatedBeforeLastPasswordReset(createDate, ((JwtUser) userDetails).getLastPasswordResetDate()));
    }
    public boolean validateToken(String token, String username) {
        final String tokenUserName = getUsernameFromToken(token);
        return (tokenUserName.equals(username) && !isTokenExpired(token));
    }


    public boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date createDate = getIssuedAtDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(createDate, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }
    public boolean canTokenBeRefreshed(String token) {
        final Date createDate = getIssuedAtDateFromToken(token);
        return !isTokenExpired(token);
    }

    public String refreshToken(String token) {
        final Date createdDate = now();
        final Date expirationDate = calculateExpirationDate(createdDate);
        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date calculateExpirationDate(Date createdDate) {
        final LocalDateTime localTime = createdDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime expirationTime = localTime.plusSeconds(expiration);
        return Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Date now() {
        return new Date();
    }
}
