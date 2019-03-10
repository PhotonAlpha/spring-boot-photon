import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-websocket-login',
  templateUrl: './websocket-login.component.html',
  styleUrls: ['./websocket-login.component.scss']
})
export class WebsocketLoginComponent implements OnInit {
  private serverUrl = 'http://localhost:8080/socket'
  private title = 'WebSockets chat';
  private stompClient;

  message: string;

  messageBody: string;

  constructor() { }

  ngOnInit() {
    this.initializeWebSocketConnection();
  }

  initializeWebSocketConnection(){
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe('/chat', (message) => {
        if(message.body) {
          console.log(message.body);
          this.messageBody = message.body ;
        }
      });
    });
  }

  sendMessage() {
    this.stompClient.send('/app/topic/messages' , {}, this.message);
  }

}
