 $(function(){
	
	window.onkeydown = presionaTecla;
	
	$('#vendedor').focus();
	
	function presionaTecla(e){
		if(e.keyCode === 13){
			e.preventDefault();
			$('.js-buscar').click();
		}
	}
	
	$('input').click(function(){
		$(this).select();
	})
	
	$('.js-buscar').click(buscarVenta);
	
	function buscarVenta(){
		$('.js-cargando').removeClass('noMostrar');
		let vendedor = $('#vendedor').val();
		let fechaDesde = $('#fechaDesde').val();
		let fechaHasta = $('#fechaHasta').val();
		if(!vendedor){
			vendedor = null;
		}
		
		$.ajax({
			url:'/reporteVenta/js/ventaPorVendedorComision',
			method:'GET',
			data:{vendedor:vendedor,fechaDesde:fechaDesde,fechaHasta:fechaHasta},
			success:getVentaSuccess,
			error:function(){
				alert('Error al recuperar reporte !');
			},
			complete:function(){
				$('.js-cargando').addClass('noMostrar');
			}
		})
	}
	
	function getVentaSuccess(ventas){
		
		let listaVenta = [];
		let sumatoriaFutter ='';
		let totalComision =0;
		let totalTotal =0;
		if(ventas.length>0){
			ventas.forEach(function(venta){
					totalComision = Number(totalComision)+Number(venta.comision);
					totalTotal = Number(totalTotal)+Number(venta.total);
						listaVenta.push(`
										 <tr>
					                          <td >${venta.fecha}</td>
					                          <td >${venta.cliente}</td>
					                          <td >${venta.producto}</td>
					                          <td class="campo-corto" >${venta.venta}</td>
					                          <td class="centrar-todo" >${$.number(venta.cantidad,'0',',','.')}</td>
					                          <td  class="money lado-derecho">${$.number(venta.total,'2',',','.')}</td>
					                          <td  class="money lado-derecho">${$.number(venta.porcComision,'2',',','.')}</td>
					                          <td  class="money lado-derecho">${$.number(venta.comision,'2',',','.')}</td>                    
					                     </tr>
						`);
			})
			sumatoriaFutter = `  <tr>
							                     	<td colspan="6"  class="lado-derecho">${$.number(totalTotal,'2',',','.')}</td>
							                     	<td></td>
													<td  class="lado-derecho">${$.number(totalComision,'2',',','.')}</td>
												 </tr> `;
			
		}else{
			listaVenta.push(`  <tr>
													<td colspan="8">Ne se encontraron ventas</td>
						 					  </tr>`)
		}
		
		$('.js-tabla-cuerpo').html(listaVenta+sumatoriaFutter);
	}
})