import { getCuentasPorCobrarByVenta } from '/js/meta/cuentaPorCobrar/cuenta.por.cobrar.queries.js';
import { formatearNumeroInput } from '/js/meta/utilidades.js';
import {cantidad_decimales} from '/js/meta/utilidades/utilidades.js';
	let cliente = $('#idCliente');
	let btnAgregarcuenta = $('.btn-abrir-cuentas-a-cobrar');
	let modal = $('#modal');
	let UUID = $('#uuid').val();
	let identificador_cuenta = $('#identificador-cuenta').val();
	getItemCobroSuccess();
	btnAgregarcuenta.click(() => {
		if (!cliente.val()) {
			Swal.fire({
				icon: 'error',
				title: 'Oops...',
				text: 'Debe informar el cliente!',
			})
		} else if(!$('#moneda-cobro').val()){
			Swal.fire({
				icon: 'error',
				title: 'Oops...',
				text: 'Debe la moneda!',
			})
		} else{
			let moneda = $('#moneda-cobro').val();
			$('.js-cargando').removeClass('noMostrar');
			$("#modal-cuenta-por-cobrar").modal('show');
			$.ajax({
				url: '/cuentaPorCobrars/cuentaPorClientes',
				method: 'GET',
				data: { id: cliente.val(),moneda:moneda },
				success: getCuentaSuccess,
				error: getCuentaError,
				complete: getCuentaComplete
			});
		}
	});

	function getCuentaSuccess(cuentas) {
		$('#uuid').val(UUID + Math.random(10));
		let listaCuentas = [];
		let importe = 0;
		let importe_cobrado = 0;
		let saldo = 0;
		let factura;
		let nro_factura;
		let condicion_venta;
		cuentas.forEach(function(cuenta,indice) {
			importe = $.number(cuenta[2], '2', ',', '.');
			importe_cobrado = $.number(cuenta[3], '2', ',', '.');
			saldo = $.number(cuenta[4], '2', ',', '.');
			factura = cuenta[7];
			nro_factura = cuenta[8];
			condicion_venta = cuenta[9];
			listaCuentas.push(`<tr class= "row-cuentas-cobrar" data-factura=${factura} data-num=${nro_factura}
									data-condicion=${condicion_venta} data-indice=${indice}>
									<td>${cuenta[5]}</td>
									<td>${cuenta[1]}</td>
									<td>${importe}</td>
									<td>${importe_cobrado}</td>
									<td>${saldo}</td>
							   </tr>
			
			`);
		});

		$('.tbody-ctas-cobrar').html(listaCuentas);

		$('.btn-procesar-cuenta').off('click').click(getItemCobro);


		$('.row-cuentas-cobrar').css('cursor', 'pointer');
		$('.row-cuentas-cobrar').off('click').click(function(e){
				adicionarItemCobro(e);	
			});
		$('.row-cuentas-cobrar').dblclick(function(e){
			
			quitarItemCobro(e);	
		});
	};

	function getItemCobro() {
		
		let uuid = $('#uuid').val();
		$.ajax({
			url: '/cobros/js/item/getCobro',
			method: 'GET',
			data: { uuid: uuid },
			success: getItemCobroSuccess,
			error: getItemCobroError,
			complete:function(){
				$("#modal-cuenta-por-cobrar").modal('hide');
				$('#condicion').focus();
			}
		});
	}

	export function getItemCobroSuccess(html) {
		$('#venta-id').val('');
		//$("#modal-cuenta-por-cobrar").modal('hide');
		$('.tbody-cuentas').html(html);
		
		$('.js-item-cobro-principal').each(function(){
			let importe = $(this).find('.js-item-importe').val();
			$(this).find('.js-item-importe').val($.number(importe,2,',','.'));
		})
		formatearNumeroInput($('.formato-numero'));
		
		$('#total').val($.number($('.js-total-cobro').data('total'),'2',',','.'));
		$('#cobrado').val() === '' ? $('#cobrado').val(0) : $('#cobrado').val();
		$('.js-condicion').focus();
		let total = $('.js-total-cobro').data('total');

		$('.total-cobro').text($.number(total, '2', ',', '.'));
		$('#importe').val($.number(total, '2', ',', '.'));
		$('#importe-cobrado').val($.number(total, '2', ',', '.'));
		$('#importe-ms').val(total);
		$('#cotizacion').val(0);
		$('#cotizacion-vuelto').val(0);
		$('#vuelto').val(0);
		
		let saldo = Number($('.js-total-cobro').data('total')) - Number($('#cobrado').val());

		$('#saldo').val(saldo);
		$('.saldo-cobro').text($.number(saldo, '2', ',', '.'));
		$('.money').mask("#.##0", { reverse: true });
		$('.js-item-importe').click((e) => {
			$(e.currentTarget).select();
		});
		$('.js-item-importe').keydown((e) => { if (e.keyCode === 13) { e.preventDefault(); modificarItem(e) } });
		$('.js-item-importe').change((e) => { modificarItem(e) });
		$('.btn-item-quitar').click( elimianrItem);
	}

	function elimianrItem() {
		let indice = $(this).data('indice');
		let uuid = $('#uuid').val();
		
		$.ajax({
			url: '/cobros/js/item/eliminar/retorno',
			method: 'DELETE',
			data: { indice: indice, uuid: uuid },
			success: getItemCobroSuccess,
			error: eliminarItemError
		});
	}

	function modificarItem(e) {
		let importe = e.currentTarget.value;
		let venta = e.currentTarget.dataset.id;
		let uuid = $('#uuid').val();
		$.ajax({
			url: '/cobros/js/item/modificar',
			method: 'PUT',
			data: { venta: venta, importe: importe, uuid: uuid },
			success: getItemCobroSuccess,
			error: modificarItemError
		});
	}

	function modificarItemError(error) {
		alert(`Error al modificar item : ${error}`);
	}
	function getItemCobroError(error) {
		alert(`error al recuperar items para cobro: ${error}`);
	}

	function getCuentaError(error) {
		alert(`error al recuperar cuentas:${error}`);
	}

	function getCuentaComplete() {
		$('.js-cargando').addClass('noMostrar');
	}



	function adicionarItemCobro(e) {
	
		$(e.currentTarget).css('background-color', 'rgb(107, 178, 255)');
		let venta = e.currentTarget.children[0].textContent;
		let fecha = e.currentTarget.children[1].textContent;
		let importe = e.currentTarget.children[4].textContent;
		let factura = e.currentTarget.dataset.factura;
		let numero_factura = e.currentTarget.dataset.num;
		let condicion_venta = e.currentTarget.dataset.condicion;
		let uuid = $('#uuid').val();
		
		if (factura === 'true' && numero_factura === 'null' && condicion_venta === '1') {
			modal.css('display', 'none');
			Swal.fire({
				title: 'La factura es a crédito',
				text: "Si presionas procesar se imprimirá la factura a crédito!",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'procesar'
			}).then((result) => {
				if (result.isConfirmed) {
					if (identificador_cuenta === localStorage.getItem("identificador")) {
						$.ajax({
							url: '/ventas/js/agregar/timbrado',
							method: 'POST',
							data: { uuid: localStorage.getItem("identificador"), venta: venta },
							success: function() {
								imprimirFactura(venta);
							},
							error: agregarTimbradoError
						});

					} else {
						Swal.fire({
							icon: 'error',
							title: 'Oops...',
							text: 'Esta terminal no tiene timbrado asignado!',
						})
					}

				}
			})
		} else {
			if (factura === 'true' && numero_factura === 'null' && condicion_venta === '0' && identificador_cuenta != localStorage.getItem("identificador")) {
				modal.css('display', 'none');
				Swal.fire({
					icon: 'error',
					title: 'Oops...',
					text: 'Esta terminal no tiene timbrado asignado para procesar factura, solo puede hacer cobros!',
				})
			} else {
				$.ajax({
					url: '/cobros/js/item/cobro',
					method: 'POST',
					data: { venta: venta, fecha: fecha, importe: importe, uuid: uuid },
					errorr: adicionarItemCobroError
				});
			}
		}

	};

	function agregarTimbradoError(error) {
		alert(`Error al recuperar la factura: ${error}`);
	}

	function adicionarItemCobroError(error) {
		alert(`error al adicionar item de cobro: ${error}`);
	}

	function quitarItemCobro(e) {
		//e.currentTarget.css('background-color', '');
		$(e.currentTarget).css('background-color', '');
		let venta = e.currentTarget.children[0].textContent;
		//let venta = $(this).data('venta');
		//console.log('quitar esta linea: ',indice);
		let uuid = $('#uuid').val();
		$.ajax({
			url: '/cobros/js/item/eliminar',
			method: 'DELETE',
			data: { venta: venta, uuid: uuid },
			error: eliminarItemError
		});
	}

	function eliminarItemError(error) {
		alert(`error al eliminar item de cobro: ${error}`);
	}

	//Agregar con el registro de la venta 
	$('#venta-id').keydown(function(e) {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			buscarCuentaPorVenta();
		}
	})

	$('.btn-agregar-con-venta').click(buscarCuentaPorVenta);

	function buscarCuentaPorVenta() {
		let venta = $('#venta-id').val();
		if (venta.trim().length > 0) {
			getCuentasPorCobrarByVenta(venta).then(
				function(cuenta) {
					cargarIemCobro(cuenta);
				}
			).catch(
				function(error) {
					alert('Error al recuperar cuentas!');
					console.log('Error al recuperar cuentas!', error);
				}
			);
		}
	}

	function cargarIemCobro(cuenta) {

		if (cuenta.id != null) {

			let venta = cuenta.venta.id;
			let fecha = moment(cuenta.fecha).format('DD/MM/YYYY');
			let importe =$.number(( Number(cuenta.importe) - Number(cuenta.importeCobrado)),'2',',','.');
			let uuid = $('#uuid').val();
			console.log('00 importe a cobrar: ',importe);
			if ($('#idCliente').val().trim().length === 0) {
				$('#idCliente').val(cuenta.venta.cliente.id);
				$('.js-cliente').val(cuenta.venta.cliente.nombre);
				$('#moneda-cobro').val(cuenta.venta.moneda.id);
				$('#moneda').val(cuenta.venta.moneda.id);
			}

			if( (Number($('#idCliente').val().trim()) === Number(cuenta.venta.cliente.id))&&(Number($('#moneda-cobro').val())=== Number(cuenta.venta.moneda.id))) {
				$.ajax({
					url: '/cobros/js/adicionar/item/con/venta',
					method: 'POST',
					data: { venta: venta, fecha: fecha, importe: importe, uuid: uuid },
					success: getItemCobroSuccess,
					error: function() {
						alert('Error adicionando detalle de documentos a cobrar');
					}
				})
			} else {
				alert(`El registro informado no es del mismo cliente: ${cuenta.venta.cliente.nombre} o la moneda no coincide: ${cuenta.venta.moneda.nombre}`);
			}

		} else {
			alert('No hay cuentas pendientes con ese registro !');
		}

	}
