$(function(){
	
	$('.btn-buscar-cuentas').click(buscarCuentasPendientes);
	$('input').click(function(){
		$(this).select();
	});
	function buscarCuentasPendientes(){
		$('.js-cargando').removeClass('noMostrar');
		let cuentaPorPagarFilter ={
			idCompra:$('#registro').val(),
			idProveedor:$('#idProveedor').val(),
			fechaDesde:$('#fechaDesde').val()? moment($('#fechaDesde').val(), "DD/MM/YYYY").format("YYYY-MM-DD"):null,
			fechaHasta:$('#fechaHasta').val()?moment($('#fechaHasta').val(), "DD/MM/YYYY").format("YYYY-MM-DD"):null,
			vencimientoDesde:$('#vencimientoDesde').val()? moment($('#vencimientoDesde').val(), "DD/MM/YYYY").format("YYYY-MM-DD"):null,
			vencimientoHasta:$('#vencimientoHasta').val()?moment($('#vencimientoHasta').val(), "DD/MM/YYYY").format("YYYY-MM-DD"):null,
		};
		
		$.ajax({
			url:'/reportes/cuentasPorPagar/pendientes',
			method:'POST',
			contentType:'application/json',
			data:JSON.stringify(cuentaPorPagarFilter),
			success:getCuentasPendientesSuccess,
			complete:function(){
				$('.js-cargando').addClass('noMostrar');		
			},
			error:function(){
				alert('Error al recuperar cuentas')
			}
		});
	}
	
	function getCuentasPendientesSuccess(cuentas){
		$('.js-ticket').off('click').click(()=>{
			recibirCuentas(cuentas);
		});
		let listaCuentas =[];
		let total_saldo=0;
		let barra_final='';
		
		cuentas.forEach(function(cuenta){
			total_saldo = Number(total_saldo)+Number(cuenta.saldo);
			listaCuentas.push(`
				<tr>
					<td>${cuenta.proveedor}</td>
					<td>${cuenta.fecha}</td>
					<td>${cuenta.vencimiento}</td>
					<td>${cuenta.compra}</td>
					<td class="lado-derecho">${$.number(cuenta.importe,'0',',','.')}</td>
					<td class="lado-derecho">${$.number(cuenta.importePagado,'0',',','.')}</td>
					<td class="lado-derecho">${$.number(cuenta.saldo,'0',',','.')}</td>
				</tr>
			`);
		});
		
		barra_final = `<td colspan="6" class="lado-derecho">${$.number(total_saldo,'0',',','.')}</td>`;
		
		if(cuentas.length>0){
			$('.tbody-cuentas-pendientes').html(listaCuentas+barra_final);
		}else{
			$('.tbody-cuentas-pendientes').html(`<tr><td colspan="6">No se encontraron registros</td></tr>`);
		}
	}
})