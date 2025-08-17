import { getAllProductos } from "/js/meta/producto/producto.queries.js";
import { tablaBuscadorProducto } from "/js/meta/producto/tabla.buscador.producto.js";
import { getStockByProducto } from '/js/meta/stockDeposito/stock.deposito.queries.js';
import { getAllPreciosByProducto } from '/js/meta/itemPrecio/item.precio.queries.js'
$(function() {

	$('#modal-buscar-producto').on('show.bs.modal', manipularAperturaModal);

	function manipularAperturaModal() {
		$('#btn-buscar-producto-modal').click(bucarProductos);
		setTimeout(function() {
			$('#nombre-producto-modal').focus();
		}, 500)

		$('#nombre-producto-modal').off('keydown').keydown(function(e) {
			if (e.keyCode === 13 || e.keyCode ===9) {
				bucarProductos();
			}
		})
	}

	function bucarProductos() {
		$('.contenedor-tabla-producto').addClass('noMostrar');
		$('.contenedor-precio-cantidad').addClass('noMostrar');
		$('.conten-cargando').removeClass('noMostrar');
		let nombreCodigo = $('#nombre-producto-modal').val();
		getAllProductos(nombreCodigo).then(
			function(productos) {
				rellenarTablaProductos(productos);
			}
		).catch(
			function(error) {
				alert('Error recuperando productos!');
				console.log('error recuperando productos: ', error);
			}
		).finally(
			function() {
				$('.contenedor-tabla-producto').removeClass('noMostrar');
				$('.contenedor-precio-cantidad').removeClass('noMostrar');
				$('.conten-cargando').addClass('noMostrar');
				$('.tabla-gen-producto').scrollTop(0);
				let indice = -1;
				tablaBuscadorProducto(indice);
			}
		);
	}


	function rellenarTablaProductos(productos) {

		let listaProductos = [];
		productos.forEach(function(producto) {
			listaProductos.push(`<tr class="tr-buscar-producto-modal cursor-tr">
									<td class="td-id-producto">
										<input type="text" class="js-input-id input-buscador" 
											value="${producto.id}"/>
									</td>
									<td>${producto.codigo}</td>
									<td class="td-nombre-producto">${producto.nombre}</td>
									<td>${producto.marca.nombre}</td>
									<td class="btn-ver-stock-precio-modal" data-id="${producto.id}"><a href="#" title="ver cantidad y precio"><i class="far fa-file-alt"></i> </a></td>
									<td class= "btn-ver-foto-modal" data-foto=${producto.foto}><a href="#" title="ver foto del producto"><i class="bi bi-file-image"></i> </a></td>
								 </tr>	`);
		});
		$('.tabla-lista-productos').html(listaProductos);
		$('.cursor-tr').off('dblclick').dblclick(adicionarProducto);
		$('.btn-ver-stock-precio-modal').click(mostrarPrecioCantidad);
		$('.btn-ver-foto-modal').click(mostrarFoto);
	}

	function adicionarProducto() {

		let nombre = $(this).find('.td-nombre-producto').text();
		let id = $(this).find('.js-input-id').val();

		$('#producto-nombre').val(nombre);
		$('#id-producto').val(id).change();
		$('#modal-buscar-producto').modal('hide');
	}

	function mostrarPrecioCantidad() {
		let producto = $(this).data('id');
		getAndFillStock(producto);

	}

	function mostrarFoto() {
		let foto = $(this).data('foto');

		let nombre = $(this).closest('tr').find('.td-nombre-producto').text();
		Swal.fire({
			title: nombre,
			imageUrl: "/fotos/" + foto,
			imageWidth: 600,
			imageHeight: 400,
			imageAlt: "Custom image"
		});
	}

	function getAndFillStock(producto) {
		getStockByProducto(producto).then(
			function(stocks) {
				fillStock(stocks, producto);
			}
		).catch(manipulacionErrorStock);
	}

	function fillStock(stocks, producto) {
		//console.log('Producto ' + producto);
		let listaStocks = [];
		let titulo;
		stocks.forEach(function(stock) {
			titulo = stock.producto.nombre;
			listaStocks.push(`<tr><td>${stock.deposito.nombre}</td><td>${$.number(stock.cantidad, '0', ',', '.')}</td></tr>`)
		});

		let contenido = `<table class="table">
											<thead>
												<tr>
													<th>Deposito</th>
													<th>Cantidad</th>
												</tr>
											</thead>
											<tbody>
												${listaStocks}
											</tbody>
									</table>`;

		buscarPrecioProducto(contenido, producto);

	}

	function manipulacionErrorStock(error) {
		alert('Error al recuperar Stock');
		console.log('Error al recuperar Stock: ', error);
	}

	function buscarPrecioProducto(contenido, producto) {
		getAllPreciosByProducto(producto).then(
			function(precios) {
				fillPrecios(precios, contenido);
			}
		).catch(function(error) {
			alert('Error recuperando precios!!');
			console.log('Error recuperando precios! ' + error);
		})
	}

	function fillPrecios(precios, contenido) {
		let listaPrecios = [];
		let titulo;
		precios.forEach(function(item) {
			titulo = item.producto.nombre;
			listaPrecios.push(`<tr><td>${item.precio.nombre}</td><td>${$.number(item.precioProducto, '2', ',', '.')}</td></tr>`);
		})
		let contenidoPrecio = `<table class="table">
											<thead>
												<tr>
													<th>Lista</th>
													<th>Precio</th>
												</tr>
											</thead>
											<tbody>
												${listaPrecios}
											</tbody>
									</table>`
		swal.fire({
			title: titulo,
			html: contenido + contenidoPrecio,
			confirmButtonText: 'Cerrar'
		});

	}


})