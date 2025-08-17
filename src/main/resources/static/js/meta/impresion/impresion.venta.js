export function imprimirVenta(registro){
	return new Promise(function(resolve, reject){
		$.ajax({
			url:'/impresiones/venta',
			method:'GET',
			data:{registro:registro},
			success:function(html){
				resolve(html);
			},
			error:function(error){
				reject(error);
			}
		})
	})
}