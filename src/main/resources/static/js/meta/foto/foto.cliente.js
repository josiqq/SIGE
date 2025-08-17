$(function(){
	
	$('.btn-foto-cliente').click(mostrar);
	function mostrar(event){
		var botonClicado =$(event.currentTarget);
		var url = botonClicado.data('url');
		var objeto = botonClicado.data('objeto') 
		var modal = $('#staticBackdrop');
		var img = modal.find('img');
		var h5 =modal.find('h5');
		h5.text(objeto);
		img.attr('src',url);
	}
})