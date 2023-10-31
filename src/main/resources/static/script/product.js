/**
 * 
 */
$().ready(function(){
	
	  $('.productUp').on('click', function(){
	   $('form[name=productForm]').attr('action', 'productUpForm');
	   $('form[name=productForm]').submit();
   });
   
   $('.productDel').on('click', function(){
	   pno = $('input[name=p_no]').val();
	    $.ajax({
			async:true,
			type:'post',
			data:{"p_no":pno},
			url: 'orderCntOfProduct',
			datatype:"json",
			success: function(cnt) {
				if(cnt>0){
					alert('주문내역이 존재합니다.\n삭제불가')
					return false;
					} else {
						r = confirm('삭제하시겠습니까?');
						if(r){
							  $('form[name=productForm]').attr('action', 'productDel');
	 						  $('form[name=productForm]').submit();
						} else {
							return false;
						}
					}
			}
		});
   });
   
}); // 레디 끝