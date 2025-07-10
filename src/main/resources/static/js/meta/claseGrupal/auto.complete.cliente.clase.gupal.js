$(function(){
	var cliente = $('.js-cliente');
	var idCliente = $('#idCliente');
	var UUID = $('#uuid').val();
	if($('#id').val()>0){
		adicionarItemSuccess();		
	}
	var options = {
		
  		url: function(nombreDocumento){
				 return "/clientes?nombreDocumento="+nombreDocumento;
			},
  		getValue: "nombre",
  		minCharNumber:2,
  		requestDelay:200,
  		ajaxSettings:{
			contentType:'application/json'
		},
		list:{
				onChooseEvent: ItemSeleccionado
			}
		
			 
	};

	cliente.easyAutocomplete(options);
	
	function ItemSeleccionado(){
		idCliente.val(cliente.getSelectedItemData().id);
		adicionarItem(idCliente.val());
		idCliente.val('');
		cliente.val('');
	};
	
	function adicionarItem(cliente){
		$.ajax({
			url:'/clasegrupales/item',
			method:'POST',
			data:{
				cliente:cliente,
				uuid:UUID
			},
			success:adicionarItemSuccess,
			error:adicionaItemError
		});
	}
	
	function adicionarItemSuccess(html){
		$('.js-item-clase-grupal').html(html);
		$('.btn-item-quitar').click((e)=>{
			$.ajax({
				url:'/clasegrupales/item/eliminar',
				method:'DELETE',
				data:{
					cliente:e.currentTarget.dataset.id,
					uuid:UUID
				},
				success:adicionarItemSuccess,
				error:adicionaItemError
			})
		})
	}
	
	function adicionaItemError(error){
		alert(`Tiene error al adicionar item: `,error);
	}
});