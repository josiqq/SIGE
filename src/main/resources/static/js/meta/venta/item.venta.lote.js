import { getLoteBydepositoProucto } from '/js/meta/lote/lote.queries.js';
import { mensajeError, mensajeExito,mensajeAviso } from '/js/meta/mensaje/mensaje.service.js';
//import {getCantidadByProductoDeposito} from '/js/meta/stockDeposito/stock.deposito.queries.js';
export function manipularLote() {
	$('#idProducto').change(cambioProductoManipular);
	$('#cantidad-producto').change(cambioCantidadManipular);
}

//=======================FUNCIONES INTERNAS=============================

function cambioProductoManipular() {
	let id = $(this).val();
	let conLote = $(this).data('lote');
	let deposito = $('#deposito').val()
	let minimo = $('#idProducto').data('minimo');
	let toti = $('#idProducto').data('toti');
	console.log('minimo stock: '+minimo+'\ntotal stock: '+toti);
	if(minimo>toti){
		mensajeAviso(`Este producto esta por debajo del stock minimo: ${minimo}`);
	}
	//console.log('buscar lote por este producto: '+id);
	//console.log('este producto tiene lote?: '+conLote);
	if (conLote === true) {

		getLoteBydepositoProucto(id, deposito).then(retornoLotes)
			.catch(function(xhr) {
				mensajeError(xhr.responseText);
			})
	}

}

function retornoLotes(lotes) {

	if (lotes.length === 0) {
		mensajeError('El producto es con lote, pero no tiene lotes cargados, almenos con el deposito de esta venta!');
	} else {
		$('#listaLoteModal').modal('show');
		let listaLotes = [];
		lotes.forEach(function(lote) {
			listaLotes.push(`<tr class="tr-lista-lote" data-id="${lote.producto.id}">
	        				<th>${lote.producto.codigo}</th>
	        				<th>${lote.producto.nombre}</th>
	        				<th class="lista-lote-numero">${lote.nroLote}</th>
	        				<th class="lista-lote-vencimiento">${moment(lote.vencimiento).format('DD/MM/YYYY')}</th>
	        				<th class="lado-derecho lista-lote-cantidad" >${lote.cantidad}</th>
	        				<th class="lado-derecho"><input type="text" class="lado-derecho form-control mediano cantidad-lote" ></th>
	        				<th><button type="button" class="btn btn-success btn-agregar-lote"><i class="fa fa-plus"></i></button></th>
	        			</tr>`);
		});

		$('.contenido-lista-lote').html(listaLotes);

		setTimeout(function() {
			$('.tr-lista-lote').eq(0).find('.cantidad-lote').focus();
		}, 500)
		$('.btn-agregar-lote').click(adicionarLoteItem);
	}
}
$('#listaLoteModal').on('show.bs.modal', modalAbiertoManipular);
$('#listaLoteModal').on('hide.bs.modal', modalCerradoManipular);
function modalAbiertoManipular() {
	console.log('el modal está abierto');

}

function modalCerradoManipular() {
	$('.mensaje-emergente').html('');
	setTimeout(function() {
		$('#precio-producto').focus();
	}, 500)

}

function adicionarLoteItem() {
	let producto = $(this).closest('.tr-lista-lote').data('id');
	let nroLote = $(this).closest('.tr-lista-lote').find('.lista-lote-numero').text();
	let vencimiento = $(this).closest('.tr-lista-lote').find('.lista-lote-vencimiento').text();
	let cantidad = $(this).closest('.tr-lista-lote').find('.cantidad-lote').val();
	let cantidadActual = $(this).closest('.tr-lista-lote').find('.lista-lote-cantidad').text();
	let uuid = $('#uuid').val();

	$.ajax({
		url: '/ventas/item-lote-adicionar',
		method: 'POST',
		data: {
			producto: producto, nroLote: nroLote, vencimiento: vencimiento, cantidad: cantidad, cantidadActual: cantidadActual, uuid: uuid
		},
		success: function(data) {
			$('#cantidad-producto').val($.number(data, '0', ',', '.'));
			mensajeExito('Lote agregado con exito!');
		},
		error: function(xhr) {
			mensajeError(xhr.responseText);
		}
	})
}

//--
function cambioCantidadManipular() {
	
	let producto = $('#idProducto').val();
	let uuid = $('#uuid').val();
	let lote = $('#idProducto').data('lote'); 
	
	
	verificarSiEsLote(producto,uuid,lote);

}

function verificarSiEsLote(producto,uuid,lote){
	//console.log('Verifica si es lote : '+producto+'\nuuid: '+uuid+'\nlote: '+lote);
	if (lote === true) {
		$.ajax({
			url: '/ventas/get-items-lotes-by-producto',
			method: 'GET',
			data: { producto: producto, uuid: uuid },
			success: getLotesRetornoSuccess,
			error: function() {
				mensajeError('Error recuperando lostes cargados!');
			}
		})
	}
}





