/**
 * web socket
 */
    // 서버의 broadsocket의 서블릿으로 웹 소켓을 한다.
    var webSocket = new WebSocket("ws://localhost:9004/userChat");
    // 콘솔 텍스트 영역
    var messageTextArea = document.getElementById("messageTextArea");
    // 접속이 완료되면
    webSocket.onopen = function(message) {
		var id = $('input[name=ch_id]').val();
		$.ajax({
			url:'/getChat',
			type:'post',
			data:{'ch_user':id},
			success: function(data){
				for (var i = 0; i < data.length; i++) {
					if(data[i].CH_KEY == id)
					$("#messageTextArea").append("<div id='talk'><b>" + data[i].CH_USER + "("+data[i].CH_NAME+")</b><br>" + data[i].CH_MSG + "</div>");
					else
					$("#messageTextArea").append("<div id='adTalk'><b>관리자</b><br>" + data[i].CH_MSG + "</div>");
				}
			}
		});
      // 콘솔에 메시지를 남긴다.
     $("#messageTextArea").append("<div id='adTalk'><b>관리자</b><br>문의사항을 남겨주세요.</div>"); 
    };
    // 접속이 끝기는 경우는 브라우저를 닫는 경우이기 떄문에 이 이벤트는 의미가 없음.
    webSocket.onclose = function(message) { };
    // 에러가 발생하면
    webSocket.onerror = function(message) {
      // 콘솔에 메시지를 남긴다.
     $("#messageTextArea").append("<div id='adTalk'><b>관리자</b><br>error....</div>"); 
    };
    // 서버로부터 메시지가 도착하면 콘솔 화면에 메시지를 남긴다.
    webSocket.onmessage = function(message) {
		var id = $('input[name=ch_id]').val();
      $("#messageTextArea").append("<div id='adTalk'><b>관리자</b><br>" + message.data + "</div>"); 
	  $('#messageTextArea').scrollTop($('#messageTextArea').prop('scrollHeight'));
	  // DB저장
	    $.ajax({
		  url:'/createChat',
		  type:'post',
		  data:{'ch_key':'admin', 'ch_name':'관리자', 'ch_msg':message.data, 'ch_user':id},
		  dataType:'json'
	  })
    };
    // 서버로 메시지를 발송하는 함수
    // Send 버튼을 누르거나 텍스트 박스에서 엔터를 치면 실행
    function sendMessage() {
		var id = $('input[name=ch_id]').val();
		var name = $('input[name=ch_name]').val();
      // 텍스트 박스의 객체를 가져옴
      let message = document.getElementById("textMessage");
      // 콘솔에 메세지를 남긴다.
       $("#messageTextArea").append("<div id='talk'><b>" + id + "("+name+")</b><br>" + message.value + "</div>");
	   $('#messageTextArea').scrollTop($('#messageTextArea').prop('scrollHeight'));
      // 소켓으로 보낸다.
      webSocket.send(message.value);
      // 텍스트 박스 초기화
      $('input[name=insert]').focus()
	  // DB 저장
	  $.ajax({
		  url:'/createChat',
		  type:'post',
		  data:{'ch_key':id, 'ch_name':name, 'ch_msg':message.value, 'ch_user':id},
		  dataType:'json'
	  })
      message.value = "";
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
    
    function chatEnd() {
		var id = $('input[name=ch_id]').val();
		if(confirm("종료시 기존 대화내용은 사라집니다\n동의하십니까?")){
		  $.ajax({
			  url:'/deleteChat',
			  type:'post',
			  data:{'ch_user':id},
			  success:function() {
			  webSocket.send('사용자가 대화를 종료하였습니다.')
			  window.close()
			  }
		 	 })
		}
	}
