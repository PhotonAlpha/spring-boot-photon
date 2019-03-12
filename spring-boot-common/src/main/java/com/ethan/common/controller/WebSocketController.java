/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/03/10
 */
package com.ethan.common.controller;

import com.ethan.common.model.WSOutPutMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String send(Message message) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ObjectMapper mapper = new ObjectMapper();
        WSOutPutMessage mess = WSOutPutMessage.builder()
                .header(now.format(formatter))
                .body("hello world")
                .title("message")
                .success(true).build();
        String result = mapper.writeValueAsString(mess);

        log.info("from {} text {}", message.getHeaders(), message.getPayload());

        return result;
    }

    @MessageMapping("/connect/{deviceId}")
    public void send(@DestinationVariable("deviceId") String deviceId, Message message) throws Exception {
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        Principal user = accessor.getUser();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ObjectMapper mapper = new ObjectMapper();
        WSOutPutMessage mess = WSOutPutMessage.builder()
                .header(now.format(formatter))
                .body(deviceId)
                .title("message")
                .success(true).build();
        String result = mapper.writeValueAsString(mess);

        log.info("from {} text {} deviceId {}", message.getHeaders(), message.getPayload(), deviceId);
        template.convertAndSendToUser(deviceId, "/queue/reply", mess);
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
}
