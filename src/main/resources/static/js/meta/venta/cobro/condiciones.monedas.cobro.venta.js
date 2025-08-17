import BancosQueries from '/js/meta/banco/BancosQueries.js';
const bancosQueries = new BancosQueries();
$(function(){
	
window.buscarCondicionesMoneda = function(parametro){
	$.ajax({
		url:'/condiciones/js/condiciones',
		method:'get',
		success:function(condiciones){
				getCondicionesSuccess(condiciones,parametro)
		},
		error: function(){
			alert('Error recuperando condiciones');
		}
		
	});
	
	$.ajax({
		url:'/monedas/js/monedas',
		method:'get',
		success:function(monedas){
			getMonedasSuccess(monedas,parametro);
		},
		error: function(){
			alert('Error recuperando monedas');
		}
		
	});
	
	bancosQueries.getAllBancos()
	.then(function(bancos){
		getBancosSuccess(bancos);
	})
	.catch(function(){
		alert('Error recuperando bancos!!');
	});
}

function getBancosSuccess(bancos){
	let lista_bancos =[];
	bancos.forEach(function(banco){
		lista_bancos.push(`<option value=${banco.id}>${banco.nombre}</option>`);
	})	
	$('.banco-cv').append(lista_bancos.join(''));
}

function getCondicionesSuccess(condiciones,parametro){
	let listaCondicion = [];
	condiciones.forEach(function(condicion){
		listaCondicion.push(`
								<option value=${condicion.id} data-condicion-cobro=${condicion.condicionCobro}>${condicion.nombre}</option>
							`)
	});
	
	$('.condicion-cv').append(listaCondicion.join(''));
	$('.condicion-cv').val(parametro.condicion.id);
	$('#condicion-hidden').val(parametro.condicion.nombre);
}

function getMonedasSuccess(monedas,parametro){
	let listaMonedas = [];
	monedas.forEach(function(moneda){
		listaMonedas.push(`
								<option value=${moneda.id} data-decimales=${moneda.decimales}>${moneda.nombre}</option>
							`)
	});
	
	$('.moneda-cv').append(listaMonedas.join(''));
	$('.moneda-cv').val($('#monedaCuenta').val());
	
	$('.moneda-vuelto-cv').append(listaMonedas.join(''));
	$('.moneda-vuelto-cv').val($('#monedaCuenta').val());
}

window.buscarUnaCotizacion = function( fecha, monedaOrigen, monedaDestino){
	return $.ajax({
		url:'/cotizaciones/js/getCotizacion',
		method:'GET',
		data:{fecha:fecha,monedaOrigen:monedaOrigen,monedaDestino:monedaDestino}
	})
}


})