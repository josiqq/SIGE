import {getClientesByNombreDocumento} from '/js/meta/cliente/cliente.queries.js';
import {tablaBuscadorCliente} from '/js/meta/cliente/tabla.buscador.cliente.js';


$(function(){
	
	$('#modal-buscar-cliente').on('show.bs.modal',function(){
		setTimeout(function() {
      		$('#nombre-cliente-modal').focus();
    	}, 500);
		
		$('#btn-buscar-cliente-modal').click(buscarCliente);
		$('.tabla-gen-cliente').scrollTop(0);
		$('.tabla-lista-clientes').html('');
		$('#nombre-cliente-modal').off('keydown').keydown(function(e){
			if( e.keyCode === 9){
				buscarCliente();
			}
		})
		
	});
	
	
	
	function buscarCliente(){
		let nombreCodigo = $('#nombre-cliente-modal').val();
			$('.conten-cargando').removeClass('noMostrar');
			$('.contenedor-tabla-buscar-cliente-modal').addClass('noMostrar');
			getClientesByNombreDocumento(nombreCodigo).then(
					function(clientes){
						cargarTablaCliente(clientes);
					}
					).catch(function(error){
						console.log('Error al recuperar clientes',error);
						alert('Error al recuperar clientes');
					}).finally(
						function(){
							$('.conten-cargando').addClass('noMostrar');
							$('.contenedor-tabla-buscar-cliente-modal').removeClass('noMostrar');
							$('.tabla-gen-cliente').scrollTop(0);
							
							let indice = -1;
							tablaBuscadorCliente(indice);
						}
					)
	}
	
	function cargarTablaCliente(clientes){
		let listaClientes =[];
		clientes.forEach(function(cliente){
			listaClientes.push(`<tr class="tr-busqueda-cliente-modal">
							<td class="td-id-cliente">${cliente.id}</td>
							<td class="td-nombre-cliente">${cliente.nombre}</td>
							<td class="td-documento-cliente">${cliente.documento}</td>
						</tr>`);
		});
		
		$('.tabla-lista-clientes').html(listaClientes);
		$('.tr-busqueda-cliente-modal').click(adicionarCliente);
	}
	
	function adicionarCliente(){
		let nombre = $(this).find('.td-nombre-cliente').text();
		let id = $(this).find('.td-id-cliente').text();
		$('#cliente-nombre').val(nombre);
		$('#id-cliente').val(id).change();
		$('#modal-buscar-cliente').modal('hide');
	}
})