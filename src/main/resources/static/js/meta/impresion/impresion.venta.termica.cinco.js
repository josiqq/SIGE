$(function(){
		var empresa;
		var ruc;
		var telefono;
		var cantidad;
		var cliente;
		var total;
		var detalle = [];
		$.ajax({
			url: '/parametros/buscar/1',
			method: 'GET',
			contentType: 'application/json',
			success:getParametroSuccess,
			errorr:function(){
				alert(`Error al recuperar parametros`);
			}
		  });
		function getParametroSuccess(param){
			empresa = param.empresa;
			ruc = param.ruc;
			telefono = param.telefono;
		}
	window.impresionVentaTermicaVentaCinco = function(registro){
		
		$.ajax({
				url:'/ventas/js/recuperar',
				method:'GET',
				data:{
					venta:registro
				},
				success:ventaSuccess,
				error:function(){
					alert('Error al recuperar venta');
				}
			});
		
		function ventaSuccess(ventas){
			let cantidad;
			let cliente;
			let total;
			let detalle = [];
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
				
				const precio = $.number(venta.precio,'0',',','.').padEnd(8);
				const subTotal = $.number((Number(venta.precio)*Number(venta.cantidad)),'0',',','.').padEnd(8);
				detalle.push(`<span class="det-producto">${venta.producto.nombre}</span>
								<span class="de-lado">${cantidad}</span>
								<span class="de-lado">${precio}</span>
								<span class="de-lado">${subTotal}</span>`)
							  
			});
			
			let host = window.location.hostname;
		let port = window.location.port;
		var htmlContent =`
			<html>
			<head>
				<style>
					body {
					    font-family: Arial, sans-serif;
					    margin: 0;
					    font-size: 12px;
					}
					.mas-grande{
						font-size: 18px;
					}
				   @page {
		                margin: 0; /* Elimina márgenes de la página impresa */
		            }
					.cabecera{
						display: block;
						width: 100%;
						padding-bottom: 1px;
					}
					.rayas{
						display: block;
						width: 100%;
						border-bottom: 1px dashed black;
						padding-top: 2px;
					}
					.de-lado{
						display: inline-block;
					    width: 1.4cm;
					    max-width: 1.6cm;
					    white-space: nowrap;
					}
					.det-producto{
						width: 4.7cm;
						display: block;
						padding-bottom: 1px;
					}
				</style>
			</head>
			<body>
				<div style="width: 5cm;">
		            <img src="http://${host}:${port}/fotos/logo_empresa.png" alt="Logo de la empresa" style="max-width: 100%; height: auto; margin: 0; padding-left: 0px;">
					<span class="cabecera">${empresa}</span>
					<span class="cabecera">Ruc: ${ruc}</span>
					<span class="cabecera">Telefono: ${telefono}</span>
					<span class="cabecera">Registro: ${registro}</span>
					<span class="cabecera">Cliente: ${cliente}</span>
					<span class="rayas"></span>
					<span class="rayas"></span>
					<div class="cabecera">
						<span class="de-lado">cant</span><span class="de-lado">precio</span><span class="de-lado">sub tot</span>
					</div>
					<div class="cabecera">
						${detalle.join('')}
					</div>
					<span class="rayas"></span>
					<span class="rayas"></span>
					<span class="cabecera mas-grande">Total: ${$.number(total,'0',',','.')}</span>
					<span class="rayas"></span>
					<span class="rayas"></span>
					<span class="cabecera">Este documento no tiene valor fiscal</span>	
	            </div>
	        </body>
            </html>
            `;
           
         let ventana = window.open('','','width=1200%,height=1200%');
         $(ventana.document.body).html(htmlContent);
         
         ventana.print();
    	 ventana.onafterprint= function(){
			ventana.close();	
		
		 }
		}
		
	}
})