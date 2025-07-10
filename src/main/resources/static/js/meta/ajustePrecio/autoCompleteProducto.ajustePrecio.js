import { formatearNumeroInput } from '/js/meta/utilidades.js';
$(function() {

	var idProducto = $('#idProducto');
	var producto = $('#producto');
	var costo = $('#costo');
	var precioMinimo = $('#precioMinimo');
	var precio = $('#precio');
	var uuid = $('#uuid').val();
	var idPrecio;
	var htmlTemplateAutocomplete = $('#template-autocomplete-cerveja').html();
	var template = Handlebars.compile(htmlTemplateAutocomplete);
	idPrecio = $('.js-select-precio').val();
	
	$('.js-select-precio').change(() => {
		idPrecio = $('.js-select-precio').val();
		
	});
	
	let fecha = $('#fecha').val();
	var options = {
		url: function(nombreCodigo) {
			// Aquí construyes tu URL con los parámetros adicionales
			return "/productos/js/productosCotizados?monedaDestino=" + $('#nombrePrecio option:selected').data('moneda') + "&fecha=" + fecha + "&nombreCodigo=" + nombreCodigo;
		},
		getValue: "nombre",
		minCharNumber: 2,
		requestDelay: 200,
		ajaxSettings: { contentType: 'application/json' },
		template: {
			type: 'custom',
			method: (nombre, producto) => {
				return template(producto);
			}
		},
		list: {
			onChooseEvent: seleccionaItem
		}
	};

	producto.easyAutocomplete(options);

	function seleccionaItem() {
		producto.getSelectedItemData();
		precioMinimo.focus();
		//console.log('Este es el costo del producto: ',$.number(producto.getSelectedItemData().costo, '2', ',', '.'));
		idProducto.val(producto.getSelectedItemData().id);
		costo.val($.number(producto.getSelectedItemData().costo, '2', ',', '.'));
		buscarPrecioPorId(idPrecio, idProducto.val());

	};

	precioMinimo.keydown((e) => {
		if (e.keyCode === 13) {
			e.preventDefault();
			precio.focus();
			precio.select();


		}
	});

	precio.keydown((e) => {
		if (e.keyCode === 9) {
			e.preventDefault();
		}
		if (e.keyCode === 13) {
			e.preventDefault();
			//console.log('valor de precio: ',precio.val());
			if (precio.val().trim() === '' || precio.val() === '0') {
				alert('Precio no puede ser cero!!');
			} else{
				var respuesta = $.ajax({
					url: '/ajustePrecios/item',
					method: 'POST',
					data: {
						id: idProducto.val(),
						costo: costo.val(),
						precioMinimo: precioMinimo.val(),
						precioProducto: precio.val(),
						uuid: uuid
					}
				})

			respuesta.done(llegoRespuesta);
			producto.val('');
		}

	}
});

function limpiar() {
	idProducto.val('');
	producto.val('');
	costo.val('');
	precioMinimo.val('');
	precio.val('');
};

function llegoRespuesta(html) {

	limpiar();
	$('.js-item-precio').html(html);
	$('.foto-img-td').click((e) => {
		let foto = e.currentTarget.dataset.foto;
		let codigo = e.currentTarget.dataset.codigo;
		let nombre = e.currentTarget.parentNode.children[2].textContent;
		if (!foto) {
			foto = 'sinfoto.png';
		}
		Swal.fire({
			title: codigo,
			text: nombre,
			imageUrl: '/fotos/' + foto,

			imageAlt: 'Custom image',
		})
	})

	$('.js-tabla-item').each(function() {
		let costo = $(this).find('.item-cantidad-js').val();
		let utilidad = $(this).find('.item-utilidad-js').val();
		let precioMinimo = $(this).find('.item-precioMinimo-js').val();
		let precio = $(this).find('.item-precioProducto-js').val();
		$(this).find('.item-cantidad-js').val($.number(costo, '2', ',', '.'));
		$(this).find('.item-precioMinimo-js').val($.number(precioMinimo, '2', ',', '.'));
		$(this).find('.item-precioProducto-js').val($.number(precio, '2', ',', '.'));
		$(this).find('.item-utilidad-js').val($.number(utilidad, '2', ',', '.'));
	})
	formatearNumeroInput($('.formato-numero'));
	var precioMinimo = $('.item-precioMinimo-js');
	var precioProducto = $('.item-precioProducto-js');
	var btnQuitar = $('.btn-item-quitar');
	//$('.money').mask("#.##0", {reverse: true});
	precioMinimo.change(modificarPrecioMinimo);
	precioProducto.change(modificarPrecioProducto);
	precioMinimo.keydown((e) => {
		if (e.keyCode === 13) {
			e.preventDefault();
			modificarPrecioMinimo(e);
		}
	});
	precioProducto.keydown((e) => {
		if (e.keyCode === 13) {
			e.preventDefault();
			modificarPrecioProducto(e);
		}
	});

	precioMinimo.click((e) => {
		var presionado = $(e.currentTarget);
		presionado.select();
	});
	precioProducto.click((e) => {
		var presionado = $(e.currentTarget);
		presionado.select();
	});

	btnQuitar.click(quitarItem);
};

function modificarPrecioMinimo(e) {
	var presionado = $(e.currentTarget);
	var respuesta = $.ajax({
		url: '/ajustePrecios/item/modificarPrecioMinimo',
		method: 'PUT',
		data: { id: presionado.data('id'), precioMinimo: presionado.val(), uuid: uuid }
	});

	respuesta.done(llegoRespuesta);
};

function modificarPrecioProducto(e) {
	var presionado = $(e.currentTarget);
	var respuesta = $.ajax({
		url: '/ajustePrecios/item/modificarPrecioProducto',
		method: 'PUT',
		data: { id: presionado.data('id'), precioProducto: presionado.val(), uuid: uuid }
	});

	respuesta.done(llegoRespuesta);
}

function quitarItem(e) {
	var presionado = $(e.currentTarget);

	var respuesta = $.ajax({
		url: '/ajustePrecios/item/eliminar/' + uuid + '/' + presionado.data('id'),
		method: 'DELETE'
	});

	respuesta.done(llegoRespuesta);
}

/*buscar precio por id de producto*/
function buscarPrecioPorId(idPrecio, idProducto) {

	var retornoPrecio = $.ajax({
		url: '/precios/buscarPrecio/js/',
		method: 'Get',
		data: {
			precio: idPrecio,
			producto: idProducto
		}
	});
	retornoPrecio.done(llegoPrecio);
	function llegoPrecio(evento) {
		if (evento.id != null) {

			costo.val($.number(evento.costo, '2', ',', '.'));
			precioMinimo.val($.number(evento.precioMinimo, '2', ',', '.'));
			precioMinimo.select();
			precio.val($.number(evento.precioProducto, '2', ',', '.'));

		} else {
			precioMinimo.val(0);
			precioMinimo.select();
			precio.val(0);

		}
	}
}
//para cargar detalles de ajuste al recuperar para modificar
if ($('.idPrecio').val() > 0) {

	var desplegarItems = $.ajax({
		url: '/ajustePrecios/desplegarItems/' + $('#uuid').val(),
		method: 'GET'
	});

	desplegarItems.done(llegoRespuesta);
}



//codigo para adicionar productos por compra o todos los productos 
$('.agregar-con-compra').val('');
$('.btn-adicionar-producto').click(() => {
	if ($('.js-select-precio').val() === '') {
		Swal.fire({
			icon: 'error',
			title: 'Oops...',
			text: 'Debe informar el precio antes de cargar el producto!',
		});
	} else {
		$('.caja-adicionar-producto').removeClass('quitar');
		$('.agregar-con-compra').val('');
	}
});
$('.btn-agregar-todo').click(() => {
	$('.caja-adicionar-producto').addClass('quitar');
	$('.agregar-con-compra').val('');
	cargarTodosItem();
});
$('.agregar-con-compra').keydown((e) => {
	if (e.keyCode === 13) {
		e.preventDefault();
		buscarPorCompra($('.agregar-con-compra').val());
		$('.caja-adicionar-producto').addClass('quitar');
		$('.agregar-con-compra').val('');
	}
});
$('.btn-adicionar-salir').click(() => {
	$('.caja-adicionar-producto').addClass('quitar');
	$('.agregar-con-compra').val('');
});
//function para cargar todos los productos al item
function cargarTodosItem() {
	var cargarTodoProducto = $.ajax({
		url: 'ajustePrecios/items/adicionarTodos',
		method: 'POST',
		data: { precio: $('.js-select-precio').val(), uuid: $('#uuid').val() }
	});
	cargarTodoProducto.done(llegoRespuesta);
}
//function para buscar por compra
function buscarPorCompra(idCompra) {
	var recuperaCompra = $.ajax({
		url: 'ajustePrecios/items/adicionarCompra',
		method: 'POST',
		data: { compra: idCompra, precio: $('.js-select-precio').val(), uuid: $('#uuid').val() }
	});
	recuperaCompra.done(llegoRespuesta);
}

//fin

})