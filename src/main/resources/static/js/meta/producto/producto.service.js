import {getProductoLoteByCodigo} from '/js/meta/producto/producto.queries.js';
export function keyProductoManipularLote(e){
	if(e.keyCode === 13 || e.keyCode === 9){
		let codigo = $('#producto-nombre').val();
		if(codigo == ''){
			$('#id-producto').val('');
		}
		getProductoLoteByCodigo(codigo).then(retornoProductoManipular)
		.catch(
			function(xhr){
				alert('Error recuperando producto: '+xhr.responseText);
				console.log('error recuperando producto! ',xhr.responseText);
			}
		)		
	}
}

//====================FUNCIONES LOCALES====================
function retornoProductoManipular(producto){
	
	if(producto.id=== null){
			$('#producto-nombre').focus();
			$('#producto-nombre').select();	
	}else{
		
		$('#producto-nombre').val(producto.nombre);
		$('#id-producto').val(producto.id).change();
		$('.js-input-pos-ac').focus();
	}
}