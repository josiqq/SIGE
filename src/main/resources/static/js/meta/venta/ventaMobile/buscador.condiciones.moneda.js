$(function(){
	buscarCondiciones();
	buscarMonedas();
	
	function buscarCondiciones(){
		$.ajax({
			url:'/condiciones/js/condiciones',
			method:'GET',
			success:getCondicionesSuccess,
			errorr:function(){
				alert(`Error al recuperar condiciones`);
			}
		})
	}
	
	function getCondicionesSuccess(condiciones){
		condiciones.forEach(function(condicion){
			$('#Condicion').append($('<option>',{value:condicion.id,text:condicion.nombre}));
		});
		$('#Condicion').val($('#condicion').val());
		
	}
	
	function buscarMonedas(){
		$.ajax({
			url:'/monedas/js/monedas',
			method:'GET',
			success:getMonedasSuccess,
			errorr:function(){
				alert(`Error al recuperar monedas`);
			}
		})
	}
	
	function getMonedasSuccess(monedas){
		monedas.forEach(function(moneda){
			$('#item-cobro-moneda').append($('<option>',{value:moneda.id,text:moneda.nombre,'data-decimal': moneda.decimales}));
			$('#item-cobro-moneda-vuelto').append($('<option>',{value:moneda.id,text:moneda.nombre,'data-decimal': moneda.decimales}));
		});
		$('#item-cobro-moneda').val($('#moneda').val());
		$('#item-cobro-moneda-vuelto').val($('#moneda').val());
		$('.js-decimales-vuelto').val(0);
		
	}
});