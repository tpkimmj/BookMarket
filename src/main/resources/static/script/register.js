
$().ready(function(){
$('#submit12').on('click', function(){
		var flen = $("form[name=topForm1] .chk1").length;
		for(var i=0; i<flen; i++){
			if($('.chk1').eq(i).val()=="" ||
		       $('.chk1').eq(i).val()==null ||
		       $('.chk1').eq(i).val().trim()==""){
			  alert($('.chk1').eq(i).attr('title')+'를 입력하세요.');
			  $('.chk1').eq(i).focus();
			  return false;
			}
		}
         $("form[name=topForm1]").submit();
    
	});
	}); 
