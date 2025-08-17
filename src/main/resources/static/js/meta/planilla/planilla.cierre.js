import { getPlanillaImportes } from '/js/meta/planilla/planilla.queries.js';
import {formatearNumeroInput} from '/js/meta/utilidades.js';
import {imprimirCierrePlanilla} from '/js/meta/impresion/impresion.cierre.planilla.js';
$(function() {
	formatearNumeroInput($('.formato-numero'));
	$('#planilla').focus();
	$('#planilla').change(cambioPlanilla);
	if($('#id').val()){
		console.log('tiene id cargado ');
		//$('#planilla').val($('#id').val());
		getPlanillaImportes($('#id').val()).then(
			function(planillasImportes) {
				console.log('Listado de importes: ', planillasImportes);
				llenarPlanillaImportes(planillasImportes);
			}
		).catch(
			function(error) {
				alert('Error recuperando listado de importes!');
				console.log('Error recuperando importes: ', error);
			}
		);
	}
	function cambioPlanilla() {
		let planilla = $(this).val();
		$("#id").val(planilla);
		$('#cuenta-origen').val($('option:selected',this).data('cuenta'));
		let fecha = moment($('option:selected',this).data('fecha')).format('DD/MM/YYYY')
		$('#fecha').val(fecha);
		getPlanillaImportes(planilla).then(
			function(planillasImportes) {
				llenarPlanillaImportes(planillasImportes);
			}
		).catch(
			function(error) {
				alert('Error recuperando listado de importes!');
				console.log('Error recuperando importes: ', error);
			}
		);
	}

	function llenarPlanillaImportes(planillasImportes) {
		let listaPlanillaImportes = [];
		let importeNeto = 0;
		let totalMonedaNacional = 0;
		let totalGeneral =0;
		planillasImportes.forEach(function(planillaImporte) {
			importeNeto = (Number(planillaImporte.cobro) + Number(planillaImporte.apertura) +
			 Number(planillaImporte.masVuelto)+Number(planillaImporte.transCredi)) - (Number(planillaImporte.menosVuelto)+Number(planillaImporte.transDebi));
			totalMonedaNacional = Number(importeNeto)*Number(planillaImporte.cotizacion);
			totalGeneral = Number(totalGeneral)+Number(totalMonedaNacional);
			listaPlanillaImportes.push(`
																<tr>
																	<td>${planillaImporte.condicion}</td>
																	<td>${planillaImporte.moneda}</td>
																	<td>${$.number(planillaImporte.cobro, '2', ',', '.')}</td>
																	<td>${$.number(planillaImporte.apertura, '2', ',', '.')}</td>
																	<td>${$.number(planillaImporte.masVuelto, '2', ',', '.')}</td>
																	<td>${$.number(planillaImporte.menosVuelto, '2', ',', '.')}</td>
																	<td>${$.number(planillaImporte.transCredi, '2', ',', '.')}</td>
																	<td>${$.number(planillaImporte.transDebi, '2', ',', '.')}</td>
																	<td>${$.number(importeNeto, '2', ',', '.')}</td>
																	<td>${$.number(planillaImporte.cotizacion, '2', ',', '.')}</td>
																	<td>${$.number(totalMonedaNacional, '2', ',', '.')}</td>
																</tr>
			`);
		});
		let footerTotal =`<tr>
											<td colspan="8"></>
											<td >${$.number(totalGeneral, '2', ',', '.')}</>
										</tr>`
		$('.js-planilla-importes').html(listaPlanillaImportes+footerTotal);
		$('#total').val($.number(totalGeneral, '2', ',', '.'));
		$('#condicion').focus();
		adicionarItemParaCierre();
	}
	
	function adicionarItemParaCierre(){
		let planilla =$('#planilla').val();
		let uuid =$('#uuid').val();
		$.ajax({
			url:'/planillas/js/adicionarItemParaCierre',
			method:'POST',
			data:{idPlanilla:planilla,uuid:uuid},
			error:function(){
				alert('Error adicionando item planilla!');
			}
		})
	}
	
	if($('#registro').val()){
		imprimirCierrePlanilla($('#registro').val());	
	}
})