
$().ready(function(){
	$('#noticeUp').on('click',function(){//수정 삭제
		 $("form[name=topForm1]").attr('action', '/noticeProc?flag=update');
         $("form[name=topForm1]").submit();
	   });
	$('#noticeUpForm').on('click',function(){
		 $("form[name=topForm1]").attr('action', '/noticeUpForm');
         $("form[name=topForm1]").submit();
	   });
	$('.noticeDel').on('click',function(){
		 $("form[name=topForm1]").attr('action',"/noticeProc?flag=delete");
         $("form[name=topForm1]").submit();
	});
	}); 
