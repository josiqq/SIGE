export function getStockByProducto(producto){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/stockDepositos/js/getStock',
			method:'GET',
			data:{producto:producto},
			success:function(stocks){
				resolve(stocks);
			},
			error:function(error){
				reject(error);
			}
		})
	})
}

export function getCantidadByProductoDeposito(producto,deposito){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/stockDepositos/js/get-cantidad',
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
