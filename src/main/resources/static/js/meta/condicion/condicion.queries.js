export function getCondicionDistintoEfectivo(){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/condiciones/getCondicion/distinto/efectivo',
			method:'GET',
			success:function(condiciones){
				resolve(condiciones);
			},
			error:function(error) {
				reject(error);
			}
		})
	})
}