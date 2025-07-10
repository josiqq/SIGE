$(function(){
	
	$('.js-excel').click(descargarExcel);
	var fecha = new Date(),
			horas = fecha.getHours(),
			minutos = fecha.getMinutes(),
			dia = fecha.getDate(),
			mes = fecha.getMonth()+1,
			year = fecha.getFullYear()
			hoy = dia+'-'+mes+'-'+year+'_'+horas+':'+minutos
			;
			
	function descargarExcel(){
		$("#tabla").table2excel({
		    exclude: ".quitar-excel",
		    name: "Documento",
		    filename: "extracto-instructor-"+hoy+".xls", 
		    preserveColors: false 
		});
	};
	
  

	
})