export function getMovimientoVendedores(vendedor,fechaDesde,fechaHasta){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/movimiento-vendedor/get',
			method:'GET',
			data:{vendedor:vendedor,fechaDesde:fechaDesde,fechaHasta:fechaHasta},
			success:function(movimientos){
				resolve(movimientos);
			},
			reject:function(xhr,status,error){
				reject(xhr,status,error);
			}
		})
	})
}

export function recalcularMovimientoVendedor(id){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/movimiento-vendedor/recalcular',
			method:'GET',
			data:{id:id},
			success:function(data){
				resolve(data);
			},
			error:function(xhr, status, error){
				reject(xhr, status,error);
			}
		})
	})
}

export function getSaldo(id){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/vendedores/buscarSaldo/'+id,
			success:function(saldo){
				resolve(saldo);
			},
			error:function(xhr,status,error){
				reject(xhr,status,error);
			}
		})
	})
}