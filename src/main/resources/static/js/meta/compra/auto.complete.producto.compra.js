import {formatearNumeroInput} from '/js/meta/utilidades.js';
$(function() {
	console.log('auto complete compra....');
	//inicio
	var numeroFila = 0;
	var producto = $('#producto');
	var idProducto = $('#idProducto');
	var cantidadProducto = $('#cantidad');
	var precioProducto = $('#precio');



	var cantidad = $('#cantidad');
	var precio = $('#precio');
	var precioVenta = $('#precioVenta');
	var uuid = $('#uuid').val();
	var total = $('.js-total');
	var idCompra = $('.idCompra').val();
	var mostrarPrecio;
	window.verificarParametroPrecio = function(mostrar) {
		if (mostrar) {
			mostrarPrecio = mostrar;
		} else {
			mostrarPrecio = false;
		}
	}
	producto.keyup(precionaTeclaProducto);
	llegoRespuesta();

	function precionaTeclaProducto(tecla) {

		if (producto.val().length >= 2 && tecla.keyCode != 38 && tecla.keyCode != 40 && tecla.keyCode != 13) {
			var listaPrecios = [];
			console.log('consultar aqui: /productos/buscarPorNombreOCodigo');
			var buscarPrecio = $.ajax({
				url: '/productos/buscarPorNombreOCodigo',
				method: 'GET',
				data: {
					nombreCodigo: producto.val()
				}

			})
			buscarPrecio.done(retornoPrecio);


			function retornoPrecio(productos) {
				numeroFila = 0;
				productos.forEach(function(producto) {
					if (producto.foto === "") {
						producto.foto = "sinfoto.png"
					}
					listaPrecios.push(
						`
					  <div class="contenedor-auto-complete-producto row">
						   <img src="/fotos/thumbnail.${producto.foto}" class="col-sm-3 imagen-producto-auto-complete"/>	                            		
	                		<div class="col-sm-9">
	                    		<div class="bloque-aparte">
	                    			<input type="hidden" value=${producto.id}>
	                    			<span class="fondo-negro-span ">${producto.codigo}</spa>
	                    		</div>
	                    		<input type="hidden" value="${producto.nombre}">
	                    		<span class= "bloque-aparte">${producto.nombre}</span>
	                    		<input type="hidden" value="${producto.pesable}"> 
	                		</div>
                		</div>
                		`);
				})
				$('.lista-auto-complete-producto').html(listaPrecios);
				//$('.contenedor-auto-complete-producto').eq(numeroFila).addClass('highlight')

				$('.contenedor-auto-complete-producto').click(clickContenedor);
				function clickContenedor(evento) {
					
					idProducto.val(evento.currentTarget.children[1].children[0].children[0].value);
					producto.val(evento.currentTarget.children[1].children[1].value);
					$('#idProducto').trigger('input');
					cantidadProducto.focus();
					$('.lista-auto-complete-producto').html("");

				}

			}


		} else if (tecla.keyCode != 40 && tecla.keyCode != 38) {
			$('.lista-auto-complete-producto').html("");
		}

		//para manejar con flechas el auto complete 
		//fecha de abajo
		if (tecla.keyCode === 40) {

			if (numeroFila === 0) {

				$('.contenedor-auto-complete-producto').removeClass('highlight');
				$('.contenedor-auto-complete-producto').eq(numeroFila).addClass('highlight');

				producto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1].children[1].value);
				
			
				idProducto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1]
					.children[0].children[0].value);
				$('#idProducto').trigger('input');

			}
			if (numeroFila < $('.contenedor-auto-complete-producto').length - 1) {
				numeroFila++;
				$('.contenedor-auto-complete-producto').removeClass('highlight');
				$('.contenedor-auto-complete-producto').eq(numeroFila).addClass('highlight');
				
				
				producto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1]
					.children[1].value);
				idProducto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1]
					.children[0].children[0].value);
				$('#idProducto').trigger('input');

			}

		}
		//fecla de arriba
		if (tecla.keyCode === 38) {
			if (numeroFila > 0) {
				numeroFila--;
				$('.contenedor-auto-complete-producto').removeClass('highlight');
				$('.contenedor-auto-complete-producto').eq(numeroFila).addClass('highlight');
				var esPesable = $('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1].children[3].value;
			
				producto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1]
					.children[1].value)
				idProducto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1]
					.children[0].children[0].value);
				$('#idProducto').trigger('input');

			}


		}
		if (tecla.keyCode === 13) {

			if (producto.val() > 0) {



				var buscarPrecioCodigo = $.ajax({
					url: '/productos/js/' + producto.val(),
					method: 'GET'
				});
				buscarPrecioCodigo.done(llegoPrecioCodigo);


				function llegoPrecioCodigo(productos) {

					if (productos.id != null) {
						idProducto.val(productos.id);
						producto.val(productos.nombre);
						$('#idProducto').trigger('input');

					
						cantidadProducto.val(1)
						cantidadProducto.select();
						cantidadProducto.focus();
					} else {
						producto.focus();
						producto.select();
					}

				}
			} else {
				producto.focus();
			}

			if (idProducto.val() > 0) {
				$('#idProducto').trigger('input');
				cantidadProducto.focus();
				$('.lista-auto-complete-producto').html("");
			}
		}

	}


	//========================INICIO DE CARGAR DETALLE=========================

	precio.keydown((e) => {
		if (e.keyCode === 13 || e.keyCode === 9) {
			if (precio.val() === '') {
				precio.val(0);
			}
			if (!$('.contenedor-precio-venta-compra').hasClass('noMostrar')) {
				$('#precioVenta').focus();
			} else {

				if (precioVenta.val() === '') {
					precioVenta.val(0);
				}
				
				$.ajax({
					url: '/compras/item',
					method: 'POST',
					data: {
						id: idProducto.val(),
						cantidad: cantidad.val(),
						precio: precio.val(),
						precioVenta: precioVenta.val(),
						uuid: uuid
					},
					success: llegoRespuesta,
					error: function(error) {
						alert(`Error al adicionar detalle de compra!:${error}`);
					}
				})

				limpiarTodo();
			}
		}
	});


	$('#precioVenta').keydown((e) => {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			if ($('#precioVenta').val() === '') {
				$('#precioVenta').val(0);
			}
			$.ajax({
				url: '/compras/item',
				method: 'POST',
				data: {
					id: idProducto.val(),
					cantidad: cantidad.val(),
					precio: precio.val(),
					precioVenta: precioVenta.val(),
					uuid: uuid
				},
				success: llegoRespuesta,
				error: function(error) {
					alert(`Error al adicionar detalle de compra!:${error}`);
				}
			})

			limpiarTodo();
		}
	})

	function limpiarTodo() {
		producto.val('');
		cantidad.val('');
		precio.val('');
		idProducto.val('');
		$('#precioVenta').val('');
	}

	function llegoRespuesta(html) {

		$('.js-item-compra').html(html);
		formatearNumeroInput($('.formato-numero'));
		$('input').click(function(){
			$(this).select();
		})
		$('.js-tabla-item').each(function(){
			let pesable = $(this).find('.item-cantidad-js').data('pesable');
			let cantidad = $(this).find('.item-cantidad-js').val();
			let precio = $(this).find('.item-precio-js').val();
			let precio_venta = $(this).find('.item-precioVenta-js').val();
			let sub_total_item = $(this).find('.js-sub-total-compra').val();
			
			$(this).find('.item-precio-js').val($.number(precio,'2',',','.'));
			$(this).find('.item-precioVenta-js').val($.number(precio_venta,'2',',','.'));
			$(this).find('.js-sub-total-compra').val($.number(sub_total_item,'2',',','.'));
			
			if(pesable === true ){
				$(this).find('.item-cantidad-js').val($.number(cantidad,'2',',','.'));
			}else{
				$(this).find('.item-cantidad-js').val($.number(cantidad,'0',',','.'));
			}
		})
		
		if (mostrarPrecio === true) {
			$('.td-precio-venta-compra').removeClass('noMostrar');
		}
		producto.focus();
		var tablaItem = $('.js-tabla-item').data('total');
		if (tablaItem > 0) {
			$('.total-cabecera').text($.number(tablaItem, 2, ',', '.'));
		}
		

		var itemCantidad = $('.item-cantidad-js');
		var itemPrecio = $('.item-precio-js');
		var itemPrecioVenta = $('.item-precioVenta-js');
		var eliminar = $('.btn-item-quitar');
		var tablaItem = $('.js-tabla-item');
		var valorTotal = tablaItem.data('total') == null ? 0 : tablaItem.data('total');
		var totalFormateado = $.number(valorTotal, '2', ',', '.');
		total.text(totalFormateado);
		eliminar.click(eliminarItem);
		itemCantidad.change(cambioItemCantidad);
		itemPrecio.change(cambioItemPrecio);

		itemCantidad.keydown((e) => {
			if (e.keyCode === 13 || e.keyCode === 9) {
				e.preventDefault();
				cambioItemCantidad(e);
			}
		});

		itemPrecio.keydown((e) => {
			if (e.keyCode === 13 || e.keyCode === 9) {
				e.preventDefault();
				return cambioItemPrecio(e);

			}
		})

		itemPrecioVenta.keydown((e) => {
			if (e.keyCode === 13 || e.keyCode === 9) {
				e.preventDefault();
				return cambioItemPrecioVenta(e);

			}
		})

		

		itemPrecio.click((e) => {
			let estePrecio = $(e.currentTarget)
			estePrecio.select();
		})
		itemPrecioVenta.click((e) => {
			let estePrecio = $(e.currentTarget)
			estePrecio.select();
		})
	}


	function cambioItemCantidad(e) {
		var itemId = $(e.target).data('id');
		var itemCantidad = $(e.target).val();
		producto.focus();
		var respuesta = $.ajax({
			url: '/compras/item/modificarCantidad/' + itemId,
			method: 'PUT',
			data: { cantidad: itemCantidad, uuid: uuid }
		});
		respuesta.done(llegoRespuesta);
	}

	function cambioItemPrecio(e) {
		var itemId = $(e.target).data('id');
		var itemPrecio = $(e.target).val();
		producto.focus();
		var respuesta = $.ajax({
			url: '/compras/item/modificarPrecio/' + itemId,
			method: 'PUT',
			data: { precio: itemPrecio, uuid: uuid }
		});

		respuesta.done(llegoRespuesta);
	}

	function cambioItemPrecioVenta(e) {
		let producto = $(e.target).data('id');
		var itemPrecio = $(e.target).val();
		$('#producto').focus();
		var respuesta = $.ajax({
			url: '/compras/item/modificarPrecioVenta',
			method: 'PUT',
			data: { producto: producto, precioVenta: itemPrecio, uuid: uuid }
		});

		respuesta.done(llegoRespuesta);
	}

	function eliminarItem(event) {
		var presion = event.currentTarget;
		var respuesta = $.ajax({
			url: '/compras/item/eliminar/' + uuid + '/' + presion.dataset.id,
			method: 'DELETE'
		});
		respuesta.done(llegoRespuesta);
	}

	$('.js-boton-cargar-detalle').off('click').click(agregaConBoton);

	function agregaConBoton() {
		
		console.log('agregar estos valores: ',
		'\nid: ',idProducto.val(),'\ncantidad: ',cantidad.val(),'\nprecio: ',precio.val(),'\nuuid: ',uuid);
		if(precioVenta.val().trim().length===0){
			precioVenta.val(0)
		}
		$.ajax({
			url: '/compras/item',
			method: 'POST',
			data: {
				id: idProducto.val(),
				cantidad: cantidad.val(),
				precio: precio.val(),
				precioVenta: precioVenta.val(),
				uuid: uuid
			},
			success: llegoRespuesta,
			error: function() {
				alert('Error agregando detalle!');
			},
			complete: limpiarTodo
		})

	}


	//fin en carga de detalle
	//inicio de agregar precio de venta
	$('#idProducto').on('input', buscarPrecioVenta);

	function buscarPrecioVenta() {

		let producto = $('#idProducto').val();
		let precio = $('#com-precio-venta').val();

		$.ajax({
			url: '/precios/js/precioProducto',
			method: 'GET',
			data: { precio: precio, producto: producto },
			success: getPrecioVentaSuccess,
			error: function(error) {
				alert(`error al recuperar precio de venta: ${error}`);
			}
		})

		function getPrecioVentaSuccess(data) {

			let precio_venta = $.number(data, '0', ',', '.');
			$('#precioVenta').val(precio_venta);
		}
	}
	//fin de agregar precio de venta

	//fin	
});