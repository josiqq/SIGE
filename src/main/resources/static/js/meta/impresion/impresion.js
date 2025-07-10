$(function(){
	$('.btn-imprimir-imprimir').click(function(){
		console.log('recuperar formato impresion!!');
		recuperarImpresion();
	});
	
	function recuperarImpresion(){
		$.ajax({
			url:'/impresiones/venta',
			method:'GET',
			success:function(html){
				console.log('Exito!'+html);
			},
			error:function(error){
				alert('Error al recuperar impresion');
				console.log('Error al recuperar impresion: '+error);
			}
		})
	}
	
})