/**
 * 
 */
$().ready(function(){
	$('update()').on('click',function(){
      //alert(1)
	})
}) // 레디
   
function update(flag){
   // 패스워드 확인하기
	openWin = window.open("/pwCheck", "pwCheck", "width = 355, height = 150, toolbar = no, location = no, menubar = no, resizable = no, scrollbars = no")
	if(flag=='u'){
		$("#upForm").attr('action', "/memUpForm");
	} else {
		$("#upForm").attr('action', "/memdelete");
		var yn = confirm("정말 삭제하시겠습니까?\n 구매이력과 포인트가 삭제됩니다.")
		if(yn==true){
		}else{
			return false;
		}
	}
}
   
function setParentText(){
	var pw1 = $('#pwck').val() //팝업에서 받은 비밀번호
	if(pw1==null || pw1.length==0){
		alert('패스워드를 입력하시오')
		$('#pwck').focus();
		return false;
	}
	var pw2 = $("#pw", opener.document).val(); //오프너값
	if(pw1==pw2){
		$("#upForm", opener.document).submit();
		this.window.close();
	}else{
		alert('비밀번호 오류')		
		this.window.close();
	}
}

  // 텍스트 박스에서 엔터를 누르면
    function enter() {
      // keyCode 13은 엔터이다.
      if(event.keyCode === 13) {
        // 서버로 메시지 전송
        setParentText(this)
        // form에 의해 자동 submit을 막는다.
        return false;
      }
      return true;
    }