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
      $("select[name='state']").change(function(){
	   	var tr =$(this).parent().parent();
	   	var td = tr.children();
	   	tr.find(td).find("input[name=ck]").prop("checked", true); 
    });
	
  	$(".selectBtn").click(function(){ 
			var tdArr = new Array();
			// 체크된 체크박스 값을 가져온다
			var checkbox = $("input[name=ck]:checked");
			checkbox.each(function(i){
			var tr = checkbox.parent().parent().eq(i);
			var td = tr.children();
			var pno   = tr.find(td).find("input[name=p_no]").val();
			var ono    =tr.find(td).find("input[name=o_no]").val();
			var memid  = tr.find(td).find('input[name=mem_id]').val();
			var state   =tr.find(td).find("select[name=state]").val(); //
				// 가져온 값을 배열에 담는다.
				tdArr.push("o_no:"+ono);
				tdArr.push("p_no:"+pno);
				tdArr.push("mem_id:"+memid);
				tdArr.push("state:"+state);
			});
			  $.ajax({ async:false,
			    type:'post',
			    data:{tdArr
				  },
		       url:"/orderMgtProc",
			   dataType:"json",
			   success:setInterval() /*콜백함수*/
		      }) 
		      
		     function setInterval(){//콜백함수
				//1 . check 박스 지우기
			 	var tr =$("select[name='state']").parent().parent();
	   			var td = tr.children();
	   			tr.find(td).find("input[name=ck]").prop("checked", false);	
			  } 
			});
		 

    $('#bookBtn1').on('click', function(){
	$('form[name=productForm]').submit();
	})
	
	$('#btn2').on('click', function(){
	$('form[name=productForm]').submit();
	})
	
	$('.submitProc').on('click', function(){
		var flen = $("form[name=topForm1] .chkt").length;
		var price = $("input[name=price]").val();
		var stock = $("input[name=stock]").val();
		for(var i=0; i<flen; i++){
			if($('.chkt').eq(i).val()=="" ||
		       $('.chkt').eq(i).val()==null ||
		       $('.chkt').eq(i).val().trim()==""){
			  alert($('.chkt').eq(i).attr('title')+'를 입력하세요.');
			  $('.chkt').eq(i).focus();
			  return false;
			}
		}
		if(isNaN(price) || isNaN(stock))
		alert("재고와 가격은 숫자만 입력가능합니다.");
		else
		$("form[name=topForm1]").submit();
	}); 
	$('.orderCancle').on('click', function(){
	   ono = $('input[name=o_no]').val();
	   pname = $('input[name=p_name]').val();
	   pno = $('input[name=p_no]').val();
	    r = confirm('삭제하시겠습니까?');
						if(r){
							  $('form[name=orderForm]').attr('action', 'orderCancle');
	 						  $('form[name=orderForm]').submit();
						} else {
							return false;
						}
		});
	$('.cancleFail').on('click', function(){
	  alert("결재중 혹은 배송준비 상품만 주문 취소가 가능합니다.\n 고객센터를 통해 문의해주세요.")
   });
   
});



  function cartUpdate(f, obj){
	    var url1;
        var quantity = $(obj).closest('tr').find('input[name=quantity]').val();
        var cno = $(obj).closest('tr').find('input[name=c_no]').val();
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
		   async:false,
		   type:'post',
		   data:{"quantity":quantity,
		         "c_no":cno
		         },
		   url: url1,
		   dataType:"json",
		   success : function() {
			   alert('장바구니 수정');
		     }
	   });
	   window.location.reload();
  }

   function orderDetail(obj){
	var ono = $(obj).closest('tr').find('input[name=o_no]').val();
	var pno = $(obj).closest('tr').find('input[name=p_no]').val();
	var memId = $(obj).closest('tr').find('input[name=mem_id]').val();
	 $('form[name=orform] input[name=o_no]').val(ono);
	 $('form[name=orform] input[name=p_no]').val(pno);
	 $('form[name=orform] input[name=mem_id]').val(memId);
	 $('form[name=orform]').submit();
   }
   
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
