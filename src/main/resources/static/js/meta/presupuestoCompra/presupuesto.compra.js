import { keyProductoManipular} from '/js/meta/presupuestoVenta/presupuesto.venta.service.js';
import {formatearNumeroInput} from '/js/meta/utilidades.js';
import {tecladoPresupuestoCompra} from '/js/meta/presupuestoCompra/teclado.presupuesto.compra.js';
$(function(){
	tecladoPresupuestoCompra();
	$('#proveedor').focus();
	$('.date').mask('00/00/0000');
	$('input').click(function(){
		$(this).select();
	})
	formatearNumeroInput($('.formato-numero'));
	$('#producto-nombre').keydown(keyProductoManipular);
	
	$('#cantidad-det-nc').keydown(function(e){
		if(e.keyCode === 13){
			
			if($('#cantidad-det-nc').val().trim()=== '' || Number($('#cantidad-det-nc').val())=== Number(0) ){
				$('#cantidad-det-nc').val(1);
			}
			$('#precio-det-nc').focus();
		} 
	})
})