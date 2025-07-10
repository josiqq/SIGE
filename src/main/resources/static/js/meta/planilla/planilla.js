import {formatearNumeroInput} from '/js/meta/utilidades.js';
import {imprimirCierrePlanilla} from '/js/meta/impresion/impresion.cierre.planilla.js';
$(function(){
	
	$('.date').mask('00/00/0000');	
	formatearNumeroInput($('.formato-numero'));
	$('#cuenta').focus();
	$('#fecha').keydown((e)=>{
		if(e.keyCode === 13){
			e.preventDefaut();
			$('#cuenta').focus();
		}
	});
	
	$('#cuenta').keydown((e)=>{
		if(e.keyCode === 13){
			e.preventDefault();
			$('#condicion').focus();
		}
	});
	
	$('#condicion').keydown((e)=>{
		if(e.keyCode === 13){
			e.preventDefault();
			$('#moneda').focus();
		}
	});
	
	$('#moneda').keydown((e)=>{
		if(e.keyCode === 13){
			e.preventDefault();
			$('#importe').focus();
			$('#importe').select();
		}
	});
	
	$('.btn-imprimir').click(function(){
		imprimirCierrePlanilla($(this).data('id'));	
	})
});