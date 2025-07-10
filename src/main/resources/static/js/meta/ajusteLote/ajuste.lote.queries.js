export function getItemAjusteLote(id){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/ajuste-lote/buscar-item-ajuste-lote-por-ajuste-id',
			method:'GET',
			data:{idAjusteLote:id},
			success:function(items){
				resolve(items);
			},
			error:function(error){
				reject(error);
			}
		})
	})
}