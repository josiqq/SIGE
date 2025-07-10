import { cargarFotoExistente } from '../upload/componente.js';
import {formatearNumeroInput} from '/js/meta/utilidades.js';
var id = $('#id');
var codigo = $('#codigo');
var nombre = $('#nombre');
var marca = $('#marca');
var gravado = $('#gravado');
var porcGravado = $('#porcGravado');
var estado = $('#estado');
var pesable = $('#pesable');
var foto = $('#foto');
var contentType = $('#contentType');
var costo = $('.js-costo');
var radio_costo_calculo = $('.js-radio-costo-calculo');

formatearNumeroInput($('.formato-numero'));
let stockMinimo = $('#stock-minimo').val();
$('#stock-minimo').val($.number(stockMinimo,'2',',','.'));

$('.js-costo').val($.number(costo.val(),'2',',','.'));
gravado.mask("#.##0", { reverse: true });
porcGravado.mask("#.##0", { reverse: true });
 $('.money-decimal').mask("#.##0,00", {reverse: true});

$('input').click(function(){
	$(this).select();
})

codigo.keydown(presionaTeclaNombre);

function presionaTeclaNombre(e) {

	if (e.keyCode === 13 || e.keyCode === 9) {
		e.preventDefault();
		
		$.ajax({
			url: "/productos/js/" + codigo.val(),
			method: "GET",
			contentType: "applicationJson",
			success: retorno,
			error:function(){
				alert('Error recuperando producto!');
			}
		})

		//respuesta.done(retorno)

	}
}

	function retorno(dato){
		
		if(dato.id !=null){
			
			 id.val(dato.id);  
			 nombre.val(dato.nombre);
			 dato.marca ? marca.val(dato.marca.id) : marca.val('');
			 gravado.val(dato.gravado);
			 $('#stock-minimo').val($.number(dato.stockMinimo,'2',',','.'));
			 porcGravado.val(dato.porcGravado);
			 estado.val(dato.activo);
			 $('#uuid').val(dato.uuid);
			 if(estado.val() === 'true') { 
				  estado.attr('checked','checked');
				  estado.val('true');
			 }else{
				estado.removeAttr('checked');
				estado.val('true');
			 }
			 
			 pesable.val(dato.pesable);
			 if(pesable.val() === 'true') { 
				  pesable.attr('checked','checked');
				  pesable.val('true');
			 }else{
				pesable.removeAttr('checked');
				pesable.val('true');
			 }
			 
			 foto.val(dato.foto); 
			 contentType.val(dato.contentType);
			 costo.val($.number(dato.costo,'2',',','.'));
			$('.js-foto-hbs').remove();
			
			if(dato.costoCalculo ==='PRECIOCOMPRA'){
				
				radio_costo_calculo[0].checked ='true';
			
			}
			if(dato.costoCalculo ==='PROMEDIO'){
				
				
				radio_costo_calculo[1].checked='true';
			}
			
			if(foto.val()){
				cargarFotoExistente();
			}else{
				limpiarFoto();		
			}
			 
			
			codigo.select();
			if(dato.codigo!=null){
				buscarCodigoAlternativo(dato.codigo,dato.uuid);	
			}else{
				$('.item-producto-codigo').html('');
			}
			 
			  if(dato.servicio === true) { 
				  $('#servicio').attr('checked','checked');
				  $('#servicio').val('true');
			 }else{
				
				$('#servicio').removeAttr('checked');
				$('#servicio').val('true');
			 }
			 
			  if(dato.conLote === true) { 
				  $('#conLote').attr('checked','checked');
				  $('#conLote').val('true');
			 }else{
				$('#conLote').removeAttr('checked');
				$('#conLote').val('true');
			 }
			 
			 $('#comision').val($.number(dato.comision,'2',',','.'));
			 
		}else{
			 
			 id.val('');  
			 nombre.val('');
			 marca.val('');
			 $('#stock-minimo').val($.number(0,'2',',','.'));
			 gravado.val('10');
			 porcGravado.val('100');
			 estado.attr('checked','checked');
			 estado.val('true');
			 pesable.removeAttr('checked');
			 
			radio_costo_calculo[1].checked='true';
			costo.val(0);
			 limpiarFoto();
			 nombre.focus();
			 $('#comision').val(0);
			 $('#servicio').removeAttr('checked');
			 $('.item-producto-codigo').html('');
			 
			 
		};
	}

function limpiarFoto() {
	$('#upload-drop').removeClass('noMostrar');
	$('.js-foto-hbs').remove();
	$('.in-foto').val('');
	$('.in-contentType').val('');
}
$(function() {
	window.onkeydown = presionaTecla;

	function presionaTecla(e) {
		if (e.keyCode === 116) {
			e.preventDefault();
			$('.btn-guardar').click();
		}

	};

})

function buscarCodigoAlternativo(codigo,uuid){
	$.ajax({
		url:'/productos/js/itemProductoCodigo',
		method:'GET',
		data:{codigo:codigo,uuid:uuid},
		success:buscarCodigoAlternativoSuccess,
		error:function(){
			alert('Error al recuperar codigo alternativo!');
		}
	})
}

function buscarCodigoAlternativoSuccess(html){
	$('.item-producto-codigo').html(html);
	$('.btn-item-quitar').off('click').click(eliminarItem);
}

function eliminarItem() {
		let indice = $(this).data('indice');
		let uuid = $('#uuid').val();
		
		$.ajax({
			url: '/productos/js/eliminar/item',
			method: 'DELETE',
			data: { indice: indice, uuid: uuid },
			success: buscarCodigoAlternativoSuccess,
			error: function() {
				alert('Error quitando código');
			}
		})
	}