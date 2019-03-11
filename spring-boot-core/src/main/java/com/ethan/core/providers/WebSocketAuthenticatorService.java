package com.ethan.core.providers;

import com.ethan.core.model.AuthorityName;
import com.ethan.core.model.Authoritys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class WebSocketAuthenticatorService {
	public UsernamePasswordAuthenticationToken getAuthenticatedOrFail(final String  username) throws AuthenticationException {
        if (username == null ) {
            throw new AuthenticationCredentialsNotFoundException("Username was null or empty.");
        }
        // Add your own logic for retrieving user in fetchUserFromDb()
//        if (fetchUserFromDb(username, password) == null) {
//            throw new BadCredentialsException("Bad credentials for user " + username);
//        }
        // null credentials, we do not pass the password along to prevent security flaw
        return new UsernamePasswordAuthenticationToken(
        			username,
                null,
                Collections.singleton((GrantedAuthority) () -> AuthorityName.ROLE_USER.name())
        );
    }
}
