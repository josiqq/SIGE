var uuid = $('#uuid').val();
var nuevoUUID = uuid + Math.random(10);
var idAgenda = $('#id').val();
var vendedor = $('.js-vendedor');
var fecha = $('.js-fecha');
var clienteParaHoraLibre;

vendedor.change(buscarAgenda);
fecha.change(verificaVendedor);

function verificaVendedor() {
	if (vendedor) {
		buscarAgenda();
	}
}

if (idAgenda) {
	mostrarItem();
}

function mostrarItem() {
	var buscarDetalle = $.ajax({
		url: '/agendas/item',
		method: 'GET',
		data: { uuid: $('#uuid').val(), estado: 'LIBRE' }
	});

	buscarDetalle.done(llegoRespuesta);
};

function buscarAgenda() {
	$('#uuid').val(uuid + Math.random(5));

	$.ajax({
		url: '/agendas/js/agendaId',
		method: 'GET',
		data: { vendedor: vendedor.val(), fecha: fecha.val(), uuid: $('#uuid').val() },
		success: retornoId
	});
}

function retornoId(dato) {

	if (dato) {
		$('#id').val(dato);
		var id = $('#id').val(dato);
		$.ajax({
			url: '/agendas/item',
			method: 'GET',
			data: { uuid: $('#uuid').val(), id: id.val() },
			success: llegoRespuesta
		});

	}
}

