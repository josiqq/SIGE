import CondicionQueries from '/js/meta/condicion/CondicionQueries.js';
const condicionQueries = new CondicionQueries();
$(function() {

	$('#modal-cobro-venta').on('shown.bs.modal', function() {
		$('.condicion-cv').focus();
		$('#importe-cv').val($('.valor-importe-principal-c-v').text());
		$('#cobrado-cv').val($('.valor-importe-principal-c-v').text());
		$('#importems').val($('#importe-cv').val());
		$('#vuelto-cv').val(0);
		$('#cotizacion-cv').val($.number(0, '0', ',', '.'));
		$('#cotizacion-vuelto-cv').val($.number(0, '2', ',', '.'));

		$('.condicion-cv').keydown(function(e) {
			if (e.keyCode === 13) {

				if ($('.cont-moneda-cv').hasClass('noMostrar')) {
					$('#importe-cv').focus();
					$('#importe-cv').select();
				} else {
					$('.moneda-cv').focus();
				}
			}
		})


		$('.moneda-cv').keydown(function(e) {
			if (e.keyCode === 13) {
				$('#importe-cv').focus();
				$('#importe-cv').select();
			}
		})

		$('#importe-cv').keydown(function(e) {
			if (e.keyCode === 13) {
				cambioImporte();
				$('#cobrado-cv').focus();
				$('#cobrado-cv').select();

			}
		})

		$('#cobrado-cv').keydown(function(e) {
			if (e.keyCode === 13 || e.keyCode === 9) {

				let importe = $('#importe-cv').val().replace(/\./g, '').replace(',', '.');
				let importe_cobrado = $('#cobrado-cv').val().replace(/\./g, '').replace(',', '.');
				if (Number(importe) > Number(importe_cobrado)) {
					Swal.fire({
						icon: "error",
						title: "Oops...",
						text: "Importe cobrado no puede ser menor a importe!",

					});
				} else if ($('.cont-moneda-vuelto-cv').hasClass('noMostrar') && !$('.cont-boleta-cv').hasClass('noMostrar')) {
					$('#boleta-cv').focus();
				} else if ($('.cont-boleta-cv').hasClass('noMostrar') && !$('.cont-banco').hasClass('noMostrar')) {
					$('.banco-cv').focus();

				} else {
					$('.moneda-vuelto-cv').focus();
					calcularVuelto();
				}
			}
		})

		$('#boleta-cv').keydown(function(e) {
			if (e.keyCode === 13 || e.keyCode === 9) {
				$('.banco-cv').focus();
			}
		})

		$('.moneda-cv').change(calcularCotizaciones);
		$('.moneda-vuelto-cv').change(cotizarVuelto);
		$('.btn-grabar-cv').click(function() {
			if (Number($('.valor-saldo-principal-c-v').text().replace(/\./g, '').replace(',', '.')) > 0) {
				Swal.fire({
					icon: "error",
					title: "Oops...",
					text: "Todavia tienes saldo: " + $('.valor-saldo-principal-c-v').text()

				});

			} else {
				guardarCobro();
			}

		});
		$('.condicion-cv').change(verificarCondicion);
		$('#importe-cv').change(cambioImporte);
		atajosTecladoModal();

	});


	function verificarCondicion() {
		let saldo_ret = $('.valor-saldo-principal-c-v').text().replace(/\./g, '').replace(',', '.');
		$('#importems').val(saldo_ret);
		$('#importe-cv').val($('.valor-saldo-principal-c-v').text());
		$('#cobrado-cv').val($('.valor-saldo-principal-c-v').text());

		let id = $(this).val();

		condicionQueries.getTipoCondicion(id)
			.then(function(condicion) {
				cambiarSegunCondicion(condicion);
			}).catch(function(error) {
				console.log('Este es el error!!', error);
				alert('Error recuperando condiciones!!');
			});


	}

	function cambiarSegunCondicion(condicion) {
		switch (condicion.condicionCobro) {
			case 'TARJETA':
				$('#condicion-hidden').val(condicion.condicionCobro);
				visibleParaTarjeta();
				atajosTecladoModal();
				break;
			case 'EFECTIVO':
				$('#condicion-hidden').val(condicion.condicionCobro);
				visibleParaEfectivo();
				atajosTecladoModal();
				break;
			case 'TRANSFERENCIA':
				$('#condicion-hidden').val(condicion.condicionCobro);
				visibleParaTransferencia();
				atajosTecladoModal();
				break;
		}
	}

	function visibleParaTarjeta() {
		$('.cont-moneda-cv').addClass('noMostrar');
		$('.cont-cotizacion-cv').addClass('noMostrar');
		$('.cont-moneda-vuelto-cv').addClass('noMostrar');
		$('.cont-cotizacion-vuelto-cv').addClass('noMostrar');
		$('.cont-vuelto-cv').addClass('noMostrar');
		$('.cont-banco').removeClass('noMostrar');
		$('.cont-boleta-cv').removeClass('noMostrar');
		$('#cobrado-cv').prop('readonly', true);
		//desde aqui colocar nuevos
		$('.moneda-cv').val($('#monedaCuenta').val());
		$('#cotizacion-cv').val(0)
		$('#boleta-cv').val('');
		$('.moneda-vuelto-cv').val($('#monedaCuenta').val());
		$('#cotizacion-vuelto-cv').val(0);
		$('#vuelto-cv').val(0);
		console.log('Colocar esta moneda aqui: ', $('#monedaCuenta').val());
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

	function visibleParaTransferencia() {
		$('.cont-moneda-cv').addClass('noMostrar');
		$('.cont-cotizacion-cv').addClass('noMostrar');
		$('.cont-moneda-vuelto-cv').addClass('noMostrar');
		$('.cont-cotizacion-vuelto-cv').addClass('noMostrar');
		$('.cont-vuelto-cv').addClass('noMostrar');
		$('.cont-banco').removeClass('noMostrar');
		$('.cont-boleta-cv').addClass('noMostrar');
		$('#cobrado-cv').prop('readonly', true);
		//nuevo desde aqui
		$('.moneda-cv').val($('#monedaCuenta').val());
		$('#cotizacion-cv').val(0)
		$('#boleta-cv').val('');
		$('.moneda-vuelto-cv').val($('#monedaCuenta').val());
		$('#cotizacion-vuelto-cv').val(0);
		$('#vuelto-cv').val(0);
		console.log('Colocar esta moneda aqui: ', $('#monedaCuenta').val());
	}

	function calcularCotizaciones() {
		let saldo_ret = $('.valor-saldo-principal-c-v').text().replace(/\./g, '').replace(',', '.');
		$('#importems').val(saldo_ret);
		let fecha = $('#fecha').val();
		let monedaOrigen = retornaCodigoMonedaSistema();
		let monedaDestino = $(this).val();
		let total = $('.valor-saldo-principal-c-v').text().replace(/\./g, "").replace(",", ".");
		let importe_cotizado = total;

		$('.moneda-vuelto-cv').val($(this).val());

		if ((monedaOrigen != monedaDestino) && (monedaDestino != 'Moneda')) {
			buscarUnaCotizacion(fecha, monedaOrigen, monedaDestino)
				.then(function(cotizacion) {

					if (cotizacion.dividir === true) {

						importe_cotizado = Number(total) / Number(cotizacion.valor);
						$('#importe-cv').val($.number(importe_cotizado, '16', ',', '.'));
						$('#cobrado-cv').val($.number(importe_cotizado, '16', ',', '.'));
						$('#cotizacion-cv').val($.number(cotizacion.valor, cotizacion.monedaDestino.decimales, ',', '.'));
					} else {
						importe_cotizado = Number(total) * Number(cotizacion.valor);
						$('#importe-cv').val($.number(importe_cotizado, '16', ',', '.'));
						$('#cobrado-cv').val($.number(importe_cotizado, '16', ',', '.'));
						$('#cotizacion-cv').val($.number(cotizacion.valor, cotizacion.monedaDestino.decimales, ',', '.'));
					}
				}).catch(function() {
					alert('Error recuperando cotizacion!');
				});

		} else {
			$('#importe-cv').val($.number(total, '0', ',', '.'));
			$('#cobrado-cv').val($.number(total, '0', ',', '.'));
			$('#cotizacion-cv').val($.number(0, '0', ',', '.'));
		}
	}

	function calcularVuelto() {

		let importe = $('#importe-cv').val().replace(/\./g, "").replace(",", ".");
		let cobrado = $('#cobrado-cv').val().replace(/\./g, "").replace(",", ".");
		let vuelto = Number(cobrado) - Number(importe);
		if (vuelto < 0) {
			vuelto = 0;
		}
		$('#vuelto-cv').val($.number(vuelto, '16', ',', '.'));
		$('#vuelto-md-sys').val(vuelto);
	}

	function cotizarVuelto() {
		let monedaOrigen = $('.moneda-cv').val();
		let monedaDestino = $(this).val();
		let fecha = $('#fecha').val()
		if ((monedaOrigen != monedaDestino) && (monedaDestino != 'Moneda vuelto')) {
			buscarUnaCotizacion(fecha, monedaOrigen, monedaDestino)
				.then(function(cotizacion) {
					if (!cotizacion.id) {
						alert('No existe cotizacion cargada para estas monedas !!');
					} else {
						let valor_vuelto = $('#vuelto-md-sys').val();
						let vuelto_cotizado = 0;
						if (cotizacion.dividir === true) {
							vuelto_cotizado = Number(valor_vuelto) / Number(cotizacion.valor);

						} else {
							vuelto_cotizado = Number(valor_vuelto) * Number(cotizacion.valor);

						}
						if ($('.moneda-vuelto-cv option:selected').data('decimales') === 2) {
							$('#vuelto-cv').val($.number(vuelto_cotizado, '16', ',', '.'));
						} else {
							$('#vuelto-cv').val($.number(vuelto_cotizado, '0', ',', '.'));
						}

						$('#cotizacion-vuelto-cv').val($.number(cotizacion.valor, '2', ',', '.'));
					}
				}).catch(function() {
					alert('Error recuperando cotizaciones!');
				})
		} else {
			$('#vuelto-cv').val($.number($('#vuelto-md-sys').val(), '2', ',', '.'));
			$('#cotizacion-vuelto-cv').val($.number(0, '2', ',', '.'));
		}
	}


	function cambioImporte() {
		console.log('cambiar importe: ',)
		let monedaOrigen = retornaCodigoMonedaSistema();
		let monedaDestino = $('.moneda-cv').val();
		let fecha = $('#fecha').val();
		let importems = $('#importe-cv').val().replace(/\./g, "").replace(",", ".");
		let valor_cotizado = 0;

		$('#importems').val(importems);
		$('#cobrado-cv').val($('#importe-cv').val());

		if (monedaOrigen != monedaDestino) {

			buscarUnaCotizacion(fecha, monedaOrigen, monedaDestino)
				.then(function(cotizacion) {
					if (!cotizacion.id) {
						alert('No existe cotizacion cargada para estas monedas !!');
					} else {



						if (cotizacion.dividir === true) {
							valor_cotizado = Number(importems) * Number(cotizacion.valor);

						} else {
							valor_cotizado = Number(importems) / Number(cotizacion.valor);
						}
						$('#importems').val(valor_cotizado);


					}
				}).catch(function() {
					alert('Error recuperando cotizaciones!');
				})
		}
	}
	function guardarCobro() {

		let cajero = { id: $('#usuario-id-cajero').val() };
		let usuario = { id: $('#usuario').val() };
		let cuenta = { id: $('#cuenta-usuario').val() };
		let planilla = {id:$('#idPlanilla').val()};
		let total = $('.valor-importe-principal-c-v').text().replace(/\./g, '').replace(',', '.');
		let uuid = $('#uuid').val();
		let cobro = {
			cajero: cajero,
			usuario: usuario,
			cuenta: cuenta,
			planilla:planilla,
			total: total,
			uuid: uuid

		};
		$.ajax({
			url: '/cobros/venta',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(cobro),
			success: guardarCobroSuccess,
			error: function() {
				alert('Error grabando cobro!!!');
			}

		});


	}

	function guardarCobroSuccess(data) {
		$('#modal-cobro-venta').modal('hide');
		/*Swal.fire({
			position: "top-end",
			icon: "success",
			title: "Cobro guardado con exito!!!",
			showConfirmButton: false,
			timer: 1500
		});*/

		/*Swal.fire({
			title: "imprimir?",
			text: "Desea imprimir la venta!",
			icon: "warning",
			showCancelButton: true,
			confirmButtonColor: "#3085d6",
			cancelButtonColor: "#d33",
			confirmButtonText: "Si, mandar impresion!"
		}).then((result) => {
			if (result.isConfirmed) {
				console.log('mandar impresion...con este registro: ',$('#registro').val());
				let registro = $('#registro').val();
				if($('#factura-retorno').val()==='false'){
					imprimirNotaComun(registro);
				}else{
					imprimirFactura(registro)
				}
			}
		});*/

		$('#producto').focus();
		atajosTecladoVenta();
	}

	function atajosTecladoModal() {
		window.onkeydown = presionaTeclaMdal;

		function presionaTeclaMdal(e) {
			if (e.keyCode === 116) {
				e.preventDefault();
				$('.btn-grabar-cv').click();
			}

		}


	}

	function atajosTecladoVenta() {
		$('.js-cabtidad-venta-producto').keydown(function(e) {
			if (e.keyCode === 13 || e.keyCode === 9) {
				e.preventDefault();
				//$('.precio-producto').focus();
			}

		});
		$('#precio-producto').keydown(function(e) {
			if (e.keyCode === 13 || e.keyCode === 9) {
				e.preventDefault();
				//$('.precio-producto').focus();
			}
		})
		window.onkeydown = presionaTeclaMdal;

		function presionaTeclaMdal(e) {
			if (e.keyCode === 116) {
				e.preventDefault();
				$('.btn-guardar').click();
			}
			if (e.KeyCode === 13) {
				e.preventDefault();
			}

		}


	}
})