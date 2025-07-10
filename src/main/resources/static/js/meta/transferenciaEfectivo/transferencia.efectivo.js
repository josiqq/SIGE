import {formatearNumeroInput} from '/js/meta/utilidades.js';
$(function() {
	$('#fecha').focus();

	$('input').click(function() {
		$(this).select();
	})
	$('#importe').val($.number($('#importe').val(),'2',',','.'));
	formatearNumeroInput($('.formato-numero'));
	
	$('#cuentaOrigen').change(function(){
		let cobranza= $('option:selected',this).data('cobranza');
		let moneda = $('option:selected',this).data('moneda');
		if(cobranza === false && moneda != 0){
			$('#moneda').val(moneda);
		}else if(moneda === 0){
			alert('Cuenta origen no tiene moneda asignada !');
		}
		
		if(cobranza === true && moneda != 0){
			$('#moneda').val($('#moneda-destino').val());
		}
	})
	
	$('#cuentaDestino').change(function(){
		let moneda = $('option:selected',this).data('moneda');
		let cuentaOriCobrnza = $('#cuentaOrigen option:selected').data('cobranza');
		if(moneda === 0){
			alert('Cuenta destino no tiene moneda asignada!')
		}else{
			$('#moneda-destino').val(moneda);
			console.log('Cuenta Origen : '+cuentaOriCobrnza);
			if(cuentaOriCobrnza === true){
				$('#moneda').val($('#moneda-destino').val());	
			}
			
		}
	})
})
