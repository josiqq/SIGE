export function getAllPreciosByProducto(producto){
	return new Promise(function(resolve, reject){
		$.ajax({
			url:'/precios/porProducto',
			method:'GET',
			data:{producto:producto},
			success:function(precios){
				resolve(precios);
			},
			error:function(error){
				reject(error);
			}
		})
	})
}