$(function(){
	console.log('buscar venta...');
	$('.date').mask('00/00/0000');
	$('#fechaDesde').click(()=>{
		$('#fechaDesde').select();
	});
	$('#fechaHasta').click(()=>{
		$('#fechaHasta').select();
	})	
	//inicio actividad en detalle de venta en busqueda
	$('.btnDetalle').click(mostrarDetalle);
	function mostrarDetalle(e){
		$('.money').mask("#.##0", {reverse: true});
		$('.btnDetalle').attr('disabled','true');
		$(e.currentTarget.parentNode.children[1]).addClass('contenedor-tabla-detalle');
		$(e.currentTarget.parentNode.children[1]).removeClass('noMostrar');
		$('.cerrar-detalle').click(()=>{
			$(e.currentTarget.parentNode.children[1]).removeClass('contenedor-tabla-detalle');
		$(e.currentTarget.parentNode.children[1]).addClass('noMostrar');
		$('.btnDetalle').removeAttr('disabled');
		})
		
		window.onkeydown = teclaDetalle;
		function teclaDetalle(eve){
			
			if(eve.keyCode === 27){
				$(e.currentTarget.parentNode.children[1]).removeClass('contenedor-tabla-detalle');
				$(e.currentTarget.parentNode.children[1]).addClass('noMostrar');
				$('.btnDetalle').removeAttr('disabled');
			}
		}
	}
	//fin de actividad en detalle de de venta en busqueda
	
	
	 const trElements = document.querySelectorAll('tr[data-pesable]');

    // Iterar a través de cada elemento tr
    trElements.forEach((trElement) => {
    // Obtener el valor del atributo data-pesable
    const esPesable = trElement.getAttribute('data-pesable') === 'true';
	
    // Si es pesable, agregar la clase que contiene la máscara de decimales, de lo contrario agregar la clase que contiene la máscara de enteros
    if (esPesable) {
		$(trElement.children[2]).addClass('money2');
		$('.money2').mask("#.##0,00", {reverse: true});
      
    } else {
		$(trElement.children[2]).text($.number($(trElement.children[2]).text(),'0',',','.'));
    }
  });
  
  $('.js-tabla-buscar-venta').each(function(){
	let totalVenta = $(this).find('.js-venta-total').text();
	$(this).find('.js-venta-total').text($.number(totalVenta,'2',',','.'));
})
	
});