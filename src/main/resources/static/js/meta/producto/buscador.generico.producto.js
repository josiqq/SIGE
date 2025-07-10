$(function(){
	let idProducto = $('#idProducto');
	let producto = $('#producto');
	let htmlTemplateAutocomplete = $('#template-autocomplete-cerveja').html();
	
	producto.click(()=>{
		producto.select();
	});
	
	template = Handlebars.compile(htmlTemplateAutocomplete);
	
	var options = {
	 	url: (nombreCodigo)=>{return "/productos?nombreCodigo="+nombreCodigo;},
	 	getValue: "nombre",
		minCharNumber:2,
		requestDelay:200,
		ajaxSettings:{contentType:'application/json'},
		template:{
			type: 'custom',
			method: (nombre,producto)=>{
				return template(producto);
			}
		},
		list:{
			onChooseEvent: seleccionaItem
		}	
	};
	
	producto.easyAutocomplete(options);
	
	function seleccionaItem(){
		producto.getSelectedItemData();
		idProducto.val(producto.getSelectedItemData().id);
	};
	
	producto.keydown(presionaTeclaProducto);
	
	function presionaTeclaProducto(e){
		
		if(producto.val()===''){
			idProducto.val('');
		}
		
		if(e.keyCode === 13 || e.keyCode === 9){
			e.preventDefault();
			if(producto.val()>0){
				$.ajax({
					url:'/productos/js/'+producto.val(),
					method:'GET',
					success:retornoProductoSuccess,
					error:retornoProductoError
				});
			}
			function retornoProductoSuccess(dato){
				producto.val(dato.nombre);
				idProducto.val(dato.id);
			}
			
			function retornoProductoError(error){
				alert(`error al recuperar producto => ${error}`);
			}
			
		}
		
	}
});