import {formatearNumeroInput} from '/js/meta/utilidades.js';
var idProducto = $('#idProducto');
var producto = $('#producto');
var precio = $('#precio');
var costo = $('#costo');
var uuid = $('#uuid');
var idPrecio = $('.idPrecio').val();
var productoSeleccionado = new Object();
var htmlTemplateAutocomplete = $('#template-autocomplete-cerveja').html();
var template = Handlebars.compile(htmlTemplateAutocomplete);

if(idPrecio){

	var respuesta = $.ajax({
		url:'/precios/item/recuperar',
		method: 'GET',
		data:{uuid:uuid.val()}
	});
	$('.js-cargando').removeClass('noMostrar');
	respuesta.done(llegoRespuesta);
}

var options = {
 	url: (nombreCodigo)=>{return "/productos?nombreCodigo="+nombreCodigo;},
 	getValue: "nombre",
	minCharNumber:2,
	requestDelay:200,
	ajaxSettings:{contentType:'application/json'},
	template:{
		type: 'custom',
		method: (nombre,producto)=>{
			console.log('vuelve a hacer la busqueda!');
			return template(producto);
		}
	},
	list:{
		onChooseEvent: seleccionaItem
	}	
};

producto.easyAutocomplete(options);

function seleccionaItem(){
	productoSeleccionado = producto.getSelectedItemData();
	precio.focus();
	idProducto.val(producto.getSelectedItemData().id);
	costo.val($.number(producto.getSelectedItemData().costo ,'0',',','.'));
}

precio.keydown((e)=>{
	if(e.keyCode === 13 || e.keyCode===9){
		if($('#precio').val()=== ''){
			precio.val(0);
		}
		var respuesta = $.ajax({
			url:'/precios/item/',
			method: 'POST',
			data:{id:idProducto.val(),costo:costo.val(),precioProducto:precio.val(),uuid:uuid.val()}
		});
		
		respuesta.done(llegoRespuesta);
		limpiarTodo();
		
	}
})
function limpiarTodo(){
	producto.val('');	
	costo.val('');
	precio.val('');
	idProducto.val('');
	
	
}
function llegoRespuesta(html){
	
	producto.focus();
	$('.js-cargando').addClass('noMostrar');
	$('.js-item-precio').html(html);
	//$('.money').mask("#.##0,00", {reverse: true});
	$('.js-tabla-item').each(function(){
		let costo = $(this).find('.item-costo-js').val();
		let precio = $(this).find('.item-precio-js').val();
		$(this).find('.item-costo-js').val($.number(costo,'2',',','.'));
		$(this).find('.item-precio-js').val($.number(precio,'2',',','.'));
	})
	formatearNumeroInput($('.formato-numero'));
	var precioProducto = $('.item-precio-js');
	var eliminar = $('.btn-item-quitar');
	eliminar.click(eliminarItem);
	precioProducto.change(cambioPrecioProducto);
	
	precioProducto.keydown((e)=>{
		if(e.keyCode === 13){
			e.preventDefault();
			cambioPrecioProducto(e);
		};
		
		
	});
	
	precioProducto.click((e)=>{
		var estePrecio = $(e.currentTarget);
		estePrecio.select();
	});
	
	
}

function cambioPrecioProducto(e){
	var itemId = $(e.target).data('id');
	var itemPrecioProducto = $(e.target).val();
	producto.focus();
	var respuesta = $.ajax({
		url: '/precios/item/modificarPrecioProducto/'+itemId,
		method: 'PUT',
		data:{precioProducto:itemPrecioProducto,uuid:uuid.val()}
	});
	
	respuesta.done(llegoRespuesta);
}

function eliminarItem(event){
	var presion = event.currentTarget;
	var respuesta = $.ajax({
		url:'/precios/item/eliminar/'+uuid.val()+'/'+presion.dataset.id,
		method:'DELETE'
	});
	respuesta.done(llegoRespuesta);
}

$('.js-cargar-producto').click(cargarTodosLosProductos);
function cargarTodosLosProductos(){
	console.log('ir a buscar productos..');
	var respuesta = $.ajax({
		url:'/precios/item/cargarTodo',
		method: 'POST',
		data:{uuid:uuid.val()}
	})
	$('.js-cargando').removeClass('noMostrar');
	respuesta.done(llegoRespuesta);
}