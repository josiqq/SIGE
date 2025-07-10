import {mensajeError,mensajeAviso,mensajeExito} from '/js/meta/mensaje/mensaje.service.js';
import {getAllProductosActivos} from '/js/meta/producto/producto.queries.js';
$(function(){
	const $procesar = $('.btn-procesar');
	const $descripcion =$('#producto');
	$procesar.click(recalcularStock);
	$descripcion.keyup(uPteclaManipular);
	function recalcularStock(){
		let producto = $('#idProducto').val();
		let deposito = $('#deposito').val();
		if(!deposito){
			mensajeError(`Debe informar el deposito !`);
		}else{
			if(producto){
				hacerPorUnProducto(producto,deposito);
			}else{
				hacerPorTodos(deposito);
			}
		}
	}
	
	function hacerPorUnProducto(producto,deposito){
		//console.log('hacer por un producto: '+producto+'\ndeposito: '+deposito);
		$.ajax({
			url:'/stock/recalcula-uno',
			method:'GET',
			data:{producto:producto,deposito:deposito},
			success:function(cantidad){
				mensajeExito(`Producto recalculada con exito: ${cantidad}`);
			},
			error:function(xhr){
				mensajeError(xhr.responseText);
			}
		})
	}
	
	function hacerPorTodos(deposito){
		
		getAllProductosActivos().then(function(productos){
			recalcularTodo(productos,deposito);
		}).catch(function(xhr){
			mensajeError(xhr.responseText);
		})
	}
	
	function recalcularTodo(productos,deposito){
		let contador = 1;
		$('.msj-aviso').removeClass('noMostrar');
		productos.forEach(function(producto){
			$('.msj-avido-tex').text('calculando:'+producto.nombre+' cantidad calculada:'+contador+ ' de '+
				productos.length);
				recalculaMeEsta(producto.id,deposito);
			contador = contador +1;
		})
		mensajeExito(`Recalculado: ${contador-1} de: ${productos.length}`)
		$('.msj-aviso').addClass('noMostrar');
	}
	
	function recalculaMeEsta(producto,deposito){
		$.ajax({
			url:'/stock/recalcula',
			method:'GET',
			data:{producto:producto,deposito:deposito},
			success:function(data){
				console.log(data);
			},
			error:function(xhr){
				mensajeError(xhr.responseText);
			}
		})
	}
	
	function uPteclaManipular(){
		let tamaño = $(this).val().length;
		if(tamaño === 0){
			$('#idProducto').val('');
		}
	}
})