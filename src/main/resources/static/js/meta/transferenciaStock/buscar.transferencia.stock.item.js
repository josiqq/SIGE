import {getItemTransferenciaStock} from '/js/meta/transferenciaStock/transferencia.stock.queries.js';
import {mensajeError} from '/js/meta/mensaje/mensaje.service.js';
$(function(){
	$('.btn-ver-detalle').click(buscarItem);
	
	function buscarItem(){
		let id = $(this).data('id');
		getItemTransferenciaStock(id).then(retornoItems)
			.catch(function(xhr){
				mensajeError(xhr.responseText);
			})
	}
	
	function retornoItems(items){
		let listaItems = [];
		let registro ;
		items.forEach(function(item){
			registro = item.transferenciaStock.id;
			listaItems.push(`<tr>
	        				<td>${item.producto.codigo}</td>
	        				<td>${item.producto.nombre}</td>
	        				<td class="lado-derecho">${$.number(item.cantidad,'0',',','.')}</td>
	        			</tr>`);
		})
		$('#titulo-modal').text('Transferencia stock '+' ('+registro+')');
		$('.contenido-item-transferencia-stock').html(listaItems);
	}
	
})