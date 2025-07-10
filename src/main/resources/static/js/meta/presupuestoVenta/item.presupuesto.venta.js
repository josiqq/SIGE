import{formatearNumeroInput} from '/js/meta/utilidades.js';
import { autoCompletar } from '/js/meta/producto/auto.complete.producto.general.js';
$(function(){
	const $productoNombre = $('#producto-nombre');
	$('#precio-det-nc').keydown(keyPrecioManipular);
	$('.btn-adiciona-item-nc').click(adicionarItem);
	$('#precio').change(modificarMonedaItem);
	$productoNombre.on('input', autoCompletar);
	adicionarItemSuccess();
	function keyPrecioManipular(e){
		if(e.keyCode === 13 || e.keyCode === 9){
			adicionarItem();
		}
	}
	
	function adicionarItem(){
		let monedaDestino = $('#moneda').val();
			let fecha = $('#fecha').val();
			let producto = $('#id-producto').val();
			let cantidad = $('#cantidad-det-nc').val();
			let precio = $('#precio-det-nc').val();
			let uuid =$('#uuid').val();
		
			
			$.ajax({
				url:'/presupuestoVentas/adicionar/item',
				method:'POST',
				data:{
					monedaDestino:monedaDestino,
					fecha:fecha,
					producto:producto,
					cantidad:cantidad,
					precio:precio,
					uuid:uuid	
					},
				success:adicionarItemSuccess,
				error:function(error){
					alert('Error adicionando item !!');
					console.log('error adicionando item: ',error);
				},
				complete:vaciar
			})
	}
	
	function adicionarItemSuccess(html){
		$('.item-presupuesto-venta').html(html);
		let total = $('.js-tabla-item-presupuesto').data('total');
		$('#total').val($.number(total,'2',',','.'));
		formatearNumeroInput($('.formato-numero'));
		
		$('input').click(function(){
			$(this).select();
		})
		
		$('.js-tabla-item-presupuesto').each(function(){
			let cantidad = $(this).find('.item-nc-venta-cantidad').val();
			let precio = $(this).find('.item-nc-venta-precio').text();
			let subTotal = $(this).find('.item-nc-venta-sub-total').text();
			$(this).find('.item-nc-venta-cantidad').val($.number(cantidad,'0',',','.'));
			$(this).find('.item-nc-venta-precio').text($.number(precio,'2',',','.'));
			$(this).find('.item-nc-venta-sub-total').text($.number(subTotal,'2',',','.'));
		})
		
		$('.item-nc-venta-cantidad').change(cambioCantidadDetalleManipular);
		$('.btn-item-quitar').click(elimianrItemManipular);
	}
	
	function cambioCantidadDetalleManipular(){
		let producto = $(this).data('id');
		let cantidad =$(this).val();
		let uuid = $('#uuid').val();
		
		
		
		$.ajax({
			url:'/presupuestoVentas/modificar/cantidad',
			method:'PUT',
			data:{producto:producto,cantidad:cantidad,uuid:uuid},
			success:adicionarItemSuccess,
			error:function(error){
				alert('Error modificando cantidad!');
				console.log('Error modificando cantidad'+error);
			}
		})
		
	}
	
	function elimianrItemManipular(){
		let indice = $(this).data('indice');
		let uuid = $('#uuid').val();
		
		$.ajax({
			url:'/presupuestoVentas/eliminar/item',
			method:'DELETE',
			data:{indice:indice,uuid:uuid},
			success:adicionarItemSuccess,
			error:function(error){
				alert('Error al eliminar item! ');
				console.log('Error al eliminar item: '+error);
			}
		})
	}
	
	function modificarMonedaItem(){
		let precio = $(this).val();
		$('#moneda').val($('option:selected',this).data('moneda'));
		let monedaDestino = $('#moneda').val();
		let fecha =$('#fecha').val();
		let uuid = $('#uuid').val();
		$.ajax({
			url:'/presupuestoVentas/cambiar/precio',
			method:'PUT',
			data:{monedaDestino:monedaDestino,fecha:fecha,precio:precio,uuid:uuid},
			success:adicionarItemSuccess,
			error:function(error){
				alert('Error al modificar precio!');
				console.log('Error al modificar precio: '+error);
			}
		})
	}
	
	 function vaciar(){
        $('#id-producto').val('');
        $('#producto-nombre').val('');
        $('#cantidad-det-nc').val('');
        $('#precio-det-nc').val('');
        $('#producto-nombre').focus();
    }
})