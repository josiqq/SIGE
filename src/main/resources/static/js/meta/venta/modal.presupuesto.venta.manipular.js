import {findPresupuestoById} from '/js/meta/presupuestoVenta/presupuesto.venta.queries.js';
import {generarUUID} from '/js/meta/utilidades/utilidades.js?ver=0.0.1';
//import {llegoAdicionarItemManipular} from '/js/meta/venta/venta.js';
$(function(){
	$('#modal-lista-presupuesto-venta').on('show.bs.modal',modalAbiertoManipular);
	$('#modal-lista-presupuesto-venta').on('hide.bs.modal',modalCerradoManipular);
	
	function modalAbiertoManipular(){
		vaciarModal()
		$(".btn-modal-buscar-pres").click(buscarPresupuesto);
	}
	
	function modalCerradoManipular(){
		
		vaciarModal();
	}
	
	function buscarPresupuesto(){
		$('.conten-cargando').removeClass('noMostrar');
		$('.tabla-lista-presu').addClass('noMostrar');
		let fechaDesde = $('#fecha-desde-presu').val();
		let fechaHasta = $('#fecha-hasta-presu').val();
		let vendedor = $('#vendedor-presu').val();
		
		$.ajax({
			url:'/presupuestoVentas/js/buscar',
			method:'GET',
			data:{fechaDesde:fechaDesde,fechaHasta:fechaHasta,vendedor:vendedor},
			success:retornoPresupuestoSuccess,
			error:function(error){
				alert('Error recuperando presupuestos!');
				console.log('Error recuperando presupuestos: '+error);
			},
			complete:function(){
				$('.conten-cargando').addClass('noMostrar');
				$('.tabla-lista-presu').removeClass('noMostrar');		
			}
		})
	}
	
	function retornoPresupuestoSuccess(presupuestos){
		let listaPresupuestos = [];
		
		presupuestos.forEach(function(presu){
			listaPresupuestos.push(`<tr class="tr-lista-presu cursor-tr">
										<td class="td-id-presu">${presu.id}</td>
										<td>${presu.fecha}</td>
										<td>${presu.moneda.nombre}</td>
										<td>${presu.cliente.nombre}</td>
										<td>${$.number(presu.total,'2',',','.')}</td>
									</tr>`);
		})
		
		$('.tbody-lista-presu').html(listaPresupuestos);
		$('.tr-lista-presu').off('click').click(manipularPresupuesto);
	}
	
	function manipularPresupuesto(){
		let id = $(this).find('.td-id-presu').text();
		
		findPresupuestoById(id).then(retornoPresupuestoManipular)
		.catch(function(error){
			alert('Error recuperando presupuesto: '+error);
			console.log('Error recuperando presupuesto');
		})
		.finally(function(){
			$('#modal-lista-presupuesto-venta').modal('hide');
			adicionarItemConPresupuesto(id);
		})
	}
	
	function retornoPresupuestoManipular(presu){
		
		$('#idCliente').val(presu.cliente.id);
		$('#cliente').val(presu.cliente.nombre);
		$('#precio').val(presu.precio.id);
		$('#moneda-venta').val(presu.moneda.id);
	}
	
	function adicionarItemConPresupuesto(id){
		$('#uuid').val(generarUUID());
		let uuid=$('#uuid').val();
		console.log('uuid: '+uuid);
		$.ajax({
			url:'/ventas/adicionar/con/presu',
			method:'POST',
			data:{presupuestoVenta:id,uuid:uuid},
			success:llegoAdicionarItem,
			error:function(error){
				alert('Error adicionando item de venta !');
				console.log('error adicionando item de venta: '+error);
			}
		})
	}
	
	
	function vaciarModal(){
		let $fechaVenta = $('#fecha').val();
		let $vendedorVenta = $('#vendedor').val();
		$('#fecha-desde-presu').val($fechaVenta);
		$('#fecha-hasta-presu').val($fechaVenta);
		$('#vendedor-presu').val($vendedorVenta);
		$('.tbody-lista-presu').html('');
	}
	
	//======================item venta===========================
	function llegoAdicionarItem(html) {

	$('.js-item-venta').html(html);
	//$('.money').mask("#.##0", { reverse: true });
	var tablaItem = $('.js-tabla-item').data('total');
	if (tablaItem > 0) {
		$('.total-cabecera').text($.number(tablaItem, '2', ',', '.'));
		  $('#precio').addClass('solo-lectura');
	}else{
		console.log('activateee....');
		$('.total-cabecera').text($.number(0, '2', ',', '.'));
		$('#precio').removeClass('solo-lectura').off('mousedown');
	}
	var cantidad = $('.item-venta-cantidad');
	var precio = $('.item-venta-precio');
	var quitar = $('.btn-item-quitar');

	$('.solo-lectura').on('mousedown', function(event) {
		event.preventDefault();
		this.blur();
	});
	
	$('.js-tabla-item').each(function() {
		
		if ($(this).children().length > 0) {
            $('#precio').addClass('solo-lectura');
        }
		let precio = $(this).find('.item-venta-precio').text();
		let subTotal = $(this).find('.item-venta-sub-total').text();
		let cantidad = $(this).find('.item-venta-cantidad').val();
		console.log('precio: '+$.number(precio,'2',',','.') );
		$(this).find('.item-venta-precio').text($.number(precio, '2', ',', '.'));
		$(this).find('.item-venta-sub-total').text($.number(subTotal, '2', ',', '.'));
		$(this).find('.item-venta-cantidad').val($.number(cantidad, '0', ',', '.'));
	})

	//inicio de actividad en cantidad detalle
	cantidad.click((e) => {
		var estePresion = $(e.target);

		if ($(e.target).data('pesable')) {
			$(e.target).addClass('money2');
			$('.money2').mask("#.##0,000", { reverse: true });
		} else {
			$(e.target).addClass('money');
			$('.money').mask("#.##0", { reverse: true });
		}

		estePresion.select();

	});

	$('.item-venta-cantidad').keydown(function(eve) {
		if (eve.keyCode === 13 || eve.keyCode === 9) {
			modificarCantidad($(this).data('id'), $(this).val());
		}
	});
	$('.item-venta-cantidad').change(function() {
		modificarCantidad($(this).data('id'), $(this).val())
	});


	//fin de actividad en cantidad detalle
	function modificarCantidad(id, cantidad) {
		$.ajax({
			url: '/ventas/item/modificar/cantidad',
			method: 'PUT',
			data: {
				producto: id,
				cantidad: cantidad,
				uuid: $('#uuid').val()
			},
			success: llegoAdicionarItem,
			error: function() {
				alert('Error modificando item!');
			}
		});
	}
	//inicio de actividad en precio detalle
	precio.click((e) => {
		var estePrecio = $(e.target);
		estePrecio.select();
		estePrecio.keydown((eve) => {
			if (eve.keyCode === 13) {

				var respuestaModificarPrecio = $.ajax({
					url: '/ventas/item/modificar/precio',
					method: 'PUT',
					data: {
						producto: estePrecio.data('id'),
						precio: estePrecio.val(),
						uuid: $('#uuid').val()
					}
				});
				respuestaModificarPrecio.done(llegoAdicionarItem);
			}
		})
	})

	//fin de actividad en precio detalle

	//inicio de actividada en boton quitar detalle
	quitar.click((e) => {
		var quitarEste = e.currentTarget;
		var respuestaEliminar = $.ajax({
			url: '/ventas/item/eliminar',
			method: 'DELETE',
			data: {
				producto: quitarEste.dataset.id,
				uuid: $('#uuid').val()
			},
			
		});
		respuestaEliminar.done(llegoAdicionarItem);
	})
	//fin de actividad en boton quitar detalle
}
	
})