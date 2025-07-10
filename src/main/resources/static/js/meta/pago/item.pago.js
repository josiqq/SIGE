import {formatearNumeroInput} from '/js/meta/utilidades.js';
$(function(){
	formatearNumeroInput($('.formato-numero'));
	const btnAbrir = $('.btn-abrir');
	const modal = $('#modal');
	const btnCerrar = $('.btn-cerrar-ctas');
	const tbodyCta = $('.tbody-ctas-pagar');
	const UUID = $('#uuid').val();
	let totalPago =$('.total-pago');
	let total = $('#total');
	let pagado = $('#pagado');
	let saldo = $('#saldo');
	total.val(0);
	pagado.val(0);
	saldo.val(0);
	btnAbrir.click(buscarCuentas);
	btnCerrar.click(cerrarModal);
	   getItemSuccess();
	function buscarCuentas(){
		let proveedor = $('#idProveedor').val();
		let moneda = $('#moneda-pago').val();
		if(!proveedor){
			Swal.fire({
			  icon: 'error',
			  title: 'Oops...',
			  text: 'Debe informar el proveedor!',
			})
		}else if(!moneda){
			Swal.fire({
			  icon: 'error',
			  title: 'Oops...',
			  text: 'Debe informar la moneda!',
			})
		}
		else{
			modal.css('display','block');
			
			$.ajax({
				url:'/cuentaPorPagars/js/buscar',
				method:'GET',
				data:{proveedor:proveedor,
							moneda: moneda},
				success:getCuentaSuccess,
				error:getCuentaError
			});
			
			function getCuentaSuccess(cuentas){
				$('#uuid').val(UUID+Math.random(10));
				$('.tbody-cuentas').html('');
				let listaCuentas =[];
				cuentas.forEach(function(cuenta){
					let fecha = new Date(cuenta.fecha);
					let dia = fecha.getDate();
					let mes = fecha.getMonth() + 1; // Los meses en JavaScript son base 0, por lo que se suma 1
					let anio = fecha.getFullYear();
					var fechaFormateada = dia.toString().padStart(2, "0") + "/" + mes.toString().padStart(2, "0") + "/" + anio;
					listaCuentas.push(`
						<tr class="row-cuenta">
							<td>${cuenta.compra.id}</td>
							<td>${cuenta.compra.factura}</td>
							<td>${fechaFormateada}</td>
							<td>${$.number(cuenta.importe,'2',',','.')}</td>
							<td>${$.number(cuenta.importePagado,'2',',','.')}</td>
							<td>${$.number(cuenta.importe-cuenta.importePagado,'2',',','.')}</td>
						</tr>
					`);
				});
				tbodyCta.html(listaCuentas);
				$('.row-cuenta').css('cursor','pointer');
				$('.row-cuenta').click(cargarItemArray);
				$('.row-cuenta').dblclick(eliminarItemArray);
				$('.btn-procesar-cuenta').click(getItems);
			}
	
			function getCuentaError(error){
				alert(`Error al recuperar cuentas por pagar: ${error}`);
			}
		}
	}
	
	function cerrarModal(){
		modal.css('display','none');
	}
	
	function cargarItemArray(e){
		let compra = e.currentTarget.children[0].textContent;
		let importe = e.currentTarget.children[5].textContent;
		$(e.currentTarget).css('background-color', 'rgb(107, 178, 255)');
		$.ajax({
			url:'/pagos/item/adicionar',
			method:'POST',
			data:{compra:compra,importe:importe,uuid:$('#uuid').val()},
			success:adicionarItemSuccess,
			error:adicionarItemError
		});
		function adicionarItemSuccess(data){
			console.log('agregado:',data);
		};
		function adicionarItemError(error){
			alert(`error al adicionar cuenta=>${error}`);
		}
	};
	
	function eliminarItemArray(e){
		$(e.currentTarget).css('background-color', '');
		let compra = e.currentTarget.children[0].textContent;
		$.ajax({
			url:'/pagos/item/eliminar',
			method:'DELETE',
			data:{compra:compra,uuid:$('#uuid').val()},
			success:eliminarItemSuccess,
			error:eliminarItemError
		});
		function eliminarItemSuccess(data){
			console.log('eliminado:',data);
		};
		function eliminarItemError(error){
			alert(`error al eliminar item de cuentas a cobrar=>${error}`);	
		}
	}
	
	function getItems(){
		modal.css('display','none');
		$('#condicion').focus();
		$.ajax({
			url:'/pagos/item/getItems',
			method:'GET',
			data:{uuid:$('#uuid').val()},
			success:getItemSuccess,
			error:getItemError
		});
		
	};
	function getItemSuccess(html){
		let totalReal ;
			$('.tbody-cuentas').html(html);
			$('input').click(function(){
				$(this).select();
			})
			$('.js-tabla-item').each(function(){
				let importe = $(this).find('.js-pago-importe').val();
				console.log('importe a pagar: '+importe);
				$(this).find('.js-pago-importe').val($.number(importe,'2',',','.'));
			})
			formatearNumeroInput($('.formato-numero'));
			total.val($.number($('.js-tabla-item').data('total'),'2',',','.'));
			totalReal = $('.js-tabla-item').data('total');
			saldo.val(Number(totalReal)-Number(pagado.val()));
			totalPago.text($.number($('.js-tabla-item').data('total'),'2',',','.'));
			$('.saldo-pago').text($.number(saldo.val(),'2',',','.'));
			
			$('.js-pago-iporte-importe').val($.number($('.js-tabla-item').data('total'),'2',',','.'));
			$('#importe-ms').val($('.js-pago-iporte-importe').val());
			
			// $('.money').mask("#.##0", {reverse: true});
			$('.btn-item-quitar').click(quitarItemPago);
			$('.js-pago-importe').change(modificarItemPago);
			$('.js-pago-importe').keydown((e)=>{
				if(e.keyCode === 13){
					modificarItemPago(e);
				}
			});
		};
		function getItemError(error){
			alert(`error al recuperar cuentas=${error}`)
		};
		function quitarItemPago(e){
			
			let compra = e.currentTarget.dataset.id;
			
			$.ajax({
				url:'/pagos/item/eliminar/retorno',
				method:'DELETE',
				data:{compra:compra,uuid:$('#uuid').val()},
				success:getItemSuccess,
				error:eliminarItemRetornoError
			});
		
		function eliminarItemRetornoError(error){
			alert(`error al eliminar item de cuentas a cobrar=>${error}`);	
		};
		};
		
		function modificarItemPago(e){
			let compra = e.currentTarget.dataset.id;
			let importe = e.currentTarget.value;
			let uuid = $('#uuid').val();
			console.log('mmodificar este item=>',importe,'\n compra: ',compra,'\n uuid:',uuid);
			$.ajax({
				url:'/pagos/item/modificar',
				method:'PUT',
				data:{compra:compra,importe:importe,uuid:uuid},
				success:getItemSuccess,
				error:modificacionError
			});
			
			function modificacionError(error){
				alert(`error al modificar item: ${error}`);	
			}
		}
	
});