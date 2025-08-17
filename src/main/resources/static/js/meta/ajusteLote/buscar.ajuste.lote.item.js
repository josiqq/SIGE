import {getItemAjusteLote} from '/js/meta/ajusteLote/ajuste.lote.queries.js';
$(function(){
	$('.btn-ver-detalle').click(buscarItemAjusteLote);
	
	function buscarItemAjusteLote(){
		let id = $(this).data('id');
		
		getItemAjusteLote(id).then(retornoItems).catch(function(error){
			alert('Error recuperando detalles');
			console.log('Error recuperando detalles: '+error);
		})
	}
	
	function retornoItems(items){
		let listaItems =[];
		let registro ;
		items.forEach(function(item){
			registro = item.ajusteLote.id;		
			listaItems.push(`<tr>
													<td>${item.producto.codigo}</td>
													<td>${item.producto.nombre}</td>
													<td>${item.nroLote}</td>
													<td>${moment(item.vencimiento).format('DD/MM/YYYY')}</td>
													<td class="lado-derecho">${$.number(item.cantidad,'0',',','.')}</td>
											</tr>`)
		})
		$('#titulo-modal').text(`Ajuste de lote (${registro})`);
		$('.contenido-item-ajuste-lote').html(listaItems);
	}
	
})