export function findPresupuestoById(id){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/presupuestoVentas/js/getById',
			method:'GET',
			data:{id:id},
			success:function(presupuesto){
				resolve(presupuesto);
			},
			error:function(error){
				reject(error);
			}			
		})
	})
}

