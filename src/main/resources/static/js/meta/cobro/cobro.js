import { formatearNumeroInput } from '/js/meta/utilidades.js';
import { getParametro } from '/js/meta/configuracion/configuracion.queries.js';
import { getPlanillaAbierta } from '/js/meta/planilla/planilla.queries.js';
import {fecha_actual} from '/js/meta/utilidades/utilidades.js';
$(function() {
	formatearNumeroInput($('.formato-numero'));
	window.onkeydown = presionaTecla;

	let cliente = $('#cliente');
	let btn_agregarcuenta = $('.btn-abrir-cuentas-a-cobrar');
	let btn_nuevo = $('.btn-nuevo');
	let btn_buscar = $('.btn-buscar');
	let btn_guardar = $('.btn-guardar');
	cliente.focus();
	if (isNaN($('#saldo').val())) {
		$('#saldo').val(0);
	}

	$('#saldo').val()
	cliente.keydown((e) => {
		if (e.keyCode === 13) {
			e.preventDefault();
			btn_agregarcuenta.focus();
		}
	});

	cliente.click(() => {
		cliente.select();
	});

	btn_agregarcuenta.keydown((e) => {
		if (e.keyCode === 13) {
			btn_agregarcuenta.click();
		}
	});

	function presionaTecla(e) {

		switch (e.keyCode) {
			case 112:
				e.preventDefault();
				btn_nuevo.click();
				break;
			case 114:
				e.preventDefault();
				btn_buscar.click();
				break;
			case 116:
				e.preventDefault();
				btn_guardar.click();
				break;
		}
	}
	
	getParametro().then(
			function(parametro) {
				verificarAbrePlanilla(parametro);
			}
		).catch(
			function(error) {
				alert('Error recuperando parametro');
				console.log('error al recuperar parametro: ', error);
			}
		)
	
		function verificarAbrePlanilla(parametro) {
		if (parametro.abrePlanilla === true) {
			let cuenta = $('#cuenta').val();
			if (cuenta) {
				getPlanillaAbierta(cuenta)
					.then(
						function(planilla) {
							
						let fechaActual = fecha_actual();
						
							if (planilla.id != null) {
								if (planilla.fecha != fechaActual) {
									Swal.fire({
										icon: "error",
										title: "Oops...",
										text: "La fecha de la planilla no coincide con la fecha del sistema!",
										footer: `fecha de planilla: ${planilla.fecha} 
												 ,fecha del sistema: ${fechaActual}`
									});
									$('#planilla').val(planilla.id);
								}else{
									$('#planilla').val(planilla.id);
								}
							} else {
								Swal.fire({
										icon: "error",
										title: "Oops...",
										text: "No existe planilla abierta!",
									});
							}
						}
					)
					.catch(
						function(error) {
							console.log('Error recuperando planilla: ', error);
							alert('Error recuperando planilla!');
						}
					);
			}
		}
	}
	
	$('#moneda-cobro').change(function(){
		$('#moneda').val($(this).val());
	})
	
});