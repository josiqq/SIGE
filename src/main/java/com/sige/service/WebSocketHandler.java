package com.sige.service;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketHandler implements org.springframework.web.socket.WebSocketHandler {
   public void afterConnectionEstablished(WebSocketSession session) throws Exception {
      System.out.println("Conexión establecida");
   }

   public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
      System.out.println("Mensaje recibido: " + message.getPayload());
      String response = "retorno desde el servidor!";
      session.sendMessage(new TextMessage(response));
   }

   public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
      System.out.println("Error de transporte");
   }

   public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
      System.out.println("Conexión cerrada");
   }

   public boolean supportsPartialMessages() {
      return false;
   }
}
