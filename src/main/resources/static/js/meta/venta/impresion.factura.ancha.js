$(function(){
	window.imprirmirFacturaAncha = function(registro){
		if(registro){
			
			$.ajax({
				url:'/ventas/js/getFactura',
				method:'GET',
				data:{
					id:registro
				},
				success:facturaSuccess,
				error:function(error){
					alert(`Error al recuperar factura!${error}`);
				}
			});
		}
		
		function facturaSuccess(facturas){
			console.log(facturas);
			let total = 0;
			facturas.forEach(function(factura){
				total = total+factura.subTotal;
			});
			console.log(NumeroALetras(total));
		}
	}
})