$(function(){
	let btn_nuevo = $('.btn-nuevo');
	$('.date').mask('00/00/0000');
	window.onkeydown = presionaTecla;
	function presionaTecla(e){
		switch(e.keyCode){
			case 112:
				e.preventDefault();
				btn_nuevo.click();
			break;	
		}
	}
});