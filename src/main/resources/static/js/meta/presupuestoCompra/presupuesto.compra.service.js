import {getProductoByCodigo} from '/js/meta/producto/producto.queries.js';
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
};

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