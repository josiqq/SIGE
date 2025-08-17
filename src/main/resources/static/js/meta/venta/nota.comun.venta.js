$(function(){
	var empresa_nombre ;
	var empresa_ruc ;
	var empresa_telefono ;
	var actividad ='' ;
	
	$.ajax({
		url: '/parametros/buscar/1',
		method: 'GET',
		contentType: 'application/json',
		success:getParametroSuccess,
		errorr:getParametroError
	  });
		
	function getParametroSuccess(parametros){
		 empresa_nombre = parametros.empresa;
		 empresa_ruc = parametros.ruc;
		 empresa_telefono = parametros.telefono;
		 
		 for (var i = 0; i < parametros.actividad.length; i++) {
		    actividad += parametros.actividad[i];
		    if ((i + 1) % 40 === 0) {
		      actividad += "\n";
		    }
		}
		
	};
	
	function getParametroError(error){
		alert(`Error al recuperar parametro: ${error}`);
	}
	window.imprimirNotaComun = function(registro){
		
		let ticket;
		let detalle = [];
		let cliente ;
		let fecha ;
		let total ;
		let cantidad = 0;
		
		if(registro){
			console.log('esta buscarndo para recuperar ?')
			$.ajax({
				url:'/ventas/js/recuperar',
				method:'GET',
				data:{
					venta:registro
				},
				success:ventaSuccess,
				error:ventaError
			});
		}
		
		function ventaSuccess(ventas){
			
			ventas.forEach(function(venta){
				
				if(venta.producto.pesable === true){
					cantidad = $.number(venta.cantidad,'3',',','.').padEnd(8);	
				}else{
					cantidad = $.number(venta.cantidad,'0',',','.').padEnd(8);
				}
				
				cliente = venta.venta.cliente.nombre;
				fecha = venta.venta.fecha;
				total = venta.venta.total;
				const codigo = venta.producto.codigo.padEnd(15);
				
				const precio = $.number(venta.precio,'2',',','.').padEnd(8);
				const subTotal = $.number((Number(venta.precio)*Number(venta.cantidad)),'2',',','.').padEnd(8);
				detalle.push(`${venta.producto.nombre}\n${codigo}${cantidad}${precio}${subTotal}\n`)
							  
			});
		 ticket = `
			<pre>
			<span>${empresa_nombre}</span>
			<span>Ruc: ${empresa_ruc}</span>
			<span>Telefono: ${empresa_telefono}</span>
			<span>----------------------------------------</span>	
			<span>Registro: ${registro}\n
			Cliente: ${cliente}\n
			Fecha: ${fecha}\n
			----------------------------------------\n
			Codigo         Cant    precio  subTotal\n
			----------------------------------------\n
			${detalle.join('')}\n
			----------------------------------------\n
			Total: ${$.number(total,'2',',','.')}\n
			----------------------------------------\n
			Este documento no tiene valor fiscal\n
			</span>	
            </pre>
            `;
	    var w = window.open();
	    $(w.document.body).html(ticket);
		w.document.getElementsByTagName('head')[0].innerHTML += 
		'<style>@page { size: auto; margin: 5px; } body { margin: 0;font-family: Arial, Helvetica, sans-serif; }</style>';
	    // Imprimir ticket
	    w.print();
	     w.onafterprint = function() {
       		 w.close();
       		 $.ajax({
					url:'/ventas/js/update/impreso',
					method:'PUT',
					data:{id:registro},
					error:function(error){
						alert(`Error, al actualizar impresion en venta: ${error} `);
					}
				})
   		};
	    
		};
		function ventaError(error){
			console.log('tiene eror',error);
		}
		
		
		
	};
	
});