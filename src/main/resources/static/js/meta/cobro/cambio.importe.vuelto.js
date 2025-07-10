import CotizacionQueries from '/js/meta/cotizacion/CotizacionQueries.js';
const cotizacionQueries = new CotizacionQueries();

$(function() {

	$('#importe').change(verificaMoneda);
	function verificaMoneda() {
		//console.log('condicion de cobro: '+ $('#condicion option:selected').data('condi'));
		let moneda_origen =0;
		let importe_ms = 0;
		let moneda_destino = $('#moneda-cobro').val();
		let fecha = $('#fecha').val();
		let importe = $('#importe').val().replace(/\./g, "").replace(',', '.');
		let saldo = $('#saldo').val()
		if($('#condicion option:selected').data('condi') === 'TRANSFERENCIA' || $('#condicion option:selected').data('condi') === 'TARJETA'){
			moneda_origen = moneda_destino;
		}else{
			 moneda_origen = $('#moneda').val();	
		}
		
		if (Number($('#saldo').val()) === Number(0)) {
			Swal.fire({
				icon: "error",
				title: "Oops...",
				text: "Ya tiene saldo cero!!!",
			});
		} else {


			if (moneda_origen != moneda_destino) {
				//console.log('Moneda origen: ',moneda_origen,' Moneda destino: ',moneda_destino, 'Fecha: ',fecha);
				cotizacionQueries.cotizar(moneda_origen, moneda_destino, fecha)
				.then(function(cotizacion) {

					if (cotizacion.multiplicar === true) {
						importe_ms = importe * cotizacion.valor
						//console.log('tiene que multiplicar ?');
					} else {
						importe_ms = importe / cotizacion.valor
						//console.log('tiene que dividir?',importe_ms);
					}
					
					$('#importe-ms').val(importe_ms);
				}).catch(function(error) {
					console.log('error con cotizacion: ', error);
					alert('Error recuperando cotizacion!!');
				});


			} else {
				$('#importe-ms').val(importe.replace(/\./g, '').replace(',', '.'));
				$('#importe-cobrado').val(importe);
			}
		}

	}


});

export function verificarSaldo(importems) {
	let saldo = $('#saldo').val();
	console.log('Este es el saldo a comparar: ',saldo,'\nEste el importems: ',importems);
	return Number(importems) > Number(saldo);

}

export function compareImporteToCobrado(importe,importe_cobrado){
	let importe_format = importe.replace(/\./g,'').replace(',','.');
	let importe_cobrado_format = importe_cobrado.replace(/\./g,'').replace(',','.');
	
	return Number(importe_format)>Number(importe_cobrado_format);
}