function getLotesRetornoSuccess(html) {
	$('.contenido-lote-existente').html(html);
	//console.log(html)
	let cantidad = Number($('.tr-lote-existente').data('canti'));
	if (cantidad > 0) {
		$('.tr-lote-existente').each(function() {
			let cantidadActual = $(this).find('.lista-lote-cantidad-actual').text();
			let cantidad = $(this).find('.cantidad-lote').val();
			$(this).find('.lista-lote-cantidad-actual').text($.number(cantidadActual, '0', ',', '.'));
			$(this).find('.cantidad-lote').val($.number(cantidad, '0', ',', '.'));
		});

		$('#loteExistenteModal').modal('show');
		$('.btn-agregar-lote').click(modificarCantidadLoteExistente);
		$('.btn-quitar-lote').click(eliminarCantidadLoteExite);

	} else {
		let id = $('#idProducto').val();
		let deposito = $('#deposito').val();
		getLoteBydepositoProucto(id, deposito).then(retornoLotes)
			.catch(function(xhr) {
				mensajeError(xhr.responseText);
			})
	}

}


function modificarCantidadLoteExistente() {
	let producto = $(this).data('id');
	let nroLote = $(this).closest('.tr-lote-existente').find('.lista-lote-numero-existe').text();
	let cantidadActual = $(this).closest('.tr-lote-existente').find('.lista-lote-cantidad-actual').text();
	let cantidad = $(this).closest('.tr-lote-existente').find('.cantidad-lote').val();
	let uuid = $('#uuid').val();

	$.ajax({
		url: '/ventas/modificar-cantidad-lote',
		method: 'PUT',
		data: { producto: producto, nroLote: nroLote, cantidad: cantidad, cantidadActual: cantidadActual, uuid: uuid },
		success: function(data) {
			//$('#cantidad-producto').val($.number(data, '0', ',', '.'));
			mensajeExito('Lote agregado con exito!');
		},
		error: function(xhr) {
			mensajeError(xhr.responseText);
		}
	})
}

function eliminarCantidadLoteExite() {
	let indice = $(this).data('indice');
	let producto = $(this).data('id');
	let uuid = $('#uuid').val();

	$.ajax({
		url: '/ventas/eliminar-item-lote',
		method: 'DELETE',
		data: { indice: indice, producto: producto, uuid: uuid },
		success: retornoEliminarItemLoteSuccess,
		error: function() {
			mensajeError('Error eliminando item de lote!');
		}
	})
}

function retornoEliminarItemLoteSuccess(html) {
	$('.contenido-lote-existente').html(html);
	$('.tr-lote-existente').each(function() {
		let cantidadActual = $(this).find('.lista-lote-cantidad-actual').text();
		let cantidad = $(this).find('.cantidad-lote').val();
		$(this).find('.lista-lote-cantidad-actual').text($.number(cantidadActual, '0', ',', '.'));
		$(this).find('.cantidad-lote').val($.number(cantidad, '0', ',', '.'));
	});

	$('#loteExistenteModal').modal('show');
	$('.btn-agregar-lote').click(modificarCantidadLoteExistente);
	$('.btn-quitar-lote').click(eliminarCantidadLoteExite);
}

//=====================SIN ENCONTRAR MEJORES OPCIONES REPETIR EL CODIGO PARA EL DETALLE============

export function verificarSiEsLoteDetalle(producto,uuid,lote){
	
	let deposito = $('#deposito').val();
	if (lote === true) {
		$.ajax({
			url: '/ventas/get-items-lotes-by-producto-detalle',
			method: 'GET',
			data: { producto: producto,deposito:deposito, uuid: uuid },
			success: function(itemsVentasLotes){
				getItemsLotesDetalleSuccess(producto,uuid,itemsVentasLotes)	
			},
			error: function() {
				mensajeError('Error recuperando lostes cargados!');
			}
		})
	}
}

function getItemsLotesDetalleSuccess(producto,uuid,itemsVentasLotes){
	//console.log('Lotes retornos: '+itemsVentasLotes.length
	//+'\nproducto: '+producto+'\nuuid:'+uuid);
	
	if(itemsVentasLotes.length>0){
		abrirModalExistente(itemsVentasLotes);
	}else{
		abrirModalParaNuevo(producto,uuid);
	}
}

