$(function(){
	window.onkeydown = presionaTecla;
	
	function presionaTecla(e){
		
		if(e.keyCode ===32){
			window.location.href= "/";
		}
	}
	
	var respuesta = $.ajax({
						url: '/parametros/buscar/1',
						method: 'GET',
						contentType: 'application/json',
					  });
							  
	respuesta.done(busquedaFinalizada);
	
	function busquedaFinalizada(parametro){
		if(parametro.mostrarHora == false){
			$('#reloj-wrap').addClass('noMostrar');
		}
	}
	

	var actualizarHora = function(){
		var fecha = new Date(),
			horas = fecha.getHours(),
			ampm,
			minutos = fecha.getMinutes(),
			segundos = fecha.getSeconds(),
			diaSemana = fecha.getDay(),
			dia = fecha.getDate(),
			mes = fecha.getMonth(),
			year = fecha.getFullYear();
			
		var pHoras = $("#horas"),
			pAmpm = $("#ampm"),
			pMinutos = $("#minutos"),
			pSegundos = $("#segundos"),
			pDiaSemana = $("#diaSemana"),
			pDia = $("#dia"),
			pMes = $("#mes"),
			pYear = $("#year");
		var semana = ['Domingo','Lunes','Martes','Miercoles','Jueves','Viernes','Sabado'];
		pDiaSemana.text(semana[diaSemana]);
		pDia.text(dia);
		var meses = ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'];
		pMes.text(meses[mes]);
		pYear.text(year);
		
		if(horas >= 12){
			horas = horas - 12;
			ampm = 'PM';
		}else{
			ampm = 'AM';
		};
		
		if(horas == 0){
			horas = 12;
		};
		
		pHoras.text(horas);
		pAmpm.text(ampm);
		
		if(minutos < 10){ minutos = "0" + minutos };
		if(segundos < 10){ segundos = "0" + segundos };
		pMinutos.text(minutos);
		pSegundos.text(segundos);
	};
	
	actualizarHora();
	var intervalo = setInterval(actualizarHora,1000);
	
})