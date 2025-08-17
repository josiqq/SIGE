export function getPresupuestos(fechaDesde,fechaHasta){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/presupuestoCompras/js/get',
			method:'GET',
			data:{fechaDesde:fechaDesde,fechaHasta:fechaHasta},
			success:function(presupuestos){
				resolve(presupuestos);
			},
			error:function(error){
				reject(error);
			}
		})
	})
}

export function getPresupuestoById(id){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/presupuestoCompras/js/get/id',
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