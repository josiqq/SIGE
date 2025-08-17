var nombre = $('#nombre');
var producto = $('#producto');
var precio = $('#precio');

nombre.focus();
nombre.keydown((e)=>{
	if(e.keyCode === 13){
		e.preventDefault();
		producto.focus();
	}
});

producto.keydown((e)=>{
	if(e.keyCode === 13){
		e.preventDefault();
		precio.focus();
	}
})

precio.keydown((e)=>{
	if(e.keyCode === 13){
		e.preventDefault();
		producto.focus();
	}
})

$('.money').mask("#.##0", {reverse: true});