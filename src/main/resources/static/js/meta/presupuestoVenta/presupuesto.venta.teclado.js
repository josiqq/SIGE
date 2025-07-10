export function tecladoPresupuestoVenta(){
	let $cliente = $('#cliente-nombre');
	let $fecha = $('#fecha');
	let $deposito = $('#deposito');
	let $moneda = $('#moneda');
	let $vendedor = $('#vendedor');
	let $precio = $('#precio');
	let $producto = $('#producto-nombre');
	
	$producto.focus();
	
	$cliente.keydown(function(e){
		if(e.keyCode === 13){
			e.preventDefault()
			$fecha.focus();
			$fecha.select();
		}
	});
	
	$fecha.keydown(function(e){
		if(e.keyCode === 13){
			e.preventDefault()
			$deposito.focus();
		}
	});
	
	$deposito.keydown(function(e){
		if(e.keyCode === 13){
			e.preventDefault()
			$moneda.focus();
			
		}
	});
	
	$moneda.keydown(function(e){
		if(e.keyCode === 13){
			e.preventDefault()
			$vendedor.focus();
		}
	});
	
	$vendedor.keydown(function(e){
		if(e.keyCode === 13){
			e.preventDefault()
			$precio.focus();
		}
	});
	
	$precio.keydown(function(e){
		if(e.keyCode === 13){
			e.preventDefault()
			$producto.focus();
			$producto.select();
		}
	});
}