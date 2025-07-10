import {cambiosEnIdProducto} from '/js/meta/ajusteStock/ajuste.stock.service.js';
import {formatearNumeroInput} from '/js/meta/utilidades.js';
$(function(){
	cambiosEnIdProducto();
	window.onkeydown = precionTecla;
	let fecha =$('.date');
	let deposito = $('.js-select-deposito');
	let producto = $('#producto');
	
	
	$('.date').mask('00/00/0000');
	
	
	deposito.change(verificarDetalles);
	
	if($('#id').val()){
		adicionarSuccess();
	}
	deposito.focus();
	
	fecha.click(()=>{
		fecha.select();
	});
	
	function precionTecla(e){
		switch(e.keyCode){
			case 13:
				e.preventDefault();
				break;
			case 116:
				e.preventDefault();
				$('.btn-guardar').click();
			break;
			
		};
	};
	
	/*producto.keydown((e)=>{
		if(e.keyCode === 13 ){
			$('#sumar').focus();	
		}
		
	});*/
	
	$('#sumar').keydown((e)=>{
		if(e.keyCode===13){
			$('#restar').focus();
		}
	});
	
	$('#restar').keydown((e)=>{
		if(e.keyCode===13){
			$('#cantidad').focus();	
		}
		
	});
	
	$('#cantidad').keydown((e)=>{
		if(e.keyCode === 13){
			
			var producto = $('#idProducto').val(); 
			var sumar = $('#sumar').val()===''?0:$('#sumar').val();
			var  restar = $('#restar').val()===''?0:$('#restar').val();  
			var cantidad = $('#cantidad').val()===''?0:$('#cantidad').val();
			var uuid = $('#uuid').val();
			if(deposito.val()===''){
				deposito.focus();
				Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'Debe informar el depósito!',
				});
				
			}else{
				$.ajax({
					url:'/ajusteStocks/item/adicionar',
					method:'POST',
					data:{
						producto:producto,
						sumar:sumar,
						restar:restar,
						cantidad:cantidad,
						deposito:deposito.val(),
						uuid:uuid
					},
					success:adicionarSuccess,
					error:adicionarError,
					complete:function(){
						$('#cantidad-actual').val(0);
					}
				});
			}
			$('#idProducto').val('');
			$('#producto').val('');
			$('#sumar').val('');
			$('#restar').val('');
			$('#cantidad').val('');
			$('#producto').focus();
		}
	});
		
	function adicionarSuccess(html){
		$('.opt-deposito').addClass('noMostrar');
		$('.js-item-ajuste-stock').html(html);
		$('.item-ajuste-stock-sumar').click(verificarPesable);
		$('.item-ajuste-stock-restar').click(verificarPesable);
		$('.item-ajuste-stock-cantidad').click(verificarPesable);
		$('.item-ajuste-stock-sumar').change(modificarItem);
		$('.item-ajuste-stock-restar').change(modificarItem);
		$('.item-ajuste-stock-cantidad').change(modificarItem);
		$('.btn-item-quitar').click(quitarItem);
		recorrerItem();
	}
	
	function recorrerItem(){
		formatearNumeroInput($('.formato-numero'));
		$('.js-tabla-item').each(function(){
			let suma = $(this).find('.item-ajuste-stock-sumar').val();
			let resta= $(this).find('.item-ajuste-stock-restar').val();
			let cantidad =$(this).find('.item-ajuste-stock-cantidad').val();
			console.log('suma: '+suma);
			$(this).find('.item-ajuste-stock-sumar').val($.number(suma,'0',',','.'));
			$(this).find('.item-ajuste-stock-restar').val($.number(resta,'0',',','.'));
			$(this).find('.item-ajuste-stock-cantidad').val($.number(cantidad,'0',',','.'));
		})
	}
	
	function adicionarError(error){
		alert(`Error al retornar item => ${error}`);
	}
	
	function verificarPesable(){
		//formatear numeros
	}
	
	function modificarItem(e){
		
		var producto = e.currentTarget.dataset.id;
		var sumar = e.currentTarget.parentNode.parentNode.children[2].children[0].value;
		var restar = e.currentTarget.parentNode.parentNode.children[3].children[0].value;
		var cantidad = e.currentTarget.parentNode.parentNode.children[4].children[0].value;
		let deposito = $ ('#deposito').val();
		var uuid =  $('#uuid').val();
		
		$.ajax({
			url:'/ajusteStocks/item/modificar',
			method:'PUT',
			data:{
				producto:producto,
				sumar:sumar,
				restar:restar,
				cantidad:cantidad,
				deposito:deposito,
				uuid:uuid
			},
			success:adicionarSuccess,
			error:adicionarError
		});
	};
	
	function quitarItem(e){
		
		var producto = e.currentTarget.dataset.id;
		var uuid = $('#uuid').val();
		
		$.ajax({
			url:'/ajusteStocks/item/eliminar',
			method:'DELETE',
			data:{
				producto:producto,
				uuid:uuid
			},
			success:adicionarSuccess,
			error:adicionarError
		});
	};
	
	function verificarDetalles(){
		

		
		$.ajax({
			url:'/ajusteStocks/item/arraySize',
			method:'GET',
			data:{uuid:$('#uuid').val()},
			success:cantidadItemSuccess,
			error:cantidadItemError
		});
		
		function cantidadItemSuccess(items){
			if(items.length>0){
 				
 				
				/*Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'Ya tiene productos cargados, no puede modificar depósito!',
				});*/
				
			}else{
				console.log('array que retorna=>',items);	
			}
			
		};
		
		function cantidadItemError(error){
			alert(`Tiene error=>${error}`);
		}
	}
});