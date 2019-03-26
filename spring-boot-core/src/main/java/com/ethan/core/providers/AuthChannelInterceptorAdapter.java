/*
package com.ethan.core.providers;

import com.ethan.core.security.jwt.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import java.util.Collections;
import java.util.Optional;

@Slf4j
public class AuthChannelInterceptorAdapter implements ChannelInterceptor {
    private final WebSocketAuthenticatorService webSocketAuthenticatorService;
    private MessageChannel clientOutboundChannel;

    private JwtTokenUtils jwtTokenUtil;
    public AuthChannelInterceptorAdapter(final WebSocketAuthenticatorService webSocketAuthenticatorService, final JwtTokenUtils jwtTokenUtil, MessageChannel clientOutboundChannel) {
        this.webSocketAuthenticatorService = webSocketAuthenticatorService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.clientOutboundChannel = clientOutboundChannel;
    }

    @Override
    public Message<?> preSend(final Message<?> message, final MessageChannel channel) throws AuthenticationException {
        String authToken = null;
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        log.info("Websocket Auth------->{} {}", accessor.getCommand(), accessor.getUser());
        if (StompCommand.CONNECT == accessor.getCommand()) {
            log.info("[head token]{}", accessor.getNativeHeader("token"));
            Optional<String> token = Optional.ofNullable(accessor.getNativeHeader("token")).orElse(Collections.emptyList()).stream().findFirst();
            if (token.isPresent() && token.get().startsWith("Bearer ")) {
                authToken = token.get().substring(7);
                String username = jwtTokenUtil.getUsernameFromToken(authToken);
                String user = accessor.getFirstNativeHeader("user");
                log.info("Websocket Auth username {} user {}", username, user);
                final UsernamePasswordAuthenticationToken userAuth = webSocketAuthenticatorService.getAuthenticatedOrFail(username);
                accessor.setUser(userAuth);
            } else {
                String sessionId = accessor.getSessionId();
                StompHeaderAccessor headerAccessor = StompHeaderAccessor.create(StompCommand.ERROR);
                headerAccessor.setMessage("Token Invalid.");
                headerAccessor.setSessionId(sessionId);
                headerAccessor.setLeaveMutable(true);
                clientOutboundChannel.send(MessageBuilder.createMessage(new byte[0], headerAccessor.getMessageHeaders()));
            }
        }
        if (StompCommand.SUBSCRIBE == accessor.getCommand()) {
            log.info("SUBSCRIBE:{}", accessor.getCommand());
        }
        if (StompCommand.DISCONNECT == accessor.getCommand()) {
            String sessionId = accessor.getSessionId();
            StompHeaderAccessor headerAccessor = StompHeaderAccessor.create(StompCommand.DISCONNECT);
            headerAccessor.setSessionId(sessionId);
            headerAccessor.setLeaveMutable(true);
            clientOutboundChannel.send(MessageBuilder.createMessage(new byte[0], headerAccessor.getMessageHeaders()));
        }
        return message;
    }




}
*/
