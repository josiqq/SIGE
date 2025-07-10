import {formatearNumeroInput} from '/js/meta/utilidades.js';
$(function(){
//inicio
$('.js-select-precio').focus();
//mascaras
//$('.money').mask("#.##0", {reverse: true});
$('.date').mask('00/00/0000');
formatearNumeroInput($('.formato-numero'));

$('#producto').keyup((e)=>{
	if($('.js-select-precio').val()===''){
		$('#producto').val('');
		Swal.fire({
		  icon: 'error',
		  title: 'Oops...',
		  text: 'Debe informar el precio antes de cargar el producto!',
		});
				
	}
});


$('.js-select-precio').keydown((e)=>{
	if(e.keyCode === 13){
		e.preventDefault();
		$('#producto').focus();	
	}
	
});

//verificar si precio esta seleccionado


$('#producto').keydown((e)=>{
	if(e.keyCode === 13){
		e.preventDefault();
		console.log('buscar por este id: ',$('#idProducto').val());
		$('#precioMinimo').focus();
		$('#precioMinimo').select();
	}
});

$('#precio').keydown((e)=>{
	if(e.keyCode === 13){
		$('#producto').focus();
	
	}
});

//funciones para guardar, buscar, nuevo
window.onkeydown = presionaTecla;
function presionaTecla(e){
	
	if(e.keyCode===116){
		
		e.preventDefault();
		$('.btn-guardar').click();
	}
	if(e.keyCode === 112){
		e.preventDefault();
		$('.btn-nuevo').click();
	}
	
	if(e.keyCode === 114){
		e.preventDefault();
		$('.btn-buscar').click();
	}	
}

//seleccionar toda la fecha al hacer click en ella
$('.js-fecha-desde').click(()=>{
	$('.js-fecha-desde').select();
});
$('.js-fecha-hasta').click(()=>{
	$('.js-fecha-hasta').select();
});
 




//buscar producto al presionar enter
 	$('#producto').keydown((e)=>{
		if(e.keyCode ===13){
			$('#producto').focus();
			/*if($('#producto').val()===''){
				$('#producto').focus();
			}else{
				var buscarProductoPorCodigo = $.ajax({
				url:'/productos/js/'+$('#producto').val(),
				method:'GET'
				});
				buscarProductoPorCodigo.done(llegoProductoPorCodigo);
			}*/
			
		} 
		
		function llegoProductoPorCodigo(producto){
			
			if(producto.id !=null){
				$('#idProducto').val(producto.id);
				$('#producto').val(producto.nombre);
				buscarPrecioPorId($('.js-select-precio').val(),$('#idProducto').val());
				
			}
		}
	 });
 
 	
	
	//function para buscar precio por id del producto
	function buscarPrecioPorId(idPrecio,idProducto){

		var retornoPrecio = $.ajax({
				url:'/precios/buscarPrecio/js/',
				method:'Get',
				data:{
					precio:idPrecio,
					producto:idProducto
				}
		});
		retornoPrecio.done(llegoPrecio);
		function llegoPrecio(evento){
			if(evento.id!=null){
				$('#costo').val(evento.costo);
				$('#precioMinimo').val(evento.precioMinimo);
				$('#precioMinimo').select();
				$('#precio').val(evento.precioProducto);
				
			}else{
				$('#costo').val(0);
				$('#precioMinimo').val(0);
				$('#precioMinimo').select();
				$('#precio').val(0);
				
			}
			
		}
}


 //fin
})

