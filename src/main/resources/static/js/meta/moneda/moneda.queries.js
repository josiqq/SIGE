export function  getMonedas(){
	return new Promise (function(resolve,reject){
					$.ajax({
						url:'/monedas/js/monedas',
						method:'GET',
						success:function(monedas){
							resolve(monedas);
						},
						error:function(error){
							reject(error);
						}
					})	
	})
}