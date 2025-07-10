$(function(){
	
	var proveedor = $('.js-proveedor');
	var idProveedor = $('#idProveedor');
	var options = {
		
  		url: function(nombreDocumento){
				 return "/proveedores?nombreDocumento="+nombreDocumento;
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

	proveedor.easyAutocomplete(options);
	
	function ItemSeleccionado(){
		idProveedor.val(proveedor.getSelectedItemData().id);
	};

})