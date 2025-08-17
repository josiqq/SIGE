import {getMovimientoVendedores} from '/js/meta/movimientoVendedor/movimiento.vendedor.queries.js';
$(function(){
	$('.js-buscar').click(buscarMovimientos);
	
	function buscarMovimientos(){
		let vendedor = $('#vendedor').val()?$('#vendedor').val():null;
		let fechaDesde = $('#fechaDesde').val();
		let fechaHasta = $('#fechaHasta').val();
		getMovimientoVendedores(vendedor,fechaDesde,fechaHasta)
		.then(function(movimientos){
			rellenarTabla(movimientos);
		}).catch(function(error){
			alert(' Error al recuperar movimientos!');
			console.log('error al recuperar movimientos: '+error);
		})
	}
	
	function rellenarTabla(movimientos){
		let listaMovimientos = [];
		let fecha ;
		let sumaCredito = 0;
		let sumaDebito = 0;
		movimientos.forEach(function(movimiento){
			fecha = moment(movimiento.fecha).format('DD/MM/YYYY');
			sumaCredito = Number(sumaCredito)+Number(movimiento.credito);
			sumaDebito = Number(sumaDebito)+Number(movimiento.debito);
			listaMovimientos.push(`<tr>
										<td>${fecha}</td>
										<td>${movimiento.vendedor.nombre}</td>
										<td>${movimiento.observacion}</td>
										<td class="lado-derecho">${$.number(movimiento.credito,'2',',','.')}</td>
										<td class="lado-derecho">${$.number(movimiento.debito,'2',',','.')}</td>
									</tr>`)
		})
		let totales = `<tr>
						<td class="lado-derecho" colspan="3"></td>
						<td class="lado-derecho">${$.number(sumaCredito,'2',',','.')}</td>
						<td class="lado-derecho">${$.number(sumaDebito,'2',',','.')}</td>
						</tr>`
		$('.js-tabla-cuerpo').html(listaMovimientos+totales);
	}
})