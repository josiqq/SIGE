import { autoCompletar } from '/js/meta/producto/auto.complete.producto.lote.js';
import { keyProductoManipularLote } from '/js/meta/producto/producto.service.js';
$(function(){
	const $productoNombre = $('#producto-nombre');
	$productoNombre.on('input', autoCompletar);
	$('#producto-nombre').keydown(keyProductoManipularLote);
	
	$('.js-lista-movimiento-lotes').each(function(){
		let entrada = $(this).find('.js-entrada').text();
		let salida = $(this).find('.js-salida').text();
		$(this).find('.js-entrada').text($.number(entrada,',','.'));
		$(this).find('.js-salida').text($.number(salida,',','.'));
		
	});	
	
	let totalEntrada = $('.js-total-entrada').text();
	let totalSalida = $('.js-total-salida').text();
	let saldo = $('.js-saldo').text();
	$('.js-total-entrada').text($.number(totalEntrada,'0',',','.'));
	$('.js-total-salida').text($.number(totalSalida,'0',',','.'));
	$('.js-saldo').text($.number(saldo,'0',',','.'));
})