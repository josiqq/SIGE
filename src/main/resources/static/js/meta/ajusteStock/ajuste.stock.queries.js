export function fCantidadStock(producto,deposito){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/ajusteStocks/get-cantidad-actual',
			method:'GET',
			data:{producto:producto,deposito:deposito},
			success:function(cantidad){
				resolve(cantidad);
			},
			error:function(xhr){
				reject(xhr);
			}
		})
	})
}