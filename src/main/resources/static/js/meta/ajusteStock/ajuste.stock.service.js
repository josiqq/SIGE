import {fCantidadStock} from '/js/meta/ajusteStock/ajuste.stock.queries.js';
import {mensajeError} from '/js/meta/mensaje/mensaje.service.js';
export function cambiosEnIdProducto(){
	$('#idProducto').change(function(){
		let producto = $(this).val();
		let deposito = $('#deposito').val();
		console.log('buscar cantidad con esta id : '+producto+'\deposito: '+deposito);
		
		fCantidadStock(producto,deposito).then(function(cantidad){
			$('#cantidad-actual').val($.number(cantidad,'2',',','.'));
		}).catch(function(xhr){
			mensajeError(xhr.responseText);
		})
		
	})
}