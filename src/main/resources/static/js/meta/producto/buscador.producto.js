import { getAllProductos } from '/js/meta/producto/producto.queries.js';
import { mensaje } from '/js/meta/mensaje/mensaje.toast.service.js';
import { getStockByProducto } from '/js/meta/stockDeposito/stock.deposito.queries.js';
import {getAllPreciosByProducto} from '/js/meta/itemPrecio/item.precio.queries.js';

$(function() {
	window.onkeydown = peresionaTecla;
	var contador = -1;
	let $nombre = $('#nombre-producto-modal');
	let $modalProducto = $('#modal-producto');
	let $btnBuscar = $('#button-addon2');

	$nombre.keydown(nombreDown);
	$modalProducto.on('show.bs.modal', modalProductoShow);
	$modalProducto.on('hide.bs.modal',modalProductoHide);
	$btnBuscar.click(buscarPorBoton);


	$('input').click(function() {
		$(this).select();
	})

	function nombreDown(e) {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			let nombreCodigo = $(this).val();
			buscarProducto(nombreCodigo);
		}
	}

	function buscarPorBoton() {
		let nombreCodigo = $nombre.val();
		buscarProducto(nombreCodigo);
	}

	function modalProductoShow() {

		setTimeout(function() {
			$nombre.focus();
			contador = -1;
		}, 500);

		$modalProducto.off('keydown').keydown(manipulacionTabla);

	}

	function buscarProducto(nombreCodigo) {
		console.log('buscar por este nombre: ' + nombreCodigo);
		//mensaje('Error','Error papi','error',5000);
		getAllProductos(nombreCodigo).then(productoResults)
			.catch(function(xhr) {
				console.log('Error recuperando producto! ' + xhr.responseText);
				mensaje('Error recuperando producto!', xhr.responseText, 'error', 5000);
			})
	}

	function productoResults(productos) {
		contador = -1;
		if (productos.length === 0) {
			mensaje('Aviso', 'No se encontraron datos', 'info', 5000);
		}
		let listaProducto = [];
		productos.forEach(function(producto) {
			listaProducto.push(`<tr class="tr-buscador-producto cursor-tr">
									<td><input type="text" class="sin-borde anchura-input-corta " id="td-id-producto"
											value="${producto.id}"></td>
									<td>${producto.codigo}</td>
									<td>${producto.nombre}</td>
									<td><button class="btn btn-primary btn-ver-foto" title="Ver foto" 
										data-foto="${producto.foto}" data-nombre ="${producto.nombre}">
											<i class="bi bi-file-earmark-image"></i>
										</button>
									</td>
									<td><button class="btn btn-primary btn-ver-stock-precio" 
										title="Ver stock y precio" data-id="${producto.id}">
										<i class="bi bi-journal-text"></i>
										</button>
									</td>	
								</tr>`);
		})

		$('.body-tabla-producto').html(listaProducto);
		$('.tr-buscador-producto').click(clickTrBuscador);
		$('.btn-ver-foto').click(alertVerFoto);
		$('.btn-ver-stock-precio').click(alerPrecioStock)
	}

	function peresionaTecla(e) {
		if (e.keyCode === 120) {
			$modalProducto.modal('show');
		}
	}

	function manipulacionTabla(e) {
		//40 abajo 38 arriba
		if (e.keyCode === 40) {
			manipularDown();
		}
		if (e.keyCode === 38) {
			manipularUp();
		}

	}

	function manipularDown() {
		
		let tamaño = $('.body-tabla-producto tr').length;
		$('.body-tabla-producto tr').eq(contador).removeClass('table-primary');
		if(contador< tamaño){
			contador = contador + 1;
			console.log('contador: '+contador);
			$('.body-tabla-producto tr').eq(contador).find('#td-id-producto').focus().addClass('table-primary');
			$('.body-tabla-producto tr').eq(contador).addClass('table-primary');
			let producto = 	$('.body-tabla-producto tr').eq(contador).find('#td-id-producto').val();
			buscarStockDeposito(producto);
			buscarPrecioPorProducto(producto);
		}
		
	}

	function manipularUp() {
		$('.body-tabla-producto tr').eq(contador).removeClass('table-primary');
		if(contador>0){
			contador = contador - 1;
			console.log('contador: '+contador);
			$('.body-tabla-producto tr').eq(contador).find('#td-id-producto').focus();
			$('.body-tabla-producto tr').eq(contador).addClass('table-primary');
			let producto = 	$('.body-tabla-producto tr').eq(contador).find('#td-id-producto').val();	
			buscarStockDeposito(producto);
			buscarPrecioPorProducto(producto);
		}else{
			contador =  -1;
			$nombre.focus();
		}
		
	}
	
	function clickTrBuscador(){
		$('.body-tabla-producto tr').eq(contador).removeClass('table-primary');
		contador = $(this).index();
		$('.body-tabla-producto tr').eq(contador).find('#td-id-producto').focus();
		$('.body-tabla-producto tr').eq(contador).addClass('table-primary');
		let producto = 	$('.body-tabla-producto tr').eq(contador).find('#td-id-producto').val();	
		buscarStockDeposito(producto);
		buscarPrecioPorProducto(producto);	
	}
	
	function buscarStockDeposito(producto){
		getStockByProducto(producto).then(getAndFillStock)
		.catch(function(xhr){
			console.log('error recuperando stock: '+xhr.responseText);
			mensaje('Error','Error recuperando Stock','error',5000);
		})	
	}
	
	function buscarPrecioPorProducto(producto){
		getAllPreciosByProducto(producto).then(getAndFillPrecio)
		.catch(function(xhr){
			console.log('Error recuperando precios: '+xhr.responseText);
			mensaje('Error','Error recuperando precio!','error',5000);
		})
	}
	
	function getAndFillStock(stocks){
		
		let listaStock = [];
		stocks.forEach(function(stock){
			listaStock.push(`<tr>
								<td>${stock.deposito.nombre}</td>
								<td>${$.number(stock.cantidad,'0',',','.')}</td>
							 </tr>`)
		})
		$('.body-tabla-deposito').html(listaStock);
	}
	function getAndFillPrecio(precios){
		let listaPrecios = [];
		precios.forEach(function(item){
			listaPrecios.push(`<tr>
									<td>${item.precio.nombre}</td>
									<td>${$.number(item.precioProducto,'2',',','.')}</td>
								</tr>`);
		})
		
		$('.body-tabla-precio').html(listaPrecios);
	}
	
	function modalProductoHide(){
		
		$('.body-tabla-deposito').html('');
		$('.body-tabla-producto').html('');
		$nombre.val('');
		
	}
	
	function alertVerFoto(){
		let nombre = $(this).data('nombre');
		let foto = $(this).data('foto');
		
		Swal.fire({
			title: nombre,
			imageUrl: "/fotos/" + foto,
			imageWidth: 600,
			imageHeight: 400,
			imageAlt: "Custom image"
		});
	}
	
	function alerPrecioStock(){
		let producto = $(this).data('id');
		alertGetAndFillStock(producto);
	}
	
	function alertGetAndFillStock(producto) {
		getStockByProducto(producto).then(
			function(stocks) {
				fillStock(stocks, producto);
			}
		).catch(function(xhr){
			console.log('Error recuperando stock: '+xhr.textContent);
			mensaje('Error','Error recuperando stock!','error',5000);
		});
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
	function buscarPrecioProducto(contenido, producto) {
		getAllPreciosByProducto(producto).then(
			function(precios) {
				fillPrecios(precios, contenido);
			}
		).catch(function(error) {
			console.log('Error recuperando precios! ' + error.responseText);
			mensaje('Error','Error recuperando precio!','error',5000);
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
});