import CondicionQueries from '/js/meta/condicion/CondicionQueries.js';
const condicionQueries = new CondicionQueries();
export default class CambioCondicion {

	constructor() {
		const self = this;
		$(function() {
			self.cambiarTipoCondicion($('#condicion').val()); // Usar la referencia 'self' en lugar de 'this'
			$('#condicion').change(function() { // Utilizar una función de flecha para mantener el contexto de 'this'
				self.getCondicion(); // Llamar a getCondicion() utilizando la referencia 'self'
			});
		});
	}

	getCondicion() {
		let id_condicion = $('#condicion').val(); 
		this.cambiarTipoCondicion(id_condicion);
	}

	cambiarTipoCondicion(id_condicion) {
		
		condicionQueries.getTipoCondicion(id_condicion).then(
			(condicion)=> {
				this.modificarEstadoCondicion(condicion);
			}
		).catch(
			(error)=> {
				console.log('error en condiciones:', error);
				alert('Error recuperando tipo de condiciones');
			}
		)
	}

	modificarEstadoCondicion(condicion) {

		if (condicion.condicionCobro === 'EFECTIVO') {
			$('.js-btn-comision-tarjeta').addClass('noMostrar');
			$('.cont-moneda').removeClass('noMostrar');
			$('.cont-cotizacion').removeClass('noMostrar');
			$('.cont-moneda-vuelto').removeClass('noMostrar');
			$('.cont-cotizacion-vuelto').removeClass('noMostrar');
			$('.cont-vuelto').removeClass('noMostrar');
			$('.cont-banco').addClass('noMostrar');
			$('.cont-boleta').addClass('noMostrar');
			$('#importe-cobrado').prop('readonly', false);
			$('#importe').val($('.saldo-cobro').text());
			$('#importe-cobrado').val($('.saldo-cobro').text());
			$('#importe-ms').val($('#saldo').val());
			$('#vuelto').val(0);
			$('#vuelto-moneda-cobro').val();
		}
		if (condicion.condicionCobro === 'TARJETA') {
			$('.js-btn-comision-tarjeta').removeClass('noMostrar');
			$('.cont-moneda').addClass('noMostrar');
			$('.cont-cotizacion').addClass('noMostrar');
			$('.cont-moneda-vuelto').addClass('noMostrar');
			$('.cont-cotizacion-vuelto').addClass('noMostrar');
			$('.cont-vuelto').addClass('noMostrar');
			$('.cont-banco').removeClass('noMostrar');
			$('.cont-boleta').removeClass('noMostrar');
			$('#importe-cobrado').prop('readonly', true);
			$('#importe').val($('.saldo-cobro').text());
			$('#importe-cobrado').val($('.saldo-cobro').text());
			$('#importe-ms').val($('#saldo').val());
			$('#moneda').val($('#moneda-cuenta').val());
			$('#moneda-vuelto').val($('#moneda-cuenta').val());
			$('#vuelto').val(0);
			$('#vuelto-moneda-cobro').val();
		}
		if (condicion.condicionCobro === 'TRANSFERENCIA') {
			$('.js-btn-comision-tarjeta').addClass('noMostrar');
			$('.cont-moneda').addClass('noMostrar');
			$('.cont-cotizacion').addClass('noMostrar');
			$('.cont-moneda-vuelto').addClass('noMostrar');
			$('.cont-cotizacion-vuelto').addClass('noMostrar');
			$('.cont-vuelto').addClass('noMostrar');
			$('.cont-banco').removeClass('noMostrar');
			$('.cont-boleta').addClass('noMostrar');
			$('#importe-cobrado').prop('readonly', true);
			$('#importe').val($('.saldo-cobro').text());
			$('#importe-cobrado').val($('.saldo-cobro').text());
			$('#importe-ms').val($('#saldo').val());
			$('#moneda').val($('#moneda-cuenta').val());
			$('#moneda-vuelto').val($('#moneda-cuenta').val());
			$('#vuelto').val(0);
			$('#vuelto-moneda-cobro').val();
		}
	}


}
