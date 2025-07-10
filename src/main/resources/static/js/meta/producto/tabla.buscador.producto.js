import { getStockByProducto } from '/js/meta/stockDeposito/stock.deposito.queries.js';
import {getAllPreciosByProducto} from '/js/meta/itemPrecio/item.precio.queries.js';
export function tablaBuscadorProducto(indice) {

	$('.js-nombreOCodigo').focus();
	let indiceAFocus = indice;
	let tamaño = $('.tabla-lista-productos tr').length - 1;

	$('.tr-buscar-producto-modal').click(manipularClickFilaProducto);
	$('#modal-buscar-producto').off('keydown').keydown(manipulaKeyDown);
	$('#modal-buscar-producto').on('hide.bs.modal', manipulaCierreModal);
	
 function manipularClickFilaProducto(){
		$('.tabla-lista-productos tr').eq(indiceAFocus).removeClass('resaltado');
		$('.tabla-lista-productos tr').eq(indiceAFocus).find('.js-input-id').removeClass('resaltado');
		let indice = $(this).index();
		indiceAFocus = indice;
		$('.tabla-lista-productos tr').eq(indiceAFocus).addClass('resaltado');
		$('.tabla-lista-productos tr').eq(indiceAFocus).find('.js-input-id').addClass('resaltado');
		getAndFillStock();
		getAndFillPrecio()
}



	function manipulaKeyDown(e) {
		if (e.keyCode === 40 && indiceAFocus < tamaño) {
			moveFocusDown();
		}
		if (e.keyCode === 38 && indiceAFocus > 0) {
			moveFocusUp();
		}

		if (e.keyCode === 13 && indiceAFocus > -1) {
			if($('#nombre-producto-modal').is(':focus')){
				
			}else{
				selectProducto();	
			}
				
		}
	}

	function moveFocusDown() {
		$('.tabla-lista-productos tr').eq(indiceAFocus).removeClass('resaltado');
		$('.tabla-lista-productos tr').eq(indiceAFocus).find('.js-input-id').removeClass('resaltado');
		indiceAFocus++;
		$('.tabla-lista-productos tr').eq(indiceAFocus).addClass('resaltado');
		$('.tabla-lista-productos tr').eq(indiceAFocus).find('.js-input-id').focus().addClass('resaltado');
		getAndFillStock();
		getAndFillPrecio();
		$('.tabla-gen-produto').scrollTop($('.tabla-gen-produto').scrollTop() + $('.tabla-gen-produto').find('.tr-buscar-producto-modal').height());
	}

	function moveFocusUp() {
		$('.tabla-lista-productos tr').eq(indiceAFocus).removeClass('resaltado');
		$('.tabla-lista-productos tr').eq(indiceAFocus).find('.js-input-id').removeClass('resaltado');
		indiceAFocus--;
		$('.tabla-lista-productos tr').eq(indiceAFocus).addClass('resaltado');
		$('.tabla-lista-productos tr').eq(indiceAFocus).find('.js-input-id').focus().addClass('resaltado');
		getAndFillStock();
		getAndFillPrecio();
		$('.tabla-gen-produto').scrollTop($('.tabla-gen-produto').scrollTop() - $('.tabla-gen-produto').find('tr').height());
	}

	function selectProducto() {
		$('#producto-nombre').val($('.tabla-lista-productos tr').eq(indiceAFocus).find('.td-nombre-producto').text());
		$('#id-producto').val($('.tabla-lista-productos tr').eq(indiceAFocus).find('.js-input-id').val()).change();
		$('#modal-buscar-producto').modal('hide');
		indiceAFocus = -1;
	}

	function getAndFillStock() {
		let producto = $('.tabla-lista-productos tr').eq(indiceAFocus).find('.js-input-id').val();
		getStockByProducto(producto).then(rellenarTablaStock).catch(manipulaError)
	}

	function getAndFillPrecio(){
		let producto =$('.tabla-lista-productos tr').eq(indiceAFocus).find('.js-input-id').val();
		getAllPreciosByProducto(producto).then(rellenarTablaPrecio).catch(manipularErrorPrecio);
	}	

	function manipulaCierreModal() {
		$('.tabla-lista-productos').html('');
		$('.tabla-lista-deposito-cantidad').html('');
		$('.js-nombreOCodigo').val('');
		$('.tabla-gen-producto').scrollTop(0);
		setTimeout(function(){
          $('#cantidad-det-nc').focus();
		},500)
		tamaño = 0
	}

	function rellenarTablaStock(stocks) {
		let listaStocks = [];
		stocks.forEach(function(stock) {
			listaStocks.push(`<tr>
												  <td>${stock.deposito.nombre}</td>
												  <td>${$.number(stock.cantidad, '0', ',', '.')}</td>
										   </tr>`);
		});

		$('.tabla-lista-deposito-cantidad ').html(listaStocks);
	}
	
	function rellenarTablaPrecio(precios){
				let listaPrecios = [];
		precios.forEach(function(item) {
			listaPrecios.push(`<tr>
												  <td>${item.precio.nombre}</td>
												  <td>${$.number(item.precioProducto, '2', ',', '.')}</td>
										   </tr>`);
		});

		$('.tabla-lista-precio  ').html(listaPrecios);
	}
	
	function manipularErrorPrecio(error){
		alert('Error recuperando precios!!');
		console.log('Error al recuperar precio!!'+error);
	}

	function manipulaError(error) {
		alert('Error al recuperar stock!!');
		console.log('Error al recuperar Stock: ', error);
	}
}