import CotizacionQueries from '/js/meta/cotizacion/CotizacionQueries.js';
import CambioCondicion from '/js/meta/cobro/cambio.condicion.cobro.js';
import { verificarSaldo } from '/js/meta/cobro/cambio.importe.vuelto.js';
import { compareImporteToCobrado } from '/js/meta/cobro/cambio.importe.vuelto.js';
import {getItemCobroSuccess} from '/js/meta/cobro/item.cobro.js';
import {cantidad_decimales} from '/js/meta/utilidades/utilidades.js'
const cotizacionQueries = new CotizacionQueries();
const cambioCondicion = new CambioCondicion();

$(function() {
	adicionarItemSuccess();
	
	$('.js-btn-comision-tarjeta').click(clickComisionTarjeta);
	
	function clickComisionTarjeta(){
		let porc_comision = Number($('option:selected', $('#condicion')).data('comision'));
		
		if (porc_comision> 0) {
			
				rutinaComisionTarjeta(porc_comision);
		}
	}
	
	$('.js-condicion').keydown(function(e) {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			if ($('.cont-moneda').hasClass('noMostrar')) {
				$('#importe').focus();
				$('#importe').select();
			} else {
				$('.js-moneda').focus();
			}

		}
	});

	$('#moneda').keydown((e) => {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			$('#importe').focus();
			$('#importe').select();
		}
	});

	$('#importe').keydown((e) => {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			$('#importe-cobrado').focus();
			$('#importe-cobrado').val($('#importe').val());
			$('#importe-cobrado').select();
		}
	});

	$('#importe-cobrado').keydown((e) => {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			if ($('.cont-moneda-vuelto').hasClass('noMostrar')) {
				$('#banco').focus();
			} else {
				$('#moneda-vuelto').focus();
				calcularVuelto();

			}

		}
	});

	$('#moneda-vuelto').keydown((e) => {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			
			if (Number($('#saldo').val()) === Number(0) || isNaN($('#saldo').val())) {
				Swal.fire({
					icon: "error",
					title: "Oops...",
					text: "Ya no puede agregar detalles porque tiene saldo cero!!"

				});
			} else {
				adicionarItem();
			}

		}
	});

	$('#banco').keydown((e) => {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			if ($('#banco').val()) {
				if ($('.cont-boleta').hasClass('noMostrar')) {
					if (Number($('#saldo').val()) === Number(0) || isNaN($('#saldo').val())) {
						Swal.fire({
							icon: "error",
							title: "Oops...",
							text: "Ya no puede agregar detalles porque tiene saldo cero!!"

						});
					} else {
						adicionarItem();
					}
				} else {
					$('#boleta').focus();
					$('#boleta').select();
				}
			} else {
			
				Swal.fire({
					icon: "error",
					title: "Oops...",
					text: "Debe informar un banco!!"

				});
			}

		}
	});

	$('#boleta').keydown((e) => {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			if (Number($('#saldo').val()) === Number(0) || isNaN($('#saldo').val())) {
				Swal.fire({
					icon: "error",
					title: "Oops...",
					text: "Ya no puede agregar detalles porque tiene saldo cero!!"

				});
			} else if ($('#boleta').val().trim().length === 0) {
				Swal.fire({
					icon: "error",
					title: "Oops...",
					text: "¡Debe informar el número de boleta!"

				});
			} else {

				adicionarItem();
			}
		}
	})

	$('.js-moneda').change(buscarCotizacion);
	$('#moneda-vuelto').change(calcularCotizacionVuelto);

	function buscarCotizacion() {
		let moneda_origen = $('#moneda-cobro').val();
		let moneda_destino = $(this).val();
		let fecha = $('.js-fecha').val();
		let importe_ms = $('#importe-ms').val();
		let valor_cotizacion = 0;
		let importe_cotizado = 0;
		let cantidad_decimal = $('option:selected', this).data('decimales');
		


		if (moneda_origen != moneda_destino) {
			cotizacionQueries.cotizar(moneda_origen, moneda_destino, fecha).then(function(cotizacion) {
				if (cotizacion.id === null) {
					Swal.fire({
						icon: "error",
						title: "Oops...",
						text: "No existe cotizacion cargada!!",
					});
					$('#cotizacion').val(valor_cotizacion);
					$('#importe').val($.number(importe_ms, '16', ',', '.'));
					$('#importe-cobrado').val($.number(importe_ms, '16', ',', '.'));
					$('#moneda').val($('#moneda-cuenta').val());
					$('#moneda-vuelto').val($('#moneda-cobro').val());
				} else {
					valor_cotizacion = $.number(cotizacion.valor, '2', ',', '.');
					
					
					if (cotizacion.dividir === true) {
						importe_cotizado = Number(importe_ms) / Number(cotizacion.valor);
					} else {
						importe_cotizado = Number(importe_ms) * Number(cotizacion.valor);
					}
					
					$('#cotizacion').val(valor_cotizacion);
					$('#importe').val($.number(importe_cotizado, cantidad_decimales(importe_cotizado), ',', '.'));
					$('#importe-cobrado').val($.number(importe_cotizado, cantidad_decimales(importe_cotizado), ',', '.'));	
					$('#moneda-vuelto').val($('#moneda').val());

				}

			});
		} else {
			$('#cotizacion').val(valor_cotizacion);
			$('#importe').val($.number(importe_ms, '2', ',', '.'));
			$('#importe-cobrado').val($.number(importe_ms, '2', ',', '.'));

			$('#moneda-vuelto').val($('#moneda').val());
		}

	}

	function calcularVuelto() {

		let importe = $('#importe').val().replace(/\./g, "").replace(",", ".");
		let importe_cobrado = $('#importe-cobrado').val().replace(/\./g, "").replace(",", ".");
		let pre_vuelto = Number(importe_cobrado) - Number(importe);
		let vuelto = 0;
		vuelto = $.number(pre_vuelto, cantidad_decimales(pre_vuelto), ',', '.');
		$('#vuelto').val(vuelto);
		$('#vuelto-moneda-cobro').val(pre_vuelto);

		

	}

	function calcularCotizacionVuelto() {
		let moneda_origen = $('#moneda').val();
		let moneda_destino = $(this).val();
		let fecha = $('#fecha').val();
		let vuelto_mc = $('#vuelto-moneda-cobro').val();
		let valor_cotizacion = 0;
		let importe_cotizado = 0;
		if (moneda_origen != moneda_destino) {
			cotizacionQueries.cotizar(moneda_origen, moneda_destino, fecha).then(function(cotizacion) {

				if (cotizacion.id === null) {
					Swal.fire({
						icon: "error",
						title: "Oops...",
						text: "No existe cotizacion cargada para esta moneda!",
					});
					$('#moneda-vuelto').val($('#moneda').val());
					$('#cotizacion-vuelto').val(valor_cotizacion);
					$('#vuelto').val($.number(vuelto_mc, '16', ',', '.'));
				} else {
					valor_cotizacion = $.number(cotizacion.valor, 2, ',', '.');
					if (cotizacion.dividir === true) {
						importe_cotizado = Number(vuelto_mc) / Number(cotizacion.valor);
					} else {
						importe_cotizado = Number(vuelto_mc) * Number(cotizacion.valor);
					}
					$('#cotizacion-vuelto').val(valor_cotizacion);
					$('#vuelto').val($.number(importe_cotizado, cantidad_decimales(importe_cotizado), ',', '.'));
				
				}

			});
		} else {
			//colocar el mismo valor 
			$('#cotizacion-vuelto').val(valor_cotizacion);
			$('#vuelto').val($.number(vuelto_mc, cantidad_decimales(vuelto_mc), ',', '.'));
		}
	}

	function adicionarItem() {
		let cuenta = $('#cuenta').val();
		let condicion = $('#condicion').val();
		let moneda = $('#moneda').val()?$('#moneda').val():$('#moneda-cobro').val();
		let valor_cotizacion = $('#cotizacion').val();
		let importe = $('#importe').val();
		let importe_ms = $.number($('#importe-ms').val(),'2',',','.');
		let importe_cobrado = $('#importe-cobrado').val();
		let moneda_vuelto = $('#moneda-vuelto').val()?$('#moneda').val():$('#moneda-cobro').val();
		let cotizacion_vuelto = $('#cotizacion-vuelto').val();
		let vuelto = $('#vuelto').val();
		let vuelto_ms = $('#vuelto-moneda-cobro').val();
		let banco = $('#banco').val();
		let boleta = $('#boleta').val();
		let uuid = $('#uuid').val();
		

	
		if (verificarSaldo(importe_ms) === true) {
			Swal.fire({
				icon: "error",
				title: "Oops...",
				text: "Importe no puede ser mayor que el saldo!!!",
			});
		} else if (compareImporteToCobrado(importe, importe_cobrado) === true) {
			
			Swal.fire({
				icon: "error",
				title: "Oops...",
				text: "Importe cobrado no puede ser menor al importe!!!",
			});
		} else {
			$.ajax({
				url: '/cobros/js/adicionar/item/importe',
				method: 'POST',
				data: {
					cuenta: cuenta,
					condicion: condicion,
					moneda: moneda,
					valorContizacion: valor_cotizacion,
					importe: importe,
					importems: importe_ms,
					cobrado: importe_cobrado,
					monv: moneda_vuelto,
					cotizacionVuelto: cotizacion_vuelto,
					vuelto: vuelto,
					vueltoms: vuelto_ms,
					banco: banco,
					boleta: boleta,
					uuid: uuid
				},
				success: adicionarItemSuccess,
				error: function() {
					alert('Error al adicionar importe!!');
				}
			})
		};
	}

	function adicionarItemSuccess(html) {

		$('.js-item-cobro-importe').html(html);

		$('.js-tabla-item-importe .importe-item').each(function() {
			var contenido = $(this).text();
			var numeroFormateado = $.number(contenido, 2, ',', '.');
			$(this).text(numeroFormateado);
		});
		
		let total_cabecera = $('#total').val().replace(/\./g,'').replace(',','.');
		let total_cobrado = $('.js-tabla-item-importe').data('total') ? $('.js-tabla-item-importe').data('total') : 0;
		let saldo = 0;
		
		saldo = Number(total_cabecera) - Number(total_cobrado);
		$('#cobrado').val(total_cobrado);
		$('.cobrado-cobro').text($.number(total_cobrado, '2', ',', '.'));
		$('#saldo').val(saldo);
		$('.saldo-cobro').text($.number(saldo, '2', ',', '.'));

		$('.btn-item-quitar-importe').off('click').click(eliminarItem);

		colocarSaldo(saldo);
		if(isNaN($('#saldo').val())){
			$('#saldo').val(0);
		}
	}

	function colocarSaldo(saldo) {

		$('#importe-ms').val(saldo);
		$('#condicion').val($('#condicion-ms').val());
		$('#moneda').val($('#moneda-cobro').val());
		$('#moneda-vuelto').val($('#moneda-cobro').val());
		$('#cotizacion').val(0);
		$('#cotizacion-vuelto').val(0);
		$('#importe').val(saldo);
		$('#importe-cobrado').val(saldo);
		$('#condicion').focus();
		$('#banco').val('');
		$('#boleta').val('');
		$('#vuelto-moneda-cobro').val(0);
		$('#vuelto').val(0);
		cambioCondicion.cambiarTipoCondicion($('#condicion').val());
	}

	function eliminarItem() {
		let indice = $(this).data('indice');
		let uuid = $('#uuid').val();

		$.ajax({
			url: '/cobros/js/eliminar/item/importe',
			method: 'DELETE',
			data: { indice: indice, uuid: uuid },
			success: adicionarItemSuccess,
			error: function() {
				alert('Error eliminando item!');
			}
		})
	}

	function rutinaComisionTarjeta(comision) {
		//alert(`Generar comisión por tarjeta ?  este es el monto : ${comision}`);
		
		let importe = $('#importe').val().replace(/\./g, '').replace(',', '.');
		if (Number(importe) > 0) {
			$('#modal-comision-tarjeta').modal('show');
			$('#modal-comision-tarjeta').on('shown.bs.modal', function() {
				
				let valorComision = comision;
				let cabecera = `Se generará una comision del ${valorComision}%`;
				$('.descripcion-cabecera-comsision').text(cabecera);
				let total_comision = (Number(importe) * Number(valorComision) / 100)

				$('#importe-comision').val($.number(total_comision, cantidad_decimales(total_comision), ',', '.'));

				$('.btn-procesar-comision-tarjeta').click(insertarComisionTarjeta);
			})
		}

	}

	function insertarComisionTarjeta() {
		//let fecha =  moment(fechaHoySinFormateo()).format('DD/MM/YYYY');
		let fecha =  fechaHoySinFormateo();
		let importe = $('#importe-comision').val().replace(/\./g,'').replace(',','.');
		let comisionTarjeta = { fecha: fecha, importe: importe };
		

		$.ajax({
			url: '/comisionTarjetas/guardar',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(comisionTarjeta),
			success: insertSuccess,
			error: function(error) {
				alert('Error generando comision para tarjeta! ');
				console.log('error al generar comision tarjeta: ', error);
			},
			complete: function() {
				$('#modal-comision-tarjeta').modal('hide');
			}

		})
	}

	function insertSuccess(comision) {
		
		let comisionTarjeta = comision.id;
		let fecha =   moment(comision.fecha).format('DD/MM/YYYY');
		let importe = $.number(comision.importe,cantidad_decimales(comision.importe),',','.');
		let uuid = $('#uuid').val();
		$.ajax({
			url:'/cobros/js/adicionar/con/comision/tarjeta',
			method:'POST',
			data:{comisionTarjeta:comisionTarjeta,fecha:fecha,importe:importe,uuid:uuid},
			success:getItemCobroSuccess,
			error:function(error){
				alert('Error al insertar tarjeta en  detalle de documentos:');
				console.log('Error al insertar tarjeta en  detalle de documentos:',error);
			}
		})
	}

	function fechaHoySinFormateo() {
		let fechaActual = new Date();
		let dia = fechaActual.getDate();
		let mes = fechaActual.getMonth() + 1;
		let año = fechaActual.getFullYear();
		if (mes < 10) {
			mes = '0' + mes;
		}
		if (dia < 10) {
			dia = '0' + dia;
		}
		
		let fechaFormateada = año+'-'+mes+'-'+dia;
		return fechaFormateada;
	}
	
	


})
