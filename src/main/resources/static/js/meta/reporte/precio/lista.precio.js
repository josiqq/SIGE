$(function(){
	let btn_buscar = $('.btn-buscar');
	
	btn_buscar.click(buscarPrecios);
	
	function buscarPrecios(){
		$('.js-cargando').removeClass('noMostrar');
		let precio = $('#precio').val();
		
		$.ajax({
			url:'/reportePrecio/js/getPreciosMobile',
			method:'GET',
			data:{precio:precio},
			success:getPrecioMobileSuccess,
			error:function(error){
				alert(`Error recuperando precios! ${error}`);
			},
			complete:function(){
				$('.js-cargando').addClass('noMostrar');
			}
		});
		
	}
	
	function getPrecioMobileSuccess(precios){
		console.log(precios);
		let lista =[];
		let precio_producto =0;
		precios.forEach(function(precio){
			precio_producto = $.number(precio[2],'0',',','.'); 
			lista.push(`
				<div class="col-12">
                 <div class="box-precios m-2 p-2">
                 	<div class="box-precios-descripcion m-1">
                 		<span class="precios-descripcion"> ${precio[1]}</span>
                 	</div>
                 	<div class="box-precios-precios ">
                 		<span class="precios-precios m-2">${precio_producto}</span>
                 	</div>
                 	<div class="box-precios-codigo m-2">
                 		<span class="precios-codigo">${precio[0]}</span>
                 	</div>
                 </div>
             	</div>
			`);
		});
		
		$('.contenedor-precios').html(lista);
	}
});