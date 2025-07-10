$(function() {
	window.onkeydown = presionaTecla;
	var guardar = $('.btn-guardar');
	var nuevo = $('.btn-nuevo');
	var buscar = $('.btn-buscar');
	let reporte_item = $('.js-reporte-item');
	var fecha = $('.js-fecha');
	$('.js-decimal').maskMoney({ decimal: ',', thousands: '.' });
	$('.js-entero').maskMoney({ precision: 0, thousands: '.' });
	$('.money').mask("#.##0", { reverse: true });
	fecha.mask('00/00/0000');
	setInterval(consulta, 600000);
	function consulta() {

		$.ajax({
			url: '/productos',
			method: 'GET',
			data: { nombreCodigo: 'caique' },
			contentType: 'application/json'
		});

	}

	var respuesta = $.ajax({
		url: '/parametros/buscar/1',
		method: 'GET',
		contentType: 'application/json',
	});
	respuesta.done(busquedaFinalizada);



	function busquedaFinalizada(parametros) {

		if (parametros.mostrarFondo == true) {
			activaMostrarFondo();
		}

		$('.js-empresa').html(parametros.empresa);
		$('.js-titulo').html(parametros.empresa);
		$('.js-titulo-hidden').val(parametros.empresa);
	};

	function activaMostrarFondo() {
		setTimeout(mostrar, 600000);
		function mostrar() {
			var host = window.location.hostname;
			console.log(host)
			window.location.href = "/fondo";

		}
	}

	function presionaTecla(e) {

		if (e.keyCode === 116) {
			e.preventDefault();
			guardar.click();
		}

		if (e.keyCode === 112) {
			e.preventDefault();
			nuevo.click();
		}

		if (e.keyCode === 114) {
			e.preventDefault();
			buscar.click();
		}
	}

	$.ajax({
		url: '/licencias/maxFecha',
		success: getMaxFecha,
		error: function(error) {
			alert(`Error recuperando licencia: ${error}`);
			$("#modal-licencia").modal('show');
		}
	});

	function getMaxFecha(dato) {

		if (dato === 'b') {
			$("#modal-licencia").modal('show');
		}
	}


	$("#modal-licencia").on('shown.bs.modal', modalAbierto);

	function modalAbierto() {
		$('.primero-lic').val(Math.floor(Math.random() * 20) + 1);
		$('.segundo-lic').val(Math.floor(Math.random() * 20) + 1);
		$('.tercero-lic').val(Math.floor(Math.random() * 20) + 1);

		$('.btn-licencia-confirmar').off('click').click(verificarLicencia)
	}

	function verificarLicencia() {
		let primero = $('.primero-lic').val();
		let segundo = $('.segundo-lic').val();
		let tercero = $('.tercero-lic').val();

		let primeroRet = $('.primero-retorno-lic').val();
		let segundoRet = $('.segundo-retorno-lic').val();
		let terceroRet = $('.tercero-retorno-lic').val();



		$.ajax({
			url: '/licencias/validar',
			method: 'POST',
			data: {
				primero: primero, segundo: segundo, tercero: tercero,
				primeroRet: primeroRet, segundoRet: segundoRet, terceroRet: terceroRet
			},
			success: getVerificarLicencia,
			error: function(error) {
				alert(`Error validando licencia : ${error}`);
			}
		});
	}

	function getVerificarLicencia(dato) {

		if (dato === 'l') {

			$("#modal-licencia").modal('hide');
		} else {


			const swalWithBootstrapButtons = Swal.mixin({
				customClass: {
					confirmButton: 'btn btn-success'

				},
				buttonsStyling: false
			})

			swalWithBootstrapButtons.fire({
				title: 'Código incorrecto',
				text: "Vuelve a solicitar el código!",
				icon: 'error',
				showCancelButton: false,
				confirmButtonText: 'ok',
				reverseButtons: true
			}).then((result) => {
				if (result.isConfirmed) {
					location.reload();
				}
			})
		}
	}
	//para notificaciones
	/*if ('serviceWorker' in navigator) {
		navigator.serviceWorker.register('/js/sw.js').then(function(registration) {
			console.log('Service Worker registrado con éxito:', registration);
		}).catch(function(error) {
			console.log('Error al registrar el Service Worker:', error);
		});
	}
	if (Notification.permission === 'default') {
		Notification.requestPermission().then(function(permission) {
			if (permission === 'granted') {
				// Permiso concedido, puedes enviar notificaciones push.
			}
		});
	}


	Push.create("hoaaaa!", {
		body: "revisa esta notificacin+'?",
		icon: '/img/logosinfondo.png',
		timeout: 400000,
		onClick: function() {
			window.focus();
			this.close();
		}
	});*/
	
	$('.solo-lectura').on('mousedown', function(event) {
		event.preventDefault();
		this.blur();
	});
	
	
	
})