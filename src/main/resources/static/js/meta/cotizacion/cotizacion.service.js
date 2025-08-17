$(function(){
	window.cotizar = function(fecha,monedaOrigen, monedaDestino){
		if(monedaOrigen!= monedaDestino){
			return $.ajax({
				url:'/cotizaciones/js/getValorCotizacion',
				method:'GET',
				data:{fecha:fecha,monedaOrigen:monedaOrigen,monedaDestino:monedaDestino}
			})
		}
	}
})