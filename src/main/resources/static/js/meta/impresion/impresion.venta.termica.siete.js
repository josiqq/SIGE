export function impresionVentaTermicaVentaSiete(registro) {
	$.ajax({
		url: '/generar-impresion-venta',
		method: 'GET',
		data: { id: registro },
		success: impresionSuccess,
		error: function(error) {
			alert('Error al generar la impresion de venta! ');
			console.log('error al generar la impresion: ' + error);
		}
	})

	function impresionSuccess(html) {
		let host = window.location.hostname;
		let port = window.location.port;
		

		let ventana = window.open('', '', 'width=1200%,height=1200%');
		let documento = ventana.document;
		let parser = new DOMParser();
		let docHTML = parser.parseFromString(html, 'text/html');
		documento.body.appendChild(docHTML.documentElement);
		$('.logo-empresa', documento).attr('src', `http://${host}:${port}/fotos/logo_empresa.png`);

		ventana.onafterprint = function() {
			ventana.close();
		};
		setTimeout(function() {

			ventana.print();

		}, 500);
	}

}
