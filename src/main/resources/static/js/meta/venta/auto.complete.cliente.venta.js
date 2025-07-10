$(function() {
	var cliente = $('.js-cliente');
	var idCliente = $('#idCliente');
	var consorcio = $('.js-consorcio');

	if (!consorcio.val()) {
		buscarClienteGeneral();
	}

	cliente.click(() => {
		cliente.select();
	});

	consorcio.change(verificarContenidoConsorcio);
	function verificarContenidoConsorcio() {
		if (consorcio.val()) {
			let id_consorcio = consorcio.val();
			console.log('buscar clientes por este consorcio: ', id_consorcio);
			buscarClienteConsorcio();

		} else {
			buscarClienteGeneral();
		}
	}

	function buscarClienteGeneral() {
		var options = {

			url: function(nombreDocumento) {
				return "/clientes?nombreDocumento=" + nombreDocumento;
			},
			getValue: "nombre",
			minCharNumber: 2,
			requestDelay: 200,
			ajaxSettings: {
				contentType: 'application/json'
			},
			list: {
				onChooseEvent: ItemSeleccionado
			}


		};


		cliente.easyAutocomplete(options);

		function ItemSeleccionado() {
			idCliente.val(cliente.getSelectedItemData().id);
			console.log('cliente en venta: ', cliente.getSelectedItemData());
			if (cliente.getSelectedItemData().precio === null) {
				Swal.fire({
					position: "top-end",
					icon: "error",
					title: "Este cliente no tiene precio de lista!",
					showConfirmButton: false,
					timer: 1500
				});
			}else{
				$('#precio').val(cliente.getSelectedItemData().precio.id).change();
				$('#moneda-venta').val(cliente.getSelectedItemData().precio.moneda.id);
			}

		};
	};

	function buscarClienteConsorcio() {
		var options = {

			url: function(cliente) {
				return "/consorcios/js/clienteConsorcio?consorcio=" + $('.js-consorcio').val() + "&cliente=" + cliente;
			},
			getValue: "nombre",
			minCharNumber: 2,
			requestDelay: 200,
			ajaxSettings: {
				contentType: 'application/json'
			},
			list: {
				onChooseEvent: ItemSeleccionado
			}


		};


		cliente.easyAutocomplete(options);

		function ItemSeleccionado() {
			idCliente.val(cliente.getSelectedItemData().id);

		};
	}
})