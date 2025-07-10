$(function(){	
	$('.js-saldo').mask("#.##0", {reverse: true});
	$('.js-fechaEstimado').mask('00/00/0000');
	var cliente = $('.js-cliente');
	var fecha = $('.js-fecha');
	var cuenta = $('.js-cuenta');
	var importe = $('.js-importe');
	var instructor = $('.js-instructor');
	var observacion = $('.js-observacion');
	var btnGuardar = $('.js-btn-guardar');
	var id=$('#id');
	cliente.focus();
	cliente.keydown(presionaTeclaCliente);
	fecha.keydown(presionaTeclaFecha);
	cuenta.keydown(presionaTeclaCuenta);
	importe.keydown(presionaTeclaImporte);
	instructor.keydown(presionaTeclaInstructor);
	observacion.keydown(presionaTeclaObservacion);
	function presionaTeclaCliente(e){
		if(e.keyCode ===13 || e.keyCode===9){
			e.preventDefault();	
		}
		
		if(e.keyCode ===13 ){
			fecha.focus();
		}
		
	};
	
	function presionaTeclaFecha(e){
		if(e.keyCode ===13 ){
			var fechaMoment = moment(fecha.val(), "DD/MM/YYYY");
			var nuevaFechaMoment = fechaMoment.add(30, 'days');
			var nuevaFecha = nuevaFechaMoment.format("DD/MM/YYYY");
			$('.js-fechaEstimado').val(nuevaFecha);
			
			cuenta.focus();
		}
		
	};
	
	function presionaTeclaCuenta(e){
		if(e.keyCode ===13 ){
			e.preventDefault();
			importe.focus();
		}
	}
	
	function presionaTeclaImporte(e){
		if(e.keyCode ===13 ){
			e.preventDefault();
			instructor.focus();
		}
	}
	function presionaTeclaInstructor(e){
		if(e.keyCode ===13 ){
			e.preventDefault();
			observacion.focus();
		}
	}
	
	function presionaTeclaObservacion(e){
		if(e.keyCode ===13 ){
			e.preventDefault();
			btnGuardar.focus();
		}
	}

	if(id.val()!=''){
		let total = Number(importe.val());	
		importe.val(total);
	}
})