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

});