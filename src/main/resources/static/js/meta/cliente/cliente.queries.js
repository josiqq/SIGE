export function getClientesByNombreDocumento (nombreDocumento){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/clientes/js/getClientes',
			method:'GET',
			data:{nombreDocumento:nombreDocumento},
			success:function(clientes){
				resolve(clientes);
			},
			error:function(error){
				reject(error);
			}
		})
	})
}

export function getClienteById(id){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/clientes/js/getClienteById',
			method:'GET',
			data:{id:id},
			success:function(cliente){
				resolve(cliente);
			},
			error:function(error){
				reject(error);
			}
		})
	})
}