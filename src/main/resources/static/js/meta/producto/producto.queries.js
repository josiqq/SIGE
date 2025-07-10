export function getAllProductos(nombreCodigo){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/productos/js/buscarTodo',
			method:'GET',
			data:{nombreCodigo:nombreCodigo},
			success:function(productos){
				resolve(productos);
			},
			error:function(error){
				reject(error);
			}
		})
	})
}

export function getProductoByCodigo(codigo){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/productos/js/getByCodigo',
			method:'GET',
			data:{codigo:codigo},
			success:function(producto){
				resolve(producto);
			},
			error:function(error){
				reject(error);
			}
		})
	})
}

export function getProductoLoteByCodigo(codigo){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/productos/js/getByCodigo-lote',
			method:'GET',
			data:{codigo:codigo},
			success:function(producto){
				resolve(producto);
			},
			error:function(xhr){
				reject(xhr);
			}
		})
	})
}

export function getProductoLotes(nombreCodigo){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/productos/js/buscarConLote',
			method:'GET',
			data:{nombreCodigo:nombreCodigo},
			success:function(productos){
				resolve(productos);
			},
			error:function(xhr){
				reject(xhr);
			}
		})
	})
}


export function getAllProductosActivos(){
	return new  Promise(function(resolve,reject){
		$.ajax({
			url:'/productos/get-all-activos',
			method:'GET',
			success:function(productos){
				resolve(productos);
			},
			error:function(xhr){
				reject(xhr);
			}
		})
	}) 	
}


