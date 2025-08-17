$(function(){
	
	let modal = $('#modal-cargar-cliente'); 
	let cliente = $('#nombre-cliente-modal');
	
	 modal.on('shown.bs.modal', function () {
   		 cliente.focus();
   		 $('.btn-guardar-modal-cliente').off('click').click(adicionarCliente);
  	});
  	
  	modal.on('hide.bs.modal', function () {
   		 $('.box-ms-error-modal-cliente').removeClass('show');
		$('.box-ms-error-modal-cliente').addClass('noMostrar');
		$('#nombre-cliente-modal').val('');
		$('#documento-cliente-modal').val('');
		$('#telefono-cliente-modal').val('');
		$('#producto').focus();
  	});
  	
  	function adicionarCliente(){
		let nombre = $('#nombre-cliente-modal').val();
		let documento = $('#documento-cliente-modal').val();
		let telefono = $('#telefono-cliente-modal').val();
		let precio = {id:$('#precio-modal-carga').val()};
		let cliente ={nombre:nombre,documento:documento,telefono:telefono,precio:precio};
		console.log('ADICIONAR CLIENTE..');
		$.ajax({
			url:'/clientes/cargar/modal',
			method:'POST',
			 contentType: "application/json; charset=utf-8",
			data:JSON.stringify(cliente),
			success:cargaClienteSuccess,
			error: cargaClienteError
		})
	}
	
	function cargaClienteSuccess(data){
		console.log('RETORNO DESPUES DE CARGAR CLIENTE..');
		$('#idCliente').val(data.id);
		$('.js-cliente').val(data.nombre);
		$('#nombre-cliente-modal').val('');
		$('#documento-cliente-modal').val('');
		$('#telefono-cliente-modal').val('');
		modal.hide();
		$('.modal-backdrop').removeClass('show');
		$('.modal-backdrop').addClass('hidden');
		$('.modal-backdrop').css('display','none');
		$('#producto').focus();
		$('.modal').removeClass('show');
	}
	
	function cargaClienteError(obj){
		let msjError = obj.responseText;
		console.log(msjError);
		$('.ms-error-modal-cliente').html(
		`<i class="fa fa-exclamation-circle me-2"></i>
				    			<span >${msjError}</span>`);
		$('.box-ms-error-modal-cliente').removeClass('noMostrar');
		$('.box-ms-error-modal-cliente').addClass('show');
	}
})