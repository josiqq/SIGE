$(function(){
	$('.btn-detalle-venta').click(buscarItemVenta);
	
	function buscarItemVenta(e){
		let id_venta = e.currentTarget.dataset.id;
		console.log('entro aqui:',id_venta);
		$.ajax({
			url:'/ventas/js/buscar/item/venta',
			metod:'GET',
			data:{venta:id_venta},
			success:getItemVenta,
			error:function(error){
				alert(`Error al recuperar detalles de compras! ${error}`);
			}
		})
	}
	
	function getItemVenta(items){
		let ListaVentas = [];
		let cliente;
		let cantidad = 0;
		let sub_total =0;
		let total =0;
		let total_linea_ultima;
		items.forEach(function(item){
				cliente =item.venta.cliente.nombre;
				if(item.producto.pesable === true){
					cantidad = $.number(item.cantidad,'3',',','.');
				}else{
					cantidad = $.number(item.cantidad,'0',',','.');
				}
				sub_total = Number(item.precio)*Number(item.cantidad);
				total = Number(total)+Number(sub_total);
			ListaVentas.push(`
				<tr>
					<td>${item.producto.codigo}</td>
					<td>${item.producto.nombre}</td>
					<td>${cantidad}</td>
					<td>${$.number(item.precio,'2',',','.')}</td>
					<td class="lado-derecho">${$.number(sub_total,'2',',','.')}</td>
				</tr>
			`);
		})
		
		total_linea_ultima = `
						<tr>
							<td colspan="5" class="lado-derecho">${$.number(total,'2',',','.')}</td>
						</tr>
		`;
		
		$('#titulo-modal').text(cliente);
		$('.contenido-detalle-venta').html(ListaVentas+total_linea_ultima);
	}
})