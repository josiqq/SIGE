export default  class BancosQueries{
	getAllBancos(){
		return new Promise(function(resolve,reject){
			$.ajax({
				url:'/bancos/js/allBancos',
				method:'GET',
				success:function(bancos){
					resolve(bancos);
				},
				error:function(){
					reject('Error recuperando bancos');
				}
					
					
			})
		})
	}
}