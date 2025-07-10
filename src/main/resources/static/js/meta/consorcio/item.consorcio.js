$(function(){
	let cliente = $('.js-cliente');
	let id_cliente = $('.idCliente');
	let uuid = $('#uuid').val();
	adicionarItemSuccess();
	cliente.keydown((e)=>{
		if(e.keyCode === 13){
			adicionarItem();	
		}
	});
	
	$('.js-adicionar-cliente-consorcio').click(adicionarItem);
	
	function adicionarItem(){
		let cliente = $('#idCliente').val();
		let uuid = $('#uuid').val();
		let monto = $('#montoMensual').val();
		let meses = $('#meses').val();
		if(!cliente){
			Swal.fire({
			  icon: 'error',
			  title: 'Oops...',
			  text: 'Debe informar el cliente!'
			})
		}else{
			$.ajax({
				url:'/consorcios/item',
				method:'POST',
				data:{
					cliente:cliente,
					monto:monto,
					meses:meses,
					uuid:uuid
				},
				success:adicionarItemSuccess,
				error:function(error){
					alert(`Error al insertar item: ${error}`);
				}
			});
			vaciar();
		}
	}
	
	function adicionarItemSuccess(html){
		$('.js-item-consorcio').html(html);
		 $('.money').mask("#.##0", {reverse: true});
		 $('.btn-item-quitar').click(eliminarItem);
	}
	
	function eliminarItem(e){
		let cliente = e.currentTarget.dataset.id;
		let uuid = $('#uuid').val();
		$.ajax({
			url:'/consorcios/item/eliminar',
			method:'DELETE',
			data:{
				cliente:cliente,
				uuid:uuid
			},
			success:adicionarItemSuccess,
			error:function(error){
				alert(`Error al quitar item: ${error}`);
			}
		});
		
	}
	
	function vaciar(){
		$('#idCliente').val('');
		$('.js-cliente').val('');
	}
})