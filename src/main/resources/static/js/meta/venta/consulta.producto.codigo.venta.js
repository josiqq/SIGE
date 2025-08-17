$(function(){
	window.buscarPorCodigo = function(){
		
			let codigo = $('#producto').val();
			let precio = $('#precio').val();
			let deposito = $('#deposito').val();
			
			$.ajax({
				url:'/productos/js/productoStockByCodigo',
				method:'GET',
				data:{
					codigo:codigo,
					deposito:deposito,
					precio:precio
				},
				success:getProductoByCodigoSuccess,
				error:function(){
					alert('Error al recuperar producto!');
				}
			})
	
	function getProductoByCodigoSuccess(producto){
		
		if(producto.id){
			
			console.log('al presionar enter....'+producto.conLote);
			$('#producto').val(producto.nombre);
			$('#idProducto').data('lote',producto.conLote);
			$('#idProducto').data('minimo',producto.stockMinimo);
			$('#idProducto').data('toti',producto.cantidad);
			$('#idProducto').val(producto.id).change();
			
			$('#precio-producto').val($.number(producto.precio,'2',',','.'));
			$('#cantidad-producto').focus();
			$('#scrollable-container').html('');
			//console.log('PrecioMinimo con enter: ',$.number(producto.precioMinimo,'2',',','.'));
			$('.js-precio-minimo-producto').text($.number(producto.precioMinimo,'2',',','.'));
			$('.precio-lista-producto').val(producto.precio);
		}else{
			$('#producto').focus();
			$('#producto').select();
		}
		
		
	}
	$('#cantidad-producto').keydown(function(e){
		if(e.keyCode===13 || e.keyCode === 9){
			if($('#cantidad-producto').val()=== ''){
				$('#cantidad-producto').val(1);
			}
			$('#precio-producto').focus();
		}
	});
	}
})