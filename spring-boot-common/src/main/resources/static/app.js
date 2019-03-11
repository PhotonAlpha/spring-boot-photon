var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('http://localhost:8080/socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({"token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzMTQ4MzE2Mi1jNTQ3LTQzN2QtOTY3NC0wNWFmODQ1ODI5MzEiLCJhdWQiOiJ3ZWIiLCJyb2xlIjpbIlJPTEVfVVNFUiJdLCJleHAiOjE1NTI4NzU1NzIsImlhdCI6MTU1MjI3NTU3Mn0.FY92zBBSf14ZrRUb8I90QKN0lix9jAGp18jCnFpTwyR3oYBkFJ3QjxClPLKw35MP-Uuhj4r2JVAoY4esYv6PHQ"}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (greeting) {
            console.log('response: ' + greeting);
            showGreeting(JSON.parse(greeting.body).body);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/connect/device", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});