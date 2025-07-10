$(function(){
	$('#condicion').keydown(function(e){
		if(e.keyCode === 13 || e.keyCode === 9){
			e.preventDefault();
			$('#moneda').focus()
		}
	});
	
	$('#moneda').keydown(function(e){
		if(e.keyCode === 13 || e.keyCode === 9){
			e.preventDefault();
			$('#importe').focus()
		}
	});
	
	$('#importe').keydown(function(e){
		if(e.keyCode === 13 || e.keyCode === 9){
			e.preventDefault();
			$('#cuenta-destino').focus()
		}
	});
	
	
})