$(function(){
	let btn_guardar = $('.btn-guardar');
	let btn_buscar = $('.btn-buscar');
	let btn_nuevo = $('.btn-nuevo');
	window.onkeydown = presionaTecla;
	function presionaTecla(e){
		
		switch(e.keyCode){
			case 112:
				e.preventDefault();
				btn_nuevo.click();
			break;
			case 114:
				e.preventDefault();
				btn_buscar.click();
			break;
			case 116:
				e.preventDefault();
				btn_guardar.click();
			break;
			case 13:
				e.preventDefault()
			break;
			
		}
	}
})