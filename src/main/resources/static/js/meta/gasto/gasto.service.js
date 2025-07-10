
$('.js-total-importe-buscar').text($.number($('.js-total-importe-buscar').text(),'2',',','.'));

$('.lista-gasto').each(function(){
	let importe = $(this).find('.js-lista-gasto-importe').text();
	$(this).find('.js-lista-gasto-importe').text($.number(importe,'2',',','.'));
})