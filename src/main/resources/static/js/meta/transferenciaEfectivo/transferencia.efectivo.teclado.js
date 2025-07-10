$('#fecha').keydown(function(e){
	if(e.keyCode === 13 || e.keyCode === 9){
		e.preventDefault();
		$('#cuentaOrigen').focus();
	}
});

$('#moneda').keydown(function(e){
	if(e.keyCode === 13 || e.keyCode === 9){
		e.preventDefault();
		$('#cuentaOrigen').focus();
	}
});

$('#cuentaOrigen').keydown(function(e){
	if(e.keyCode === 13 || e.keyCode === 9){
		e.preventDefault();
		$('#cuentaDestino').focus();
	}
});

$('#cuentaDestino').keydown(function(e){
	if(e.keyCode === 13 || e.keyCode === 9){
		e.preventDefault();
		$('#importe').focus();
		$('#importe').select();
	}
});

$('#importe').keydown(function(e){
	if(e.keyCode === 13 || e.keyCode === 9){
		e.preventDefault();
		$('#observacion').focus();
		
	}
});
