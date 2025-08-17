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

$('.tr-extracto').each(function(){
	
	let entrada = $(this).find('.td-entrada').text();
	let salida = $(this).find('.td-salida').text();
	$(this).find('.td-entrada').text($.number(entrada,'0',',','.'));
	$(this).find('.td-salida').text($.number(salida,'0',',','.'));
})

let totalEntrada = $('.td-total-entrada').text();
let totalSalida = $('.td-total-salida').text();
let saldo = $('.td-saldo').text();
$('.td-total-entrada').text($.number(totalEntrada,'0',',','.'));
$('.td-total-salida').text($.number(totalSalida,'0',',','.'));
$('.td-saldo').text($.number(saldo,'0',',','.'));
//fin
})