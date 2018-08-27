(function ($) {

	$(window).scroll(function () {
		if ($(this).scrollTop() > 200) {
			$('.button-topo').fadeIn();
		} else {
			$('.button-topo').fadeOut();
		}
	});
  
}(jQuery));


$('#myModal1').on('shown', function() 
{
    $(document).off('focusin.modal');
});
