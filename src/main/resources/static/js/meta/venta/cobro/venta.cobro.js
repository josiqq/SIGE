import { getParametro } from '/js/meta/configuracion/configuracion.queries.js';
import { getPlanillaAbierta } from '/js/meta/planilla/planilla.queries.js';
import {impresionVentaTermicaVentaSiete } from '/js/meta/impresion/impresion.venta.termica.siete.js';
import {getParametroVenta} from '/js/meta/parametroVenta/parametro.venta.queries.js';
import {fecha_actual} from '/js/meta/utilidades/utilidades.js';
$(function() {




	if ($('#registro').val()) {
		
		getParametroVenta().then(function(parametroVenta){
			if(parametroVenta.cobroVenta === true){
				buscarParametro();
			}
		}).catch(function(error){
			console.log('Error recuperando parametros de ventas!',error);
			alert('Error recuperando parametros de ventas!!');
		})

	}

	function buscarParametro(){
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
	}

	function verificarAbrePlanilla(parametro) {
		if (parametro.abrePlanilla === true) {
			let cuenta = $('#cuenta-usuario').val();
			console.log('debe verificar si existe planilla', cuenta);
			if (cuenta) {
				getPlanillaAbierta(cuenta)
					.then(
						function(planilla) {
							//let fechaActual = new Date();
							//let fechaActualFormato = fechaActual.toISOString().slice(0, 10);
							let fechaActual = fecha_actual();
							console.log(planilla);
							if (planilla.id != null) {
								if (planilla.fecha != fechaActual) {
									Swal.fire({
										icon: "error",
										title: "Oops...",
										text: "La fecha de la planilla no coincide con la fecha del sistema!",
										footer: `fecha de planilla: ${planilla.fecha} 
												 ,fecha del sistema: ${fechaActual}`
									});
								} else {
									$('#idPlanilla').val(planilla.id);
									verificaPermisoParaCobrar();
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
		} else {
			verificaPermisoParaCobrar();
			console.log('Puede cobrar sin problemas!');
		}
	}

	function verificaPermisoParaCobrar() {
		if ($('#cuenta-identificador').val() === localStorage.getItem("identificador")) {
			if ($('#condicion-venta').val() === 'CONTADO') {
				consultarParametroVenta()
					.then(function(parametro) {
						if (parametro.cobroVenta === true) {
							buscarCondicionesMoneda(parametro);
							let moneda = parametro.moneda;
							buscarDeuda($('#registro').val()).then(function(cuentaResult) {
								retornoDeuda(cuentaResult, moneda);
								agregarItemCobroImporte(cuentaResult);
							})
								.catch(function() {
									alert('Error recuperando deuda !!-segunda')
								});
							window.retornaCodigoMonedaSistema = function() {
								return parametro.moneda.id;
							}
						} else {
							if ($('#factura-retorno').val() === 'false') {
								Swal.fire({
									title: 'Desea imprmir?',
									text: "Se mandará una impresion de la venta!",
									icon: 'info',
									showCancelButton: true,
									confirmButtonColor: '#3085d6',
									cancelButtonColor: '#d33',
									confirmButtonText: 'Si, imprimir!'
								}).then((result) => {
									if (result.isConfirmed) {
										//imprimirNotaComun($('#registro').val());
										impresionVentaTermicaVentaSiete($('#registro').val());
									}
								})

							}
						}
					}).catch(function() {
						alert('Error al recuperar parametro!');
					});
			} else {
				console.log('venta a credito, imprimit ticket o factura ');
				if ($('#factura-retorno').val() === 'false') {
					Swal.fire({
						title: 'Desea imprmir?',
						text: "Se mandará una impresion de la venta!",
						icon: 'info',
						showCancelButton: true,
						confirmButtonColor: '#3085d6',
						cancelButtonColor: '#d33',
						confirmButtonText: 'Si, imprimir!'
					}).then((result) => {
						if (result.isConfirmed) {
							//imprimirNotaComun($('#registro').val());
							impresionVentaTermicaVentaSiete($('#registro').val());
						}
					})

				}
			}
		}
	}

	function retornoDeuda(cuentaResult, monedaResult) {

		let fecha = moment(cuentaResult.fecha).format('DD/MM/YYYY');
		let moneda = monedaResult.id;
		let valor = Number(cuentaResult.importe) - Number(cuentaResult.importeCobrado);

		consultaCotizacion(moneda, fecha).then(
			function(cotizaciones) {
				cargarTotalesCabecera(cotizaciones, valor, monedaResult);
			}
		).catch(
			function() {
				alert('Error recuperando cotizaciones');
			}
		);
	}

	function buscarDeuda(registro) {

		return new Promise((resolve, reject) => {
			consultarDeuda(registro)
				.then(function(cuenta) {
					if (Number(cuenta.importe) > Number(cuenta.importeCobrado)) {
						$("#modal-cobro-venta").modal("show");
						resolve(cuenta)
					} else {
						resolve(null);
					}
				}).catch(function() {
					reject('Error recuperando deuda');
				});

		})
	}

	function cargarTotalesCabecera(cotizaciones, valor, moneda) {
		let listaTotales = [];
		let total = 0;
		let totalPrincipal = $.number(valor, moneda.decimales, ',', '.');

		cotizaciones.forEach(function(cotizacion) {
			if (cotizacion.dividir === true) {
				total = Number(valor) / Number(cotizacion.valor)
			} else {
				total = Number(valor) * Number(cotizacion.valor)
			}

			total = $.number(Number(valor) / Number(cotizacion.valor), cotizacion.decimales, ',', '.');
			listaTotales.push(`
					<div class="importes-cotizados-cv col-md-3">
						<span class="importes-cotizados-sigla-cv">${cotizacion.sigla}: </span>
						<span class="importes-cotizados-total-cv">${total}</span>
					</div>
			`);
		});

		$('.sigla-importe-principal-c-v').text(moneda.sigla + ':');
		$('.valor-saldo-principal-c-v').text(totalPrincipal);
		$('.valor-importe-principal-c-v').text(totalPrincipal);
		$('.conten-importe-cotizado-cv').html(listaTotales);
	}

	function agregarItemCobroImporte(cuentaResult) {
		let fecha = moment(cuentaResult.fecha).format('DD/MM/YYYY');
		let total = $.number(Number(cuentaResult.importe) - Number(cuentaResult.importeCobrado), '0', ',', '.');
		let cuentaContenido = `
				<tr>
					<td>${cuentaResult.venta.id}</td>
					<td>${fecha}</td>
					<td>${total}</td>
				</tr>
			`;
		$.ajax({
			url: '/cobros/item/cobro',
			method: 'POST',
			data: { venta: cuentaResult.venta.id, fecha: fecha, importe: total, uuid: $('#uuid').val() },
			success: function() {
				console.log('insercion de documentos')
			},
			error: function() {
				alert('Error recuperando documentos!!!');
			}
		})
		$('.content-cuenta-cv').html(cuentaContenido);
	}

	function consultarParametroVenta() {
		return $.ajax({
			url: '/parametroVentas/js/getParametroVenta',
			method: 'GET'
		})
	};

	function consultarDeuda(registro) {
		return $.ajax({
			url: '/cuentaPorCobrars/buscar/cuenta',
			method: 'get',
			data: { venta: registro }
		})
	}

	function consultaCotizacion(moneda, fecha) {
		return $.ajax({
			url: '/cotizaciones/js/cotizacionesDTO',
			method: 'get',
			data: {
				fecha: fecha,
				monedaOrigen: moneda
			}
		})
	}
})