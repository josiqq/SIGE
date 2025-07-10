import {getNotificaciones} from '/js/meta/notificacion/notificacion.queries.js';
import {tratamientoNotificacion} from '/js/meta/notificacion/modal.notificacion.service.js'; 

	
	if($('#js-id-vendedor-usuario-principal').val()){
		
		let vendedor = $('#js-id-vendedor-usuario-principal').val();
		
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
	
	export function agregarNotificaciones(notificaciones){
		
		let listaNotificaciones= [];
		let cantidadNotificaciones ='';
		notificaciones.forEach(function(notificacion){
			listaNotificaciones.push(` <a href="#" class="dropdown-item js-contenedor-notificacion-principal">
									                                <h6 class="fw-normal mb-0">${notificacion.mensaje}</h6>
									                                <small>${notificacion.fecha} ${notificacion.hora}</small>
									                                 <input type="hidden" class="not-vendedor-solicitud-nombre" value="${notificacion.vendedorSolicitante.nombre}">
									                                 <input type="hidden" class="not-precio-lista" value="${notificacion.precioLista}">
									                                <input type="hidden" class="not-precio" value=${notificacion.precio}>
									                                <input type="hidden" class="not-precio-minimo" value="${notificacion.precioMinimo}">
									                                <input type="hidden" class="not-uuid" value="${notificacion.uuid}">
									                                <input type="hidden" class="not-producto-id" value="${notificacion.producto.id}">
									                                <input type="hidden" class="not-producto-codigo" value="${notificacion.producto.codigo}">
									                                <input type="hidden" class="not-producto-nombre" value="${notificacion.producto.nombre}">
									                                <input type="hidden" class="not-id" value="${notificacion.id}">
									                            </a>
									                            <hr class="dropdown-divider">`)
		})
		
		
		if(notificaciones.length>0){
			cantidadNotificaciones =`<span class="icono-notificacion noMostrar">${notificaciones.length}</span>`;
			$('.icono-contenedor').append(cantidadNotificaciones);	
		}else{
			$('.icono-contenedor .icono-notificacion').remove();
		}
		$('.js-lista-notificaciones').html(listaNotificaciones);
		tratamientoNotificacion();
	}
