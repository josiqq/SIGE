$(function(){
	let add_identificador = $('.add-identificador');
	$('.js-decimal-plugin').mask("#.##0,00", {reverse: true});
	$('.js-entero-plugin').mask("#.##0", {reverse: true});
	console.log(localStorage.getItem("identificador"));
	add_identificador.click(()=>{
		
		Swal.fire({
		  title: 'Estas seguro de cambiar el identificador de la caja?',
		  text: "Al cambiar el identificador, esta terminal queda como caja cobranza!",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Si, modificar'
		}).then((result) => {
		  if (result.isConfirmed) {
			  $('#identificador').val(crypto.randomUUID());
			  localStorage.setItem("identificador",$('#identificador').val());
		    Swal.fire(
		      'Id modificado',
		       $('#identificador').val(),
		      'success'
		    )
		  }
		})
		
		
	})
	
})