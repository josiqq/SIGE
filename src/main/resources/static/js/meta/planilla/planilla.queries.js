export function getPlanillaAbierta(cuenta){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/planillas/get/planilla/abierta',
			method:'GET',
			data:{cuenta:cuenta},
			success:function(planilla){
				resolve(planilla);
			},
			error:function(error){
				reject(error);
			}
		});
	})
}

export function getPlanillasByFecha(fecha){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/planillas/js/getPlanilla/byFecha',
			method:'GET',
			data:{fecha:fecha},
			success:function(planillas){
				resolve(planillas);
			},
			error:function(error){
				reject(error)
			}
		})
	})
}

export function getPlanillaImportes(planilla){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/planillas/js/planilla/lista/importes',
			method:'GET',
			data:{planilla:planilla},
			success:function(planillasImportes){
				resolve(planillasImportes);
			},
			error:function(error){
				reject(error);
			}
		})
	})
}

export function getItemPlanillaRendicion(planilla){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/planillas/js/getItemPlanillaRendicion',
			method:'GET',
			data:{idPlanilla:planilla},
			success:function(itemPlanillaRendiciones){
				resolve(itemPlanillaRendiciones);
			},
			error:function(error){
				reject(error);
			}
		})
	})
}