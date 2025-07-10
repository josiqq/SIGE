import { fCotizar } from '/js/meta/cotizacion/cotizacion.queries.js';
$(function() {
	let pago_importe_importe = $('.js-pago-iporte-importe');
	let cuenta = $('.js-cuenta');
	let saldo = 0;

	adicionarItemImporteSuccess();
	pago_importe_importe.keydown((e) => {
		if (e.keyCode === 13) {
			if (cuenta.val() === '') {
				Swal.fire({
					icon: 'error',
					title: 'Oops...',
					text: 'debe informar la cuenta!',
				})
			} else if(!$('#condicion').val()){
				Swal.fire({
					icon: 'error',
					title: 'Oops...',
					text: 'debe informar la condición!',
				})
			}else if($('#importe').val().replace(/\./g,'').replace(',','.')=== "0.00"||$('#importe').val().trim()===''){
				Swal.fire({
					icon: 'error',
					title: 'Oops...',
					text: 'importe no puede ser cero!',
				})
			}else if($('#moneda').val()!=$('#cuenta option:selected').data('moneda')){
				Swal.fire({
					icon: 'error',
					title: 'Oops...',
					text: 'La moneda de la cuenta no es igual a la moneda del importe o la cuenta no tienen una moneda asignada!',
				})
			}else{
				console.log('Verifica la cotizacion');
				cotizarMonedaPago();
			}


			
		}
	});
	
	function adicionarImporteCobro(){
		$.ajax({
					url: '/pagos/item/importe/adicionar',
					method: 'POST',
					data: {
						condicion: $('#condicion').val(),
						moneda: $('#moneda').val(),
						importeMs: $('#importe-ms').val(),
						cuenta: cuenta.val(),
						importe: pago_importe_importe.val(),
						uuid: $('#uuid').val()
					},
					success: adicionarItemImporteSuccess,
					error: adicionarItemImporteError,
					complete:vaciar
				});
	}

	function adicionarItemImporteSuccess(html) {
		$('.js-item-pago-importe').html(html);

		$('.js-tabla-item-importe').each(function() {
			let importe = $(this).find('.item-pago-importe-ret').text();
			$(this).find('.item-pago-importe-ret').text($.number(importe, '2', ',', '.'));
		})

		if (!$('.js-tabla-item-importe').data('total')) {
			$('#saldo').val($('.js-tabla-item').data('total'));
			saldo = Number($('.js-tabla-item').data('total'));
		} else {
			saldo = Number($('.js-tabla-item').data('total')) - Number($('.js-tabla-item-importe').data('total'));
			$('#saldo').val(Number($('.js-tabla-item').data('total')) - Number($('.js-tabla-item-importe').data('total')));
		}
		//$('.money').mask("#.##0", {reverse: true});
		$('.pagado-pago').text($.number($('.js-tabla-item-importe').data('total'), '2', ',', '.'));
		$('.saldo-pago').text($.number(saldo, '2', ',', '.'));

		$('#pagado').val($('.js-tabla-item-importe').data('total'));
		//$('.js-pago-iporte-importe').val($.number(saldo, '2', ',', '.'));
		console.log('Saldo sin nada!! '+saldo);
		$('#moneda').val($('#moneda-pago').val());
		$('#importe-ms').val($.number(saldo,'2',',','.'));
		$('#importe').val($('#importe-ms').val());
		//agregar eliminar linea
		$('.btn-item-quitar-importe').click((e) => {
			let indice = e.currentTarget.dataset.indice;
			let uuid = $('#uuid').val();
			$.ajax({
				url: '/pagos/item/importe/eliminar',
				method: 'DELETE',
				data: { indice: indice, uuid: uuid },
				success: quitarImporteSuccess,
				error: quitarImporteError
			});

			function quitarImporteSuccess(html) {
				adicionarItemImporteSuccess(html);
			};

			function quitarImporteError(error) {
				alert(`Error al eliminar item de importe:${error}`);
			};
		})
		$('.js-cuenta-item').change((e) => {
			let indice = e.currentTarget.dataset.indice;
			let cuenta = e.currentTarget.value;
			let importe = e.currentTarget.dataset.importe;
			let uuid = $('#uuid').val();
			modificarItem(indice, cuenta, importe, uuid);
		});

		$('.js-pago-importe-item').change((e) => {
			let indice = e.currentTarget.dataset.indice;
			let cuenta = e.currentTarget.dataset.cuenta;
			let importe = e.currentTarget.value;
			let uuid = $('#uuid').val();
			modificarItem(indice, cuenta, importe, uuid);
		});

		$('.js-pago-importe-item').keydown((e) => {
			if (e.keyCode === 13) {
				let indice = e.currentTarget.dataset.indice;
				let cuenta = e.currentTarget.dataset.cuenta;
				let importe = e.currentTarget.value;
				let uuid = $('#uuid').val();
				modificarItem(indice, cuenta, importe, uuid);
			};
		});

		function modificarItem(indice, cuenta, importe, uuid) {
			$.ajax({
				url: '/pagos/item/importe/modificar',
				method: 'PUT',
				data: {
					indice: indice,
					cuenta: cuenta,
					importe: importe,
					uuid: uuid
				},
				success: modificarItemSuccess,
				error: modificarItemError
			});

			function modificarItemSuccess(html) {
				adicionarItemImporteSuccess(html);
			};

			function modificarItemError(error) {
				alert(`error al modificar item: ${error}`);
			}
		}
	};

	function adicionarItemImporteError(error) {
		alert(`error al adicionar item de pago: ${error}`);
	};

	function vaciar() {
		//pago_importe_importe.val('')
		cuenta.val('');
	$('#condicion').focus();
	}

	$('#moneda').change(function() {
		console.log('moneda origen: ', $('#moneda-pago').val(), '\moneda destino: ', $(this).val(), '\nfecha: ', $('#fecha').val(), '\nimporte ms: ', $('#importe-ms').val());
		fCotizar($('#moneda-pago').val(), $(this).val(), $('#fecha').val(), $('#importe-ms').val()).then(
			function(valorCotizado) {
				console.log('Valor cotizado: ', valorCotizado);
				if (valorCotizado === Number(0)) {
					Swal.fire({
						icon: 'error',
						title: 'Oops...',
						text: 'No existe cotización cargada para esta moneda!',
					}).then(function(){
						$('#moneda').val($('#moneda-pago').val());
						$('#importe').val($('#importe-ms').val());
					})
				} else {
					$('#importe').val($.number(valorCotizado, '2', ',', '.'));
				}
			}
		).catch(
			function(error) {
				alert('Error recuperando cotización!!');
				console.log('Error recuperando cotizacion: ', error);
			}
		);
	});

	function cotizarMonedaPago(){
		fCotizar($('#moneda').val(), $('#moneda-pago').val(), $('#fecha').val(), $('#importe').val()).then(
			function(valorCotizado){
				
				if (valorCotizado === Number(0)) {
					Swal.fire({
						icon: 'error',
						title: 'Oops...',
						text: 'No existe cotización cargada entre la moneda del importe y la moneda del pago!',
					}).then(function(){
						$('#moneda').val($('#moneda-pago').val());
						$('#importe').val($('#importe-ms').val());
					})
				} else {
					console.log('Este es el valor cotizado!',valorCotizado);
					
					verificaMontosTotales($.number(valorCotizado, '2', ',', '.'));
				}
			}
		).catch(
			function(error){
				alert('Error al recuperar cotizacion!');
				console.log('Error al recuperar cotizacion',error);
			}
		)
	}
	
	function verificaMontosTotales(importeMs){
		let importeMonedaPago = importeMs.replace(/\./g,'').replace(',','.');
		let saldo = $('.saldo-pago').text().replace(/\./g,'').replace(',','.');
		console.log('importe ms ',Number(importeMonedaPago),'\nsaldo: ',saldo);
		if(Number(importeMonedaPago)>Number(saldo)){
			Swal.fire({
						icon: 'error',
						title: 'Oops...',
						text: `Importe: ${$.number(importeMonedaPago,'2',',','.')} ya supera al saldo: ${$.number(saldo,'2',',','.')} `,
					})
		}else{
			$('#importe-ms').val(importeMs);
			
			adicionarImporteCobro();
		}
	}
});