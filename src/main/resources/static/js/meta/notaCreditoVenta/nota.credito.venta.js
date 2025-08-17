import {cambioIdCliente} from '/js/meta/notaCreditoVenta/nota.credito.venta.service.js';
import {getProductoByCodigo} from '/js/meta/producto/producto.queries.js';
import {formatearNumeroInput} from '/js/meta/utilidades.js';
import {retornoItemSuccess} from '/js/meta/notaCreditoVenta/item.nota.credito.venta.js';

	$('#cliente-nombre').focus();
	$('.date').mask('00/00/0000');
	formatearNumeroInput($('.formato-numero'));
	cambioIdCliente();
	
	
	
	$('input').click(function(){
		$(this).select();
	});

	$('.btn-recupera-venta').click(recuperarVentaPorId);
	$('#producto-nombre').keydown(keyProductoManipular);
	$('#id-venta').keydown(function(e){
		if(e.keyCode === 13){
			e.preventDefault();
			recuperarVentaPorId();
		}
	})
	
	function recuperarVentaPorId(){
		let id = $('#id-venta').val();
		$.ajax({
			url:'/ventas/buscarItem/por/id',
			method:'GET',
			data:{venta:id},
			success:retornaDetalleSuccess,
			error:function(error){
				alert('Error recuperando venta!');
				console.log('Error recuperando venta!',error);
			}
		});
	}
	
	function retornaDetalleSuccess(itemVentas){
		let venta = itemVentas[0].venta;
		let cliente = venta.cliente.nombre;
		let deposito = venta.deposito.id;
		let moneda = venta.moneda.id;
		let idCliente = venta.cliente.id ;
		let precio = venta.precio.id;
		let vendedor = venta.vendedor.id;
		
		$('#cliente-nombre').val(cliente);
		$('#deposito').val(deposito);
		$('#moneda').val(moneda);
		$('#id-cliente').val(idCliente);
		$('#precio').val(precio);
		$('#vendedor').val(vendedor);
		
		adicionarItemPorVenta(venta.id);
	
	}
	
	function adicionarItemPorVenta(venta){
		let uuid = $('#uuid').val();
		$.ajax({
			url:'/ventas/js/adicionar/por/venta',
			method:'POST',
			data:{venta:venta,uuid:uuid},
			success:retornoItemSuccess,
			error:function(xhr, status,error){
				alert('Error al adicionar item !!');
				console.log('Error al adicionar item: '+xhr.responseJSON.message+
						'\n'+xhr.responseJSON.details);
			}
		})
	}
	
	
		
	
	
function keyProductoManipular(e){
	if(e.keyCode === 13 || e.keyCode === 9){
		
		let codigo = $(this).val();
		getProductoByCodigo(codigo).then(retornoProductoManipular)
		.catch(
			function(error){
				alert('Error al recuperar producto!');
				console.log('error recuperando producto!'+error);
			}
		)
	}
}	

function retornoProductoManipular(producto){
	
	if(!$('#precio').val()){
		Swal.fire({
			position: "top-end",
			icon: "error",
			title: "Debe informar el precio primero!",
			showConfirmButton: false,
			timer: 1500
		  });
        $('#producto-nombre').focus();
		$('#producto-nombre').select();
	}else if(producto.id === null){
		$('#producto-nombre').focus();
		$('#producto-nombre').select();
	}else{
		$('#id-producto').val(producto.id).change();
		$('#producto-nombre').val(producto.nombre);
	}
}
