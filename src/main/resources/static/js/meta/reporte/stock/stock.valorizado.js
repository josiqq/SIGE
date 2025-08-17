$(function(){
	let btn_buscar = $('.btn-buscar');
	
	btn_buscar.click(buscarStockValorizado);
	
	function buscarStockValorizado(){
		$('.js-cargando').removeClass('noMostrar');
		let deposito = $('#deposito').val();
		let precio = $('#precio').val();
		$.ajax({
			url:'/reporteStock/js/stockValorizado',
			method:'GET',
			data:{deposito:deposito,precio:precio},
			success:getStockValorizadoSuccess,
			error:function(error){
				alert(`Error al recuperar Stock valorizado! ${error}`);
			},
			complete:function(){
				$('.js-cargando').addClass('noMostrar');
			}
		});
	}
	
	function getStockValorizadoSuccess(stockValorizados){
		let lista =[];
		let cantidad;
		let total_costo = 0;
		let total_precio = 0;
		let total_utilidad = 0;
		let totales;
		stockValorizados.forEach(function(stock){
			total_costo = total_costo+stock.totalCosto;
			total_precio = total_precio+stock.totalPrecio;
			total_utilidad = total_utilidad+stock.utilidad;
			if(stock.pesable === true){
				cantidad = $.number(stock.cantidad,'3',',','.');
			}else{
				cantidad = $.number(stock.cantidad,'0',',','.');
			}
			lista.push(`
				<tr>
					<td>${stock.id}</td>
					<td>${stock.codigo}</td>
					<td>${stock.nombre}</td>
					<td class="lado-derecho">${$.number(stock.costo,'0',',','.')}</td>
					<td class="lado-derecho">${$.number(stock.precio,'0',',','.')}</td>
					<td class="lado-derecho">${cantidad}</td>
					<td class="lado-derecho">${$.number(stock.totalCosto,'0',',','.')}</td>
					<td class="lado-derecho">${$.number(stock.totalPrecio,'0',',','.')}</td>
					<td class="lado-derecho">${$.number(stock.utilidad,'0',',','.')}</td>
				</tr>
			`);
		});
		
		totales =`<tr>
					<td colspan="7" class="lado-derecho">${$.number(total_costo,'0',',','.')}</td>
					<td  class="lado-derecho">${$.number(total_precio,'0',',','.')}</td>
					<td  class="lado-derecho">${$.number(total_utilidad,'0',',','.')}</td>
				</tr>`;
		
		$('.js-stock-valorizado-resul').html(lista+totales);
	}
});