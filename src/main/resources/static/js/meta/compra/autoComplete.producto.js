$(function(){
var idProducto = $('#idProducto');
var producto = $('#producto');
var cantidad = $('#cantidad');
var precio = $('#precio');
var precioVenta = $('#precioVenta');
var uuid = $('#uuid').val();
var total = $('.js-total');
var idCompra = $('.idCompra').val();
var productoSeleccionado = new Object();
var htmlTemplateAutocomplete = $('#template-autocomplete-cerveja').html();


//inicio de enter en producto
$('.js-producto-compra').keydown((e)=>{
	console.log('este=>',e);
	if(e.keyCode === 13 || e.keyCode === 9){
		console.log('presiona enter o tab',$('.js-producto-compra').val());
		e.preventDefault();
		if($('.js-producto-compra').val().length<=0){
			$('-js-producto-compra').focus();
		}else{
			
		}
	}
});

if(idCompra){
	 var respuesta = $.ajax({
			url: '/compras/item',
			method: 'GET',
			data:{uuid:uuid}
		})
		respuesta.done(llegoRespuesta);
}

template = Handlebars.compile(htmlTemplateAutocomplete);
var options = {
 	url: (nombreCodigo)=>{return "/productos?nombreCodigo="+nombreCodigo;},
 	getValue: "nombre",
	minCharNumber:2,
	requestDelay:200,
	ajaxSettings:{contentType:'application/json'},
	template:{
		type: 'custom',
		method: (nombre,producto)=>{
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
	cantidad.focus();
	
	var esPesable = producto.getSelectedItemData().pesable;
	if(esPesable){
		console.log('este producto es pesable?',producto.getSelectedItemData().pesable);
		$('#cantidad').removeClass('money');
		$('#cantidad').addClass('money2');
		$('.money2').mask("#.##0,00", {reverse: true});
	}else{
		$('#cantidad').removeClass('money2');
		$('#cantidad').addClass('money');
		$('.money').mask("#.##0", {reverse: true});
	}
	idProducto.val(producto.getSelectedItemData().id);
}

precio.keydown((e) =>{
	if(e.keyCode === 13 || e.keyCode === 9 ){
		if(precioVenta.val()===''){
			precioVenta.val(0);
		}
		var respuesta = $.ajax({
			url: '/compras/item',
			method: 'POST',
			data: {
				id:idProducto.val(),
				cantidad:cantidad.val(),
				precio: precio.val(),
				precioVenta:precioVenta.val(),
				uuid:uuid
			}
		})
		respuesta.done(llegoRespuesta);
		limpiarTodo();
	}
});
 
function limpiarTodo(){
	producto.val('');
	cantidad.val('');
	precio.val('');
	idProducto.val('');
}
	
 function llegoRespuesta(html){
	producto.focus();
	$('.js-item-compra').html(html);
	
	
	 const trElements = document.querySelectorAll('tr[data-pesable]');

    // Iterar a través de cada elemento tr
    trElements.forEach((trElement) => {
    // Obtener el valor del atributo data-pesable
    const esPesable = trElement.getAttribute('data-pesable') === 'true';
	
    // Si es pesable, agregar la clase que contiene la máscara de decimales, de lo contrario agregar la clase que contiene la máscara de enteros
    if (esPesable) {
	  
      trElement.children[3].children[0].value=$.number(trElement.children[3].children[0].value,'2',',','.');
      trElement.children[4].children[0].value=$.number(trElement.children[4].children[0].value,'0',',','.');
      trElement.children[5].children[0].value=$.number(trElement.children[5].children[0].value,'0',',','.');

    } else {
     trElement.children[3].children[0].value=$.number(trElement.children[3].children[0].value,'0',',','.');
      trElement.children[4].children[0].value=$.number(trElement.children[4].children[0].value,'0',',','.');
      trElement.children[5].children[0].value=$.number(trElement.children[5].children[0].value,'0',',','.');
    }
  });
	
	var itemCantidad =$('.item-cantidad-js');
	var itemPrecio = $('.item-precio-js');
	var eliminar = $('.btn-item-quitar');
	var tablaItem = $('.js-tabla-item');
	var valorTotal = tablaItem.data('total') == null ? 0 :tablaItem.data('total');
	var totalFormateado = $.number(valorTotal,'0',',','.');
	total.text(totalFormateado);	
	eliminar.click(eliminarItem);
	itemCantidad.change(cambioItemCantidad);
	itemPrecio.change(cambioItemPrecio);
	
	itemCantidad.keydown((e)=>{
		if(e.keyCode === 13){
			e.preventDefault();
			cambioItemCantidad(e);
		}
	});
	
	itemPrecio.keydown((e)=>{
		if(e.keyCode === 13){
			e.preventDefault();
			return cambioItemPrecio(e);
			
		}
	})
	
	itemCantidad.click((e)=>{
		esteCantidad = $(e.currentTarget)
		esteCantidad.select();
		if($(e.currentTarget).data('pesable')){
			esteCantidad.addClass('money2');
			$('.money2').mask("#.##0,00", {reverse: true});	
		}else{
			esteCantidad.addClass('money');
			$('.money').mask("#.##0", {reverse: true});
		}
		
	});
	
	itemPrecio.click((e)=>{
		estePrecio =$(e.currentTarget)
		estePrecio.select();
	})
}


function cambioItemCantidad(e){
	var itemId = $(e.target).data('id');
	var itemCantidad = $(e.target).val();
	producto.focus();
	var respuesta = $.ajax({
		url: '/compras/item/modificarCantidad/'+itemId,
		method:'PUT',
		data:{cantidad:itemCantidad,uuid:uuid}
	});
	respuesta.done(llegoRespuesta);
}

function cambioItemPrecio(e){
	var itemId = $(e.target).data('id');
	var itemPrecio = $(e.target).val();
	producto.focus();
	var respuesta = $.ajax({
		url: '/compras/item/modificarPrecio/'+itemId,
		method:'PUT',
		data:{precio:itemPrecio,uuid:uuid}
	});
	
	respuesta.done(llegoRespuesta);
}

function eliminarItem(event){
	var presion = event.currentTarget;
	var respuesta = $.ajax({
		url:'/compras/item/eliminar/'+uuid+'/'+presion.dataset.id,
		method:'DELETE'
	});
	respuesta.done(llegoRespuesta);
}

$('.js-boton-cargar-detalle').click(agregaConBoton);

function agregaConBoton(){
	var respuesta = $.ajax({
			url: '/compras/item',
			method: 'POST',
			data: {
				id:idProducto.val(),
				cantidad:cantidad.val(),
				precio: precio.val(),
				uuid:uuid
			}
		})
		respuesta.done(llegoRespuesta);
		limpiarTodo();
}




})