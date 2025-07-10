import {impresionCobroTermica} from '/js/meta/cobro/impresion.termica.cobro.js';

$(function(){
	if($('#registro').val()){
		impresionCobroTermica($('#registro').val());
	}
	
	$('.btn-imprimir').click(function(){
		impresionCobroTermica($(this).data('id'));
	})
})


/*$(function(){
	
	var es_generico;
	var es_cinco_cm;

	$.ajax({
			url:'/parametroVentas/js/getParametroVenta',
			method:'GET',
			success:getParametroVentaSuccess,
			errorr:function(){
				alert('Error recuperando parametros de venta venta');
			}
		});
	
	function getParametroVentaSuccess(param){
		es_generico = param.ticketGenerico;
		es_cinco_cm = param.ticketCinco;
	}
	
	let registro = $('#registro').val();
	
	if(registro){
		$.ajax({
			url:'/cobros/js/buscar/item/cobro',
			method:'GET',
			data:{cobro:registro},
			success:function(cobros) {
					    processCobros(cobros, 0);
					  },
			error:getItemCobroError
		})
	}
	
	
	function processCobros(cobros, index) {
	  if (index >= cobros.length) {
	    // Cuando se haya recorrido todo el array, terminar la recursión.
	    return;
	  }
	
	  const cobro = cobros[index];
	  if (cobro.venta.factura === true && cobro.venta.impreso === false) {
		 Swal.fire({
	      title: 'Desea imprimir la factura?',
	      text: "Al presionar si, se imprimirá la factura!",
	      icon: 'warning',
	      showCancelButton: true,
	      confirmButtonColor: '#3085d6',
	      cancelButtonColor: '#d33',
	      confirmButtonText: 'Si, imprimir!'
	    }).then((result) => {
	      if (result.isConfirmed) {
			//imprirmirFacturaAncha(cobro.venta.id);
	        imprimirFactura(cobro.venta.id);
	      }
	
	      processCobros(cobros, index + 1);
	    });
	         
		} else if (cobro.venta.factura === false && cobro.venta.impreso === false) {
			Swal.fire({
		      title: 'Desea imprimir la nota Común ?',
		      text: "Al presionar si, mandará una impresión!",
		      icon: 'warning',
		      showCancelButton: true,
		      confirmButtonColor: '#3085d6',
		      cancelButtonColor: '#d33',
		      confirmButtonText: 'Si, imprimir!'
		    }).then((result) => {
		      if (result.isConfirmed) {
					
				  if(es_generico===true){
					
		        	imprimirNotaComun(cobro.venta.id)
				  }else{
					  if(es_cinco_cm === true){
						  impresionVentaTermicaVentaCinco(cobro.venta.id);
					  }else{
						impresionCobroTermica(cobro.venta.id);
					}
				  }
		      }
		
		      processCobros(cobros, index + 1);
		    });
		}else{
			// Si no cumple la condición, simplemente avanzamos a la siguiente iteración.
	   		 processCobros(cobros, index + 1);	    
	  }
	}
	
	
	
	function getItemCobroError(error){
		alert(`Error al recuperar cobro para impresion ${error}`);
	}
})*/