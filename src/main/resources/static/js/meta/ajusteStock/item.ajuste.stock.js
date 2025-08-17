$(function(){
	var numeroFila =0;
	var producto = $('#producto');
	var idProducto = $('#idProducto');
	 var cantidadProducto= $('.js-cant-det');
	producto.keyup(precionaTeclaProducto);
	
	function precionaTeclaProducto(tecla){
		
		if(producto.val().length>=2 && tecla.keyCode != 38 && tecla.keyCode != 40 && tecla.keyCode !=13){
			var listaPrecios = [];
			
			var buscarPrecio = $.ajax({
				url:'/productos/buscarPorNombreOCodigo',
				method:'GET',
				data:{
					nombreCodigo:producto.val()
				}
				
			})	
			buscarPrecio.done(retornoPrecio);
			
			
			function retornoPrecio(productos){
				numeroFila =0;
				productos.forEach(function(producto){
					if(producto.foto ===""){
						producto.foto = "sinfoto.png"
					}
					listaPrecios.push(
					   `
					   <div class="contenedor-auto-complete-producto row">
						   <img src="/fotos/${producto.foto}" class="col-sm-3 imagen-producto-auto-complete"/>	                            		
	                		<div class="col-sm-9">
	                    		<div class="bloque-aparte">
	                    			<input type="hidden" value=${producto.id}>
	                    			<span class="fondo-negro-span ">${producto.codigo}</spa>
	                    		</div>
	                    		<input type="hidden" value="${producto.nombre}">
	                    		<span class= "bloque-aparte">${producto.nombre}</span>
	                    		<input type="hidden" value="${producto.pesable}"> 
	                		</div>
                		</div>
                		`);
				})
				$('.lista-auto-complete-producto').html(listaPrecios);
				//$('.contenedor-auto-complete-producto').eq(numeroFila).addClass('highlight')
				$('.contenedor-auto-complete-producto').click(clickContenedor);
				function clickContenedor(evento){
					
					var esPesable = evento.currentTarget.children[1].children[3].value;
					if(esPesable==='true'){
						cantidadProducto.removeClass('money');
						cantidadProducto.addClass('money2');
						$('.money2').mask("#.##0,00", {reverse: true});
					}else{
						cantidadProducto.removeClass('money2');
						cantidadProducto.addClass('money');
						$('.money').mask("#.##0", {reverse: true});
					}
					//idProducto.val(evento.currentTarget.children[1].children[0].children[0].value);
					$('#idProducto').val(evento.currentTarget.children[1].children[0].children[0].value).trigger('change');
					producto.val(evento.currentTarget.children[1].children[1].value);
					$('.lista-auto-complete-producto').html("");
					$('#sumar').focus();
					
				};
			}
			
			
		}else if(tecla.keyCode!=40 && tecla.keyCode != 38){
			$('.lista-auto-complete-producto').html("");
		}
		
		//para manejar con flechas el auto complete 
		//fecha de abajo
		if(tecla.keyCode===40){
			
			if(numeroFila ===0){
			
				$('.contenedor-auto-complete-producto').removeClass('highlight');
				$('.contenedor-auto-complete-producto').eq(numeroFila).addClass('highlight');
				
				producto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1].children[1].value);
				//idProducto.val();
				$('#idProducto').val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1].children[0].children[0].value).trigger('change');
							
				var esPesable = $('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1].children[3].value;
				if(esPesable==='true'){
					cantidadProducto.removeClass('money');
					cantidadProducto.addClass('money2');
					$('.money2').mask("#.##0,00", {reverse: true});
				}else{
					cantidadProducto.removeClass('money2');
					cantidadProducto.addClass('money');
					$('.money').mask("#.##0", {reverse: true});
				}
				
			}
			if(numeroFila< $('.contenedor-auto-complete-producto').length-1){
				numeroFila ++;
				$('.contenedor-auto-complete-producto').removeClass('highlight');
				$('.contenedor-auto-complete-producto').eq(numeroFila).addClass('highlight');
				
				producto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1]
				.children[1].value);	
				//idProducto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1].children[0].children[0].value);
				$('#idProducto').val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1].children[0].children[0].value).trigger('change');
				
				var esPesable = $('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1].children[3].value;
				if(esPesable==='true'){
					cantidadProducto.removeClass('money');
					cantidadProducto.addClass('money2');
					$('.money2').mask("#.##0,00", {reverse: true});
				}else{
					cantidadProducto.removeClass('money2');
					cantidadProducto.addClass('money');
					$('.money').mask("#.##0", {reverse: true});
				}
			}
			
		}
		//fecla de arriba
		if(tecla.keyCode === 38){
			if(numeroFila>0){
				numeroFila --;
				$('.contenedor-auto-complete-producto').removeClass('highlight');
				$('.contenedor-auto-complete-producto').eq(numeroFila).addClass('highlight');
				producto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1]
				.children[1].value)
				//idProducto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1].children[0].children[0].value);
				$('#idProducto').val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1].children[0].children[0].value).trigger('change');
				var esPesable = $('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1].children[3].value;
				if(esPesable==='true'){
					cantidadProducto.removeClass('money');
					cantidadProducto.addClass('money2');
					$('.money2').mask("#.##0,00", {reverse: true});
				}else{
					cantidadProducto.removeClass('money2');
					cantidadProducto.addClass('money');
					$('.money').mask("#.##0", {reverse: true});
				}
			}
			
		
		}
		if(tecla.keyCode === 13){
			if(producto.val()>0){
			var buscarPrecioCodigo = $.ajax({
				url:'/productos/js/'+producto.val(),
				method:'GET'
			});
			
			buscarPrecioCodigo.done(llegoPrecioCodigo);
			function llegoPrecioCodigo(productos){
			
				if(productos.id !=null){
					//idProducto.val(productos.id);
					$('#idProducto').val(productos.id).trigger('change');
					producto.val(productos.nombre);
					var esPesable = productos.pesable
					if(esPesable==='true'){
						cantidadProducto.removeClass('money');
						cantidadProducto.addClass('money2');
						$('.money2').mask("#.##0,00", {reverse: true});
					}else{
						cantidadProducto.removeClass('money2');
						cantidadProducto.addClass('money');
						$('.money').mask("#.##0", {reverse: true});
					}
					$('#sumar').focus();
				}else{
					producto.focus();
					producto.select();
				}
				
			}
			}else{
				producto.focus();
			}
			
			if(idProducto.val() >0){
				$('.lista-auto-complete-producto').html("");
				$('#sumar').focus();
			}
		}
		
	}
	
	
});