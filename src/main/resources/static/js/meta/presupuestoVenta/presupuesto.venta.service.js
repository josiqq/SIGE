import {getProductoByCodigo} from '/js/meta/producto/producto.queries.js';
import{getPrecioByProductoPrecio} from '/js/meta/precio/precio.queries.js';



export function cambioIdProductoManipular(){
	let producto = $(this).val();
	let precio = $('#precio').val();
	
	
	getPrecioByProductoPrecio(producto,precio).then(retornoPrecioManipular)
	.catch(
		function(error){
			alert('Error al recuperar precio del producto');
			console.log('Error al recuperar precio: '+error);
		}
	)
		
}

function retornoPrecioManipular(precio){
	$('#precio-det-nc').val($.number(precio.precioProducto,'2',',','.'));
}

export function keyProductoManipular(e){
	if(e.keyCode === 13 || e.keyCode === 9){
		let codigo = $('#producto-nombre').val();
		getProductoByCodigo(codigo).then(retornoProductoManipular)
		.catch(
			function(error){
				alert('Error recuperando producto!');
				console.log('error recuperando producto! ',error);
			}
		)		
	}
}

function retornoProductoManipular(producto){
	if(producto.id=== null){
		$('#producto-nombre').focus();
		$('#producto-nombre').select();
	}else{
		$('#producto-nombre').val(producto.nombre);
		$('#id-producto').val(producto.id).change();
		$('#cantidad-det-nc').val(1).select().focus();
	}
}

export function keyCantidadManipular(e){
	if(e.keyCode === 13 || e.keyCode === 9){
		if(!$('#cantidad-det-nc').val()){
			$('#cantidad-det-nc').val(1);
		}
		
		$('#precio-det-nc').focus();
		$('#precio-det-nc').select();
	}
}


