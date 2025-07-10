import {formatearNumeroInput} from '/js/meta/utilidades.js';

    const  $cantidad = $('#cantidad-det-nc');
    const $precio =$("#precio-det-nc");
    const $btnAgregar = $('.btn-adiciona-item-nc');
	retornoItemSuccess()
    $cantidad.keydown(keyCantidadManypular);
    $precio.keydown(keyPrecioManipular);
    $btnAgregar.click(adicionarItemNc);
    function keyCantidadManypular(e){
        if(e.keyCode === 13 ){
            $precio.focus();
            $precio.select();
        }
    }

    function keyPrecioManipular(e){
        if(e.keyCode === 13 || e.keyCode === 9){
            adicionarItemNc();
        }
    }

    function adicionarItemNc(){
        let moneda = $('#moneda').val();
        let fecha = $('#fecha').val();
        let producto = $('#id-producto').val();
        let precio =$('#precio-det-nc').val();
        let cantidad = $('#cantidad-det-nc').val();
        let uuid =$('#uuid').val();
   
        $.ajax({
            url:'/ventas/nc/adicionarItem',
            method:'POST',
            data:{
                moneda:moneda,fecha:fecha,producto:producto,precio:precio,cantidad:cantidad,uuid:uuid
            },
            success:retornoItemSuccess,
            error:function(error){
                alert('Error al adicionar item: ');
                console.log('Error al adicionar item: ',error);
            },
            complete:vaciar
        })
    }

	export function retornoItemSuccess(html){
		$('.item-nota-credito').html(html);	
		formatearNumeroInput($('.formato-numero'));
		$('input').click(function(){
			$(this).select();
		})
		let total = $('.js-tabla-item-nc-venta').data('total')
		$('#total').val($.number(total,'2',',','.'));
		$('.js-tabla-item-nc-venta').each(function(){
			let cantidad = $(this).find('.item-nc-venta-cantidad').val();
			let precio = $(this).find('.item-nc-venta-precio').text();
			let subTotal = $(this).find('.item-nc-venta-sub-total').text();
			$(this).find('.item-nc-venta-cantidad').val($.number(cantidad,'0',',','.'));
			$(this).find('.item-nc-venta-precio').text($.number(precio,'2',',','.'));
			$(this).find('.item-nc-venta-sub-total').text($.number(subTotal,'2',',','.'));
		})
		
		$('.btn-item-quitar').off('click').click(eliminarItemNc);
		$('.item-nc-venta-cantidad').change(ModificarCantidadItemNc);
	}
	
	function eliminarItemNc(){
		let indice = $(this).data('indice');
		let uuid = $('#uuid').val();
		console.log('eliminar este indice : '+indice);
		$.ajax({
			url:'/ventas/nc/elimiarItem',
			method:'DELETE',
			data:{indice:indice,uuid:uuid},
			success:retornoItemSuccess,
			error:function(error){
				alert('Error eliminando item!');
				console.log('Error eliminando item: '+error);
			}
		})
	}
	
	
	function ModificarCantidadItemNc(){
		let producto = $(this).data('id');
		let cantidad = $(this).val();
		let uuid = $('#uuid').val();
		if(!cantidad){
			cantidad = 1;
		}
	
		
		$.ajax({
			url:'/ventas/item/modificar/cantidad/nc',
			method:'PUT',
			data:{producto:producto,cantidad:cantidad,uuid:uuid},
			success:retornoItemSuccess,
			error:function(error){
				alert('Error modificando cantidad!!');
				console.log('Error modificando cantidad!!',error);
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
    
    