function llegoRespuesta(html) {

	$('.js-item-precio').html(html);


	$('.reservar-js').click((e) => {

		var esteValor = $(e.currentTarget.parentNode.parentNode.children[0]);
		var cliente = $(e.currentTarget.parentNode.parentNode.children[1]);
		var nombreCliente = $(e.currentTarget.parentNode.parentNode.children[2]);
		var observacion = $(e.currentTarget.parentNode.parentNode.children[5]);
		var estado = "RESERVADO";

		var itemIdReservar = $(e.currentTarget).data('id');
		if (cliente.val() === '') {
			Swal.fire('¡Debe informar cliente !');
		} else {
			$('#lista-hora-modal').modal('show');
			esteValor.addClass('amarillo');
			esteValor.removeClass('verde');
			esteValor.removeClass('rojo');
			esteValor.removeClass('azul');
			modificarEstado(itemIdReservar, esteValor.text(), cliente.val(), observacion.val(), estado, nombreCliente.val());
		}

	});

	$('.libre-js').click((e) => {
		var esteValor = $(e.currentTarget.parentNode.parentNode.children[0]);
		cliente = null;
		nombreCliente = '';
		observacion = '';
		estado = "LIBRE";
		uuid = $('#uuid').val();
		esteValor.removeClass('amarillo');
		esteValor.addClass('verde');
		esteValor.removeClass('rojo');
		esteValor.removeClass('azul');
		var itemIdLibre = $(e.currentTarget).data('id');
		cambiarEstado(itemIdLibre, esteValor.text(), cliente, nombreCliente, observacion, estado, uuid);

	});

	$('.cancelar-js').click((e) => {
		var esteValor = $(e.currentTarget.parentNode.parentNode.children[0]);
		var cliente = $(e.currentTarget.parentNode.parentNode.children[1]);
		var nombreCliente = $(e.currentTarget.parentNode.parentNode.children[2]);
		var observacion = $(e.currentTarget.parentNode.parentNode.children[5]);
		var estado = "CANCELADO";
		uuid = $('#uuid').val();
		esteValor.removeClass('amarillo');
		esteValor.removeClass('verde');
		esteValor.removeClass('azul');
		esteValor.addClass('rojo');
		var itemIdCancelado = $(e.currentTarget).data('id');
		cambiarEstado(itemIdCancelado, esteValor.text(), cliente.val(), nombreCliente.val(), observacion.val(), estado, uuid);
	});

	$('.azul-js').click((e) => {
		var esteValor = $(e.currentTarget.parentNode.parentNode.children[0]);
		var cliente = $(e.currentTarget.parentNode.parentNode.children[1]);
		var nombreCliente = $(e.currentTarget.parentNode.parentNode.children[2]);
		var observacion = $(e.currentTarget.parentNode.parentNode.children[5]);
		var estado = "TERMINADO";
		uuid = $('#uuid').val();
		esteValor.removeClass('amarillo');
		esteValor.removeClass('verde');
		esteValor.removeClass('rojo');
		esteValor.addClass('azul');
		var itemIdTerminado = $(e.currentTarget).data('id');
		cambiarEstado(itemIdTerminado, esteValor.text(), cliente.val(), nombreCliente.val(), observacion.val(), estado, uuid);
	});

	/* aqui comienza el codigo para el autocomplete de cliente*/

	let cliente = $('.js-cliente');

	cliente.keyup(buscarCliente);

	function buscarCliente(e) {

		var estePresionado = $(e.target);
		estePresionado.click(() => { estePresionado.select() });
		var clientes = '';
		var contenedorListaCaja = e.target.parentNode.children[3];
		if (estePresionado.val().length > 1) {

			$.ajax({
				url: '/clientes/js/clientes?nombreDocumento=' + estePresionado.val(),
				method: 'GET',
				success: retornoCliente

			});

			clientes = '';

			function retornoCliente(dato) {

				dato.forEach((cliente) => {
					clientes += `
						<div class="lista-cliente-contenedor">
				   				<span class="lista-cliente-nombre"> ${cliente.nombre}</span>
				   				<span class="lista-cliente-cedula">${cliente.documento}</span>
								<span class="noMostrar">${cliente.id}</span>
						</div>
						`

				});
				contenedorListaCaja.innerHTML = clientes;

				$('.lista-cliente-nombre').click((e) => {
					var idHidden = $(e.target.parentNode.parentNode.parentNode.children[1]);
					idHidden.val($(e.target.parentNode.children[2]).text());
					estePresionado.val($(e.currentTarget).text());
					clientes = '';
					contenedorListaCaja.innerHTML = clientes;
				});

			}


		}


		if (estePresionado.val() === '') {

			clientes = '';
			contenedorListaCaja.innerHTML = clientes;
		}
	}

	/*fin de auto complete de clientes */

	/*inicio de busqueda de horas libres */

	function buscarHorasLibres(cliente, id_cliente, hora) {
		clienteParaHoraLibre = cliente;
		id_clienteHoraLibre = id_cliente;
		 $.ajax({
			url: 'agendas/horas/libres',
			method: 'GET',
			data: { hora: hora, uuid: $('#uuid').val() },
			success:retornoHorasLibres
		});
	};


	function retornoHorasLibres(dato) {
		var horas = '';
		var comparaHora = [];

		$('.cliente-lista-hora').text(clienteParaHoraLibre);
		dato.forEach((itemAgenda) => {
			horas += `<span class="m-2 horas-disponible-span verde col-md-2" data-id=${itemAgenda.id}>${itemAgenda.hora}</span>`;
		});

		$('.js-lista-hora-contenedor-caja').html(horas);

		$('.horas-disponible-span').off('click').click((e) => {
			var estaHora = $(e.currentTarget);
			
			var esIgual = comparaHora.some((dato) => {
				return dato === estaHora.text();
			});

			if (esIgual) {
				//hacer algoritmo para eliminar por la hora 
				console.log('eliminar item por esta hora :' , estaHora.text(),'reviro',estaHora.text().substr(0, 5));
				estaHora.removeClass('amarillo');
				var elimi = comparaHora.indexOf(estaHora.text());
				comparaHora.splice(elimi);
				
				$.ajax({
					url:'/agendas/item/dejar/libre',
					method:'PUT',
					data:{hora:estaHora.text().substr(0, 5),
								uuid:$('#uuid').val()}
				})

			} else {
				var laHora = estaHora.text().substr(0, 5);
				estaHora.addClass('amarillo');
				var idLibre = e.currentTarget.dataset.id;
				$.ajax({
					url: '/agendas/item/modificar/primero', 
					method: 'PUT',
					data: {
						itemId: idLibre,
						hora: laHora,
						cliente: id_clienteHoraLibre,
						observacion: `Para el cliente:${clienteParaHoraLibre}`,
						UUID: $('#uuid').val(),
						estado: 'RESERVADO'
					}
				});
				comparaHora.push(estaHora.text());
			}

		});

		$('.js-listo').click(() => {
			retornaTodo();
		});
		$('#lista-hora-modal').on('hidden.bs.modal', retornaTodo);
	}

	/*fin de busqueda de horas libres */
	function retornaTodo() {
		$.ajax({
			url: '/agendas/item/mostrarTodo',
			method: 'GET',
			data: { uuid: $('#uuid').val() },
			success: llegoRespuesta
		});

	}
	/*inicio de modificacion de estado */
	function modificarEstado(a_itemId, a_hora, a_cliente, a_observacion, a_estado, a_nombreCliente) {
		var itemId = a_itemId;
		var hora = a_hora;
		var cliente = a_cliente;
		var observacion = a_observacion;
		var estado = a_estado;
		var nombreCliente = a_nombreCliente;

		$.ajax({
			url: '/agendas/item/modificar/primero',
			method: 'PUT',
			data: {itemId: itemId,
						hora: hora,
						cliente: cliente,
						observacion: observacion,
						UUID: $('#uuid').val(),
						estado: estado},
			success: function(dato) {
				$('.js-lista-hora-contenedor').removeClass('noMostrar');
				buscarHorasLibres(nombreCliente, cliente, hora);
			}
		});



	}
	/*fin de modificacion de estado */

	/* cambiar estado sin reservado*/

	function cambiarEstado(a_itemId, a_hora, a_cliente, a_nombreCliente, a_observacion, a_estado, a_uuid) {

		respuesta = $.ajax({
			url: '/agendas/item/cambiarEstado',
			method: 'PUT',
			data: {
				itemId: a_itemId,
				hora: a_hora,
				cliente: a_cliente,
				observacion: a_observacion,
				UUID: a_uuid,
				estado: a_estado
			}
		});
		respuesta.done(llegoRespuesta);
	}

	/*fin de cambiar estado si reservado*/


}