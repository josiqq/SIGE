$(function(){
	let cliente = $('.js-cliente');
	$('.date').mask('00/00/0000');
	$('.date').click(()=>{
		$('.date').select();
	});
	
	cliente.click(()=>{
		cliente.select();
	})
	
	window.animacionBotones = function(){  
	    $(".animated-button").click(function() {
	        var button = $(this);
			console.log('clickando...');
	      
	        button.addClass("clicked");
	        
	       
	        setTimeout(function() {
	            button.removeClass("clicked");
	        }, 200); 
	    });
	}
	$('#precio').change(cambioPrecioManipular);
	function cambioPrecioManipular(){
		let moneda = $('option:selected',this).data('moneda');
		$('#moneda-venta-mb').val(moneda);
		console.log('Modena del precio: '+moneda);
	} 
})