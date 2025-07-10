import {formatearNumeroInput} from '/js/meta/utilidades.js';
$(function(){
	window.onkeydown = presionaTecla;
	formatearNumeroInput($('.formato-numero'));
	var guardar = $('.btn-guardar');
	var nuevo = $('.btn-nuevo');
	var buscar = $('.btn-buscar');
	var modalProducto = $('#modal-producto');
	function presionaTecla(e){
		switch(e.keyCode){
			case 120:
				e.preventDefault();
				modalProducto.modal('show');
			break;
			case 116:
				e.preventDefault();
				guardar.click();
			break;
			case 112:
				e.preventDefault();
				nuevo.click();
			break;
			case 114:
				e.preventDefault();
				buscar.click();
			break;
			case 13:
				e.preventDefault();
			break;
		}
	
	
	
	
	
	}
	
	$('.js-cabtidad-venta-producto').off('keydown').keydown(function(e){
		if(e.keyCode === 13 || e.keyCode === 9){
			$('#precio-producto').focus();
			$('#precio-producto').select();
			
		}
	});
})