/*
package com.ethan.core.providers;

import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

public class WebSocketAuthorizationSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer{
    @Override
    protected void configureInbound(final MessageSecurityMetadataSourceRegistry messages) {
    	System.out.println("registerStompEndpoints1");
        // You can customize your authorization mapping here.
        messages.anyMessage().authenticated();
    }

    // TODO: For test purpose (and simplicity) i disabled CSRF, but you should re-enable this and provide a CRSF endpoint.
    @Override
    protected boolean sameOriginDisabled() {
    	System.out.println("registerStompEndpoints2");
        return true;
    }
}
*/
