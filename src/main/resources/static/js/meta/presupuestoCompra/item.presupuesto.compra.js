import { formatearNumeroInput } from '/js/meta/utilidades.js';
import { autoCompletar } from '/js/meta/producto/auto.complete.producto.general.js';
$(function() {
	adicionarItemSuccess();
	const $productoNombre = $('#producto-nombre');
	$productoNombre.on('input', autoCompletar);
	$('#precio-det-nc').keydown(function(e) {
		if (e.keyCode === 13 || e.keyCode === 9) {
			if ($('#id-producto').val() === '') {
				alert('Debe informar producto!')
			} else {
				adicionarItem();
			}

		}
	})

	$('.btn-adiciona-item-nc').click(function() {
		if ($('#id-producto').val() === '') {
			alert('Debe informar producto!')
		} else {
			adicionarItem();
		}
	});

	function adicionarItem() {
		if ($('#precio-det-nc').val() === '') {
			$('#precio-det-nc').val(0);
		}
		let producto = $('#id-producto').val();
		let cantidad = $('#cantidad-det-nc').val();
		let precio = $('#precio-det-nc').val();
		let uuid = $('#uuid').val();

		$.ajax({
			url: '/presupuestoCompras/item/adicionar',
			method: 'POST',
			data: { producto: producto, cantidad: cantidad, precio: precio, uuid: uuid },
			success: adicionarItemSuccess,
			error: function(error) {
				alert('Error adicionando item !');
				console.log('Error adicionando item: ' + error);
			},
			complete: function() {
				vaciar();
			}
		})

	}

	function adicionarItemSuccess(html) {
		$('.item-presupuesto-compra').html(html);
		$('input').click(function() {
			$(this).select();
		})
		formatearNumeroInput($('.formato-numero'));
		let total = $('.js-tabla-item-presupuesto').data('total');
		$('#total').val($.number(total, '2', ',', '.'));

		$('.js-tabla-item-presupuesto').each(function() {
			let cantidad = $(this).find('.item-nc-venta-cantidad').val();
			let precio = $(this).find('.item-nc-venta-precio').text();
			let subTotal = $(this).find('.item-nc-venta-sub-total').text()
			$(this).find('.item-nc-venta-cantidad').val($.number(cantidad, '0', ',', '.'));
			$(this).find('.item-nc-venta-precio').text($.number(precio, '2', ',', '.'));
			$(this).find('.item-nc-venta-sub-total').text($.number(subTotal, '2', ',', '.'))
		});

		$('.item-nc-venta-cantidad').change(cambioCantidadManipular);
		$('.btn-item-quitar').off('click').click(eliminarItemManipular);
	}

	function cambioCantidadManipular() {
		let producto = $(this).data('id');
		let cantidad = $(this).val();
		let uuid = $('#uuid').val();
		$.ajax({
			url: '/presupuestoCompras/item/modificar/cantidad',
			method: 'PUT',
			data: { producto: producto, cantidad: cantidad, uuid: uuid },
			success: adicionarItemSuccess,
			error: function(error) {
				alert('Error adicionando item !');
				console.log('Error adicionando item: ' + error);
			}
		})
	}

	function eliminarItemManipular() {
		let indice = $(this).data('indice');
		let uuid = $('#uuid').val();
		$.ajax({
			url: '/presupuestoCompras/item/eliminar',
			method: 'DELETE',
			data: { indice: indice, uuid: uuid },
			success: adicionarItemSuccess,
			error: function(error) {
				alert('Error eliminando item!');
				console.log('Error eliminando item: ' + error);
			}
		})
	}

	function vaciar() {
		$('#id-producto').val('');
		$('#cantidad-det-nc').val('');
		$('#precio-det-nc').val('');
		$('#producto-nombre').val('');
		$('#producto-nombre').focus();
	}
})