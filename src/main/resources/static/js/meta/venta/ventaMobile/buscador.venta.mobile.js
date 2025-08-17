$(function(){
	$('.date').mask('00/00/0000');
	let cliente = $('.js-cliente');
	let id_cliente = $('#idCliente');
	let fecha_desde =$('.fecha-desde-buscador-venta-mobile');
	let fecha_hasta = $('.fecha-hasta-buscador-venta-mobile');
	let registro = $('.id-buscador-venta-mobile');
	cliente.click(()=>{
		cliente.select();
	});
	
	cliente.keyup(()=>{
		if(cliente.val().length===0){
			id_cliente.val('');
		}
	});
	
	fecha_desde.click(()=>{
		fecha_desde.select();
	});
	
	fecha_hasta.click(()=>{
		fecha_hasta.select();
	});
	
	registro.click(()=>{
		registro.select();
	});
})