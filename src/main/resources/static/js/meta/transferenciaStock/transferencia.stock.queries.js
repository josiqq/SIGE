export function getItemTransferenciaStock(id){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/transferencia-stock/get/item-transferecnia-stock',
			method:'GET',
			data:{transferenciaStock:id},
			success:function(items){
				resolve(items);
			},
			error:function(xhr){
				reject(xhr);
			}
			
		})
	});
}