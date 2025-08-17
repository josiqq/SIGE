import { getMonedas } from '/js/meta/moneda/moneda.queries.js';
import { getCondicionDistintoEfectivo } from '/js/meta/condicion/condicion.queries.js';
$(function() {
	console.log('Arqueo de caja!!');

	$('.js-buscar-planilla').click(consultar);

	function consultar() {
		getMonedas().then(
			function(monedas) {
				agregaCase(monedas);
			}
		).catch(
			function(error) {
				console.log('Error al recuperar monedas!!', error);
				alert('Error al recuperar monedas!!');
			}

		);
	}


	function agregaCase(monedas) {
		let lineasCase = '';
		let cabeceraHtmlEfectivo = '';
		monedas.forEach(function(moneda) {
			lineasCase += `,case 
			when exists(select 1 from item_cobro_importe join cobro on cobro.id = item_cobro_importe .id_cobro
									join condicion on condicion.id = item_cobro_importe.id_condicion
									where item_cobro_importe.id_moneda = ${moneda.id} and condicion.condicion_cobro = 0) then 
				 ifnull( (SELECT sum(item_cobro_importe.importe) FROM item_cobro_importe join condicion on condicion.id = item_cobro_importe.id_condicion
				  		WHERE item_cobro_importe.id_cobro = cobro.id  and condicion.condicion_cobro = 0 AND   item_cobro_importe.id_moneda = ${moneda.id}),0)
			else
				0
        end as ${moneda.sigla.replace(/\s+/g, '_')}`;
			cabeceraHtmlEfectivo += `<th>${moneda.sigla}</th>`
		});

		
		buscarCondicion(lineasCase, cabeceraHtmlEfectivo);
	}

	function buscarCondicion(lineasCase, cabeceraHtmlEfectivo) {
		getCondicionDistintoEfectivo().then(
			function(condiciones) {
				agregarCaseCondicion(lineasCase, condiciones, cabeceraHtmlEfectivo);
			}
		).catch(
			function(error) {
				alert(`Error recuperando condiciones`);
				console.log('Error recuperando condiciones: ', error);
			}
		);
	}

	function agregarCaseCondicion(lineasCase, condiciones, cabeceraHtmlEfectivo) {
		let consultaDinamica = '';
		let caseCondiciones = '';
		let cabeceraHtmlCondicion = '';
		condiciones.forEach(function(condicion) {
			caseCondiciones += `,case 
			when exists(select 1 from item_cobro_importe join cobro on cobro.id = item_cobro_importe .id_cobro
									join condicion on condicion.id = item_cobro_importe.id_condicion
									where item_cobro_importe.id_condicion = ${condicion.id} and condicion.condicion_cobro  <> 0) then 
				 ifnull( (SELECT sum(item_cobro_importe.importe) FROM item_cobro_importe 
				  		WHERE item_cobro_importe.id_cobro = cobro.id AND item_cobro_importe.id_condicion =${condicion.id}),0)
			else
				0
        end as ${condicion.nombre.replace(/\s+/g, '_')}`;
			cabeceraHtmlCondicion += `<th>${condicion.nombre}</th>`
		})
		
		consultaDinamica = lineasCase + caseCondiciones;
		generaConsulta(consultaDinamica, cabeceraHtmlEfectivo, cabeceraHtmlCondicion);

	}

	function generaConsulta(consultaDinamica, cabeceraHtmlEfectivo, cabeceraHtmlCondicion) {
		let cabeceraInicial = `<th>cobro</th><th>cliente</th><th class="campo-corto">venta</th>`;
		let cabeceraTabahtml = ` <tr>${cabeceraInicial}${cabeceraHtmlEfectivo}${cabeceraHtmlCondicion}</tr>`;
		let inicioCosulta = `select  cobro.id,cliente.nombre,GROUP_CONCAT(DISTINCT item_cobro.id_venta) AS venta`;
		let finalConsulta = ` from cobro left join item_cobro_importe on cobro.id = item_cobro_importe.id_cobro
			left join item_cobro on item_cobro.id_cobro = cobro.id 
			left join condicion on item_cobro_importe.id_condicion = condicion.id
			left join cliente on cobro.id_cliente = cliente.id
		where 
			(cobro.id_planilla =:planilla )
		group by cobro.id`;
		let sql = '';

		sql = inicioCosulta + consultaDinamica + finalConsulta;
		
		$('.js-tabla-cabecera').html(cabeceraTabahtml);
		buscarArqueo(sql);
	}

	function buscarArqueo(sql) {
		$('.js-cargando').removeClass('noMostrar');
		let planilla = $('.js-lista-planilla').val();
		$.ajax({
			url: '/reporteCobro/js/getArqueo',
			method: 'GET',
			data: { sql: sql, planilla: planilla },
			success: getArqueoSuccess,
			error: function() {
				alert('Error recuperando arqueo de caja!!');
			},
			complete: function() {
				$('.js-cargando').addClass('noMostrar');
			}
		})
	}

	function getArqueoSuccess(arqueos) {
		
		let listaArqueo = [];
		
		if (arqueos.length>0) {
			let tamArr = Object.keys(arqueos[0]).length;
			arqueos.forEach(function(arqueo) {
				
				let listatr = '';
				let contador = 0;
				for (contador = 0; contador < tamArr; contador++) {
					if(contador<3){
						listatr += `<td class="campo-corto">${arqueo[contador]}</td>`;
					}else{
						listatr += `<td>${$.number(arqueo[contador],'2',',','.')}</td>`;
					}
				}
				listaArqueo.push(`<tr>
													${listatr}
												</tr>`)
			});
		}


		$('.js-tabla-cuerpo').html(listaArqueo);
	}

})