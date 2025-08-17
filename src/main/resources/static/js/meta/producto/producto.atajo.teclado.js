window.onkeydown = presionaTecla;
var guardar = $('.btn-guardar');
var nuevo = $('.btn-nuevo');
var buscar = $('.btn-buscar');
var nombre = $('#nombre');
var marca = $('#marca');
var gravado = $('#gravado');
var porcGravado = $('#porcGravado');
function presionaTecla(e){
	if(e.keyCode === 116){
		e.preventDefault();
		guardar.click();
	}
	
	if(e.keyCode === 112){
		e.preventDefault();
		nuevo.click();
	}
	
	if(e.keyCode === 114){
		e.preventDefault();
		buscar.click();
	}
}

nombre.keydown((e) =>{
	if(e.keyCode === 13){
		e.preventDefault();
		marca.focus();
	}
});

marca.keydown((e)=>{
	if(e.keyCode ===13){
		e.preventDefault();
		gravado.focus();	
	}	
});

gravado.keydown((e)=>{
	if(e.keyCode === 13){
		e.preventDefault();
		porcGravado.focus();
	}
})