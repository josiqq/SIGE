$(function(){
	$(".item-cliente-consorcio").click(function(e) {
    	$('.contenedor-item-consorcio-importe').css('display','block');
    	$('.box-item-consorcio-importe').css('height', '500px');
    	buscarItemConsorcioImporte(e.currentTarget.dataset.id,e.currentTarget.dataset.consorcio);
  	});
  	
  	$('.btn-cerrar-item-consorcio-importe').click(()=>{
		  $('.contenedor-item-consorcio-importe').css('display','none');
		  $('.box-item-consorcio-importe').css('height', '0px');
	 });
	 
	 function buscarItemConsorcioImporte(cliente,consorcio){
		 $.ajax({
			 url:'/consorcios/itemConsorcioImporte/lista',
			 method:'GET',
			 data:{cliente:cliente,consorcio:consorcio},
			 success:itemConsorcioImporteSuccess,
			 error:function(error){
				 alert(`Error al recuperar deudas: ${error}`);
			 }
		 })
	 }
	  
	 function itemConsorcioImporteSuccess(items){
		 let deudas = [];
		 let cliente ;
		 let fecha ;
		 let monto;
		 let monto_cobrado;
		 let saldo;
		 
		 items.forEach(function(item){
			 cliente = item.itemConsorcio.cliente.nombre;
			 fecha = moment(item.fecha).format("DD/MM/YYYY");
			 monto = $.number(item.monto,'0',',','.');
			 monto_cobrado = $.number(item.montoCobrado,'0',',','.');
			 saldo =$.number(item.saldo,'0',',','.');
			 deudas.push(`
			 			<tr>
							<td class="id-consorcio">${item.id}</td>
							<td>${fecha}</td>
							<td class="monto">${monto}</td>
							<td class="cobrado-actual">${monto_cobrado}</td>
							<td class="saldo">${saldo}</td>
							<td class="cobrar-linea-consorcio" data-id="${item.id}"><a href="#"><i class="bi bi-cash-stack"></i></a></td>
							<td class="quitar-cobro" data-id="${item.id}"><a href="#"><i class="bi bi-cloud-minus"></i></a></td>
						</tr>
			 		`);
		 });
		 
		 $('.cliente-tem-consorcio-importe').text(cliente);
		 $('.tb-item-consorcio-importe').html(deudas);
		 
		  var tabla = document.getElementById("tabla");
		  var filas = tabla.getElementsByTagName("tr");
		  
		  for (var i = 1; i < filas.length; i++) {
		    var saldo_fi = parseFloat(filas[i].getElementsByTagName("td")[4].textContent); 
		    if (saldo_fi === 0) {
		      filas[i].style.backgroundColor = "green";
		      filas[i].style.color = "#fff";
		    }
		  }

		 $('.cobrar-linea-consorcio').click(cobrarConsorcioLinea);
		 $('.quitar-cobro').click(quitarCobro);
	 }
	 
	 function quitarCobro(e){
		let fila = $(e.target).closest('tr');
		let id_consorcio = fila.find('.id-consorcio').text();
		fila.find('.cobrado-actual').text(0);
		fila.find('.saldo').text(fila.find('.monto').text());
		let monto_cobrado = fila.find('.cobrado-actual').text();
		let saldo = fila.find('.saldo').text();
		fila.css('background-color', 'transparent');
		fila.css('color', '#000000');
		let cuenta = $('.cuenta-cobro-consorcio ').val();
		
		$.ajax({
				url:'/consorcios/cobro/consorcio/importe',
				method:'put',
				data:{
					id_consorcio:id_consorcio,
					montoCobrado:monto_cobrado,
					saldo:saldo,
					cuenta:cuenta
				},
				error:function(error){
					alert(`Error actualizando cobro de consorcio: ${error}`);
				}
			})
		
	}
	 
	 function cobrarConsorcioLinea(e){
		 let fila = $(e.currentTarget).closest('tr');
		 let cobrado_actual = parseInt(fila.find('.cobrado-actual').text().replace(/[^0-9,-]+/g,""));
		 let saldo = fila.find('.saldo').text();
		 let saldo_actual = parseInt(fila.find('.saldo').text().replace(/[^0-9,-]+/g,""));
		 let id_consorcio = fila.find('.id-consorcio').text();
		 
		 $('.contenedor-cobro-consorcio').css('display','block');
		 $('.input-input-cobro-consorcio').focus();
		 $('.input-input-cobro-consorcio').off('click').click(()=>{
			$('.input-input-cobro-consorcio').select();
		  })
		 $('.input-input-cobro-consorcio').val(saldo);
		 
		 $('.btn-cobrar-consorcio').off('click').click(()=>{
			 $('.contenedor-cobro-consorcio').css('display','none');
			 let cobrado_new = parseInt($('.input-input-cobro-consorcio').val().replace(/[^0-9,-]+/g,"")); 
			 let total_cobrado = cobrado_new+cobrado_actual;
			 let saldo_new = saldo_actual-cobrado_new;
			 let cuenta = $('.cuenta-cobro-consorcio ').val();
			 fila.find('.saldo').text($.number(saldo_new,'0',',','.'));
			 fila.find('.cobrado-actual').text($.number(total_cobrado,'0',',','.'));
			 if(saldo_new === Number(0)){
				fila.css('background-color', 'rgb(39, 100, 40)');
			 	fila.css('color', 'rgb(252, 223, 223)');	
			}
			
			
			
			$.ajax({
				url:'/consorcios/cobro/consorcio/importe',
				method:'put',
				data:{
					id_consorcio:id_consorcio,
					montoCobrado:total_cobrado,
					saldo:saldo_new,
					cuenta:cuenta
				},
				error:function(error){
					alert(`Error actualizando cobro de consorcio: ${error}`);
				}
			})
			 
		 });
		 
		 $('.btn-cancelar-consorcio').click(()=>{
			$('.contenedor-cobro-consorcio').css('display','none');	
		});
	 }
	 
	 
})