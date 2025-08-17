$(function(){
	let id_retorno = $('#idRetorno').val();
	var totito =0;
	var cobro_venta_mobile;
	

	$.ajax({
			url:'/parametroVentas/js/getParametroVenta',
			method:'GET',
			success:getParametroVentaSuccess,
			errorr:function(){
				alert('Error recuperando parametros de venta venta');
			}
		});
	
	function getParametroVentaSuccess(param){
		cobro_venta_mobile = param.cobroVentaMobile;
		if(id_retorno){
			if(cobro_venta_mobile === true){
				buscarCuenta(id_retorno);
			}
		}
	}
	
	
	
	function buscarCuenta(id_retorno){
		$.ajax({
			url:'/cuentaPorCobrars/buscar/cuenta',
			method:'GET',
			data:{venta:id_retorno},
			success:getCuentasSuccess,
			error:function(){
				alert(`Error al recuperar cuenta`);
			}	
		});
	}
	
	function getCuentasSuccess(cuentas){
		if(Number(cuentas.importe)>Number(cuentas.importeCobrado)){
			let importe_real = Number(cuentas.importe)-Number(cuentas.importeCobrado);
			mostrarCotizacion(importe_real);
			$('#importe').val($.number(importe_real,'0',',','.'));
			$('#cobrado').val($.number(importe_real,'0',',','.'));
			$('#hidden-importe').val(importe_real);
			$('.js-total-gs').text(`Total: ${$.number(importe_real,'0',',','.')}`);
			$('.js-saldo-gs').text(`Saldo: ${$.number(importe_real,'0',',','.')}`);
			$('.js-saldo-hidden').val(importe_real);
			$('#modal-cobro-venta-mobile').modal('show');
			$('#fecha-cuenta').val(cuentas.fecha);
		}
	}
	
	 $('#modal-cobro-venta-mobile').on('shown.bs.modal', function () {
    	$('#cobrado').focus();
    	$('#cobrado').select();
    	$('input').click(function(){
			$(this).select();
		});
  	 });
	
	 window.mostrarCotizacion = function(total){
		if(total){
		 totito= total;	
		}
		if(Number(total)===0){
			totito = total;
		}
		
		if($('#moneda').val()){
			
			$.ajax({
				url:'/cotizaciones/js/cotizacionesDTO',
				method:'GET',
				data:{
					fecha:$('#fecha').val(),
					monedaOrigen:$('#moneda').val()
				},
				success:getCotizacionesDTOSuccess,
				error:function(){
					alert(`Error recuperando cotizaciones`);
				}
			})
		}
	}
	
	function getCotizacionesDTOSuccess(cotizaciones){

		let listaCotizaciones = [];
		let total_cotizado = 0;
		cotizaciones.forEach(function(cotizacion){
			if(cotizacion.multiplicar === true){
				total_cotizado = Number(totito)*Number(cotizacion.valor)
				
			}else{
				total_cotizado = Number(totito)/Number(cotizacion.valor)
				
			}
			listaCotizaciones.push(`
				<div class="col-md-3">
	       			<span class="span-grande">${cotizacion.sigla}:</span><span class="span-grande">${$.number(total_cotizado,cotizacion.decimales,',','.')} </span>
	       		</div>
			`);
		})
		
		$('.js-total-a-cobrar').html(listaCotizaciones);
	}
	
	
})