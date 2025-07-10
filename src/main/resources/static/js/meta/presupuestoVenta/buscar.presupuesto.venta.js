import {impresionPresupuestoSiete} from '/js/meta/presupuestoVenta/impresion.presupuesto.siete.js';
$(function(){
	$('.date').mask('00/00/0000');
	$('input').click(function(){
		$(this).select();
	})
	$('.btn-imprimir').click(imprimirPresupuesto);
	function imprimirPresupuesto(){
		let id = $(this).data('id');
		impresionPresupuestoSiete(id);
	}
	
	$('.js-tabla-buscar-venta').each(function(){
		let total = $(this).find('.js-venta-total').text();
		$(this).find('.js-venta-total').text($.number(total,'2',',','.'));
	})
})