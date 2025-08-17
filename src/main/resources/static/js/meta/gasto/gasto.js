import { formatearNumeroInput } from '/js/meta/utilidades.js'
$(function() {
	//$('.js-decimal-plugin').mask("#.##0,00", {reverse: true});
	//$('.js-entero-plugin').mask("#.##0", {reverse: true});
	var proveedor = $('.js-proveedor');
	var factura = $('.js-factura');
	var fecha = $('.js-fecha');
	var cuenta = $('.js-cuenta');
	var importe = $('.js-importe');
	var observacion = $('.js-observacion');
	var btnGuardar = $('.js-btn-guardar');
	var id = $('#id');
	
	formatearNumeroInput($('.formato-numero'));
	$('#importe').val($.number($('#importe').val(), '2', ',', '.'));
	
	$('input').click(function(){
		$(this).select();
	})
	
	proveedor.focus();
	proveedor.keydown(presionaTeclaProveedor);
	factura.keydown(presionaTeclaFactura)
	fecha.keydown(presionaTeclaFecha);
	cuenta.keydown(presionaTeclaCuenta);
	importe.keydown(presionaTeclaImporte);
	observacion.keydown(presionaTeclaObservacion);

	function presionaTeclaProveedor(e) {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
		}

		if (e.keyCode === 13 || e.keyCode === 9) {
			factura.focus();
		}

	};
	function presionaTeclaFactura(e) {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			fecha.focus();
			fecha.select();
		}

	}
	function presionaTeclaFecha(e) {
		if (e.keyCode === 13 || e.keyCode === 9) {
			$('#moneda-gasto').focus();
		}

	};

	$('#moneda-gasto').keydown(function(e) {
		if (e.keyCode === 13 || e.keyCode === 9) {
			$('#cuenta').focus()
		}
	})
	function presionaTeclaCuenta(e) {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			if ($('option:selected', this).data('moneda') != $('#moneda-gasto').val()) {
				Swal.fire({
					text: "Moneda de la cuenta no es igual a la moneda del gasto!",
					icon: "error"
				});
			} else {
				importe.focus();
				importe.select();
			}

		}
	}

	function presionaTeclaImporte(e) {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			observacion.focus();
		}
	}

	function presionaTeclaObservacion(e) {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			btnGuardar.focus();
		}
	}



	
})