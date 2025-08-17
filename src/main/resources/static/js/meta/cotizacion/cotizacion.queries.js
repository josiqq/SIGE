export function getCotizacionCurDate(){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/cotizaciones/js/getCotizacion/curDate',
			method:'GET',
			success:function(cotizaciones){
				resolve(cotizaciones);
			},
			error:function(error){
				reject(error);
			}
		})
	})
}

export function fCotizar(monedaOrigen,monedaDestino,fecha,importe){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/cotizaciones/js/fCotizar',
			method:'GET',
			data:{
				monedaOrigen:monedaOrigen,
				monedaDestino:monedaDestino,
				fecha:fecha,
				importe:importe
			},
			success:function(cotizacion){
				resolve(cotizacion);
			},
			error:function(error){
				reject(error);
			}
		})
	})
}