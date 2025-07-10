import {teclado} from '/js/meta/transferenciaStock/transferencia.teclado.js';
$(function(){
	teclado();
	console.log('transferencia de stock ...');
	$('.date').mask('00/00/0000');
	$('input').click(function(){
		$(this).select();
	})
	$('#fecha').focus();
	$('#deposito-origen').change(cambioDepositoOrigenManipular);
	$('#deposito-destino').change(cambioDepositoDestinoManipular);
	
	function cambioDepositoOrigenManipular(){
		let depositoDestino = $('#deposito-destino').val();
		let depositoOrigen = $(this).val();
		if(depositoOrigen=== depositoDestino){
			Swal.fire({
			  title: "Aviso",
			  text: "El deposito origen no puede ser igual al deposito destino!",
			  icon: "warning"
			});
		}
	}
	function cambioDepositoDestinoManipular(){
		let depositoOrigen = $('#deposito-origen').val();
		let depositoDestino = $(this).val();
		if(depositoOrigen=== depositoDestino){
			Swal.fire({
			  title: "Aviso",
			  text: "El deposito destino no puede ser igual al deposito origen!",
			  icon: "warning"
			});
		}
	}
})