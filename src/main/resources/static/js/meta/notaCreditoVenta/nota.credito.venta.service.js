import {getClienteById} from '/js/meta/cliente/cliente.queries.js'
import {getPrecioByProductoPrecio} from '/js/meta/precio/precio.queries.js';
import {retornoItemSuccess} from '/js/meta/notaCreditoVenta/item.nota.credito.venta.js';
export function cambioIdCliente(){
	
	const $idCliente = $('#id-cliente');
	const $deposito = $('#deposito');
	const $idProducto = $('#id-producto');
	const $precio = $('#precio');

	$idCliente.change(idClienteManipular);
	$deposito.change(cambioDepositoManipular);
	$idProducto.change(cambioIdProductoManipular);
	$precio.change(cambioPrecioManipular);

	function idClienteManipular(){
		let id = $(this).val();
		getClienteById(id).then(getClienteManipular)
		.catch(
			function(error){
				alert('Error recuperando datos del cliente!!');
				console.log('Error recuperando datos del cliente!!',error);
			}
		)
	}
	
	function cambioIdProductoManipular(){
		let producto = $(this).val();
		let precio = $('#precio').val();
		if(!precio){
			Swal.fire({
				position: "top-end",
				icon: "error",
				title: "Debe informar el precio primero!",
				showConfirmButton: false,
				timer: 1500
			  });
		}else{
			getPrecioByProductoPrecio(producto,precio).then(getPrecioManipular)
			.catch(
				function(error){
					alert('Error recuperando precio del producto!');
					console.log('Error recuperando precio del producto: '+error)
				}
			)
		}
			
		
		

	}

	function getClienteManipular(cliente){
		
		if(cliente.precio){
			$('#moneda').val(cliente.precio.moneda.id);
			$('#precio').val(cliente.precio.id);	
		}
		
	}
	
	function cambioDepositoManipular(){
		console.log('manipulacion de deposito: '+$(this).val());
	}

	function getPrecioManipular(precio){
		
		$('#precio-det-nc').val($.number(precio.precioProducto,'2',',','.'));
		$('#cantidad-det-nc').focus().val(1).select();
	}

	function cambioPrecioManipular(){
		let moneda = $('option:selected',this).val();
	
		if(moneda === 0){
			Swal.fire({
				position: "top-end",
				icon: "error",
				title: "Precio no tiene moneda asignada",
				showConfirmButton: false,
				timer: 1500
			  })
		}else{
			$('#moneda').val(moneda);
			let fecha = $('#fecha').val();
			let precio= $('#precio').val()
			let uuid= $('#uuid').val();
			$.ajax({
				url:'/ventas/cambiar/precio/nc',
				method:'PUT',
				data:{
					monedaDestino:moneda,
					fecha:fecha,
					precio:precio,
					uuid:uuid
				},
				success:retornoItemSuccess,
				error:function(error){
					alert('Error al adicionar item!!');
					console.log('Error adicionando item: '+error);
				}
			})
		}		
	}
}