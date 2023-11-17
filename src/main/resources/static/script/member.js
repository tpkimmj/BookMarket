/**
 * 
 */
$().ready(function(){
	var idchk=1;
	var pcheck = true;	// 두개가 다르면 true, 같으면 false
	$('#submitTop').on('click', function(){
		var flen = $("form[name=topForm] .chkt").length;
		for(var i=0; i<flen; i++){
			if($('.chkt').eq(i).val()=="" ||
		       $('.chkt').eq(i).val()==null ||
		       $('.chkt').eq(i).val().trim()==""){
			  alert($('.chkt').eq(i).attr('title')+'를 입력하세요.');
			  $('.chkt').eq(i).focus();
			  return false;
			}
		}
		$("form[name=topForm]").submit();
	}); 
	
	$('.submit1').on('click', function(){
		var flen = $("form[name=topForm1] .chkt").length;
		for(var i=0; i<flen; i++){
			if($('.chkt').eq(i).val()=="" ||
		       $('.chkt').eq(i).val()==null ||
		       $('.chkt').eq(i).val().trim()==""){
			  alert($('.chkt').eq(i).attr('title')+'를 입력하세요.');
			  $('.chkt').eq(i).focus();
			  return false;
			}
		}
		$("form[name=topForm1]").submit();
	}); 
	//
	$('.submit2').on('click', function(){
		var flen = $("form[name=topForm2] .chk2").length;
		for(var i=0; i<flen; i++){
			if($('.chk2').eq(i).val()=="" ||
		       $('.chk2').eq(i).val()==null ||
		       $('.chk2').eq(i).val().trim()==""){
			  alert($('.chk2').eq(i).attr('title')+'를 입력하세요.');
			  $('.chk2').eq(i).focus();
			  return false;
			}
		}
		if(pcheck){
			alert("비밀번호가 일치하지 않습니다.")
		} else {
			$("form[name=topForm2]").submit();
		}
	}); 
	//
	$('#submit11').on("click", function(){
		if(validate()){
			if(idchk==1){
				alert('아이디 중복\n다시 해주세요')
				$('#idchk').focus();
				return false;
			}
			if(pcheck){
				alert('패스워드가 다릅니다.')
				return false;
			}
			$('form').submit();
		}
	});
   // 		
	$('#idchk').on('propertychange change input paste',function(){
		$.ajax({
			async:true,
			type:'post',
			url:'idCheck',
			data:{'mem_id':$('#idchk').val()},
			dataType:"json",
			success:function(data){
				if(data>0){ //이미 존재하는 id
					$('font[id=warning]').text('');
					$('font[id=warning]').attr('color','red');
					$('font[id=warning]').text('이미 존재하는 아이디 입니다.');
					$('#idchk').focus();
					idchk =1; //submit 불가  
				}else{ //사용가능한 id
					$('font[id=warning]').text('');
					$('font[id=warning]').attr('color','blue');
					$('font[id=warning]').text('사용가능한 아이디 입니다.');
					$('#idchk').focus();
				
				idchk=0; //전송가능
				}
			}
		});
	});	
	
	$('#check1, #check2').keyup(function(){
		$('font[id=check]').text('');
			if($('#check1').val()!=$('#check2').val()){
				$('font[id=check]').text(''); 
				$('font[id=check]').attr('color','red');
				$('font[id=check]').text('패스워드 불일치'); 
				pcheck=true;
			} else {
				$('font[id=check]').text(''); 
				$('font[id=check]').attr('color','#008000');
				$('font[id=check]').text('패스워드 일치');
		pcheck=false;
		}
	});
})//ready 끝

//chk에 대해서 점검
	function validate(){
		var flen = $("form[name=form1] .chk").length;
		for(var i=0; i<flen; i++){
			if($('.chk').eq(i).val()=="" ||
		       $('.chk').eq(i).val()==null ||
		       $('.chk').eq(i).val().trim()==""){
			  alert($('.chk').eq(i).attr('title')+'은/는 필수입력');
			  $('.chk').eq(i).focus();
			  return false;
			}
		}
		var str = document.form1.m_email.value;
		var atPos = str.indexOf('@');
		var atLastPos = str.lastIndexOf('@');
		var dotPos = str.indexOf('.');
		var spacePos = str.indexOf(' ');
		var commaPos = str.indexOf(',');
		var emailSize = str.length;
		if(!(atPos > 1 && atPos==atLastPos && dotPos > 3 && spacePos==-1 && commaPos==-1 && atPos+1 < dotPos && dotPos+1 < emailSize)){
			alert('Email 주소 형식이 잘못되었습니다.\n다시 입력해 주세요.')
			 document.form1.m_email.focus();
			 return false;
		}
		return true;
	}
	
	function enter() {
      // keyCode 13은 엔터이다.
      if(event.keyCode === 13) {
		var flen = $("form[name=topForm] .chkt").length;
		for(var i=0; i<flen; i++){
			if($('.chkt').eq(i).val()=="" ||
		       $('.chkt').eq(i).val()==null ||
		       $('.chkt').eq(i).val().trim()==""){
			  alert($('.chkt').eq(i).attr('title')+'를 입력하세요.');
			  $('.chkt').eq(i).focus();
			  return false;
			}
		}
		$("form[name=topForm]").submit();
      }
	}