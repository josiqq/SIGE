export function tablaBuscadorCliente(indice){
	 
	 $('#nombre-cliente-modal').trigger('focus');
    let  indiceAFocus = indice;
    let tamaño = $('.tabla-lista-clientes tr').length-1;
   
    $('.tabla-fila').click(function(){
        $('.tabla-lista-clientes tr').eq(indiceAFocus).removeClass('resaltado');
        let indice = $(this).index();
        indiceAFocus = indice;
        $('.tabla-lista-clientes tr').eq(indiceAFocus).addClass('resaltado');
    });
    
    $('.js-nombreDocumento').off('keydown').keydown(function(e){
        if(e.keyCode === 40 && indiceAFocus<tamaño){
                $('.tabla-lista-clientes tr').eq(indiceAFocus).removeClass('resaltado');
                indiceAFocus++;  
                $('.tabla-lista-clientes tr').eq(indiceAFocus).addClass('resaltado');
          	   $('.tabla-gen-cliente').scrollTop($('.tabla-gen-cliente').scrollTop() + $('.tabla-gen-cliente').find('.tr-busqueda-cliente-modal').height());
        }

        
        if(e.keyCode === 38 && indiceAFocus>0){
            $('.tabla-lista-clientes tr').eq(indiceAFocus).removeClass('resaltado');
            indiceAFocus--;
            $('.tabla-lista-clientes tr').eq(indiceAFocus).addClass('resaltado');
          $('.tabla-gen-cliente').scrollTop($('.tabla-gen-cliente').scrollTop() - $('.tabla-gen-cliente').find('tr').height());
        }

        if(e.keyCode === 13 && indiceAFocus >-1){
            $('#cliente-nombre').val($('.tabla-lista-clientes tr').eq(indiceAFocus).find('.td-nombre-cliente').text());
            $('#id-cliente').val($('.tabla-lista-clientes tr').eq(indiceAFocus).find('.td-id-cliente').text()).change();
            $('#modal-buscar-cliente').modal('hide');
            indiceAFocus = -1;
        }
    })
    $('#modal-buscar-cliente').on('hide.bs.modal',function(){
		
		$('.tabla-lista-clientes').html('');
		$('#nombre-cliente-modal').val('');
		
		$('.tabla-gen-cliente').scrollTop(0);
		tamaño = 0
	});
  
}