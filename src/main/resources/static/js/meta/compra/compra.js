import {formatearNumeroInput} from '/js/meta/utilidades.js';
$(function(){
var proveedor = $('#proveedor');
var factura = $('#factura');
var limite = $('#limite');
var fechaDesde = $('#fechaDesde');
var fechaHasta = $('#fechaHasta');
let total_cabecera = $('.total-cabecera').text()

$('.total-cabecera').text($.number(total_cabecera,'2',',','.'));

$('#registro').select();
proveedor.focus();
//$('.money').mask("#.##0", {reverse: true});
$('.date').mask('00/00/0000');
formatearNumeroInput($('.formato-numero'));


factura.click(()=>{
	factura.select();
})


limite.click(()=>{
	limite.select();
})

fechaDesde.click(()=>{
	fechaDesde.select();
})

fechaHasta.click(()=>{
	fechaHasta.select();
})



window.onkeydown = presionaTecla;
function presionaTecla(e){
	//si presiona f5
	if(e.keyCode===116){
		e.preventDefault();
		$('.btn-guardar').click();
	}
	//si presiona f1
	if(e.keyCode === 112){
		e.preventDefault();
		$('.btn-nuevo').click();
	}
	//si presiona f3
	if(e.keyCode === 114){
		e.preventDefault();
		$('.btn-buscar').click();
	}	
}

$.ajax({
	url:'/parametroCompras/getParametroCompra',
	method:'GET',
	success:getParametroCompraSuccess,
	error:function(error){
		alert(`error al recuperar parametros de compra: ${error}`);
	}
});

function getParametroCompraSuccess(parametroCompra){
	
	if(parametroCompra.id){
		if(parametroCompra.deposito){
			$('#deposito').val(parametroCompra.deposito.id);
		}
		if(parametroCompra.agregarPrecio === true){
			$('.contenedor-precio-venta-compra').removeClass('noMostrar');
			$('.js-contenedor-preciov-compra').removeClass('noMostrar');
			$('.js-precio-venta-com').removeClass('noMostrar');
			verificarParametroPrecio(parametroCompra.agregarPrecio);
		
			if(parametroCompra.precio){
				$('#com-precio-venta').val(parametroCompra.precio.id);
			}
		}	
	}
}
})