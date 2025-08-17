import { autoCompletar } from '/js/meta/producto/auto.complete.producto.lote.js';
import { keyProductoManipularLote } from '/js/meta/producto/producto.service.js';
import { formatearNumeroInput } from '/js/meta/utilidades.js';
import {gerLoteByNumero} from '/js/meta/lote/lote.queries.js';
import {mensajeError} from '/js/meta/mensaje/mensaje.service.js';
$(function(){
	adicionarItemSuccess();
	formatearNumeroInput($('.formato-numero'));
	const $productoNombre = $('#producto-nombre');
	$productoNombre.on('input', autoCompletar);
	$('#producto-nombre').keydown(keyProductoManipularLote);
	$('#numero-lote').change(cambioNumeroLoteManipular);
	$('#id-producto').change(function() {
		setTimeout(function() {
			$('.js-input-pos-ac').focus();
		}, 300)

	})
	
	$('.btn-adiciona-item').click(adicionarItem);
	
	$('#cantidad-lote').keydown(function(e) {
		if (e.keyCode === 13 || e.keyCode === 9) {
			adicionarItem();
		}
	})
	
	function adicionarItem() {
		let producto = $('#id-producto').val();
		let cantidad = $('#cantidad-lote').val();
		let vencimiento = $('#vencimiento').val();
		let nroLote = $('#numero-lote').val();
		if($('#cantidad-lote').val()=== ''){
			cantidad = 1;
		}
		let uuid = $('#uuid').val();

		$.ajax({
			url: '/ajuste-lote/item-adicionar',
			method: 'POST',
			data: { producto: producto, nroLote:nroLote,cantidad: cantidad,vencimiento:vencimiento, uuid: uuid },
			success: adicionarItemSuccess,
			error: function(xhr) {
				alert('Error adicionando item!'+xhr.responseText);
				console.log('Error adicionando item: ' + xhr.responseText);
			},
			complete:function(){
				vaciar();
			}
		})

	}

	function adicionarItemSuccess(html) {
		$('.item-ajuste-lote').html(html);
		formatearNumeroInput($('.formato-numero'));

		$('.js-tabla-item').each(function() {
			let cantidad = $(this).find('.item-cantidad').val();
			$(this).find('.item-cantidad').val($.number(cantidad, '0', ',', '.'));
		});

		$('.btn-item-quitar').click(eliminarItem);
		$('.item-cantidad').change(cambioCantidad);
	}

	function eliminarItem() {
		let indice = $(this).data('indice');
		let uuid = $('#uuid').val();
		
		$.ajax({
			url:'/ajuste-lote/item-eliminar',
			method:'DELETE',
			data:{indice:indice,uuid:uuid},
			success:adicionarItemSuccess,
			error:function(error){
				alert('Error al eliminar item!');
				console.log('Error al eliminar item: '+error);
			}
		})
	}
	
	function cambioCantidad(){
		let cantidad = $(this).val();
		let producto = $(this).data('id');
		let nroLote = $(this).data('lote');
		let uuid= $('#uuid').val();
		if($(this).val()=== ''){
			cantidad = 1;
		}
		$.ajax({
			url:'/ajuste-lote/item-modificar-cantidad',
			method:'PUT',
			data:{producto:producto,nroLote:nroLote,cantidad:cantidad,uuid:uuid},
			success:adicionarItemSuccess,
			error:function(error){
				alert('Error al modificar cantidad!');
				console.log('Error al modificar cantidad: '+error);
			}
		})
	}
	
	function cambioNumeroLoteManipular(){
		let numeroLote = $(this).val();
		let idProducto = $('#id-producto').val();
		let deposito =$('#deposito').val();
		console.log('Buscar por este numero de lote: '+numeroLote);
		gerLoteByNumero(numeroLote,idProducto,deposito).then(retornoLoteSuccess)
		.catch(function(xhr){
			mensajeError(xhr.responseText);
			vaciar();
		});
	}
	
	function retornoLoteSuccess(lote){
		if(lote.id === null){
			console.log('lote no retornado: '+lote.id+' cantidad: '+lote.cantidad);	
		}else{
			console.log('lote retornado: '+lote.id+' cantidad: '+lote.cantidad);
			$('#vencimiento').val(moment(lote.vencimiento).format('DD/MM/YYYY'));
			$('#cantidad-actual').val($.number(lote.cantidad,'0',',','.'));
		}
		
	}
	
	function vaciar(){
		$('#cantidad-lote').val('');
		$('#id-producto').val('');
		$('#numero-lote').val('');
		$('#producto-nombre').val('');
		$('#producto-nombre').focus();
		$('#vencimiento').val('');
		$('#cantidad-actual').val('');
		
	}
})