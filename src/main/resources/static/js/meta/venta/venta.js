import { getCotizacionCurDate } from '/js/meta/cotizacion/cotizacion.queries.js';
import { getParametro } from '/js/meta/parametro/parametro.queries.js';
import { impresionVentaTermicaVentaSiete } from '/js/meta/impresion/impresion.venta.termica.siete.js';
import { manipularLote, verificarSiEsLoteDetalle } from '/js/meta/venta/item.venta.lote.js';
import { getCantidadByProductoDeposito } from '/js/meta/stockDeposito/stock.deposito.queries.js';
import { mensajeError, mensajeExito,mensajeAviso } from '/js/meta/mensaje/mensaje.service.js';
import { getItemLoteVentaByProducto } from '/js/meta/lote/lote.queries.js';
$(function() {

	var precioProducto = $('#precio-producto');
	var UUID = $('#uuid').val();
	$('.total-cabecera').text($.number($('.total-cabecera').text(), '2', ',', '.'));
	manipularLote();
	//mascaras
	$('.date').mask('00/00/0000');
	//$('.money').mask("#.##0", { reverse: true });

	if ($('.total-cabecera').text().replace(/\./g, '').replace(',', '.') > Number(0)) {
		$('#precio').addClass('solo-lectura');
	}

	//capturar la tecla presionada
	window.onkeydown = presionaTecla;

	llegoAdicionarItem();



	function presionaTecla(e) {

		//quitar el comportamiento por defecto del enter
		if (e.keyCode === 13) {
			e.preventDefault();
		}

	}

	precioProducto.keydown((e) => {
		if (e.keyCode === 13 || e.keyCode === 9) {
			let precio_minimo = $('.js-precio-minimo-producto').text().replace(/\./g, '').replace(',', '.');
			let precio = precioProducto.val().replace(/\./g, '').replace(',', '.');
			let producto = $('#idProducto').val();
			let uuid = $('#uuid').val();
			let cantidadItem = 0;
			let isLote = $('#idProducto').data('lote');
			if (isLote === true) {
				getItemLoteVentaByProducto(producto, uuid).then(function(items) {
					//console.log('items retornado desde venta: ' + items);
					cantidadItem = items.length;
					console.log('items retornado desde venta: ' + cantidadItem);
					if (cantidadItem === 0) {
						
						mensajeError(`Debe informar la cantidad de lote!`);
					} else {
						otrasValidaciones()
					}
				}).catch(function(xhr) {
					mensajeError(xhr.responseText);
				})
			} else {
				otrasValidaciones()
			}


		}
	});

	function otrasValidaciones() {
		let precio_minimo = $('.js-precio-minimo-producto').text().replace(/\./g, '').replace(',', '.');
		let precio = precioProducto.val().replace(/\./g, '').replace(',', '.');
		if (((Number(precio_minimo) > Number(precio)) || precioProducto === '')
			&& (Number($('.item-autorizado-venta').val()) != Number(1)) && ($('#vendedor option:selected').data('supervisor') === false)) {

			Swal.fire({
				icon: "error",
				title: "Oops...",
				text: "No puede vender por debajo del precio minimo!",

			});
		} else if ($('#cantidad-producto').val() === '') {
			$('#cantidad-producto').focus();
		} else {
			adicionarItem();
			producto.focus();
		}
	}

	$('.js-boton-cargar-detalle').click(() => {


		let producto = $('#idProducto').val();
		let uuid = $('#uuid').val();
		let cantidadItem = 0;

		getItemLoteVentaByProducto(producto, uuid).then(function(items) {
			//console.log('items retornado desde venta: ' + items);
			cantidadItem = items.length;
			console.log('items retornado desde venta: ' + cantidadItem);
			if (cantidadItem === 0) {
				console.log('reviro');
				mensajeError(`Debe informar la cantidad de lote!`);
			} else {
				otrasValidaciones()
			}
		}).catch(function(xhr) {
			mensajeError(xhr.responseText);
		})
		/*	let precio_minimo = $('.js-precio-minimo-producto').text().replace(/\./g, '').replace(',', '.');
			let precio = precioProducto.val().replace(/\./g, '').replace(',', '.');
			if (((Number(precio_minimo) > Number(precio)) || precioProducto === '')
				&& (Number($('.item-autorizado-venta').val()) != Number(1)) && ($('#vendedor option:selected').data('supervisor') === false)) {
	
				Swal.fire({
					icon: "error",
					title: "Oops...",
					text: "No puede vender por debajo del precio minimo!",
	
				});
			} else {
				adicionarItem();
				producto.focus();
			}*/
	});

	window.adicionarItem = function() {
		let monedaDestino = $('#moneda-venta').val();
		let fecha = $('#fecha').val();
		let cantidadActual = $('#cantidad-producto').val().replace(/\./g, '').replace(',', '.');;

		//consultar si hay stock e informar
		getCantidadByProductoDeposito($('#idProducto').val(), $('#deposito').val()).then(function(cantidad) {

			if (Number(cantidadActual) > Number(cantidad)) {
				mensajeAviso(`La cantidad informada supera a la cantidad de stock existente !`);
			}
		}).catch(function(xhr) {
			mensajeError(xhr.responseText);
		})
		//fin de subrutina para consultar stock
		$.ajax({
			url: '/ventas/item/adicionar',
			method: 'POST',
			data: {
				monedaDestino: monedaDestino,
				fecha: fecha,
				producto: $('#idProducto').val(),
				precio: $('#precio-producto').val(),
				cantidad: $('#cantidad-producto').val(),
				uuid: $('#uuid').val()

			},
			success: llegoAdicionarItem,
			error: function() {
				alert('Error al agregar iten de venta!!');
			},
			complete: function() {
				$('#idProducto').val('');
				$('#producto').val('');
				$('#cantidad-producto').val('');
				$('#precio-producto').val('');
				$('.js-precio-minimo-producto').text('');
				$('.item-autorizado-venta').val('');
				$('.precio-lista-producto').val('');
			}
		});


	}

	function llegoAdicionarItem(html) {

		$('.js-item-venta').html(html);
		//$('.money').mask("#.##0", { reverse: true });
		var tablaItem = $('.js-tabla-item').data('total');
		if (tablaItem > 0) {
			$('.total-cabecera').text($.number(tablaItem, '2', ',', '.'));
			$('#precio').addClass('solo-lectura');
		} else {

			$('.total-cabecera').text($.number(0, '2', ',', '.'));
			$('#precio').removeClass('solo-lectura').off('mousedown');
		}
		var cantidad = $('.item-venta-cantidad');
		var precio = $('.item-venta-precio');
		var quitar = $('.btn-item-quitar');

		$('.solo-lectura').on('mousedown', function(event) {
			event.preventDefault();
			this.blur();
		});

		$('.js-tabla-item').each(function() {

			if ($(this).children().length > 0) {
				$('#precio').addClass('solo-lectura');
			}
			let precio = $(this).find('.item-venta-precio').text();
			let subTotal = $(this).find('.item-venta-sub-total').text();
			let cantidad = $(this).find('.item-venta-cantidad').val();
			$(this).find('.item-venta-precio').text($.number(precio, '2', ',', '.'));
			$(this).find('.item-venta-sub-total').text($.number(subTotal, '2', ',', '.'));
			$(this).find('.item-venta-cantidad').val($.number(cantidad, '0', ',', '.'));
		})

		//inicio de actividad en cantidad detalle
		cantidad.click((e) => {
			var estePresion = $(e.target);

			if ($(e.target).data('pesable')) {
				$(e.target).addClass('money2');
				$('.money2').mask("#.##0,000", { reverse: true });
			} else {
				$(e.target).addClass('money');
				$('.money').mask("#.##0", { reverse: true });
			}

			estePresion.select();

		});

		$('.item-venta-cantidad').keydown(function(eve) {
			if (eve.keyCode === 13 || eve.keyCode === 9) {
				let producto = $(this).data('id');
				let cantidadActual = $(this).val().replace(/\./g, '').replace(',', '.');;
				let deposito = $('#deposito').val();
				getCantidadByProductoDeposito(producto, deposito).then(function(cantidad) {

					if (Number(cantidadActual) > Number(cantidad)) {
						mensajeAviso(`La cantidad informada supera a la cantidad de stock existente !`);
					}
				}).catch(function(xhr) {
					mensajeError(xhr.responseText);
				})

				modificarCantidad($(this).data('id'), $(this).val(), $(this).data('lote'));
			}
		});
		$('.item-venta-cantidad').change(function() {

			let producto = $(this).data('id');
			let cantidadActual = $(this).val().replace(/\./g, '').replace(',', '.');;
			let deposito = $('#deposito').val();
			getCantidadByProductoDeposito(producto, deposito).then(function(cantidad) {

				if (Number(cantidadActual) > Number(cantidad)) {
					mensajeAviso(`La cantidad informada supera a la cantidad de stock existente !`);
				}
			}).catch(function(xhr) {
				mensajeError(xhr.responseText);
			})

			modificarCantidad($(this).data('id'), $(this).val(), $(this).data('lote'))
		});


		//fin de actividad en cantidad detalle
		function modificarCantidad(id, cantidad, lote) {
			verificarSiEsLoteDetalle(id, $('#uuid').val(), lote);
			$.ajax({
				url: '/ventas/item/modificar/cantidad',
				method: 'PUT',
				data: {
					producto: id,
					cantidad: cantidad,
					uuid: $('#uuid').val()
				},
				success: llegoAdicionarItem,
				error: function() {
					alert('Error modificando item!');
				}
			});
		}
		//inicio de actividad en precio detalle
		precio.click((e) => {
			var estePrecio = $(e.target);
			estePrecio.select();
			estePrecio.keydown((eve) => {
				if (eve.keyCode === 13) {

					var respuestaModificarPrecio = $.ajax({
						url: '/ventas/item/modificar/precio',
						method: 'PUT',
						data: {
							producto: estePrecio.data('id'),
							precio: estePrecio.val(),
							uuid: $('#uuid').val()
						}
					});
					respuestaModificarPrecio.done(llegoAdicionarItem);
				}
			})
		})

		//fin de actividad en precio detalle

		//inicio de actividada en boton quitar detalle
		quitar.click((e) => {
			var quitarEste = e.currentTarget;
			var respuestaEliminar = $.ajax({
				url: '/ventas/item/eliminar',
				method: 'DELETE',
				data: {
					producto: quitarEste.dataset.id,
					uuid: $('#uuid').val()
				},

			});
			respuestaEliminar.done(llegoAdicionarItem);
		})
		//fin de actividad en boton quitar detalle
	}

	//verifica si hay cotizaciones cargadas 

	getParametro().then(
		function(parametro) {
			if (parametro.cotizacionAutomatica == false) {
				verificarParametroVenta();
			}
		}
	).catch(
		function(error) {
			alert('Error recuperando parametro!!');
			console.log('Error recuperando parametro: ', error);
		}
	);

	function verificarParametroVenta() {
		getCotizacionCurDate().then(
			function(cotizaciones) {
				subRutinaParaBloqueoCotizacion(cotizaciones);
			}
		).catch(
			function(error) {
				console.log('Error recuperando cotizaciones!!', error);
				alert('Error recuperando cotizaciones!!');
			}
		)
	}



	function subRutinaParaBloqueoCotizacion(cotizaciones) {
		if (cotizaciones.length > Number(0)) {
			$('#modal-cotizacion-bloqueo').modal('show');
			let listaCotizaciones = [];
			cotizaciones.forEach(function(cotizacion) {
				listaCotizaciones.push(`<tr>
																<td>${cotizacion.monedaOrigen.nombre}</td>
																<td>${cotizacion.monedaDestino.nombre}</td>
																<td>${$.number(cotizacion.valor, '2', ',', '.')}</td>
														  </tr>`)
			})
			$('.js-item-cotizaciones').html(listaCotizaciones);
		}
	}

	if ($('#registro').val()) {
		impresionVentaTermicaVentaSiete($('#registro').val());
		/*Swal.fire({
			title: 'Desea imprmir?',
			text: "Se mandará una impresion de la venta!",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Si, imprimir!'
		}).then((result) => {
			if (result.isConfirmed) {
				//imprimirNotaComun($('#registro').val());
				impresionVentaTermicaVentaSiete($('#registro').val());
			}
		})*/
	}
	//===================PARA QE LOS SELECT SEAN DE SOLO LECTURA==============================
	$('.solo-lectura').on('mousedown', function(event) {
		event.preventDefault();
		this.blur();
	});
	//====================================FIN PARA QE LOS SELECT SEAN DE SOLO LECTURA====================================================
	//===================CAMBIO EN EL COMBO PRECIO================================
	$('#precio').change(cambiarPrecioDetalle);

	function cambiarPrecioDetalle() {

		$('#moneda-venta').val($(this).find('option:selected').data('moneda'));
		let monedaDestino = $(this).find('option:selected').data('moneda');
		let fecha = $('#fecha').val();
		let precio = $(this).val();
		let uuid = $('#uuid').val();

		$.ajax({
			url: '/ventas/cambiar/precio',
			method: 'PUT',
			data: {
				monedaDestino: monedaDestino,
				fecha: fecha,
				precio: precio,
				uuid: uuid
			},
			success: llegoAdicionarItem,
			error: function(error) {
				console.log('Error al modificar precio'), error;
				alert('Error al modificar precio');
			}
		})
	}

	//===================FIN CAMBIO EN EL COMBO PRECIO================================
})