$(function(){
	
	$('.btn-buscar-cuentas').click(buscarCuentasPendientes);
	$('input').click(function(){
		$(this).select();
	});
	function buscarCuentasPendientes(){
		$('.js-cargando').removeClass('noMostrar');
		let cuentaPorCobrarFilter ={
			idVenta:$('#registro').val(),
			idCliente:$('#idCliente').val(),
			fechaDesde: moment($('#fechaDesde').val(), "DD/MM/YYYY").format("YYYY-MM-DD"),
			fechaHasta:moment($('#fechaHasta').val(), "DD/MM/YYYY").format("YYYY-MM-DD"),
			condicionVenta: $('#condicion').val()
		};
		
		$.ajax({
			url:'/reporte/cuentas/pendientes',
			method:'POST',
			contentType:'application/json',
			data:JSON.stringify(cuentaPorCobrarFilter),
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
					<td>${cuenta.cliente}</td>
					<td>${cuenta.fecha}</td>
					<td>${cuenta.venta}</td>
					<td class="lado-derecho">${$.number(cuenta.importe,'2',',','.')}</td>
					<td class="lado-derecho">${$.number(cuenta.importeCobrado,'2',',','.')}</td>
					<td class="lado-derecho">${$.number(cuenta.saldo,'2',',','.')}</td>
					 <td>
     						<button type="button" class="btn-det btn-detalle-venta" data-id="${cuenta.venta}" data-bs-toggle="modal" data-bs-target="#itemVentaModal">
							  <i class="far fa-file-alt tooltip-test"  title="Ver detalles"></i>
							</button>
							
     					</td>
				</tr>
			`);
		});
		
		barra_final = `<td colspan="6" class="lado-derecho">${$.number(total_saldo,'2',',','.')}</td>`;
		
		if(cuentas.length>0){
			$('.tbody-cuentas-pendientes').html(listaCuentas+barra_final);
		}else{
			$('.tbody-cuentas-pendientes').html(`<tr><td colspan="6">No se encontraron registros</td></tr>`);
		}
		$('.btn-detalle-venta').click(buscarItemVenta);
	}
	
	//===================================BUSCAR ITEM VENTA=============================================
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
				cliente =item.venta.cliente.nombre+'  '+`venta: `+item.venta.id;
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