$(function() {
	adicionarItemRendicionSuccess();
	$('#cuenta-destino').keydown(function(e) {
		if (e.keyCode === 13 || e.keyCode === 9) {
			if ($('#moneda').val() != $('#cuenta-destino option:selected').data('moneda-cuenta-destino')) {
				Swal.fire({
					position: "top-end",
					icon: "error",
					title: "La moneda de la cuenta destino no coincide con la moneda del importe!",
					showConfirmButton: false,
					timer: 2000
				});
			} else {
				verificarExistencia();
			}
		}
	});

	function adicionarItemRendicion() {
		let condicion = $('#condicion').val();
		let moneda = $('#moneda').val();
		let importe = $('#importe').val();
		let cuentaOrigen = $('#cuenta-origen').val();
		let cuentaDestino = $('#cuenta-destino').val();
		let uuid = $('#uuid').val();

		$.ajax({
			url: '/planillas/js/adicionar/itemPlanillaRendicion',
			method: 'POST',
			data: { condicion: condicion, moneda: moneda, importe: importe, vcuentaOrigen: cuentaOrigen, vcuentaDestino: cuentaDestino, uuid: uuid },
			success: adicionarItemRendicionSuccess,
			error: function() {
				alert('Error al adicionar item rendición!');
			},
			complete: function() {
				$('#condicion').focus();
				vaciarTodo();
			}
		})
	}

	function adicionarItemRendicionSuccess(html) {

		$('.js-planilla-rendicion').html(html);
		let totalImporteMn = 0;
		$('.js-items-rendicion').each(function() {
			let row = $(this);
			let decimales = row.find('.js-moneda-item-planilla-rendicion').data('decimales');
			let importe = row.find('.js-importe-item-planilla-rendicion').text();
			let importeMn = row.data('importe-mn');
			totalImporteMn = Number(totalImporteMn) + Number(importeMn);

			row.find('.js-importe-item-planilla-rendicion').text($.number(importe, decimales, ',', '.'));
		});
		$('.js-total-rendido-planilla').val($.number(totalImporteMn, '0', ',', '.'));
		$('.btn-item-planilla-rendicion-quitar').off('click').click(quitarItemRendido);
	}

	function quitarItemRendido() {
		let indice = $(this).data('indice');
		let uuid = $('#uuid').val();
		$.ajax({
			url: '/planillas/js/eliminar/itemPlanillaRendicion',
			method: 'DELETE',
			data: { indice: indice, uuid: uuid },
			success: adicionarItemRendicionSuccess,
			error: function() {
				alert('Error quitando item de rendición!');
			}
		})
	}

	function vaciarTodo() {
		$('#condicion').val('');
		$('#moneda').val('');
		$('#importe').val('');
		$('#cuenta-destino').val('');
	}

	function verificarExistencia() {
		if (!$('#condicion').val()) {
			//alert('Debe informar condicion!');
			Swal.fire({
				position: "top-end",
				icon: "error",
				title: "Debe informar condición!",
				showConfirmButton: false,
				timer: 1500
			}).then(() => {
				$('#condicion').focus();
			});
		} else if (!$('#moneda').val()) {
			//alert('Debe informar moneda!');
			Swal.fire({
				position: "top-end",
				icon: "error",
				title: "Debe informar moneda!",
				showConfirmButton: false,
				timer: 1500
			}).then(() => {
				$('#moneda').focus();
			});
		} else if ($('#importe').val().trim().length === 0 || Number($('#importe').val()) === 0) {
			//alert('Debe informar el importe!');
			Swal.fire({
				position: "top-end",
				icon: "error",
				title: "Debe informar importe!",
				showConfirmButton: false,
				timer: 1500
			}).then(() => {
				$('#importe').focus();
			});
		} else if (!$('#cuenta-destino').val()) {
			//alert('Debe informar cuenta destino!');
			Swal.fire({
				position: "top-end",
				icon: "error",
				title: "Debe informar cuenta destino!",
				showConfirmButton: false,
				timer: 1500
			}).then(() => {
				$('#cuenta-destino').focus();
			});
		} else {
			adicionarItemRendicion();
		}
	}
})