export function getParametro(){
	return new Promise(function(resolve,reject){
			$.ajax({
				url:'/parametros/js/get/parametros',
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