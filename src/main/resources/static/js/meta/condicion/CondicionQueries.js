export default class CondicionQueries{
	getTipoCondicion(id_condicion){
		return new Promise(function(resolve,reject){
			$.ajax({
			url:'/condiciones/condicionPorId',
			method:'GET',
			data:{id:id_condicion},
			success:function(condicion){
				resolve(condicion);
			},
			error:function(){
				reject('Error recuperando condicion!!!');
			}
		})	
		})
		
		
	}
}