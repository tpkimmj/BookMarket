/**
 * 
 */
$().ready(function(){
	var plen = $('.price').length;
 	for(var i=0; i<plen; i++){
	 var p=$('.price').eq(i).text();
	 var to_p = 
	     p.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,",");
	 $('.price').eq(i).text(to_p);
   }
	
	$('#bookBtn1').on('click', function(){
	$('form[name=productForm]').submit();
	});
	
	$('#btn2').on('click', function(){
	$('form[name=productForm]').submit();
	});

	
	  $('.productUp').on('click', function(){
	   $('form[name=productForm]').attr('action', 'productUpForm');
	   $('form[name=productForm]').submit();
   });
   
   $('.productDel').on('click', function(){
	   pno = $('input[name=p_no]').val();
	   r = confirm('삭제하시겠습니까?');
						if(r){
							  $('form[name=productForm]').attr('action', 'productDel');
	 						  $('form[name=productForm]').submit();
						} else {
							return false;
						}
	    /*$.ajax({
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
		});*/
   });


   
}); // 레디 끝

function count(type)  {
  // 결과를 표시할 element
  const resultElement = document.getElementById('result');
  var bkprice = null;
  const element = document.getElementById('bkprice');
  const element2 = document.getElementById('bkprice2').innerHTML;
  const bkstock = document.getElementById('bkstock').innerHTML;
  // 현재 화면에 표시된 값
  let number = resultElement.value;
  // 더하기/빼기
  if(type === 'plus') {
	  if(parseInt(number) < parseInt(bkstock)){
		  number = parseInt(number) + 1;
		  bkprice = parseInt(element2) * parseInt(number);
	  } else if (parseInt(number) >= parseInt(bkstock)){
		  alert("재고 수량이 부족합니다.");
	  }
  }else if(type === 'minus')  {
	  if(number == 1){
	    alert("1개 이상 구매가능합니다.");
	  } else if(number > 1) {
		number = parseInt(number) - 1;
	    bkprice = parseInt(element2) * parseInt(number);
	  }
  }
  // 결과 출력
  
 const priceTot = bkprice.toString()
  .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");

  element.textContent = priceTot;
  
  resultElement.value = number;
  
}

  function cartUpdate(f, obj){
	    var url1;
        var quantity = $(obj).closest('tr').find('input[name=quantity]').val();
        var pno = $(obj).closest('tr').find('input[name=p_no]').val();
        var stock =$(obj).closest('tr').find('input[name=stock]').val();
	   if(f=='D'){
		   //삭제	
		   url1='cartProc?flag=delete';
		   $(obj).closest('tr').remove();
	   }else if(f=='U'){
		   if(parseInt(quantity)>parseInt(stock)){
			   alert('재고가 부족합니다.')
			   return false;
		   }
		   //수정
		   url1='cartProc?flag=update';
	   }
	   $.ajax({
		   async:true,
		   type:'post',
		   data:{"quantity":quantity,
		         "p_no":pno
		         },
		   url: url1,
		   dataType:"json",
		   success : function() {
//			   alert('장바구니 수정');
		     }
	   });
  }

