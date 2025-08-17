export function getListVendedorSupervisor(){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/vendedores/js/getVendedorSupervisor',
			method:'GET',
			success:function(vendedores){
				resolve(vendedores);
			},
			error:function(error){
				reject(error);
			}
		})
	});
}