$(function(){
	$('.date').mask('00/00/0000');
	$('input').click(function(){
		$(this).select();
	})
	
	$('.js-tabla-buscar-venta').each(function(){
		let total = $(this).find('.js-venta-total').text();
		$(this).find('.js-venta-total').text($.number(total,'2',',','.'));
	})
})