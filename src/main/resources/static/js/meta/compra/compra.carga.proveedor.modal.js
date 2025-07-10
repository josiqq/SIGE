$(function(){
	let modal = $('#modal-cargar-proveedor'); 
	let proveedor = $('#nombre-proveedor-modal');
	
	 modal.on('shown.bs.modal', function () {
   		 proveedor.focus();
   		 $('.btn-guardar-modal-proveedor').off('click').click(adicionarCliente);
  	});
  	
  	modal.on('hide.bs.modal', function () {
   		 $('.box-ms-error-modal-proveedor').removeClass('show');
		$('.box-ms-error-modal-proveedor').addClass('noMostrar');
		$('#nombre-proveedor-modal').val('');
		$('#documento-proveedor-modal').val('');
		$('.js-proveedor').focus();
  	});
  	
  	function adicionarCliente(){
		let nombre = $('#nombre-proveedor-modal').val();
		let documento = $('#documento-proveedor-modal').val();
		let proveedor ={nombre:nombre,documento:documento};
		$.ajax({
			url:'/proveedores/cargar/modal',
			method:'POST',
			 contentType: "application/json; charset=utf-8",
			data:JSON.stringify(proveedor),
			success:cargaProveedorSuccess,
			error: cargaProveedorError
		})
	}
	
	function cargaProveedorSuccess(data){
		$('#idProveedor').val(data.id);
		$('.js-proveedor').val(data.nombre);
		$('#nombre-proveedor-modal').val('');
		$('#documento-proveedor-modal').val('');
		modal.hide();
		$('.modal-backdrop').removeClass('show');
		$('.modal-backdrop').addClass('hidden');
		$('.modal-backdrop').css('display','none');
		$('#factura').focus();
		$('.modal').removeClass('show');
	}
	
	function cargaProveedorError(obj){
		let msjError = obj.responseText;
		$('.ms-error-modal-proveedor').html(
		`<i class="fa fa-exclamation-circle me-2"></i>
				    			<span >${msjError}</span>`);
		$('.box-ms-error-modal-proveedor').removeClass('noMostrar');
		$('.box-ms-error-modal-proveedor').addClass('show');
	}
})