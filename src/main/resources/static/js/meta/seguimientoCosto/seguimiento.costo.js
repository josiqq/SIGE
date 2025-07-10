$(function(){
//inicio
var idProducto = $('#idProducto');
var producto = $('#producto');
var htmlTemplateAutocomplete = $('#template-autocomplete-cerveja').html();

//mascaras
  $('.date').mask('00/00/0000');
  //preguntar si es pesable primero
   $('.money').text=$.number($('.money').text,'0',',','.');
  $('.money2').mask("#.##0,00", {reverse: true});
//f mascaras
//selectores de campos
producto.focus();
producto.click(()=>{
	producto.select();
})
//f selectores de campo
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
	producto.getSelectedItemData();
	idProducto.val(producto.getSelectedItemData().id);
	
};


$('.tabla-seguimiento-costo').each(function(){
	let cantidad_actual = $(this).find('.cantidad-actual').text();
	let costo_actual = $(this).find('.costo-actual').text();
	let cantidad_compra = $(this).find('.cantidad-compra').text();
	let precio_compra = $(this).find('.precio-compra').text();
	let sumatoria_actual = $(this).find('.sumatoria-actual').text();
	let sumatoria_compra = $(this).find('.sumatoria-compra').text();
	let costo_total = $(this).find('.costo-total').text();
	let cantidad_total = $(this).find('.cantidad-total').text();
	let costo_calculo = $(this).find('.costo-calculo').text();
	$(this).find('.cantidad-actual').text($.number(cantidad_actual,'2',',','.'));
	$(this).find('.costo-actual').text($.number(costo_actual,'2',',','.'));
	$(this).find('.cantidad-compra').text($.number(cantidad_compra,'2',',','.'));
	$(this).find('.precio-compra').text($.number(precio_compra,'2',',','.'));
	$(this).find('.sumatoria-actual').text($.number(sumatoria_actual,'2',',','.'));
	$(this).find('.sumatoria-compra').text($.number(sumatoria_compra,'2',',','.'));
	$(this).find('.costo-total').text($.number(costo_total,'2',',','.'));
	$(this).find('.cantidad-total').text($.number(cantidad_total,'2',',','.'));
	$(this).find('.costo-calculo').text($.number(costo_calculo,'2',',','.'));
})


})