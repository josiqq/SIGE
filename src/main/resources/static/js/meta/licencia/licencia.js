$(function(){
	  $('.date').mask('00/00/0000');
	$('.primero-a').val(Math.floor(Math.random() * 30) + 1);
	$('.segundo-a').val(Math.floor(Math.random() * 30) + 1);
	$('.tercero-a').val(Math.floor(Math.random() * 30) + 1);
	
	$('.btn-confirmar-a').click(confirmarLicencia);
	
	function confirmarLicencia(){
		let primero_a = $('.primero-a').val();
		let segundo_a = $('.segundo-a').val();
		let tercero_a = $('.tercero-a').val();
		
		let primero_b = $('.primero-b').val();
		let segundo_b = $('.segundo-b').val();
		let tercero_b = $('.tercero-b').val();
		let fecha = $('.fecha-licencia').val();
		$.ajax({
			url:'/licencias/renovar',
			method:'POST',
			data:{primero_a:primero_a,segundo_a:segundo_a,tercero_a:tercero_a,
				  primero_b:primero_b,segundo_b:segundo_b,tercero_b:tercero_b,
				  fecha:fecha
				},
			success:function(data){
				console.log('data',data);
				if(data === "b"){
					const swalWithBootstrapButtons = Swal.mixin({
						  customClass: {
						    confirmButton: 'btn btn-success'
						    
						  },
						  buttonsStyling: false
						})
					swalWithBootstrapButtons.fire({
					  title: 'Código incorrecto',
					  text: "Vuelve a solicitar el código!",
					  icon: 'error',
					  showCancelButton: false,
					  confirmButtonText: 'ok',
					  reverseButtons: true
					}).then((result) => {
					  if (result.isConfirmed) {
					    location.reload();
					  } 
					})
				}else{
						const swalWithBootstrapButtons = Swal.mixin({
						  customClass: {
						    confirmButton: 'btn btn-success'
						    
						  },
						  buttonsStyling: false
						})
					swalWithBootstrapButtons.fire({
					  title: 'Renovación exitosa',
					  text: "La licencia fue renovada correctamente!",
					  icon: 'success',
					  showCancelButton: false,
					  confirmButtonText: 'ok',
					  reverseButtons: true
					}).then((result) => {
					  if (result.isConfirmed) {
					    location.reload();
					  } 
					})
				}
			},
			error:function(error){
				alert(`Error al renovar licencia: ${error}`);
			}
		});
		
	}
})