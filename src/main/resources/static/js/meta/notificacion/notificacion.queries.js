export function getNotificaciones(vendedor){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/notificaciones/js/getNotificacionesByVendedor',
			method:'GET',
			data:{vendedor:vendedor},
			success:function(notificaciones){
				resolve(notificaciones);
			},
			error:function(error){
				reject(error);	
			}
			
		})
	})
}