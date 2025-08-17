$(function(){
	var totito = Number(0);
	adicionarItemSuccess();
	window.adicionarItem = function(){
		let agregar = $('.lista-producto-agregar');
		agregar.off('click').click(agregarItem);
		
		function agregarItem(e){
			let cantidad = Number(1);
			let producto = e.currentTarget.dataset.id;
			let costo = e.currentTarget.dataset.costo;
			let precio = e.currentTarget.dataset.precio;
			let uuid = $('#uuid').val();
			if($(e.currentTarget).closest('.box-agregar').find('.js-input-cantidad-venta-mobile').val()){
				cantidad = $(e.currentTarget).closest('.box-agregar').find('.js-input-cantidad-venta-mobile').val();
				if(Number(cantidad)<0){
					cantidad = Number(1);
				}
			}
			
				$.ajax({
					url:'/ventaMobiles/item/adicionar',
					method:'POST',
					data:{
						producto:producto,
						costo:costo,
						precio:precio,
						cantidad:cantidad,
						uuid:uuid
					},
					success:adicionarItemSuccess,
					error:adicionarItemError
				
				})
				$(e.currentTarget).closest('.box-agregar').find('.js-input-cantidad-venta-mobile').val('');
		
		}
		
		
		
		
		
		
		
		
		
		function adicionarItemError(error){
			alert(`Error al insertar item: ${error}`);
		}
	}
	
	function adicionarItemSuccess(html){
			$('.contenedor-item-venta-mobile').html(html);
			
			$('.item-venta-mobile').each(function(){
				let precio = $(this).find('.item-precio-mb').text();
				let subTotal = $(this).find('.item-sub-total-mb').text();	
				$(this).find('.item-precio-mb').text($.number(precio,'2',',','.'));
				$(this).find('.item-sub-total-mb').text($.number(subTotal,'2',',','.'))
			});
			$('.total-lineas-mobile').text($('.item-venta-mobile').data('linea'));
			let total = $('.item-venta-mobile').data('total');
			let total_venta = $.number(total,'2',',','.');
			$('.total-venta-mobile').text(total_venta);
			mostrarCotizacion(total);
			animacionBotones();
			
			$('.item-venta-mobile-sumar').click(sumarCantidad);
			$('.item-venta-mobile-eliminar').click(eliminarItem);
			$('.item-venta-mobile-restar').click(restarCantidad);
		}
	function sumarCantidad(e){
			
			$('.nombre-producto-cantidad').text(e.currentTarget.dataset.id);
			let producto = e.currentTarget.dataset.id;
			let uuid = $('#uuid').val();
			$.ajax({
				url:'/ventaMobiles/item/sumar/cantidad',
				method:'PUT',
				data:{producto:producto,uuid:uuid},
				success:adicionarItemSuccess,
				error: function(jqXHR, textStatus, errorThrown){
					alert(`Error sumando cantidad: ${textStatus} ${errorThrown}`)
				}
			})
		}
	function eliminarItem(e){
			let producto = e.currentTarget.dataset.id;
			let uuid = $('#uuid').val();
			$.ajax({
				url:'/ventaMobiles/item/eliminar',
				method:'DELETE',
				data:{producto:producto,uuid:uuid},
				success:adicionarItemSuccess,
				error: function(jq,status,error){
					alert(`Error al eliminar item: ${status} ${error}`)
				}
			})
		}
	function restarCantidad(e){
			let producto = e.currentTarget.dataset.id;
			let uuid = $('#uuid').val();
			$.ajax({
				url:'/ventaMobiles/item/restar/cantidad',
				method:'PUT',
				data:{producto:producto,uuid:uuid},
				success:adicionarItemSuccess,
				error: function(jq,status,error){
					alert(`Error restando cantidad: ${status} ${error}`)
				}
			})
		}
	function mostrarCotizacion(total){
		
		
		if(total){
		 totito= total;	
		}
		
		if($('#moneda').val()){
			
			$.ajax({
				url:'/cotizaciones/js/cotizacionesDTO',
				method:'GET',
				data:{
					fecha:$('#fecha').val(),
					monedaOrigen:$('#moneda').val()
				},
				success:getCotizacionesDTOSuccess,
				error:function(){
					alert(`Error recuperando cotizaciones`);
				}
			})
		}
	}
	
	function getCotizacionesDTOSuccess(cotizaciones){
		
		let listaCotizaciones = [];
		let total_cotizado = 0;
		cotizaciones.forEach(function(cotizacion){
			if(cotizacion.multiplicar === true){
				total_cotizado = Number(totito)*Number(cotizacion.valor)
			}else{
				total_cotizado = Number(totito)/Number(cotizacion.valor)
			}
			listaCotizaciones.push(`
				<div class="col-md-2">
					<span class="negrita">${cotizacion.sigla}:</span><span class="total-venta-mobile money ">${$.number(total_cotizado,cotizacion.decimales,',','.')}</span>
				<div>
			`);
		})
		
		$('.content-cotizaciones-item').html(listaCotizaciones);
	}
});