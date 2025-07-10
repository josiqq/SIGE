
//inicio
	var numeroFila =0;
	var precio = $('#precio');
	var idProducto = $('#idProducto');
	var producto = $('#producto');
	var cantidadProducto = $('#cantidad-producto');
	var precioProducto = $('#precio-producto');
	producto.focus();
	producto.keyup(precionaTeclaProducto);
	if(precio.val()===''){
		producto.attr('readonly','readonly');
	}
	
	precio.change(()=>{
		if(precio.val()>0){
			producto.removeAttr('readonly');
		}else{
			producto.attr('readonly','readonly');	
		}
	})
	producto.click(()=>{
		producto.select();
	})
	function precionaTeclaProducto(tecla){
		
		if(producto.val().length>=2 && tecla.keyCode != 38 && tecla.keyCode != 40 && tecla.keyCode !=13){
			var listaPrecios = [];
			
			var buscarPrecio = $.ajax({
				url:'/precios/buscarPrecioProducto',
				method:'GET',
				data:{nombreCodigo:producto.val(),precio:precio.val()}
			})	
			buscarPrecio.done(retornoPrecio);
			
			
			function retornoPrecio(precios){
				numeroFila =0;
				precios.forEach(function(precio){
					if(precio.producto.foto ===""){
						precio.producto.foto = "sinfoto.png"
					}
					listaPrecios.push(
					   `
					   <div class="contenedor-auto-complete-producto row">
						   <img src="/fotos/thumbnail.${precio.producto.foto}" class="col-sm-3 imagen-producto-auto-complete"/>	                            		
	                		<div class="col-sm-9">
	                    		<div class="bloque-aparte">
	                    			<input type="hidden" value=${precio.producto.id}>
	                    			<span class="fondo-negro-span ">${precio.producto.codigo}</spa>
	                    		</div>
	                    		<input type="hidden" value="${precio.producto.nombre}">
	                    		<span class= "bloque-aparte">${precio.producto.nombre}</span>
	                    		<input type="hidden" value="${precio.precioProducto}">
	                    		<span class="negrita bloque-aparte money">${$.number(precio.precioProducto,'0',',','.')}</span>
	                    		<input type="hidden" value="${precio.producto.pesable}"> 
	                		</div>
                		</div>
                		`);
				})
				$('.lista-auto-complete-producto').html(listaPrecios);
				//$('.contenedor-auto-complete-producto').eq(numeroFila).addClass('highlight')
				
				$('.contenedor-auto-complete-producto').click(clickContenedor);
				function clickContenedor(evento){
					var esPesable =evento.currentTarget.children[1].children[5].value;
					if(esPesable==='true'){
						cantidadProducto.removeClass('money');
						cantidadProducto.addClass('money2');
						$('.money2').mask("#.##0,00", {reverse: true});
					}else{
						cantidadProducto.removeClass('money2');
						cantidadProducto.addClass('money');
						$('.money').mask("#.##0", {reverse: true});
					}
					idProducto.val(evento.currentTarget.children[1].children[0].children[0].value);
					producto.val(evento.currentTarget.children[1].children[1].value);
					
					var precioNuevo = evento.currentTarget.children[1].children[3].value;
					precioProducto.val($.number(precioNuevo,'0',',','.'));
					cantidadProducto.focus();
					$('.lista-auto-complete-producto').html("");
					
				}
				
					
				
				
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
				var esPesable =$('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1].children[5].value;
				if(esPesable==='true'){
						cantidadProducto.removeClass('money');
						cantidadProducto.addClass('money2');
						$('.money2').mask("#.##0,00", {reverse: true});
					}else{
						cantidadProducto.removeClass('money2');
						cantidadProducto.addClass('money');
						$('.money').mask("#.##0", {reverse: true});
					}
				idProducto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1]
				.children[0].children[0].value);
				precioNuevo = $('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1]
				.children[3].value;
				precioProducto.val($.number(precioNuevo,'0',',','.'));
			}
			if(numeroFila< $('.contenedor-auto-complete-producto').length-1){
				$(this).scrollTop($(this).scrollTop() + 50);
				numeroFila ++;
				$('.contenedor-auto-complete-producto').removeClass('highlight');
				$('.contenedor-auto-complete-producto').eq(numeroFila).addClass('highlight');
				var esPesable =$('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1].children[5].value;
				if(esPesable==='true'){
						cantidadProducto.removeClass('money');
						cantidadProducto.addClass('money2');
						$('.money2').mask("#.##0,00", {reverse: true});
					}else{
						cantidadProducto.removeClass('money2');
						cantidadProducto.addClass('money');
						$('.money').mask("#.##0", {reverse: true});
					}
				producto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1]
				.children[1].value);	
				idProducto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1]
				.children[0].children[0].value);
				precioNuevo = $('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1]
				.children[3].value;
				precioProducto.val($.number(precioNuevo,'0',',','.'));
			}
			
		}
		//fecla de arriba
		if(tecla.keyCode === 38){
			if(numeroFila>0){
				numeroFila --;
				$('.contenedor-auto-complete-producto').removeClass('highlight');
				$('.contenedor-auto-complete-producto').eq(numeroFila).addClass('highlight');
				var esPesable =$('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1].children[5].value;
				if(esPesable==='true'){
						cantidadProducto.removeClass('money');
						cantidadProducto.addClass('money2');
						$('.money2').mask("#.##0,00", {reverse: true});
					}else{
						cantidadProducto.removeClass('money2');
						cantidadProducto.addClass('money');
						$('.money').mask("#.##0", {reverse: true});
					}
				producto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1]
				.children[1].value)
				idProducto.val($('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1]
				.children[0].children[0].value);
				precioNuevo = $('.contenedor-auto-complete-producto').eq(numeroFila)[0].children[1]
				.children[3].value;
				precioProducto.val($.number(precioNuevo,'0',',','.'));
			}
			
		
		}
		if(tecla.keyCode === 13){
			
			if(producto.val()>0){
				console.log('el valor es mayor a cero');
					let valorProducto = producto.val();
					let iniIdentificador = valorProducto.substring(0,2);
					let codigoReal = valorProducto.substring(2,7);
					let entero = valorProducto.substring(7,9);
					let decimal = valorProducto.substring(9,12);
					let identificadorFin = valorProducto.substring(12);
					console.log('ini identificador=> ',iniIdentificador);
						if(iniIdentificador === "20"){
							console.log('buscarProducto?');
							$.ajax({
								url:'/precios/buscarPrecioPorCodigo',
								method:'GET',
								data:{codigo:codigoReal,precio:precio.val()},
								success:buscarCodigoseparadoSuccess
							});
						}else{
							buscarDirecto();
						}
						function buscarCodigoseparadoSuccess(data){
							if(data.id!=null && data.producto.pesable === true){
								
								console.log('llego con esto el codigo separado=>',data);
								let cantidadReal = entero+'.'+decimal;
								cantidadProducto.val($.number(cantidadReal,3,',','.'));
								precioProducto.val($.number(data.precioProducto,'0',',','.'));
								idProducto.val(data.producto.id);
								producto.val(data.producto.nombre);
								adicionarItem();
								vaciar();
								producto.focus();
							}else{
								buscarDirecto();
							}
						}
				}else{
					buscarDirecto();
				}
			function buscarDirecto(){
				var buscarPrecioCodigo = $.ajax({
					url:'/precios/buscarPrecioPorCodigo',
					method:'GET',
					data:{codigo:producto.val(),precio:precio.val()}
				});
			
				buscarPrecioCodigo.done(llegoPrecioCodigo);
			}
			function llegoPrecioCodigo(itemPrecio){
				if(itemPrecio.producto !=null){
					precioProducto.val($.number(itemPrecio.precioProducto,'0',',','.'));
					idProducto.val(itemPrecio.producto.id);
					producto.val(itemPrecio.producto.nombre);
					
					if(itemPrecio.producto.pesable){
						cantidadProducto.removeClass('money');
						cantidadProducto.addClass('money2');
						$('.money2').mask("#.##0,00", {reverse: true});
					}else{
						cantidadProducto.removeClass('money2');
						cantidadProducto.addClass('money');
						$('.money').mask("#.##0", {reverse: true});
					}
					cantidadProducto.val(1)
					cantidadProducto.select();
					cantidadProducto.focus();
				}
			}
				
			
			if(idProducto.val() >0){
				cantidadProducto.focus();
				$('.lista-auto-complete-producto').html("");
			}
		}
		
	}
	
	cantidadProducto.keydown((e)=>{
		if(e.keyCode === 13){
			if(cantidadProducto.val()===''){
				cantidadProducto.val(1);
			}
			precioProducto.focus();
			precioProducto.select();
		}
	});
	
	
//fin
