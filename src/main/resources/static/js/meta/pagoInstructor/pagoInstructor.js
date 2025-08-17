$(function(){
	$('.js-decimal-plugin').mask("#.##0,00", {reverse: true});
	$('.js-entero-plugin').mask("#.##0", {reverse: true});
	
	
	var fecha = $('#fecha');
	var cuenta= $('#cuenta');
	var importe = $('#importe');
	var instructor = $('#instructor');
	var observacion = $('.js-observacion');
	var btnGuardar = $('.js-btn-guardar');
	
	instructor.change(cambioInstructor);
	
	function cambioInstructor(){
		
		$.ajax({
			url:'/instructores/buscar/'+instructor.val(),
			method: 'GET',
			contentType:'application/Json'
		}).done(resultado)
	}
	
	function resultado(dato){
		
		importe.val(dato);
		
	}
	
	fecha.keydown(presionaTeclaFecha);
	cuenta.keydown(presionaTeclaCuenta);
	importe.keydown(presionaTeclaImporte);
	instructor.keydown(presionaTeclaInstructor);
	observacion.keydown(presionaTeclaObservacion);
	fecha.focus();
	
	function presionaTeclaFecha(e){
		if(e.keyCode == 13){
			e.preventDefault();
			instructor.focus();
		}
	}
	
	function presionaTeclaCuenta(e){
		if(e.keyCode == 13){
			e.preventDefault();
			importe.focus();
		}
	}
	
	function presionaTeclaImporte(e){
		if(e.keyCode == 13){
			e.preventDefault();
			observacion.focus();
		}
	}
	function presionaTeclaInstructor(e){
		if(e.keyCode == 13){
			e.preventDefault();
			cuenta.focus();
		}
	}
	
	function presionaTeclaObservacion(e){
		if(e.keyCode == 13){
			e.preventDefault();
			btnGuardar.focus();
		}
	}
})