import {formatearNumeroInput} from '/js/meta/utilidades.js';
let vendedor = $('.js-vendedor');
let importe = $('.js-importe');

formatearNumeroInput($('.formato-numero'));

vendedor.change(buscarSaldoVendedor);

function buscarSaldoVendedor(){
	let consultaSaldo = $.ajax({
		url: '/vendedores/buscarSaldo/'+vendedor.val(),
		method: 'GET'
	});
	consultaSaldo.done(mostrarValoresRetorno)
};

function mostrarValoresRetorno(e){
	importe.val($.number(e,'2',',','.'));
	
};


importe.click(()=>{importe.select()});