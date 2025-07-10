$(function(){
	$('.money').mask("#.##0", {reverse: true});
	$('.money2').mask("#.##0,00", {reverse: true});
	$('.btn-agregar-detalle-vm').off('click').click(adicionarItemImporte);
	
	function adicionarItemImporte(){
				
		let condicion =$('#Condicion').val();
		let moneda = $('#item-cobro-moneda').val();
		let importe = $('#importe').val();
		let importeCobrado = $('#cobrado').val();
		let monedaVuelto = $('#item-cobro-moneda-vuelto').val();
		let vuelto = $('#vuelto').val();
		let uuid = $('#uuid').val();
		
		if(!vuelto){
			vuelto = Number(0);
		}
		
		$.ajax({
			url:'/cobros/js/adicionar/item/importe/mb',
			method:'POST',
			data:{
				 cuenta:1 , importe:importe, importeCobrado:importeCobrado, vuelto:vuelto, condicion:condicion,
				 moneda:moneda, monedaVuelto:monedaVuelto , uuid:uuid
			},
			success:setItemCobroImporteSuccess,
			error:function(){
				alert(`Error al adicionar detalle de importe!`)
			}
		})
	}
	
	function setItemCobroImporteSuccess(html){
		
		$('.item-cobro-importe-vm').html(html);
		verificarTotal();
		// inicio verificar cantidad de decimal en moneda para formatear valores
		$('.content-item-cobro-importe-v-mb').each(function() {
			let decimales = $(this).find('.cobrado-conten-v-mb').data('decimalimp');
			let decimales_vuelto = $(this).find('.vuelto-conten-v-mb').data('decimalvue');
			if(Number(decimales) === 0){
				$(this).find('.cobrado-conten-v-mb').addClass('money');
				$(this).find('.importe-conten-v-mb').addClass('money');
			}else{
				$(this).find('.cobrado-conten-v-mb').addClass('money2');
				$(this).find('.importe-conten-v-mb').addClass('money2');
			}
			if(Number(decimales_vuelto)===0){
				$(this).find('.vuelto-conten-v-mb').addClass('money');
			}else{
				$(this).find('.vuelto-conten-v-mb').addClass('money2');
			}
		})
		// inicio verificar cantidad de decimal en moneda para formatear valores
		
	    $('.money').mask("#.##0", {reverse: true});
	  	$('.money2').mask("#.##0,00", {reverse: true});
		
		$('.js-quitar-linea').off('click').click(eliminarItem);
		
	}
	
	// inicio eliminar item de importe 
	function eliminarItem(e){
		let indice = e.currentTarget.dataset.indice;
		let uuid = $('#uuid').val();
		$.ajax({
			url:'/cobros/js/eliminar/item/importe/mb',
			method:'DELETE',
			data:{indice:indice,uuid:uuid},
			success: setItemCobroImporteSuccess,
			error:function(){
				alert('Error eliminando item !');
			}
		})		
	}
	// fin eliminar item de importe
	$('#cobrado').change(calcularVuelto);
	
	function calcularVuelto(){
		if($('#item-cobro-moneda').val()!=$('#item-cobro-moneda-vuelto').val()){
			$('#item-cobro-moneda-vuelto').val($('#item-cobro-moneda').val());
			$('.js-decimales-vuelto').val($('.js-decimales-moneda').val());
		}
		
		let importe = $('#importe').val().replace(/\./g, "").replace(",",".");
		let cobrado = $('#cobrado').val().replace(/\./g, "").replace(",",".");
		let vuelto = Number(cobrado)-Number(importe);
		if(vuelto<0){
			vuelto = 0;
		}
		if(Number($('.js-decimales-vuelto').val())===Number(0)){
			$('#vuelto').val($.number(vuelto,'0',',','.'));	
			$('.js-vuelto-hidden').val(vuelto);
		}else{
			$('#vuelto').val($.number(vuelto,'2',',','.'));
			$('.js-vuelto-hidden').val(vuelto);
		}
		
		
	}
	
	$('#item-cobro-moneda').change(getCotizacionImporte);
	
	function getCotizacionImporte(){
		let fecha =moment($('#fecha-cuenta').val()).format("DD/MM/YYYY") ;
		let monedaOrigen = $('#moneda').val();
		let monedaDestino = $('#item-cobro-moneda').val();
		buscarvalorCotizacion(fecha,monedaOrigen,monedaDestino)
	}
	
	function buscarvalorCotizacion(fecha,monedaOrigen,monedaDestino){
		
		$.ajax({
			url:'/cotizaciones/js/getCotizacion',
			method:'GET',
			data:{fecha:fecha,monedaOrigen:monedaOrigen,monedaDestino:monedaDestino},
			success:getCotizacionSuccess,
			error:function(){
				alert(`Error al recuperar cotizacion`);
			}
		})
	}
	
	function getCotizacionSuccess(dato){
		if(dato.id){
			if(dato.dividir===true){
				let old_importe = $('#hidden-importe').val();
				let new_importe = Number(old_importe)/Number(dato.valor);
				if(dato.monedaDestino.decimales === 2){
					$('#importe').removeClass('money');
					$('#importe').addClass('money2');
					$('#cobrado').removeClass('money');
					$('#cobrado').addClass('money2');
					$('#importe').val($.number(new_importe,'2',',','.'));
					$('#cobrado').val($.number(new_importe,'2',',','.'));
					$('#item-cobro-moneda-vuelto').val(dato.monedaDestino.id);
					$('.js-decimales-vuelto').val(dato.monedaDestino.decimales);
					$('.js-decimales-moneda').val(dato.monedaDestino.decimales);
					$('#vuelto').val(0);
					$('.js-vuelto-hidden').val(0);
				}
				
			}
		}else{
			$('#importe').removeClass('money2');
			$('#importe').addClass('money');
			$('#cobrado').removeClass('money2');
			$('#cobrado').addClass('money');
			$('#importe').val($.number($('#hidden-importe').val(),'0',',','.'));
			$('#cobrado').val($.number($('#hidden-importe').val(),'0',',','.'));
			$('#item-cobro-moneda-vuelto').val($('#moneda').val());
			$('.js-decimales-vuelto').val(0);
			$('.js-decimales-moneda').val(0);
			$('#vuelto').val(0);
			$('.js-vuelto-hidden').val(0);
		}
		
		$('.money').mask("#.##0", {reverse: true});
		$('.money2').mask("#.##0,00", {reverse: true});
	}
	
	//inicio de cambio en select de moneda vuelto
	$('#item-cobro-moneda-vuelto').change(calcularCotizacionVuelto)
	function calcularCotizacionVuelto(){
		let monedaOrigen = $('#item-cobro-moneda').val();
		let monedaDestino =$('#item-cobro-moneda-vuelto').val(); 
		let fecha = moment($('#fecha-cuenta').val()).format("DD/MM/YYYY") ;
		let vuelto_con_monedas_iguales = 0;
		
		if(monedaOrigen === monedaDestino){
			//calcular vuelto para moneda igual al del importe despues de modificar 
			vuelto_con_monedas_iguales= Number($('#cobrado').val().replace(/\./g,"").replace(",","."))-Number($('#importe').val().replace(/\./g,"").replace(",","."));
			if($('.js-decimales-vuelto').val()===0){
				$('#vuelto').val($.number(vuelto_con_monedas_iguales,'0',',','.'));
			}else{
				$('#vuelto').val($.number(vuelto_con_monedas_iguales,'2',',','.'));
			}
		}else{
			$.ajax({
				url:'/cotizaciones/js/getCotizacion',
				method:'GET',
				data:{fecha:fecha,monedaOrigen:monedaOrigen,monedaDestino:monedaDestino},
				success:getCotizacionVueltoSuccess,
				error:function(){
					alert(`Error al recuperar cotizacion`);
				}
			})
		}
	}
	
	function getCotizacionVueltoSuccess(dato){
		
		let valor_cotizado =0;
		let vuelto_cotizado =0;
		if(dato.id){
			$('.js-decimales-vuelto').val(dato.monedaDestino.decimales);
			if(dato.multiplicar===true){
				
			 	valor_cotizado =Number($('.js-vuelto-hidden').val())*Number(dato.valor);
			}
			if(dato.dividir===true){
				
			 	valor_cotizado =Number($('.js-vuelto-hidden').val())/Number(dato.valor);
			}
		}else{
			Swal.fire({
			  icon: 'error',
			  title: 'Oops...',
			  text: 'No existe cotizacion cargada para esta moneda',
			})
		}
		
		if(Number($('.js-decimales-vuelto').val())===Number(0)){
			vuelto_cotizado = $.number(valor_cotizado,'0',',','.');
		}else{
			vuelto_cotizado = $.number(valor_cotizado,'2',',','.');
		}
		
		$('#vuelto').val(vuelto_cotizado);
	}
	//fin de cambio en select de moneda vuelto
	//inicio de verificar total
	function verificarTotal(){
		
		let moneda_sistema = $('#moneda').val();
		let moneda_importe = $('#item-cobro-moneda').val();
		let saldo = $('.js-saldo-hidden').val().replace(/\./g,"").replace(",",".");
		let importe = $('#importe').val().replace(/\./g,"").replace(",",".");
		let importe_cobrado = $('#cobrado').val().replace(/\./g,"").replace(",",".");
		let nuevo_saldo =0;
		if(moneda_importe === moneda_sistema){			
			if(Number(saldo) === Number(importe)){
				if(Number(importe)<=Number(importe_cobrado)){
					$('#importe').val(0);
					$('#cobrado').val(0);
					$('.js-saldo-hidden').val(0);
					$('.js-saldo-gs').text(`Saldo: ${$.number(0,'0',',','.')}`);
				}else{
					nuevo_saldo = Number(importe)-Number(importe_cobrado)
					$('#importe').val($.number(nuevo_saldo,'0',',','.'));
					$('#cobrado').val($.number(nuevo_saldo,'0',',','.'));
					$('.js-saldo-hidden').val(nuevo_saldo);
					$('#hidden-importe').val(nuevo_saldo);
					$('.js-saldo-gs').text(`Saldo: ${$.number(nuevo_saldo,'0',',','.')}`);
				}
			}
		}else{
			console.log('HACER ALGORITMO PARA CALCULAR EL SALDO CON MONEDA DISTINTO A GUARANIES');
		}
	}
	// fin de verificar total
})