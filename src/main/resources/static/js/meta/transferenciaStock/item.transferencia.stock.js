import { autoCompletar } from '/js/meta/producto/auto.complete.producto.general.js';
import { keyProductoManipular } from '/js/meta/presupuestoVenta/presupuesto.venta.service.js';
import { formatearNumeroInput } from '/js/meta/utilidades.js';
$(function() {
	adicionarItemSuccess();
	formatearNumeroInput($('.formato-numero'));
	const $productoNombre = $('#producto-nombre');
	$productoNombre.on('input', autoCompletar);
	$('#producto-nombre').keydown(keyProductoManipular);
	$('#id-producto').change(function() {
		setTimeout(function() {
			$('#cantidad').focus();
		}, 500)

	})

	$('.btn-adiciona-item').click(adicionarItem);
	$('#cantidad').keydown(function(e) {
		if (e.keyCode === 13 || e.keyCode === 9) {
			adicionarItem();
		}
	})
	function adicionarItem() {
		let producto = $('#id-producto').val();
		let cantidad = $('#cantidad').val();
		if($('#cantidad').val()=== ''){
			cantidad = 1;
		}
		let uuid = $('#uuid').val();

		$.ajax({
			url: '/transferencia-stock/item-adicionar',
			method: 'POST',
			data: { producto: producto, cantidad: cantidad, uuid: uuid },
			success: adicionarItemSuccess,
			error: function(error) {
				alert('Error adicionando item!');
				console.log('Error adicionando item: ' + error);
			},
			complete:function(){
				vaciar();
			}
		})

	}

	function adicionarItemSuccess(html) {
		$('.item-transferencia').html(html);
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
			url:'/transferencia-stock/item-eliminar',
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
		let uuid= $('#uuid').val();
		if($(this).val()=== ''){
			cantidad = 1;
		}
		$.ajax({
			url:'/transferencia-stock/item-modificar-cantidad',
			method:'PUT',
			data:{producto:producto,cantidad:cantidad,uuid:uuid},
			success:adicionarItemSuccess,
			error:function(error){
				alert('Error al modificar cantidad!');
				console.log('Error al modificar cantidad: '+error);
			}
		})
	}
	
	function vaciar(){
		$('#cantidad').val('');
		$('#id-producto').val('');
		$('#producto-nombre').val('');
		$('#producto-nombre').focus();
		
	}
});
