$(function(){
	$('.credito-anterior').text($.number($('.credito-anterior').text(),'2',',','.'));
	$('.total-credito').text($.number($('.total-credito').text(),'2',',','.'));
	$('.debito-anterior').text($.number($('.total-debito').text(),'2',',','.'));
	$('.total-debito').text($.number($('.total-debito').text(),'2',',','.'));
	$('.total-saldo').text($.number($('.total-saldo').text(),'2',',','.'));
	
	
	$('.js-lista-movimiento-cuenta').each(function(){
		row = $(this);
		credito = row.find('.js-credito').text();
		debito = row.find('.js-debito').text();
		row.find('.js-credito').text($.number(credito,'2',',','.'));
		row.find('.js-debito').text($.number(debito,'2',',','.'));
	})
})