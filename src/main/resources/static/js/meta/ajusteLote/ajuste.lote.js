import {presionaTecla} from '/js/meta/ajusteLote/ajuste.lote.teclado.js';
$(function(){
	presionaTecla();
	$('#fecha').focus();
	$('.date').mask('00/00/0000');
	$('input').click(function(){
		$(this).select();
	})
	
	
})