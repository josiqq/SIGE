export function  agregarListaNotificacion(notificacion){
	return ` <a href="#" class="dropdown-item js-contenedor-notificacion-principal">
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
									                            <hr class="dropdown-divider">`;	
}