function abrirModalExistente(itemsVentasLotes){
	
	let listaLotes = [];
	let totalCantidad =0;
	let indice = 0;
	itemsVentasLotes.forEach(function(item){
		totalCantidad = totalCantidad+item.cantidad;
		listaLotes.push(`<tr class="tr-lote-existente"    data-canti="${totalCantidad}">
		<th >${item.producto.codigo}</th>
		<th >${item.producto.nombre}</th>
		<th class="lista-lote-numero-existe" >${item.nroLote}</th>
		<th class="lista-lote-vencimiento-existe" >${item.vencimiento}</th>
		<th class="lado-derecho lista-lote-cantidad-actual"  >${item.cantidadActual}"</th>
		<th class="lado-derecho">
			<input type="text" class="lado-derecho form-control mediano cantidad-lote"  
							data-id="${item.producto.id}" value="${item.cantidad}">
		</th>
		<th><button type="button" class="btn btn-success btn-agregar-lote"  
				  data-id="${item.producto.id}" data-indice="${indice}"><i class="fa fa-plus"></i></button></th>
		<th><button type="button" class="btn btn-danger btn-quitar-lote"  
				  data-id="${item.producto.id}" data-indice="${indice}"><i class="fa fa-trash"></i></button></th>
		
</tr>`);
	indice = indice +1;
	});
	getLotesRetornoSuccessDetalle(listaLotes);
}

function abrirModalParaNuevo(producto,uuid){
	let deposito = $('#deposito').val();

	getLoteBydepositoProucto(producto, deposito).then(retornoLotesDetalle)
			.catch(function(xhr) {
				mensajeError(xhr.responseText);
			})
}


function retornoLotesDetalle(lotes) {

	if (lotes.length === 0) {
		mensajeError('El producto es con lote, pero no tiene lotes cargados, almenos con el deposito de esta venta!');
	} else {
		$('#listaLoteModal').modal('show');
		let listaLotes = [];
		lotes.forEach(function(lote) {
			listaLotes.push(`<tr class="tr-lista-lote" data-id="${lote.producto.id}">
	        				<th>${lote.producto.codigo}</th>
	        				<th>${lote.producto.nombre}</th>
	        				<th class="lista-lote-numero">${lote.nroLote}</th>
	        				<th class="lista-lote-vencimiento">${moment(lote.vencimiento).format('DD/MM/YYYY')}</th>
	        				<th class="lado-derecho lista-lote-cantidad" >${lote.cantidad}</th>
	        				<th class="lado-derecho"><input type="text" class="lado-derecho form-control mediano cantidad-lote" ></th>
	        				<th><button type="button" class="btn btn-success btn-agregar-lote"><i class="fa fa-plus"></i></button></th>
	        			</tr>`);
		});

		$('.contenido-lista-lote').html(listaLotes);

		setTimeout(function() {
			$('.tr-lista-lote').eq(0).find('.cantidad-lote').focus();
		}, 500)
		$('.btn-agregar-lote').click(adicionarLoteItemDetalle);
	}
}

function adicionarLoteItemDetalle() {
	let producto = $(this).closest('.tr-lista-lote').data('id');
	let nroLote = $(this).closest('.tr-lista-lote').find('.lista-lote-numero').text();
	let vencimiento = $(this).closest('.tr-lista-lote').find('.lista-lote-vencimiento').text();
	let cantidad = $(this).closest('.tr-lista-lote').find('.cantidad-lote').val();
	let cantidadActual = $(this).closest('.tr-lista-lote').find('.lista-lote-cantidad').text();
	let uuid = $('#uuid').val();

	$.ajax({
		url: '/ventas/item-lote-adicionar',
		method: 'POST',
		data: {
			producto: producto, nroLote: nroLote, vencimiento: vencimiento, cantidad: cantidad, cantidadActual: cantidadActual, uuid: uuid
		},
		success: function(data) {
			//$('#cantidad-producto').val($.number(data, '0', ',', '.'));
			mensajeExito('Lote agregado con exito!');
		},
		error: function(xhr) {
			mensajeError(xhr.responseText);
		}
	})
}


function getLotesRetornoSuccessDetalle(html) {
	$('.contenido-lote-existente').html(html);
	//console.log(html)
	let cantidad = Number($('.tr-lote-existente').data('canti'));
	if (cantidad > 0) {
		$('.tr-lote-existente').each(function() {
			let cantidadActual = $(this).find('.lista-lote-cantidad-actual').text();
			let cantidad = $(this).find('.cantidad-lote').val();
			$(this).find('.lista-lote-cantidad-actual').text($.number(cantidadActual, '0', ',', '.'));
			$(this).find('.cantidad-lote').val($.number(cantidad, '0', ',', '.'));
		});

		$('#loteExistenteModal').modal('show');
		$('.btn-agregar-lote').click(modificarCantidadLoteExistente);
		$('.btn-quitar-lote').click(eliminarCantidadLoteExite);

	} else {
		let id = $('#idProducto').val();
		let deposito = $('#deposito').val();
		getLoteBydepositoProucto(id, deposito).then(retornoLotes)
			.catch(function(xhr) {
				mensajeError(xhr.responseText);
			})
	}

}