$(function(){
	window.onkeydown = presionaTecla;
	 $('.money').mask("#.##0,00", {reverse: true});
	 
	$('#condicion').change(cambioCondicion);
	
	if($('#condicion').val()==='TARJETA'){
		$('.contenedor-comision').removeClass('noMostrar');
	}
	
	$('input').click(function(){
		$(this).select();
	})
	
	function cambioCondicion(){
		if($(this).val()==='TARJETA'){
			$('.contenedor-comision').removeClass('noMostrar');
		}else{
			$('.contenedor-comision').addClass('noMostrar');
		}
	}
	
	function presionaTecla(e){
		switch(e.keyCode){
			case 13:
				e.preventDefault();
			break;
			case 116:
				e.preventDefault();
				$('.btn-guardar').click();
			break;
		}
	}
	
});