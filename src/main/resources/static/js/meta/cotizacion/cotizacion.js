$(function(){
	window.onkeydown = presionaTecla;
	$('.date').mask('00/00/0000');
	$('.money').mask("#.##0", {reverse: true});
	$('.money2').mask("#.##0,00", {reverse: true});
	
	let fecha =$('#fecha');
	let moneda_origen =$('#monedaOrigen');
	let moneda_destino = $('#monedaDestino');
	fecha.focus();
	fecha.change(()=>{
		if(($('#monedaOrigen').val())&&($('#monedaDestino').val())&&($('#fecha').val())){
			buscarCotizacion();
		}
	});
	
	moneda_origen.change(()=>{
		if(($('#monedaOrigen').val())&&($('#monedaDestino').val())&&($('#fecha').val())){
			buscarCotizacion();
		}
	});
	
	moneda_destino.change(()=>{
		if(($('#monedaOrigen').val())&&($('#monedaDestino').val())&&($('#fecha').val())){
			buscarCotizacion();
		}
	});
	
	$('input').click(function(){
		$(this).select();
	})
	
	function presionaTecla(e){
		
		switch(e.keyCode){
			case 13:
				e.preventDefault();
			break;
			case 116:
				e.preventDefault();
				$('.btn-guardar').click();
			break;
		}
	}
	
	
	function buscarCotizacion(){
		let fecha = $('#fecha').val();
		let monedaOrigen = $('#monedaOrigen').val();
		let monedaDestino = $('#monedaDestino').val();
		
		
		
		$.ajax({
			url:'/cotizaciones/js/getCotizacion',
			method:'GET',
			data:{fecha:fecha,monedaOrigen:monedaOrigen,monedaDestino:monedaDestino},
			success:getCotizacionSuccess,
			errorr:function(){
				alert(`Error al recuperar cotizacion`);
			}
		}) 
	}
	
	function getCotizacionSuccess(cotizacion){
		
		if(cotizacion.id!=null){
			$('#id-cotizacion').val(cotizacion.id);
			$('#cotizacion').val($.number(cotizacion.valor,'2',',','.'));
			
			if(cotizacion.multiplicar === true){
				$('.js-multiplicar').attr('checked','checked');	
			}
			if(cotizacion.dividir=== true){
				$('.js-dividir').attr('checked','checked');	
			}
			
		}else{
			$('#id-cotizacion').val('');
			$('#cotizacion').val(0);
			$('.js-dividir').removeAttr('checked');
			$('.js-multiplicar').removeAttr('checked');
		}
	}
});