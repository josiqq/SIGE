$(function(){
	var guardar = $('.btn-guardar');
	var nuevo = $('.btn-nuevo');
	var buscar = $('.btn-buscar');


	
	if($('#id').val()>0){
		$('.caja-saldo').removeClass('noMostrar');
		$('.caja-valor').removeClass('noMostrar');
		
		$('.js-valor').keyup((e)=>{
				
				diferencia = Number($('.js-valor').unmask().val())-Number($('.js-saldo').unmask().val());
				
				if(diferencia >0){
					$('.js-importe').removeAttr('readonly');
					$('.js-importe').val(diferencia);
				}else{
					$('.js-importe').val(0);
					$('.js-importe').attr('readonly','readonly');
				}
			});
	}
	
	window.onkeydown = presionaTecla;
	function presionaTecla(e){
	
		if(e.keyCode===13){
			e.preventDefault();
		}
		
		if(e.keyCode === 116){
			e.preventDefault();
			guardar.click();
		}
		
		if(e.keyCode === 112){
			console.log('presiona en f1..');
			e.preventDefault();
			nuevo.click();
		}
		
		if(e.keyCode === 114){
			e.preventDefault();
			buscar.click();
		}
	}
	
	$('.date').mask('00/00/0000');
	
	$('.js-cliente').focus();
	$('.js-cliente').keydown((e)=>{
		if(e.keyCode === 13){
			e.preventDefault();
			$('.js-fecha').focus();
			$('.js-fecha').select();
		}
	});
	
	$('.js-fecha-desde').click(()=>{
		$('.js-fecha-desde').select();
	});
	$('.js-fecha-hasta').click(()=>{
		$('.js-fecha-hasta').select();
	});
	$('.js-cliente').click(()=>{
		$('.js-cliente').select();
	});
	$('.money-entero').mask("#.##0", {reverse: true});
	$('.js-importe').click(()=>{$('.js-importe').select()});
	$('.js-fecha').click(()=>{$('.js-fecha').select()});
	
	
})
  
  