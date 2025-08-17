import { getProductoLotes } from '/js/meta/producto/producto.queries.js';
export function autoCompletar(e) {
	const $contenedorListaProducto = $('.contenedor-lista-producto');
	const $productoNombre = $('#producto-nombre');
	let productos = [];
	let productoIndex = -1;

	const inputValue = $(this).val().trim();
	if (inputValue.length >= 3) {
		getProductoLotes(inputValue)
			.then(actualizarListaProductos)
			.catch(mostrarError);
	} else {
		limpiarListaProductos();
	}

	function actualizarListaProductos(listaProductos) {
		productos = listaProductos;
		const listaHTML = listaProductos.map(producto =>
			`<div class="lista-producto">
                <p class="lista-producto-nombre" data-id="${producto.id}"'>${producto.nombre}</p>
                <span class="lista-producto-codigo-codigo"><strong>${producto.codigo}</strong></span>
            </div>`
		).join('');
		$contenedorListaProducto.html(listaHTML);
		productoIndex = -1;
	}


	function limpiarListaProductos() {
		productos = [];
		$contenedorListaProducto.empty();
		productoIndex = -1;
	}

	function mostrarError(xhr) {
		console.error('Error recuperando productos:', xhr.responseText);
		alert('Error recuperando productos!: '+xhr.responseText);
	}

	$productoNombre.on('keydown', function(e) {
		const $listaProductos = $('.lista-producto');
		const listaProductosCount = $listaProductos.length;

		if (e.keyCode === 40 || e.keyCode === 38 || e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			let nombre;
			let id;
			if (e.keyCode === 40) {
				productoIndex = Math.min(productoIndex + 1, listaProductosCount - 1);
				nombre = $listaProductos.eq(productoIndex).find('.lista-producto-nombre').text();
				id = $listaProductos.eq(productoIndex).find('.lista-producto-nombre').data('id');
				adicionarValorProducto(nombre, id);

			} else if (e.keyCode === 38) {
				productoIndex = Math.max(productoIndex - 1, -1);
				nombre = $listaProductos.eq(productoIndex).find('.lista-producto-nombre').text();
				id = $listaProductos.eq(productoIndex).find('.lista-producto-nombre').data('id');
				adicionarValorProducto(nombre, id);
			} else if (e.keyCode === 13 || e.keyCode === 9) {
				limpiarListaProductos();
				setTimeout(function(){$('.js-input-pos-ac').focus();},200);
				
			}
			$listaProductos.removeClass('pintar-linea').eq(productoIndex).addClass('pintar-linea');

			// Manejo del desplazamiento del scroll
			const $contenedorListaProducto = $('.contenedor-lista-producto');
			const alturaProducto = $listaProductos.eq(productoIndex).outerHeight(true);
			const scrollTop = $contenedorListaProducto.scrollTop();
			const contenedorHeight = $contenedorListaProducto.height();
			const scrollBottom = scrollTop + contenedorHeight;

			if (e.keyCode === 40) { // Flecha abajo
				if (scrollBottom < alturaProducto * (productoIndex + 1)) {
					$contenedorListaProducto.scrollTop(scrollTop + alturaProducto);
				}
			} else if (e.keyCode === 38) { // Flecha arriba
				if (scrollTop > alturaProducto * productoIndex) {
					$contenedorListaProducto.scrollTop(scrollTop - alturaProducto);
				}
			}
		}
	});

	function adicionarValorProducto(nombre, id) {
		$('.js-ac-id-producto').val(id);
		$('.js-ac-nombre-producto').val(nombre);
	}

}