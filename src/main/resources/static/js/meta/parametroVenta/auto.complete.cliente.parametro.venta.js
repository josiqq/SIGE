$(function(){
	 
	var cliente = $('.js-cliente');
	var idCliente = $('#idCliente');
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
		
	};
})