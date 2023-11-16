/**
 * web socket
 */
    // 서버의 broadsocket의 서블릿으로 웹 소켓을 한다.
    var webSocket = new WebSocket("ws://localhost:9004/userChat");
    // 콘솔 텍스트 영역
    var messageTextArea = document.getElementById("messageTextArea");
    // 접속이 완료되면
    webSocket.onopen = function(message) {
      // 콘솔에 메시지를 남긴다.
      messageTextArea.value += "문의사항을 남겨주세요.\n";
    };
    // 접속이 끝기는 경우는 브라우저를 닫는 경우이기 떄문에 이 이벤트는 의미가 없음.
    webSocket.onclose = function(message) { };
    // 에러가 발생하면
    webSocket.onerror = function(message) {
      // 콘솔에 메시지를 남긴다.
      messageTextArea.value += "error...\n";
    };
    // 서버로부터 메시지가 도착하면 콘솔 화면에 메시지를 남긴다.
    webSocket.onmessage = function(message) {
      $("#messageTextArea").append("<div id='adTalk'><b>관리자</b><br>" + message.data + "</div>"); 
	  $('#messageTextArea').scrollTop($('#messageTextArea').prop('scrollHeight'));
    };
    // 서버로 메시지를 발송하는 함수
    // Send 버튼을 누르거나 텍스트 박스에서 엔터를 치면 실행
    function sendMessage() {
		var me = $('input[name=name]').val();
      // 텍스트 박스의 객체를 가져옴
      let message = document.getElementById("textMessage");
      // 콘솔에 메세지를 남긴다.
       $("#messageTextArea").append("<div id='talk'><b>" + me + "</b><br>" + message.value + "</div>");
	   $('#messageTextArea').scrollTop($('#messageTextArea').prop('scrollHeight'));
      // 소켓으로 보낸다.
      webSocket.send(message.value);
      // 텍스트 박스 초기화
      message.value = "";
      $('input[name=name]').focus();
    }
    // 텍스트 박스에서 엔터를 누르면
    function enter() {
      // keyCode 13은 엔터이다.
      if(event.keyCode === 13) {
        // 서버로 메시지 전송
        sendMessage();
        // form에 의해 자동 submit을 막는다.
        return false;
      }
      return true;
    }
