import  {getPlanillasByFecha} from '/js/meta/planilla/planilla.queries.js';

$(function(){
	$('#fecha').change(buscarPlanilla);
	function buscarPlanilla(){
		formatearSelecctPlanilla();
		let fecha = $(this).val();
		getPlanillasByFecha(fecha).then(
			function(planillas){
				listarPlanillas(planillas);
			}
		).catch(
			function(error){
				console.log('Error recuperando planillas!',error);
				alert('Error recuperando planillas!');
			}
		);
		
	}
	
	function listarPlanillas(planillas){
		
		let listaPlanilla ='';
		planillas.forEach(function(planilla){
			listaPlanilla +=`<option value="${planilla.id}">${planilla.id} - ${planilla.cuenta.nombre}</option>`			
		})
		
		$('.js-lista-planilla').append(listaPlanilla);
		
		
	}
	
	function formatearSelecctPlanilla(){
		$('.js-lista-planilla').html(`<option value="">seleccionar</option>`);
	}
});