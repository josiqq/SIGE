import { fecha_actual, hora_actual } from '/js/meta/utilidades/utilidades.js';
import { tratamientoNotificacion, alCerrarMododal } from '/js/meta/notificacion/modal.notificacion.service.js';
import { agregarListaNotificacion } from '/js/meta/notificacion/notificacion.interface.js';


let url = window.location.href;
let regex = /(?:https?:\/\/)?([\d\w\.-]+)/;
let ip = url.match(regex)[1];
tratamientoNotificacion();
alCerrarMododal();
const stompClient = new StompJs.Client({
	brokerURL: 'ws://' + ip + ':8080/ws'
});

connect();

$(".btn-silicitar-autorizacion-venta").click(function() {
	$('.mensaje-de-error-solicitud-autorizacion').html(``);
	if ($('#vendedor-supervisor').val()) {
		sendNotificacion();
	} else {
		$('.mensaje-de-error-solicitud-autorizacion').html(`Debe informar el vendedor!`);
	}
});

function connect() {
	stompClient.activate();
}

function sendNotificacion() {
	$('.conten-cargando').removeClass('noMostrar');
	let mensaje = 'Solicitud de autorizacion de venta, vendedor: ' + $('#vendedor option:selected').text();
	let id_vendedor_solicitante = $('#vendedor').val();
	let id_vendedor = $('#vendedor-supervisor').val()
	let id_producto = $('#idProducto').val();
	let uuid = $('#uuid').val();
	let fecha = fecha_actual();
	let hora = hora_actual();
	let precioLista = $('.precio-lista-producto').val();
	let vendedor = { id: id_vendedor };
	let producto = { id: id_producto };
	let vendedorSolicitante = { id: id_vendedor_solicitante };
	let precio = $('#precio-producto').val() ? $('#precio-producto').val().replace(/\./g, '').replace(',', '.') : Number(0);
	let precioMinimo = $('.js-precio-minimo-producto').text() ? $('.js-precio-minimo-producto').text().replace(/\./g, '').replace(',', '.') : Number(0);
	let notificacion = {
		fecha: fecha, hora: hora, vendedor: vendedor, uuid: uuid,
		mensaje: mensaje, producto: producto, precio: precio, precioMinimo: precioMinimo,
		vendedorSolicitante: vendedorSolicitante, precioLista: precioLista
	};


	if (producto.id) {
		stompClient.publish({
			destination: "/app/notificaciones",
			body: JSON.stringify(notificacion)
		});
	} else {
		$('.conten-cargando').addClass('noMostrar');
		$('.mensaje-de-error-solicitud-autorizacion').html(`Debe informar un producto a enviar, el precio y el precio mínimo!`);
	}
}

stompClient.onConnect = (frame) => {

	console.log('Connected: ' + frame);
	stompClient.subscribe('/topic/notificaciones', (notificacion) => {
		getListaNotificaciones(JSON.parse(notificacion.body));
	});
	stompClient.subscribe('/topic/notificaciones/modificar', (notificacion) => {
		getNotificacionModificado(JSON.parse(notificacion.body));
	});
};

stompClient.onWebSocketError = (error) => {
	console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
	console.error('Broker reported error: ' + frame.headers['message']);
	console.error('Additional details: ' + frame.body);
};

function getListaNotificaciones(notificacion) {

	if (notificacion) {
		if (Number(notificacion.vendedor.id) === Number($('#js-id-vendedor-usuario-principal').val())) {
			agregarValoresNotificacionEmergente(notificacion);

			if (!$('.icono-notificacion').text()) {
				let cantidadNotificaciones = `<span class="icono-notificacion noMostrar">${0}</span>`;
				$('.icono-contenedor').append(cantidadNotificaciones);
			}
			let rows = Number($('.icono-notificacion').text()) + Number(1);
			let notificacionnew = agregarListaNotificacion(notificacion);
			$('.js-lista-notificaciones').append(notificacionnew);
			$('.icono-notificacion').removeClass('noMostrar');
			$('.icono-notificacion').html(rows);
		}
		//--
	}
	tratamientoNotificacion();
}

function getNotificacionModificado(notificacion) {

	if (($('#uuid').val() === notificacion.uuid) && notificacion.autorizado === true) {
		$('.conten-cargando').addClass('noMostrar');
		$('.item-autorizado-venta').val(1);
		$('#modal-solicitud-notificacion').modal('hide');
		$('#precio-producto').val($.number(notificacion.precio, '2', ',', '.'));
	

		Swal.fire({
			position: "top-end",
			icon: "success",
			title: "Tu solicitud ha sido autorizado!",
			showConfirmButton: false,
			timer: 1500
		}).then(() => {
				$('#precio-producto').focus();
		});

	} else if (($('#uuid').val() === notificacion.uuid) && notificacion.rechazado === true) {
		$('.conten-cargando').addClass('noMostrar');
		$('.item-autorizado-venta').val('');
		$('#modal-solicitud-notificacion').modal('hide');
		$('#precio-producto').val($.number(notificacion.precioLista,'2',',','.'));
		Swal.fire({
			position: "top-end",
			icon: "error",
			title: "Tu solicitud ha sido rechazado!",
			showConfirmButton: false,
			timer: 1500
		}).then(()=>{
			$('#precio-producto').focus();
		});
	}

}

function agregarValoresNotificacionEmergente(notificacion) {
	$("#notificationSound")[0].play();
	$('.hora-notificacion-emergente').text(notificacion.hora);
	$('.cuerpo-notificacion-emergente').text(notificacion.mensaje);
	$('.toast').toast('show');
}

export function modificarNotificacionWs(notificacion) {

	stompClient.publish({
		destination: "/app/notificaciones/modificar",
		body: JSON.stringify(notificacion)
	});
}
