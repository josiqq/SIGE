$(function(){
	let btn_buscar = $('.btn-buscar');
	
	btn_buscar.click(getInventario);
	
	function getInventario(){
		$('.js-cargando').removeClass('noMostrar');
		let deposito = $('#deposito').val();
		let marca = $('#marca').val();
		$.ajax({
			url:'/reporteStock/js/getInventario',
			method:'GET',
			data:{deposito:deposito,marca:marca},
			success:getInventarioSuccess,
			error: function(error){
				alert(`Error al recuperar inventario ! ${error}`);
			},
			complete:function(){$('.js-cargando').addClass('noMostrar');}	
		});
	}
	
	function getInventarioSuccess(inventarios){
		let listInventario = [];
		let cantidad;
		inventarios.forEach(function(inventario){
			if(inventario.pesable === true){
				cantidad = $.number(inventario.cantidad,'3',',','.');
			}else{
				cantidad = $.number(inventario.cantidad,'0',',','.');
			}
			listInventario.push(`
				<tr>
					<td>${inventario.id}</td>
					<td>${inventario.codigo}</td>
					<td>${inventario.nombre}</td>
					<td>${inventario.marca}</td>
					<td class="lado-derecho">${cantidad}</td>
					<td>------------</td>
				</tr>
			`)
		});
		
		$('.js-inventario-resul').html(listInventario);
	}
});