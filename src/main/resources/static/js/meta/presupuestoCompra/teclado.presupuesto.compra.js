export function tecladoPresupuestoCompra(){
	let $proveedor = $('#proveedor');
	let $fecha = $('#fecha');
	let $deposito = $('#deposito');
	let $moneda = $('#moneda');
	let $producto = $('#producto-nombre');
	
	$proveedor.keydown(function(e){
		if(e.keyCode === 13){
			e.preventDefault();
			$fecha.focus()
			$fecha.select();
		}
	});
	
	$fecha.keydown(function(e){
		if(e.keyCode === 13){
			e.preventDefault();
			$deposito.focus()
		}
	});
	
	$deposito.keydown(function(e){
		if(e.keyCode === 13){
			e.preventDefault();
			$moneda.focus()
		}
	});
	
	$moneda.keydown(function(e){
		if(e.keyCode === 13){
			e.preventDefault();
			$producto.focus()
			$producto.select();
		}
	})			
	
	window.onkeydown = presionaTecla;
	
	function presionaTecla(e){
		if(e.keyCode === 116){
			e.preventDefault();
			$('.btn-guardar').click();
		}
	}
	 
}