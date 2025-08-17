import { autoCompletar } from '/js/meta/producto/auto.complete.producto.lote.js';
import { keyProductoManipularLote } from '/js/meta/producto/producto.service.js';
$(function(){
	const $productoNombre = $('#producto-nombre');
	$productoNombre.on('input', autoCompletar);
	$('#producto-nombre').keydown(keyProductoManipularLote);
	$('.js-listado-lotes').each(function(){
		let cantidad = $(this).find('.lote-cantidad').text();
		$(this).find('.lote-cantidad').text($.number(cantidad,'0',',','.'));
	});
	let total = $(this).find('.js-lote-total').text();
	$(this).find('.js-lote-total').text($.number(total,'0',',','.'));
})