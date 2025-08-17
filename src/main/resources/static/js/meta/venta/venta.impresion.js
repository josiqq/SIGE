import { impresionVentaTermicaVentaSiete } from '/js/meta/impresion/impresion.venta.termica.siete.js';
$(function(){
	var es_generico;
	var es_cinco_cm;

	$.ajax({
			url:'/parametroVentas/js/getParametroVenta',
			method:'GET',
			success:getParametroVentaSuccess,
			errorr:function(){
				alert('Error recuperando parametros de venta venta');
			}
		});
	
	function getParametroVentaSuccess(param){
		es_generico = param.ticketGenerico;
		es_cinco_cm = param.ticketCinco;
	}
	
	$('.btn-imprimir').click(mandarReimprimir);
	function mandarReimprimir(e){
		let registro = e.currentTarget.dataset.id;
		let factura = e.currentTarget.dataset.factura;
		
		if(factura === 'true'){
			imprimirFactura(registro);
		}else{
			if(es_generico === true){
				imprimirNotaComun(registro);
			}else{
				//if(es_cinco_cm === true){
					//impresionVentaTermicaVentaCinco(registro);
				//}
				impresionVentaTermicaVentaSiete(registro);
			}
		}
	}
	
});