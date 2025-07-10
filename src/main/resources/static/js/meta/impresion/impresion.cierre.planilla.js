import { getPlanillaImportes,getItemPlanillaRendicion } from '/js/meta/planilla/planilla.queries.js';
export function imprimirCierrePlanilla(idPlanilla) {
	
	getPlanillaImportes(idPlanilla).then(
			function(planillasImportes) {
				console.log('Listado de importes: ', planillasImportes);
				llenarPlanillaImportes(planillasImportes,idPlanilla);
			}
		).catch(
			function(error) {
				alert('Error recuperando listado de importes!');
				console.log('Error recuperando importes: ', error);
			}
		);
		
		function llenarPlanillaImportes(planillasImportes,idPlanilla) {
		let listaPlanillaImportes = [];
		let importeNeto = 0;
		let totalMonedaNacional = 0;
		let totalGeneral =0;
		planillasImportes.forEach(function(planillaImporte) {
			importeNeto = (Number(planillaImporte.cobro) + Number(planillaImporte.apertura) + Number(planillaImporte.masVuelto)) - Number(planillaImporte.menosVuelto);
			totalMonedaNacional = Number(importeNeto)*Number(planillaImporte.cotizacion);
			totalGeneral = Number(totalGeneral)+Number(totalMonedaNacional);
			listaPlanillaImportes.push(`
																<tr>
																	<td class="campos-lista-cobros">${planillaImporte.condicion}</td>
																	<td class="campos-lista-cobros">${planillaImporte.moneda}</td>
																	<td class="campos-lista-cobros">${$.number(planillaImporte.cobro, '2', ',', '.')}</td>
																	<td class="campos-lista-cobros">${$.number(planillaImporte.apertura, '2', ',', '.')}</td>
																	<td class="campos-lista-cobros">${$.number(planillaImporte.masVuelto, '2', ',', '.')}</td>
																	<td class="campos-lista-cobros">${$.number(planillaImporte.menosVuelto, '2', ',', '.')}</td>
																	<td class="campos-lista-cobros">${$.number(planillaImporte.transCredi, '2', ',', '.')}</td>
																	<td class="campos-lista-cobros">${$.number(planillaImporte.transDebi, '2', ',', '.')}</td>
																	<td class="campos-lista-cobros">${$.number(importeNeto, '2', ',', '.')}</td>
																	<td class="campos-lista-cobros">${$.number(planillaImporte.cotizacion, '2', ',', '.')}</td>
																	<td class="campos-lista-cobros">${$.number(totalMonedaNacional, '2', ',', '.')}</td>
																</tr>
			`);
		});
		let footerTotal =`<tr>
											<td colspan="8"></>
											<td class="campos-lista-cobros" >${$.number(totalGeneral, '2', ',', '.')}</>
										</tr>`
		$('.js-planilla-importes-impresion').html(listaPlanillaImportes+footerTotal);
		//$('#total').val($.number(totalGeneral, '2', ',', '.'));
		//cargarDatosSegunSistema(listaPlanillaImportes+footerTotal);
		buscarRendicion(idPlanilla,listaPlanillaImportes+footerTotal)
	}
	
	function buscarRendicion(idPlanilla,listaImportes){
		getItemPlanillaRendicion(idPlanilla).then(function(itemPlanillaRendiciones){
			cargarRendicion(itemPlanillaRendiciones,listaImportes);
		}).catch(function(error){
			alert('Error recuperando rendiciones!!');
			console.log('Error recuperando rendiciones: ',error);
		})
	}
	
	function cargarRendicion(itemRendicones,listaImportes){
		console.log(itemRendicones);
		let listaRendiciones =[];
		let fecha_cierre = '';
		let cajero_cierre ='';
		itemRendicones.forEach(function(item){
			fecha_cierre =  moment(item.planilla.fechaCierre).format('DD/MM/YYYY');
			cajero_cierre = item.planilla.usuario.cajero.nombre;
			listaRendiciones.push(`
					<tr>
						<td class="campos-lista-cobros">${item.condicion.nombre}</td>
						<td class="campos-lista-cobros">${item.moneda.sigla}</td>
						<td class="campos-lista-cobros">${$.number(item.importe,'2',',','.')}</td>
						<td class="campos-lista-cobros">${item.cuentaDestino.nombre}</td>
					</tr>
			`)
		})
		cargarDatosSegunSistema(listaImportes,listaRendiciones,fecha_cierre,cajero_cierre);
	}
	
	function cargarDatosSegunSistema(reviro,listaRendiciones,fecha_cierre,cajero_cierre){
		
	
	let htmlContent =` 
	<html>
            <head>
            	 <title>Cierre de caja</title>
            	 <link href="/css/bootstrap.min.css" rel="stylesheet">
            	 <link href="/css/meta/style.css?ver=0.1.7" rel="stylesheet">
            </head>
      <body>
      		<h3>Numero de planilla: ${idPlanilla}</h3>
      		<div class="col-md-12">
				<span> Fecha cierre: ${fecha_cierre}</span>
			</div>
			<div class="col-md-12 mb-2">
				<span> Cajero cierre: ${cajero_cierre}</span>
			</div>
			<hr>
      		<div class="table-responsive contenedor-lista-cobros mb-2">
      					
						<div >
							<h6>Cobro según sistema</h6>
						</div>
						<table class="table">
							<thead>
								<tr>
									<th class="campos-lista-cobros">Condición</th>
									<th class="campos-lista-cobros">Mon</th>
									<th class="campos-lista-cobros">Cobro</th>
									<th class="campos-lista-cobros">Aper</th>
									<th class="campos-lista-cobros"> Ing vuelto</th>
									<th class="campos-lista-cobros"> Eg vuelto</th>
									<th class="campos-lista-cobros"> Tran credi</th>
									<th class="campos-lista-cobros"> Tran debi</th>
									<th class="campos-lista-cobros">Importe neto</th>
									<th class="campos-lista-cobros">Cotiz</th>
									<th class="campos-lista-cobros">Mon Nac</th>
								</tr>
							</thead>
							<tbody class="js-planilla-importes-impresion">
								${reviro}
							</tbody>
						</table>
					</div>
					<div class="table-responsive contenedor-lista-rendicion">
					<h6>Detalle de rendición</h6>
						<table class="table">
							<thead>
								<tr>
									<th class="campos-lista-cobros">Condición</th>
									<th class="campos-lista-cobros">Moneda</th>
									<th class="campos-lista-cobros">Importe</th>
									<th class="campos-lista-cobros"S>Cuenta destino</th>
								</tr>
							</thead>
							<tbody class="js-planilla-rendicion">
								${listaRendiciones}
							</tbody>
						</table>
					</div>
					
					<div class=" contenedor-cajero-tesorero ">
							<span  class=" con-linea linea-cajero">Cajero</span>
							<span class=" con-linea linea-tesorero">Tesorero</span>
					</div>
      </body>
    </html>
            `;
	let ventana = window.open('', '', 'width=1200%,height=1200%');
	$(ventana.document.body).html(htmlContent);
				ventana.onafterprint = function() {
					ventana.close();
				};
	setTimeout(function() {
		ventana.print();

	}, 500);
	}
}