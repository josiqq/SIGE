import {formatearNumeroInput} from '/js/meta/utilidades.js';
import { modificarNotificacionWs} from '/js/meta/notificacion/notificacion.web.socket.js';
import {getNotificaciones} from '/js/meta/notificacion/notificacion.queries.js';
import {agregarNotificaciones} from '/js/meta/notificacion/notificacion.principal.js';
export function tratamientoNotificacion(){
	$('.js-contenedor-notificacion-principal').click(abreModal);
	$('.btn-notificacion-autorizar').click(autorizarNotificacion);
	$('.btn-notificacion-rechazar').click(rechazarNotificacion);
	function abreModal(){
		$('#modal-autorizacion-notificacion').modal('show');
		$('#modal-noti-precio-lista').val($.number($(this).find('.not-precio-lista').val(),'2',',','.'));
		$('#modal-noti-precio-minimo').val($.number($(this).find('.not-precio-minimo').val(),'2',',','.'));
		$('#modal-noti-precio-descuento').val($.number($(this).find('.not-precio').val(),'2',',','.'));
		$('.modal-noti-uuid').val($(this).find('.not-uuid').val());
		$('.modal-noti-codigo-producto').text($(this).find('.not-producto-codigo').val());
		$('.modal-noti-nombre-producto').text($(this).find('.not-producto-nombre').val());
		$('.modal-noti-id-producto').val($(this).find('.not-producto-id').val())
		$('.modal-noti-id-notificacion').val($(this).find('.not-id').val());
		formatearNumeroInput($('#modal-noti-precio-descuento'));
		$('input').click(function(){
			$(this).select();
		})
	}
	
	function autorizarNotificacion(){
		$('#modal-autorizacion-notificacion').modal('hide');
		let autorizado = true;
		let rechazado = false;
		 modificarNotificacion(autorizado,rechazado);
	}
	
	function rechazarNotificacion(){
		$('#modal-autorizacion-notificacion').modal('hide');
		let autorizado = false;
		let rechazado = true;
		 modificarNotificacion(autorizado,rechazado)
	}
	
	
	
}

export function alCerrarMododal(){
	$('#modal-solicitud-notificacion').on('hidden.bs.modal',function(){
		$('.conten-cargando').addClass('noMostrar');
		$('.mensaje-de-error-solicitud-autorizacion').html('');
		
	});
}

 function modificarNotificacion(autorizado,rechazado){
	let precio = $('#modal-noti-precio-descuento').val().replace(/\./g,'').replace(',','.');
	let id_notificacion = $('.modal-noti-id-notificacion').val();
	let vendedor = $('#js-id-vendedor-usuario-principal').val();
	let notificacion = {id:id_notificacion,precio:precio,autorizado:autorizado,rechazado:rechazado};
	 modificarNotificacionWs(notificacion);
	 getNotificaciones(vendedor).then(
			function(notificaciones){
				agregarNotificaciones(notificaciones);
			}
		).catch(
			function(error){
				alert('Error recuperando notificaciones!!');
				console.log('Error recuperando notificaciones! ',error);
			}
		);
}