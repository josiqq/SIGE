export function getParametroVenta(){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/parametroVentas/js/getParametroVenta',
			method:'GET',
			success:function(parametro){
				resolve(parametro);
			},
			error:function(error){
				reject(error);
			}
		})
	})
}