export default class CotizacionQueries{
	 cotizar(moneda_origen,moneda_destino,fecha){
		
		return new Promise(function(resolve, reject){
			$.ajax({
				url:'/cotizaciones/js/getCotizacion',
				method:'Get',
				data:{fecha:fecha,monedaOrigen:moneda_origen,monedaDestino:moneda_destino},
				success:function(cotizacion){
					resolve(cotizacion);
				},
				reject:function(){
					reject('Error recuperando cotizacion!!');
					alert('Error recuperando cotizacion');
				}
			});
		})
		
	}
}