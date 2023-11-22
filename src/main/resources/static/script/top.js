/**
 * 
 */
$().ready(function(){
$('#sidebarCollapse').on('click', function () {
	if($('#sidebar').hasClass('active'))
		$('#sidebar').removeClass('active');
	else
    $('#sidebar').addClass('active');
});

$(".hov-anim").mouseover(function() {
  $(this).attr("src", $(this).data("animated"))
}),
$(".hov-anim").mouseout(function() {
  $(this).attr("src", $(this).data("static"))
});

//검색 자동완성 부분
// 검색어의 길이가 바뀔 때마다 호출
$("#displayList").hide();
var search = $("input[name=searchText]");
search.on("propertychange change paste input", function(){
var html = "";
if(search.val().length == 0){
			$("#displayList").hide();
		} else {
			$.ajax({
				url:"/wordSearchShow",
				type:"post",
				data:{"searchText": search.val() },
				dataType:"json",
				success:function(data){
					if(data.length > 0){
						for(var i = 0; i < data.length; i++) {
							html += "<span class='autoText' onclick='textIn(this)'>"+data[i]+"</span><br>";
						}
							$("#displayList").html(html);
							$("#displayList").show();
					}
				}
			});
		} 
})
});// 레디 끝

function clsName(r){
	r.className="autoAction";
}

function textIn(text) {
	$("input[name=searchText]").val(text.innerHTML);
	$("form[name=searchForm]").submit();
}