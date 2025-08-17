import {getPresupuestos,getPresupuestoById} from '/js/meta/presupuestoCompra/presupuesto.compra.queries.js';
import {generarUUID} from '/js/meta/utilidades/utilidades.js?ver=0.0.1';
$(function(){
	var total = $('.js-total');
	$('#modal-lista-presupuesto-compra').on('show.bs.modal',modalAbiertoManipular);
	$('#modal-lista-presupuesto-compra').on('hide.bs.modal',modalCerradoManipular);
	
	function modalAbiertoManipular(){
		console.log('modal presupuesto compra abierto');
		$('#fecha-desde-presu').val($('#fecha').val());
		$('#fecha-hasta-presu').val($('#fecha').val());
		$('.btn-modal-buscar-pres').click(buscarPresupuesto);
		
	}
	
	function modalCerradoManipular(){
		console.log('modal presupuesto compra cerrado');
	}
	
	function buscarPresupuesto(){
		$('.tabla-lista-presu').addClass('noMostrar');
		$('.conten-cargando').removeClass('noMostrar');
		let fechaDesde = $('#fecha-desde-presu').val();
		let fechaHasta = $('#fecha-hasta-presu').val();
		getPresupuestos(fechaDesde,fechaHasta).then(retornoPresupuesto)
		.catch(
			function(error){
				alert('Error recuperando presupuestos!');
				console.log('Error recuperando presupuesto: '+error);
			}
		).finally(
			function(){
				$('.tabla-lista-presu').removeClass('noMostrar');
				$('.conten-cargando').addClass('noMostrar');		
			}
		)
	}
	
	function retornoPresupuesto(presupuestos){
		console.log('retorno presupuesto: '+presupuestos);
		let listaPresupuestos = [];
		presupuestos.forEach(function(presu){
			listaPresupuestos.push(`<tr class="tr-lista-presu cursor-tr">
										<td class="presu-id">${presu.id}</td>
										<td>${presu.fecha}</td>
										<td>${presu.moneda.nombre}</td>
										<td>${presu.proveedor.nombre}</td>
										<td>${$.number(presu.total,'2',',','.')}</td>
									</tr>
									`)
		})
		$('.tbody-lista-presu').html(listaPresupuestos);
		$('.tr-lista-presu').click(agregarDatosCompra);
	}
	
	function agregarDatosCompra(){
		let id = $(this).find('.presu-id').text();
		console.log('Busca por esta id '+id);
		getPresupuestoById(id).then(retornoPresuIdManipular)
		.catch(
			function(error){
				alert('Error recuperando presupuesto para compra!');
				console.log('Error recuperando presupuesto para compra: '+error);
			}
		).finally(function(){
			$('#modal-lista-presupuesto-compra').modal('hide');
			adicionarItemConPresu(id);
		})
	}
	
	function retornoPresuIdManipular(pres){
		$('#idProveedor').val(pres.proveedor.id);
		$('#proveedor').val(pres.proveedor.nombre);
		$('#moneda').val(pres.moneda.id);
		
	}
	
	function adicionarItemConPresu(id){
		$('#uuid').val(generarUUID());
		let uuid=$('#uuid').val();
		console.log('uuid: '+uuid);
		$.ajax({
			url:'/compras/adicionar/con/presu',
			method:'POST',
			data:{id:id,uuid:uuid},
			success:llegoRespuesta,
			error:function(error){
				alert('Error adicionando item con pedido!');
				console.log('Error adicionando item: '+error);
			}
			
		})
	}
	
//==============================RETORNO DE ITEM==================================
 function llegoRespuesta(html){
	producto.focus();
	$('.js-item-compra').html(html);
	let total = $('.js-tabla-item').data('total');
	$('.total-cabecera').text($.number(total,'2',',','.'));
	$('.js-tabla-item').each(function(){
		let cantidad = $(this).find('.item-cantidad-js').val();
		let precio = $(this).find('.item-precio-js').val();
		let subTotal = $(this).find('.js-sub-total-compra').val();
		$(this).find('.item-cantidad-js').val($.number(cantidad,'0',',','.'));
		$(this).find('.item-precio-js').val($.number(precio,'2',',','.'));
		$(this).find('.js-sub-total-compra').val($.number(subTotal,'2',',','.'));
		
	})
	
	$('.item-cantidad-js').change(modificarCantidad);
	$('.item-precio-js').change(modificarPrecio);
	$('.btn-item-quitar').off('click').click(eliminarItem);
}

function modificarCantidad(){
	let id = $(this).data('id');
	let cantidad=$(this).val();
	let uuid= $('#uuid').val();
	
	$.ajax({
		url: '/compras/item/modificarCantidad/'+id,
		method:'PUT',
		data:{cantidad:cantidad,uuid:uuid},
		success:llegoRespuesta,
		error:function(error){
			alert('Error modificando cantidad!');
			console.log('Error modificando cantidad!'+error);
		}
	});
}

function modificarPrecio(){
	let id = $(this).data('id');
	let precio=$(this).val();
	let uuid= $('#uuid').val();
	$.ajax({
		url: '/compras/item/modificarPrecio/'+id,
		method:'PUT',
		data:{precio:precio,uuid:uuid},
		success:llegoRespuesta,
		error:function(error){
			alert('Error modificando precio!');
			console.log('Error modificando precio: '+error);
		}
	});
}



function eliminarItem(){
	let id = $(this).data('id');
	let uuid=$('#uuid').val();
	$.ajax({
		url:'/compras/item/eliminar/'+uuid+'/'+id,
		method:'DELETE',
		success:llegoRespuesta,
		error:function(error){
			alert('Error quitando item !');
			console.log('Error quitando item: '+error);
		}
	});
	
}
	
})