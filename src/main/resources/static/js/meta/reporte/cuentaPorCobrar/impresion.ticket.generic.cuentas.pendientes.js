$(function(){
		var empresa_nombre ;
		var empresa_ruc ;
		var empresa_telefono ;
		var actividad ='' ;
	/*inicio de agregar parametros de empresa */
			$.ajax({
			url: '/parametros/buscar/1',
			method: 'GET',
			contentType: 'application/json',
			success:getParametroSuccess,
			errorr:function(){
				alert(`Error al recuperar parametros`);
			}
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
		
		/*fin de agregar parametros de empresa */
	window.recibirCuentas = function(cuentas){
		let detalle = [];
		let total =0 ;
		
		/*inicio de carcar cuentas detalle */
		cuentas.forEach(function(cuenta){
				total = Number(total)+Number(cuenta.saldo);
				const ventadet = $.number(cuenta.venta,'0',',','.').padEnd(8);
				const saldo = $.number(cuenta.saldo,'0',',','.').padEnd(11);
				const importe = $.number(cuenta.importe,'0',',','.').padEnd(11);
				const cobrado = $.number(cuenta.importeCobrado,'0',',','.').padEnd(10);
				detalle.push(`${cuenta.cliente} (${cuenta.fecha})\n${ventadet}${importe}${cobrado}${saldo}\n`)
							  
			});
		/*fin de cargar cuentas detalle */
		
		
		var htmlContent =`
			<pre>
			<span>          ${empresa_nombre}</span>
			<span>Ruc: ${empresa_ruc}</span>
			<span>Telefono: ${empresa_telefono}</span>
			
			----------------------------------------\n
			Venta   importe    cobrado   saldo\n
			----------------------------------------\n
			${detalle.join('')}\n
			----------------------------------------\n
			Total: ${$.number(total,'0',',','.')}\n
			----------------------------------------\n
			Este documento no tiene valor fiscal\n
			</span>	
            </pre>
            `;

		
		let ventana = window.open('','_blank','width=1200%,height=1200%');
		//ventana.document.write(htmlContent);
		$(ventana.document.body).html(htmlContent);
		 
        	ventana.print();
        	ventana.onafterprint= function(){
				ventana.close();	
			
    		}
    	
	}
})