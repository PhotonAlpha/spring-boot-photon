import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-websocket-login',
  templateUrl: './websocket-login.component.html',
  styleUrls: ['./websocket-login.component.scss']
})
export class WebsocketLoginComponent implements OnInit {
  private serverUrl = 'http://localhost:8080/socket';
  private title = 'WebSockets chat';
  private stompClient;

  message: string;

  messageBody: string = '1';

  constructor(private changeDetector: ChangeDetectorRef) { }

  ngOnInit() {
    this.initializeWebSocketConnection();
  }

  initializeWebSocketConnection(){
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    const header = {
      'token': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzMTQ4MzE2Mi1jNTQ3LTQzN2QtOTY3NC0wNWFmODQ1ODI5MzEiLCJhdWQiOiJ3ZWIiLCJyb2xlIjpbIlJPTEVfVVNFUiJdLCJleHAiOjE1NTI4NzU1NzIsImlhdCI6MTU1MjI3NTU3Mn0.FY92zBBSf14ZrRUb8I90QKN0lix9jAGp18jCnFpTwyR3oYBkFJ3QjxClPLKw35MP-Uuhj4r2JVAoY4esYv6PHQ'
    };
    this.stompClient.connect(header, function(frame) {
      that.stompClient.subscribe('/topic/messages', (message) => {
        if(message.body) {
          console.log(message.body);
          const result = JSON.parse(message.body);
          this.messageBody = result.body;
          console.log(this.messageBody);
          // this.changeDetector.markForCheck();
        }
      });
    });
  }

  sendMessage() {
    this.messageBody = '2';
    this.stompClient.send('/app/chat' , {}, this.message);
  }

}
