$(function(){
	adicionarItemSuccess();
	$('#importe').keydown((e)=>{
		if(e.keyCode === 13 || e.keyCode === 9){
			e.preventDefault();
			if(!$('#moneda').val()){
				alert('Debe informar moneda');
			  }else if(!$('#importe').val() || Number($('#importe').val())=== Number(0) ){
				alert('debe informar el importe');
			}else if(!$('#condicion').val()){
				alert('Debe informar condición!');
			}else{
				agregarDetalle();	
			}
			
		}
	});
	
	
	function agregarDetalle(){
		let condicion = $('#condicion').val();
		let moneda = $('#moneda').val();
		let importe =$('#importe').val()
		let uuid = $('#uuid').val();
		
		$.ajax({
			url:'/planillas/adicionar/item',
			method:'POST',
			data:{condicion:condicion,moneda:moneda,importe:importe,uuid:uuid},
			success:adicionarItemSuccess,
			complete:vaciar,
			error:function(){
				alert('Error adicionando detalle!');
			}
		})
		
	
	}
	
	function vaciar(){
		$('#condicion').val();
		$('#moneda').val();
		$('#importe').val(0);
		$('#condicion').focus();
	}
	
	function adicionarItemSuccess(html){
		$('.js-item-planilla').html(html);
		
		$('.js-item-planilla-retorno').each(function(){
			let row = $(this);
			let decimales = row.find('.js-moneda-item-planilla').data('decimales');
			let importe = row.find('.js-importe-item-planilla').text()
			row.find('.js-importe-item-planilla').text($.number(importe,decimales,',','.'));
			
		});
		
		$('.btn-item-planilla-quitar').off('click').click(quitarItemPlanilla);
		
	}
	
	function quitarItemPlanilla(){
		let indice = $(this).data('indice');
		let uuid = $('#uuid').val();
		
		$.ajax({
			url:'/planillas/item/planilla/eliminar',
			method:'DELETE',
			data:{indice:indice,uuid:uuid},
			success:adicionarItemSuccess,
			error:()=>{
				alert('error eliminando item!!');
			}
		});
	}
})