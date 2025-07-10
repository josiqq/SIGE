export function getCuentasPorCobrarByVenta(venta){
	return new Promise(function(resolve,reject){
		 $.ajax({
			url:'/cuentaPorCobrars/buscar/cuenta',
			method:'GET',
			data:{venta:venta},
			success:function(cuenta){
				resolve(cuenta);
			},
			error:function(error){
				reject(error);
			}
		})
	})
}