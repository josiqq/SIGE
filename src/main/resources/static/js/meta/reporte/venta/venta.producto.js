$(function(){
	$('#producto').focus();
	$('#producto').keydown((e)=>{if(e.keyCode === 13 || e.keyCode === 9){$('#fechaDesde').focus()}});
	
	$('.btn-buscar').click(buscarVentas);
	
	function buscarVentas(){
		let producto = $('#idProducto').val();
		let fechaDesde = $('#fechaDesde').val();
		let fechaHasta = $('#fechaHasta').val();
		
		let venta = {producto:producto,fechaDesde:fechaDesde,fechaHasta:fechaHasta};
		let parametros = $.param(venta);
		$.ajax({
			url:'/reporteVenta/js/ventaPorProductos?'+parametros,
			method:'GET',
			success:ventasSuccess,
			error:ventasError
		});
		
		function ventasSuccess(ventas){
			let listaVenta = [];
			let totalCantidad = 0;
			let totalCosto = 0;
			let totalTotal =0;
			let totalUtilidad =0;
			let totales = '';
			let pesable = false;
			ventas.forEach(function(venta){
				totalCantidad = Number(totalCantidad)+Number(venta[3]);
				totalCosto = Number(totalCosto)+Number(venta[4]);
				totalTotal = Number(totalTotal)+Number(venta[5]);
				totalUtilidad = Number(totalUtilidad)+Number(venta[6]);
				pesable = venta[8];
				let cantidad = 0;
				let codigo = venta[1];
				let producto = venta[2];
				let fecha = venta[7];
				let costo = $.number(venta[4],'0',',','.');
				let total = $.number(venta[5],'0',',','.');
				let utilidad = $.number(venta[6],'0',',','.');
				
				if(venta[8]=== true){
					cantidad = $.number(venta[3],'3',',','.');
				}else{
					cantidad = $.number(venta[3],'0',',','.');
				}
				
				listaVenta.push(`
					<tr>
						<td>${codigo}</td>
						<td>${producto}</td>
						<td>${fecha}</td>
						<td class="lado-derecho">${cantidad}</td>
						<td class="lado-derecho">${costo}</td>
						<td class="lado-derecho">${total}</td>
						<td class="lado-derecho">${utilidad}</td>
					</tr>
				`)
			});
			if(pesable === true){
				totalCantidad = $.number(totalCantidad,'3',',','.');
			}else{
				totalCantidad = $.number(totalCantidad,'0',',','.');
			}
			
			totalCosto = $.number(totalCosto,'0',',','.');
			totalTotal = $.number(totalTotal,'0',',','.');
			totalUtilidad = $.number(totalUtilidad,'0',',','.');
			
			totales =`<tr>
					<td></td>
					<td></td>
					<td></td>
					<td class="lado-derecho">${totalCantidad}</td>
					<td class="lado-derecho">${totalCosto}</td>
					<td class="lado-derecho">${totalTotal}</td>
					<td class="lado-derecho">${totalUtilidad}</td>
				</tr>`
			$('.js-venta-producto-body').html(listaVenta+totales);
		}
		
		function ventasError(error){
			alert(`error al recuperar el reporte:${error}`);
		}
	}
});