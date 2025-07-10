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
	
	 window.imprimirFactura = function(registro){
		let ticket;
		let detalle = [];
		let cliente ;
		let fecha ;
		let timbrado;
		let fecha_ini;
		let fecha_fin;
		let documento;
		let total=0 ;
		let condicion;
		let nro_factura;
		let cantidad =0;
		let gravado_cinco =0;
		let gravado_dies =0;
		let impuesto_cinco =0;
		let impuesto_dies = 0;
		if(registro){
			
			$.ajax({
				url:'/ventas/js/getFactura',
				method:'GET',
				data:{
					id:registro
				},
				success:facturaSuccess,
				error:facturaError
			});
		}
		function facturaSuccess(facturas){
			console.log(facturas);
			
			facturas.forEach(function(factura){
				cliente = factura.cliente;
				fecha = factura.fecha;
				timbrado = factura.timbrado;
				fecha_ini = factura.fechaInicio;
				fecha_fin = factura.fechaFin;
				documento = factura.documento;
				condicion = factura.condicion;
				nro_factura = factura.factura;
				total = Number(total)+Number(factura.subTotal);
				impuesto_cinco = Number(impuesto_cinco)+Number(factura.impuestoCinco);
				impuesto_dies = Number(impuesto_dies)+Number(factura.impuestoDies);
				gravado_cinco = Number(gravado_cinco)+Number(factura.gravadoCinco);
				gravado_dies = Number(gravado_dies)+Number(factura.gravadoDies);
				const codigo = factura.codigo.padEnd(14);
				
				if(factura.pesable === true){
					cantidad = $.number(factura.cantidad,'3',',','.').padEnd(5);	
				}else{
					cantidad = $.number(factura.cantidad,'0',',','.').padEnd(5);
				}
				 
				const precio = $.number(factura.precio,'2',',','.').padEnd(10);
				const subTotal = $.number(factura.subTotal,'2',',','.').padEnd(11);
				detalle.push(`${factura.producto}\n${codigo}${cantidad}${precio}${subTotal}\n`)
							  
			});
			
			ticket = `
			<pre>
				<span>${empresa_nombre}</span>
				<span>Ruc: ${empresa_ruc}</span>
				<span>Telefono: ${empresa_telefono}</span>
				<span>----------------------------------------</span>	
				<span>${actividad}</span>
				<span>----------------------------------------</span>
				<span>Timbrado: ${timbrado}</span>
				<span>Inicio vigencia: ${fecha_ini}</span>
				<span>fin de vigencia: ${fecha_fin}</span>
				<span>----------------------------------------</span>
				Fctura: ${condicion} ${nro_factura}\n
				<span>Registro: ${registro}\n
				Cliente: ${cliente}\n
				Ruc: ${documento}
				Fecha: ${fecha}\n
				----------------------------------------\n
				Codigo       Cant  precio    subTotal\n
				----------------------------------------\n
				${detalle.join('')}\n
				----------------------------------------\n
				Total: ${$.number(total,'2',',','.')}\n
				----------------------------------------\n
				Liquidacion IVA \n
				Gravado 10: ${$.number(gravado_dies,'2',',','.')}\n
				Impuesto 10: ${$.number(impuesto_dies,'2',',','.')}\n
				Gravado 5: ${$.number(gravado_cinco,'2',',','.')}\n
				Impuesto 5: ${$.number(impuesto_cinco,'2',',','.')}\n
				----------------------------------------\n
				Gracias por su preferencia \n
				
				""
			</span>	
			</pre>
		`
		let w = window.open();
	    $(w.document.body).html(ticket);
	    w.document.getElementsByTagName('head')[0].innerHTML += 
		'<style>@page { size: auto; margin: 5px; } body { margin: 0;font-family: Arial, Helvetica, sans-serif; }</style>';
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
			
		}
		function facturaError(error){
			alert(`Error al recuperar la factura: ${error}`);
		}
		
		
	}
});