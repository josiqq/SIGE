$(function(){
	console.log('Instructor...');
	$('.js-decimal-plugin').mask("#.##0,00", {reverse: true});
	$('.js-entero-plugin').mask("#.##0", {reverse: true});
	
	var nombre = $('#nombre');
	var documento = $('#documento');
	var porcentaje = $('#porcentaje');
	var activo = $('#gridCheck1');
	var btnGuardar = $('.js-btn-guardar');
	
	nombre.keydown(presionaTeclaNombre);
	documento.keydown(presionaTeclaDocumento);
	porcentaje.keydown(presionaTeclaPorcentaje);
	activo.keydown(presionaTeclaActivo);
	
	function presionaTeclaNombre(e){
		if(e.keyCode ==13){
			e.preventDefault();
			documento.focus();
		}
	}
	function presionaTeclaDocumento(e){
		if(e.keyCode ==13){
			e.preventDefault();
			porcentaje.focus();
		}
	}
	function presionaTeclaPorcentaje(e){
		if(e.keyCode ==13){
			e.preventDefault();
			activo.focus();
		}
	}
	function presionaTeclaActivo(e){
		if(e.keyCode ==13){
			e.preventDefault();
			btnGuardar.focus();
		}
	}
})