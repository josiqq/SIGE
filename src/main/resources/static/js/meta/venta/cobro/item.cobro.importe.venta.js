$(function() {

	$('.moneda-vuelto-cv').keydown(function(e) {
		if (e.keyCode === 13) {
			agregarDetalle();
		}
	})

	$('.banco-cv').keydown(function(e) {
		if (e.keyCode === 13) {
			agregarDetalle();
		}
	})

	$('.btn-agregar-det-cv').click(agregarDetalle);

	function agregarDetalle() {
		let condicion = $('.condicion-cv').val();
		let moneda = $('.moneda-cv').val();
		let cotizacion = $('#cotizacion-cv').val();
		let importe = $('#importe-cv').val();
		let importems = $('#importems').val();
		let cobrado = $('#cobrado-cv').val();
		let monedaVuelto = $('.moneda-vuelto-cv').val();
		let cotizacionVuelto = $('#cotizacion-vuelto-cv').val()
		let vuelto = $('#vuelto-cv').val();
		let vueltoms = $('#vuelto-md-sys').val();
		let cuenta = 1;
		let banco = $('.banco-cv').val();
		let boleta = $('#boleta-cv').val();
		let uuid = $('#uuid').val();
		let importe_ret = $('#importe-cv').val().replace(/\./g, '').replace(',', '.');
		let importe_cobrado_ret = $('#cobrado-cv').val().replace(/\./g, '').replace(',', '.');
		let saldo = $('.valor-saldo-principal-c-v').text().replace(/\./g, '').replace(',', '.');
		if (banco === 'Seleccionar') {
			banco = '';
		}
		console.log('Importe moneda sistema: ', $('#importems').val(),
			'\nsaldo del sistema: ', $('.valor-saldo-principal-c-v').text().replace(/\./g, '').replace(',', '.'));
		if (Number(saldo) === Number(0)) {
			Swal.fire({
				icon: "error",
				title: "Oops...",
				text: "Ya tienes saldo cero!",

			});
		} else if (Number($('#importems').val()) > Number($('.valor-saldo-principal-c-v').text().replace(/\./g, '').replace(',', '.'))) {

			Swal.fire({
				icon: "error",
				title: "Oops...",
				text: "Importe no puede ser mayor al total!",

			});
		} else if (Number(importe_ret) > Number(importe_cobrado_ret)) {
			Swal.fire({
				icon: "error",
				title: "Oops...",
				text: "Importe cobrado no puede ser menor a importe!",

			});
		} else if ($('.condicion-cv option:selected').data('condicion-cobro') === 'TARJETA') {
			if (boleta.trim().length === 0 || banco === 'Seleccionar') {
				Swal.fire({
					icon: "error",
					title: "Oops...",
					text: "Nro de boleta y banco son obligatorios!",

				});
			} else {
				enviarItem();
			}
		} else if ($('.condicion-cv option:selected').data('condicion-cobro') === 'TRANSFERENCIA') {
			if (banco === 'Seleccionar') {
				Swal.fire({
					icon: "error",
					title: "Oops...",
					text: "Banco es obligatorio!",

				});
			} else {
				enviarItem()

			}
		} else if ($('.condicion-cv option:selected').data('condicion-cobro') === 'EFECTIVO') {

			enviarItem();
		}

		function enviarItem() {
			$.ajax({
				url: '/cobros/item/importe',
				method: 'POST',
				data: {
					cuenta: cuenta,
					condicion: condicion,
					moneda: moneda,
					valorContizacion: cotizacion,
					importe: importe,
					importems: importems,
					cobrado: cobrado,
					monv: monedaVuelto,
					cotizacionVuelto: cotizacionVuelto,
					vuelto: vuelto,
					vueltoms: vueltoms,
					banco: banco,
					boleta: boleta,
					uuid: uuid
				},
				success: setItemCobroImporteSuccess,
				complete: function() {
					$('.condicion-cv').focus();
				},
				error: function() {
					alert('Error insertando detalle de cobro !');
				}
			})
			vaciar();
		}


	}

	function setItemCobroImporteSuccess(html) {
		$('.item-cobro-importe-cv').html(html);

		//colocar dos decimales 
		$('.tr-item-cobro-importe .importe-item').each(function() {
			var contenido = $(this).text();
			var numeroFormateado = $.number(contenido, 2, ',', '.');
			$(this).text(numeroFormateado);
		});

		let totalCabecera = $('.valor-importe-principal-c-v').text().replace(/\./g, "");
		let totalMs = $('.tr-item-cobro-importe').data('total') ? $('.tr-item-cobro-importe').data('total') : 0;
		cancularSaldo(totalCabecera, totalMs);

		$('.btn-eliminar-linea-cv').off('click').click(eliminarFila);
	}


	function eliminarFila() {

		let indice = $(this).data('indice');
		let uuid = $('#uuid').val();
		$.ajax({
			url: '/cobros/item/importe/eliminar',
			method: 'DELETE',
			data: { indice: indice, uuid: uuid },
			success: setItemCobroImporteSuccess,
			error: function() {
				alert('Error eliminando detalle de cobro!');
			}
		})

	}

	window.vaciar = function vaciar() {
		console.log('mandar para vaciar!!');
		$('.condicion-cv').val($('#condicion-cobro').val());
		visibleParaEfectivo();
	}

	function cancularSaldo(totalCabecera, totalMs) {
		let saldo = Number(totalCabecera) - Number(totalMs);
		$('.valor-saldo-principal-c-v').text($.number(saldo, '0', ',', '.'));
		$('.moneda-cv').val($('#monedaCuenta').val());
		$('.moneda-vuelto-cv').val($('#monedaCuenta').val());
		$('#cotizacion-cv').val(0);
		$('#importe-cv').val($('.valor-saldo-principal-c-v').text());
		$('#importems').val(saldo);
		$('#cobrado-cv').val(saldo);
		$('#cotizacion-vuelto-cv').val(0);
		$('#vuelto-cv').val(0);
	}

	function visibleParaEfectivo() {
		$('.cont-moneda-cv').removeClass('noMostrar');
		$('.cont-cotizacion-cv').removeClass('noMostrar');
		$('.cont-moneda-vuelto-cv').removeClass('noMostrar');
		$('.cont-cotizacion-vuelto-cv').removeClass('noMostrar');
		$('.cont-vuelto-cv').removeClass('noMostrar');
		$('.cont-banco').addClass('noMostrar');
		$('.cont-boleta-cv').addClass('noMostrar');
		$('#cobrado-cv').prop('readonly', false);
		//nuevo desde aqui
		$('.moneda-cv').val($('#monedaCuenta').val());
		$('#cotizacion-cv').val(0)
		$('#boleta-cv').val('');
		$('.moneda-vuelto-cv').val($('#monedaCuenta').val());
		$('#cotizacion-vuelto-cv').val(0);
		$('#vuelto-cv').val(0);
		console.log('Colocar esta moneda aqui: ', $('#monedaCuenta').val());
	}
})