$(function(){
	let cobro_importe_importe = $('.js-cobro-iporte-importe');
	let cobro_importe_vuelto = $('.js-cobro-iporte-vuelto');
	let cobro_importe_cuenta = $('.js-cuenta');
	$('.js-cobro-iporte-vuelto').val(0);
	adicionarItemImporteSuccess();
	cobro_importe_cuenta.keydown((e)=>{
		if(e.keyCode === 13){
			e.preventDefault();
			cobro_importe_importe.focus();
		}
	})
	
	 cobro_importe_importe.click(()=>{
		 cobro_importe_importe.select();
	 });
	 
	 cobro_importe_importe.focus(()=>{
		 cobro_importe_importe.select();
	 })
	 
	 cobro_importe_importe.keydown((e)=>{
		 if(e.keyCode === 13){
			 e.preventDefault();
			 let importe_cobrado = $('.js-cobro-iporte-importe').val();
			 let cobrado = parseFloat(importe_cobrado.replace(/[^0-9,-]+/g,""))
			 if(Number(cobrado)<Number($('#saldo').val())){
				$('.js-cobro-iporte-vuelto').val(0); 
			 }else{
			 	$('.js-cobro-iporte-vuelto').val(Number(cobrado)-Number($('#saldo').val()));
			 } 
			 cobro_importe_vuelto.focus();
		 }
	 })
	 
	 cobro_importe_vuelto.keydown((e)=>{
		 if(e.keyCode === 13){
			 e.preventDefault();
			 adicionarItemImporte();
		 }
	 });
	 
	 function adicionarItemImporte(){
		 let cuenta = $('.js-cuenta').val() ;
		 let importe = $('#saldo').val();
		 let importe_cobrado = $('.js-cobro-iporte-importe').val();
		 
		 let vuelto = $('.js-cobro-iporte-vuelto').val();
		 let uuid = $('#uuid').val();
		
		 $.ajax({
			url:'/cobros/js/adicionar/item/importe',
			method:'POST',
			data:{cuenta:cuenta,importe:importe,importeCobrado:importe_cobrado,vuelto:vuelto,uuid:uuid},
			success:adicionarItemImporteSuccess,
			error:adicionarItemImporteError 
		 });
	 }
	 
	 function adicionarItemImporteSuccess(html){
		 $('.js-item-cobro-importe').html(html);
		 
		 let cuenta = $('.js-cuenta-item');
		 let importe_cobrado = $('.js-pago-importe-cobrado-item');
		 let btn_quitar = $('.btn-item-quitar-importe');
		 $('.money').mask("#.##0", {reverse: true});
		 vaciar();
		 $('.js-cuenta').focus();
		 
		 $('#cobrado').val($('.js-tabla-item-importe').data('total'));
		 let cobrado = $('#cobrado').val();
		 let total = $('#total').val();
		 let saldo =0;
		 $('.cobrado-cobro').text($.number(cobrado,'0',',','.'));
		 $('#saldo').val(Number(total)-Number(cobrado));
		 if(Number($('#saldo').val())>0){
			 saldo = $('#saldo').val();	 
		 }else{
			 saldo =0;
		 }
		 
		 $('.saldo-cobro').text($.number(saldo,'0',',','.'));
		 
		 cuenta.change((e)=>{
			 let cuenta = e.currentTarget.value;
			 let indice = e.currentTarget.dataset.indice;
			 let importe = e.currentTarget.parentNode.parentNode.children[2].children[0].value;
			 let uuid = $('#uuid').val();
			 modificarItemImporte(cuenta,importe,indice,uuid);
		 });
		 
		 importe_cobrado.keydown((e)=>{
			 if(e.keyCode === 13){
				e.preventDefault();
				let cuenta = e.currentTarget.parentNode.parentNode.children[0].children[0].value;
				let importe = e.currentTarget.value;
				let indice = e.currentTarget.dataset.indice;
				let uuid = $('#uuid').val();
				modificarItemImporte(cuenta,importe,indice,uuid);	 
			 }
			 
		 });
		 
		 importe_cobrado.change((e)=>{
			 let cuenta = e.currentTarget.parentNode.parentNode.children[0].children[0].value;
				let importe = e.currentTarget.value;
				let indice = e.currentTarget.dataset.indice;
				let uuid = $('#uuid').val();
				modificarItemImporte(cuenta,importe,indice,uuid);	
		 });
		 
		 btn_quitar.click((e)=>{
			let indice =  e.currentTarget.dataset.indice;
			let uuid = $('#uuid').val();
			quitarItemImporte(indice,uuid);
		 });
	 }
	 
	 function quitarItemImporte(indice,uuid){
		 $.ajax({
			url:'/cobros/js/eliminar/item/importe',
			method:'DELETE',
			data:{indice,uuid},
			success:adicionarItemImporteSuccess,
			error:quitarItemImporteError
		 });
	 }
	 
	 function quitarItemImporteError(error){
		 alert(`Error al eliminar item de importe : ${error}`);
	 }
	 
	 function  modificarItemImporte(cuenta,importe,indice,uuid){
		 $.ajax({
			url:'/cobros/js/modificar/item/importe',
			method:'PUT',
			data:{cuenta:cuenta,importeCobrado:importe,indice:indice,uuid:uuid},
			success:adicionarItemImporteSuccess,
			error:modificarItemImporteError
		 });
	 }
	 
	 function modificarItemImporteError(error){
		 alert(`Error al modificar detalle de pago:${error}`);
	 }
	 
	 function adicionarItemImporteError(error){
		 alert(`Error al insertar detalle de importe :${error}`);
	 }
	 
	 function vaciar(){
		 $('.js-cobro-iporte-importe').val(0);
		 $('.js-cobro-iporte-vuelto').val(0);
	 }
});