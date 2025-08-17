$(function() {
	let proveedor = $('#proveedor');
	let fecha = $('#fecha');
	let cuenta = $('#cuenta');
	let importe = $('#importe');
	let btn_abrir = $('.btn-abrir');
	let UUID = $('#uuid').val();
	let btn_guardar = $('.btn-guardar');
	let btn_buscar = $('.btn-buscar');
	let btn_nuevo = $('.btn-nuevo');
	$('#moneda').val($('#moneda-pago').val());
	$('#moneda-pago').change(function() {
		$('#moneda').val($(this).val());
	})

	proveedor.change(() => {
		let cuentas = `<tr >
							<td colspan="4">Sin documentos agregados</td>
						</tr>`;
		let importes = `<tr >
							<td colspan="4">No hay detalles de importe</td>
						</tr>
		
						`;
		$('#total').val(0);
		$('#saldo').val(0);
		$('#pagado').val(0);
		$('.total-pago').text($('#total').val());
		$('.pagado-pago').text($('#pagado').val());
		$('.saldo-pago').text($('#saldo').val());
		$('.tbody-cuentas').html(cuentas);
		$('.js-item-pago-importe').html(importes);
		$('#uuid').val(UUID + Math.random(10));
	});

	$('.date').mask('00/00/0000');
	//$('.money').mask("#.##0", {reverse: true});
	proveedor.focus();
	proveedor.click(() => {
		proveedor.select();
	});
	proveedor.keydown((e) => {
		if (e.keyCode === 13 || e.keyCode === 9) {
			fecha.focus();
		}
	});

	fecha.keydown((e) => {
		if (e.keyCode === 13 || e.keyCode === 9) {
			//btn_abrir.focus();
			$('#moneda-pago').focus();
		}
	});

	$('#moneda-pago').keydown((e) => {
		if (e.keyCode === 13 || e.keyCode === 9) {
			btn_abrir.focus();
		}
	});
	$('#condicion').keydown((e) => {
		if (e.keyCode === 13) {
			if(!$('#condicion').val()){
				Swal.fire({
					icon: 'error',
					title: 'Oops...',
					text: 'Debe informar la condición!',
				})
			}else{
				$('#moneda').focus();	
			}
			
		}

	});

	$('#moneda').keydown((e) => {
		if (e.keyCode === 13) {
			cuenta.focus();
		}

	});

	cuenta.keydown(function(e) {
		if (e.keyCode === 13) {
			let moneda_cuenta = $('option:selected', this).data('moneda');
			console.log('moneda cuenta: ' + moneda_cuenta);
			if ($('#moneda').val() != moneda_cuenta) {
				Swal.fire({
					icon: 'error',
					title: 'Oops...',
					text: 'La moneda de la cuenta no es igual a la moneda del importe, o la cuenta no tiene asignado una moneda!',
				})
			} else {
				importe.focus();
				importe.select()
			}

		}

	});


	fecha.click(() => {
		fecha.select();
	});

	window.onkeydown = presionaTecla;
	function presionaTecla(e) {
		switch (e.keyCode) {
			case 13:
				e.preventDefault();
				break;
			case 116:
				e.preventDefault();
				btn_guardar.click();
				break;
			case 114:
				e.preventDefault();
				btn_buscar.click();
				break;
			case 112:
				e.preventDefault();
				btn_nuevo.click();
				break;
		}
	}

	btn_abrir.keydown((e) => {
		if (e.keyCode === 13) {
			btn_abrir.click();
		}
	})
})