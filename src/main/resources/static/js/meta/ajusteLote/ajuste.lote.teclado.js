export function presionaTecla(){
	window.onkeydown = presionaGeneral;
	$('#numero-lote').keydown(function(e){
		if(e.keyCode === 13){
			$('#vencimiento').focus();
			$('#vencimiento').select();
		}
	})
	
	$('#vencimiento').keydown(function(e){
		if(e.keyCode === 13){
			$('#cantidad-lote').focus();
			$('#cantidad-lote').select();
		}
	})
	
	
}
//========================FUNCIONES LOCALES==========================
function presionaGeneral(e){
		if(e.keyCode === 116){
			e.preventDefault();
			$('.btn-guardar').click();
		}
}