$(function(){
	console.log('ajuste...');
	$('.tabla-ajuste-venta').each(function(){
		let precio = $(this).find('.precio-producto').text();
		$(this).find('.precio-producto').text($.number(precio,'2',',','.'));
	})
})