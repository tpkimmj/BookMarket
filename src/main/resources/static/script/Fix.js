
$().ready(function(){
	$('#noticeUp').on('click',function(){//수정 삭제
		 $("form[name=topForm1]").attr('action', '/noticeProc');
		 $("input[name=flag]").val('update');
         $("form[name=topForm1]").submit();
	   });
	$('.noticeList').on('click',function(){
		 $("form[name=topForm1]").attr('action', '/ClientCenter');
		 $("input[name=flag]").val('list');
         $("form[name=topForm1]").submit();
	   });
	$('.noticeDel').on('click',function(){
		 $("form[name=topForm1]").attr('action',"/noticeProc");
		 $("input[name=flag]").val('delete');
         $("form[name=topForm1]").submit();
	});
	}); 
