$(function() {
	$('#producto').focus();
	$('#producto').keyup(buscarProducto);

	$('input').click(function(){
		$(this).select();
	})
	
	function buscarProducto(e) {
		
		if (e.keyCode != 38 && e.keyCode != 40 && $('#producto').val().length > 1) {
			$('.v-list-auto-complete-prod').html('');
			let nombreCodigo = $('#producto').val();
			let deposito = $('#deposito').val();
			let precio = $('#precio').val();

			$.ajax({
				url: '/productos/js/productoStock',
				method: 'GET',
				data: {
					nombreCodigo: nombreCodigo,
					deposito: deposito,
					precio: precio
				},
				success: getProductoStockSuccess,
				error: function() {
					alert('Error al recuperar productos!!');
				}
			})

		}
		
		if($('#producto').val().length <= 1){
			$('.v-list-auto-complete-prod').html('');	
		}
	}

	function getProductoStockSuccess(productos) {
		
		$('.v-list-auto-complete-prod').html('');
		
		
		let listaProductos = [];
		productos.forEach(function(producto) {
			
			let precio = $.number(producto.precio, '2', ',', '.');
			let precioMinimo =  $.number(producto.precioMinimo, '2', ',', '.');
			let cantidad = 0;
			if (producto.pesable === true) {
				cantidad = $.number(producto.cantidad, '3', ',', '.');
			} else {
				cantidad = $.number(producto.cantidad, '0', ',', '.');
			}
			listaProductos.push(`
							<div class="row v-conten-list-pro" data-lote="${producto.conLote}" 
									data-minimo="${producto.stockMinimo}" data-toti="${producto.cantidad}">
								<input type="hidden" class="v-conten-list-pro-id" value=${producto.id}>
								<div class="col-2">
									<img src="/fotos/${producto.foto}" class="v-list-prod-foto"/>
								</div>	
								<div class=" row col-10">
									<div class="col-12">
										<span class="v-list-prod-nombre negrita">${producto.nombre}</span>
									</div>
									<div class="col-6 v-list-prod-codigo-conten">
										<span class="v-list-prod-codigo-descri negrita">Código: </span>
										<span class="v-list-prod-codigo negrita">${producto.codigo}</span>
									</div>
									<div class="col-6 v-list-prod-precio-conten">
										<span class="v-list-prod-precio-descri negrita">Precio: </span>
										<span class="v-list-prod-precio negrita" data-precio-minimo=${precioMinimo}>${precio}</span>
									</div>
									<div class="col-12 v-list-prod-deposito-conten">
										<span class="v-list-prod-deposito negrita">Stock: </span>
										<span class="v-list-prod-canti negrita">${cantidad}</span>
									</div>
								</div>   
							</div>				 
			`);
		})
		$('.v-list-auto-complete-prod').html(listaProductos);
			
		presionaTeclaAbajoArriba();
		
		$('.v-conten-list-pro').off('click').click(seleccionarItem);
	}
	
	function seleccionarItem(){
		$('#producto').val($(this).closest('.v-conten-list-pro').find('.v-list-prod-nombre').text());
		$('#idProducto').val($(this).closest('.v-conten-list-pro').find('.v-conten-list-pro-id').val()).change();
		$('#idProducto').data('lote',$(this).closest('.v-conten-list-pro').data('lote'));
		$('#idProducto').data('minimo',$(this).closest('.v-conten-list-pro').data('minimo'));
		$('#idProducto').data('toti',$(this).closest('.v-conten-list-pro').data('toti'));
		$('#precio-producto').val($(this).closest('.v-conten-list-pro').find('.v-list-prod-precio').text());			
		$('.v-list-auto-complete-prod').html('');
		//console.log('Precio minimo : ',$(this).closest('.v-conten-list-pro').find('.v-list-prod-precio').data('precio-minimo'));
		$('.js-precio-minimo-producto').text($(this).closest('.v-conten-list-pro').find('.v-list-prod-precio').data('precio-minimo'));
		$('.precio-lista-producto').val($(this).closest('.v-conten-list-pro').find('.v-list-prod-precio').text().replace(/\./g,'').replace(',','.'));
	}
	
	function presionaTeclaAbajoArriba() {
		const $container = $(".v-list-auto-complete-prod");
		const $items = $container.find(".v-conten-list-pro");
		let selectedItemIndex = -1;
		
		$('#producto').off('keydown').keydown(verificarAbajo);
		
		function verificarAbajo(e) {
			let presionaTecla =0;
			if (e.keyCode === 40) {
				if (selectedItemIndex < $items.length - 1) {
					selectedItemIndex++;
					presionaTecla++;
				
					$items.removeClass("resaltar"); // Eliminar la clase de resaltado de todos los elementos
					const $selectedItem = $items.eq(selectedItemIndex);
					$selectedItem.addClass("resaltar"); // Agregar la clase de resaltado al elemento seleccionado
					
					const itemHeight = $selectedItem.height();
					$('#producto').val($selectedItem.find('.v-list-prod-nombre').text());
					$('#precio-producto').val($selectedItem.find('.v-list-prod-precio').text());
					if(selectedItemIndex>0){
						$container.scrollTop($container.scrollTop() + itemHeight);	
					}
				}
				e.preventDefault();
				
				
			}
			if (e.keyCode === 38) {
				if (selectedItemIndex > 0) {
					selectedItemIndex--;
					$items.removeClass("resaltar"); // Eliminar la clase de resaltado de todos los elementos
					const $selectedItem = $items.eq(selectedItemIndex);
					$selectedItem.addClass("resaltar"); // Agregar la clase de resaltado al elemento seleccionado
					const itemHeight = $selectedItem.height();
					$container.scrollTop($container.scrollTop() - itemHeight);
					$('#producto').val($selectedItem.find('.v-list-prod-nombre').text());
					$('#precio-producto').val($selectedItem.find('.v-list-prod-precio').text());
				}
				e.preventDefault();
				
			}
			
			if(e.keyCode ===13 || e.keyCode === 9){
				e.preventDefault();
				buscarPorCodigo();//este metodo viene de consulta.producto.codigo.venta.js
			}
		};
	}
})