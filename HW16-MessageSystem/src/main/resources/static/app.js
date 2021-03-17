var stompClient = null;

function getListUsers() {
    connect();
}

function loadUsers() {
    var request = new XMLHttpRequest();
    request.open('PUT', '/api/users');
    request.send();
}

function connect() {
      var socket = new SockJS('/gs-guide-websocket');
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function(frame) {
      console.log('Connected: ' + frame);
      stompClient.subscribe('/topic/response', (dtoUser) => addUserToTable(JSON.parse(dtoUser.body).name, JSON.parse(dtoUser.body).login));
      });
}

const addUserToTable = (name, login) => $("#listUsers").append("<tr><td>" + name + "</td><td>" + login + "</td></tr>")

const sendUser = () => stompClient.send("/app/addUser", {}, JSON.stringify({'login': $("#login").val(),
        'name': $("#name").val(), 'password': $("#password").val()}))

$(function () {
    $("form").on('submit', (event) => {
        event.preventDefault();
    });
    $("#send").click(sendUser);
});
