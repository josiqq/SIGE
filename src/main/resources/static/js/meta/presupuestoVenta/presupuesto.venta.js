import { cambioIdProductoManipular, keyProductoManipular, keyCantidadManipular } from '/js/meta/presupuestoVenta/presupuesto.venta.service.js';
import { impresionPresupuestoSiete } from '/js/meta/presupuestoVenta/impresion.presupuesto.siete.js';
import { formatearNumeroInput } from '/js/meta/utilidades.js';
import { getClienteById } from '/js/meta/cliente/cliente.queries.js'
import {tecladoPresupuestoVenta} from '/js/meta/presupuestoVenta/presupuesto.venta.teclado.js';
formatearNumeroInput($('.formato-numero'));
tecladoPresupuestoVenta();
$('#id-producto').change(cambioIdProductoManipular);
$('#producto-nombre').keydown(keyProductoManipular);
$('#cantidad-det-nc').keydown(keyCantidadManipular);
$('#id-cliente').change(cambioIdClienteManipular);

$('input').click(function() {
	$(this).select();
})

if ($('#registro').val()) {
	
	impresionPresupuestoSiete($('#registro').val());
}

function cambioIdClienteManipular() {
	let cliente = $(this).val();
	getClienteById(cliente).then(retornoClienteManipular)
		.catch(
			function(error) {
				alert('Error al recuperar precio de cliente!');
				console.log('Error al recuperar precio de cliente: ' + error);
			}
		)
}

function retornoClienteManipular(cliente) {
	if (cliente.precio) {
		$('#precio').val(cliente.precio.id);
		$('#moneda').val(cliente.precio.moneda.id);
	} else {
		Swal.fire({
			position: "top-end",
			icon: "error",
			title: "El cliente no tiene lista de precio! ",
			showConfirmButton: false,
			timer: 2000
		});
	}
}
$(function() {
	window.onkeydown = presionaTecla;
	function presionaTecla(e) {
		if (e.keyCode === 116) {
			e.preventDefault();
			$('.btn-guardar').click();
		}
	}
})


