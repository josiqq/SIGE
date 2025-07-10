import {recalcularMovimientoVendedor,getSaldo} from '/js/meta/movimientoVendedor/movimiento.vendedor.queries.js';
$(function(){
	$('.btn-procesar').click(recalcular);
	
	function recalcular(){
		$('.conte-mensaje-exito').addClass('noMostrar');
		$('.conten-mensaje-error').addClass('noMostrar');
		let id = $('#vendedor').val();
		recalcularMovimientoVendedor(id).then(function(data){
			verificaSaldo()
			
		}).catch(function(xhr){
			
			$('.js-mensaje-error').text(xhr.responseText);
			$('.conten-mensaje-error').removeClass('noMostrar');
		})
	}
	
	function verificaSaldo(){
		let id = $('#vendedor').val();
		
		getSaldo(id).then(function(saldo){
			
			$('.mensaje-exito').text('Movimiento de vendedor recalculado con éxito!  Saldo: '+$.number(saldo,'2',',','.'));
		}).catch(function(xhr){
			$('.js-mensaje-error').text(xhr.responseText);
			$('.conten-mensaje-error').removeClass('noMostrar');
			
		}).finally(function(){
			$('.conte-mensaje-exito').removeClass('noMostrar');
		})
	}
})