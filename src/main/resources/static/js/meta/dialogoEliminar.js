$(function(){
	var btnEliminar = $('.btn-eliminar');
	btnEliminar.on('click',eliminar);
	
	function eliminar(event){
		event.preventDefault();
		var botonClicado = $(event.currentTarget);
		var url = botonClicado.data('url');
		var objeto = botonClicado.data('objeto');
		console.log(url);
		Swal.fire({
			  icon: 'error',
			  title: 'Esta seguro?',
			  text: 'Eliminar registro de '+objeto,
			  showCancelButton:true
			}).then((result) => {

				  if (result.isConfirmed) {
				    
				    eliminacionConfirmada(url);
				  }
			})
	};
	
	function eliminacionConfirmada(url){
		console.log('Ir a buscar el cliente para eliminar!!!'+url);
		$.ajax({
			url:url,
			method:'DELETE',
			success: elimaExito,
			error: eliminaError
		});
	};
	
	function elimaExito(){
		Swal.fire('Listo!', '', 'success');
		location.reload();
		
	}
	
	function eliminaError(xhr){
		Swal.fire({
			  icon: 'error',
			  title: 'se produjo un error',
			  text: xhr.responseText
			})
	}
	
	
});