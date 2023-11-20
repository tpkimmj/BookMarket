/**
 * 
 */
  // 서버의 admin의 서블릿으로 웹 소켓을 한다.
    var webSocket = new WebSocket("ws://localhost:9004/adminChat");
    // 운영자에서의 open, close, error는 의미가 없어서 형태만 선언
    webSocket.onopen = function(message) { };
    webSocket.onclose = function(message) { };
    webSocket.onerror = function(message) { };
    // 서버로 부터 메시지가 오면
    webSocket.onmessage = function(message) {
      // 메시지의 구조는 JSON 형태로 만들었다.
      let node = JSON.parse(message.data);
      // 메시지의 status는 유저의 접속 형태이다.
      // visit은 유저가 접속했을 때 알리는 메시지다.
      if(node.status === "visit") {
        // 위 템플릿 div를 취득한다.
        let form = $(".template").html();
        // div를 감싸고 속성 data-key에 unique키를 넣는다.
        form = $("<div class='float-left'></div>").attr("data-key",node.key).append(form);
        // body에 추가한다.
        $("body").append(form);
        
        /*$("input[name=endBtn]").attr('onclick','chatEnd('+ node.key +')');*/
        
        // key로 해당 div영역을 찾는다.
        let $div = $("[data-key='"+node.key+"']");
        // console영역을 찾는다.
        let log = $div.find("#messageTextArea");
        
        $.ajax({
			url:'/getAdminChat',
			type:'post',
			data:{'ch_key':node.key},
			success: function(data){
				for (var i = 0; i < data.length; i++) {
					if(data[i].CH_NAME == '사용자')
					log.append("<div id='adTalk'><b>사용자</b><br>" + data[i].CH_MSG + "</div>");
					else
					log.append("<div id='talk'><b>관리자</b><br>" + data[i].CH_MSG + "</div>");
				}
			}
		});
        
      // message는 유저가 메시지를 보낼 때 알려주는 메시지이다.
      } else if(node.status === "message") {
        // key로 해당 div영역을 찾는다.
        let $div = $("[data-key='"+node.key+"']");
        // console영역을 찾는다.
        let log = $div.find("#messageTextArea");
        // 아래에 메시지를 추가한다.
        log.append("<div id='adTalk'><b>사용자</b><br>" + node.message + "</div>");
        log.scrollTop(log.prop('scrollHeight'));
        
        // DB저장
		    $.ajax({
			  url:'/createChat',
			  type:'post',
			  data:{'ch_key':node.key, 'ch_name':'사용자', 'ch_msg':node.message, 'ch_user':'사용자'},
			  dataType:'json'
		  })
        
      // bye는 유저가 접속을 끊었을 때 알려주는 메시지이다.
      } else if(node.status === "bye") {
        // 해당 키로 div를 찾아서 dom을 제거한다.
        $("[data-key='"+node.key+"']").remove();
      }
    };
    // 전송 버튼을 클릭하면 발생하는 이벤트
    $(document).on("click", ".sendBtn", function(){
      // div 태그를 찾는다.
      let $div = $(this).closest(".float-left");
      // 메시지 텍스트 박스를 찾아서 값을 취득한다.
      let message = $div.find(".message").val();
      // 유저 key를 취득한다.
      let key = $div.data("key");
      // console영역을 찾는다.
      let log = $div.find("#messageTextArea");
      // 아래에 메시지를 추가한다.
      log.append("<div id='talk'><b>관리자</b><br>" + message + "</div>");
      log.scrollTop(log.prop('scrollHeight'));
      // 텍스트 박스의 값을 초기화 한다.
      $div.find(".message").val("");
      // 웹소켓으로 메시지를 보낸다.
      webSocket.send(key+"#####"+message);
      
      // DB저장
	    $.ajax({
		  url:'/createChat',
		  type:'post',
		  data:{'ch_key':key, 'ch_name':'관리자', 'ch_msg':message, 'ch_user':'사용자'},
		  dataType:'json'
	  })
      
    });
    // 텍스트 박스에서 엔터키를 누르면
    $(document).on("keydown", ".message", function(){
      // keyCode 13은 엔터이다.
      if(event.keyCode === 13) {
        // 버튼을 클릭하는 트리거를 발생한다.
        $(this).closest(".float-left").find(".sendBtn").trigger("click");
        // form에 의해 자동 submit을 막는다.
        return false;
      }
      return true;	
    });
    
    function chatEnd(user) {
		userkey =  user.closest(".float-left").getAttribute("data-key");
		/*userkey = document.querySelector('.float-left');*/
    	  if(confirm('사용자의 기록을 삭제하시겠습니까?')){	
			 $.ajax({
				  url:'/deleteAdminChat',
				  type:'post',
				  data:{'ch_key':userkey},
				  success:$("[data-key='"+userkey+"']").remove()
			 })
		  }
	}