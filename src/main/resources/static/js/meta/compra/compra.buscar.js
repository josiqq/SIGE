$(function(){
	console.log('buscar compra...');
	
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
  
  $('.tabla-compra-tbody-tr').each(function(){
	let total = $(this).find('.js-total-compra').text();
	$(this).find('.js-total-compra').text($.number(total,'2',',','.'));
})
  
});