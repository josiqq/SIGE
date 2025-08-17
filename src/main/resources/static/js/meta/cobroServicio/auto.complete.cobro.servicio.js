console.log('auto complete cobro servicio..');
if($('#idCliente').val()>0 && $('#id').val()===''){
	buscarSaldoCliente($('#idCliente').val());
//console.log('id del cliente',$('#idCliente').val());
}
var cliente = $('.js-cliente');
	var idCliente = $('#idCliente');
	var options = {
		
  		url: function(nombreDocumento){
				 return "/clientes?nombreDocumento="+nombreDocumento;
			},
  		getValue: "nombre",
  		minCharNumber:2,
  		requestDelay:200,
  		ajaxSettings:{
			contentType:'application/json'
		},
		list:{
				onChooseEvent: ItemSeleccionado
			}
	};

	cliente.easyAutocomplete(options);
	
	function ItemSeleccionado(){
		idCliente.val(cliente.getSelectedItemData().id);
		buscarSaldoCliente(idCliente.val());
		
	};
	
	function buscarSaldoCliente(id){
		console.log('buscar saldo por este id=>',id)
		var respuestaSaldo = $.ajax({
			url:'/clientes/buscarSaldo',
			method:'GET',
			data:{id:id}
		});
		respuestaSaldo.done(llegoRespuestaSaldo);
	};
	
	function llegoRespuestaSaldo(evento){
		if(evento>0){
			
			$('.caja-saldo').removeClass('noMostrar');
			$('.caja-valor').removeClass('noMostrar');
			$('.js-saldo').val(evento);
			$('.js-importe').attr('readonly', 'readonly');
			let diferencia =0;
			$('.js-valor').keyup((e)=>{
				
				diferencia = Number($('.js-valor').unmask().val())-Number(evento);
				
				if(diferencia >0){
					
					$('.js-importe').removeAttr('readonly');
					$('.js-importe').val(diferencia);
				}else{
					
					$('.js-importe').attr('readonly','readonly');
					$('.js-importe').val(0);
				}
			});
		}else{
			$('.caja-saldo').addClass('noMostrar');
			$('.caja-valor').addClass('noMostrar');
			$('.js-saldo').val(0);
			$('.js-importe').removeAttr('readonly');
		}
	}