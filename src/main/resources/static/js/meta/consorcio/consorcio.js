$(function(){
	window.onkeydown = presionaTecla;
	 $('.date').mask('00/00/0000');
	 $('.money').mask("#.##0", {reverse: true});
	 let fecha_inicio = $('#fechaInicio');
	 let fecha_fin = $('#fechaFin');
	 let monto_mensual = $('#montoMensual');
	 
	 fecha_fin.keydown((e)=>{
		if(e.keyCode === 13 || e.keyCode === 9){
			if(!fecha_inicio.val() || !fecha_fin.val()){
				fecha_inicio.focus();
			}else{
				e.preventDefault();			
				calcularMeses();
	
			}
		} 
	 });
	 
	 fecha_fin.change(calcularMeses);
	 function calcularMeses(){
		 function parseAndFormatDate(dateString) {
			      let dateObj = moment(dateString, "DD/MM/YYYY");
			      return dateObj.format("YYYY-MM-DD");
			    }

			    let fecha_inicio_format = parseAndFormatDate(fecha_inicio.val());
			    let fecha_fin_format = parseAndFormatDate(fecha_fin.val());
				let startDate = moment(fecha_inicio_format);
	    		let endDate = moment(fecha_fin_format);
	    		let diffInMonths = endDate.diff(startDate, 'months', true);
	    		let cantidad_meses = Math.floor(diffInMonths);
	    		$('#meses').val(Number(cantidad_meses)+Number(1));
	    		monto_mensual.focus();
	 }
	 
	 function presionaTecla(e){
		switch(e.keyCode){
			case 13:
				e.preventDefault()
			break
		}	 
	 }
});