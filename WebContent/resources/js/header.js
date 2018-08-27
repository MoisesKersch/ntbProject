function resize() 
{
    if ($(window).width() < 514)
     $('#loginName').addClass('three-dots-for-avatar');
    if (($(window).width() < 390))
    	$('#avatar01').css('display', 'none');
    else
	{
    	$('#loginName').removeClass('three-dots-for-avatar');
    	$('#avatar01').css('display', 'block');
	}
}

$(document).ready( function() 
{
	$( ".img-circle, #sair" ).mouseover(function() 
	{
		$("#sair").removeClass( "hidden" ).click(function() {
			window.location.href="logout";
		});
	}).mouseout(function() {
		$("#sair").addClass( "hidden" ).unbind('click');
	});
	
    $(window).resize(resize);
    resize();
});