$(function(){
	$('.btn-detalle-compra').click(buscarDetalle);
	function buscarDetalle(e){
		let id_compra = e.currentTarget.dataset.id;
		$.ajax({
			url:'/compras/js/buscar/item/compra',
			metod:'GET',
			data:{compra:id_compra},
			success:getItemCompra,
			error:function(error){
				alert(`Error al recuperar detalles de compras! ${error}`);
			}
		})
	}
	
	function getItemCompra(items){
		let ListaCompras = [];
		let proveedor;
		let cantidad = 0;
		let sub_total =0;
		let total =0;
		let total_linea_ultima;
		items.forEach(function(item){
				proveedor =item.compra.proveedor.nombre;
				if(item.producto.pesable === true){
					cantidad = $.number(item.cantidad,'3',',','.');
				}else{
					cantidad = $.number(item.cantidad,'0',',','.');
				}
				sub_total = Number(item.precio)*Number(item.cantidad);
				total = Number(total)+Number(sub_total);
			ListaCompras.push(`
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
		
		$('#titulo-modal').text(proveedor);
		$('.contenido-detalle-compra').html(ListaCompras+total_linea_ultima);
	}
});