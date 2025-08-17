import { getMonedas } from '/js/meta/moneda/moneda.queries.js';
$(function() {
	console.log('reporte de cobros realizados ..');
	$('.date').mask('00/00/0000');
	$('input').click(function() {
		$(this).select();
	})
	$('.btn-buscar').click(buscarCobros);

	function buscarCobros() {
		getMonedas()
			.then(retornoMonedaManipular)
			.catch(function(error) {
				alert('Error al recuperar moneda!');
				console.log('Error al recuperar monedas: ' + error);
			});

	}

	function retornoMonedaManipular(monedas) {
		let columnas = []
		let consulta = '';
		monedas.forEach(function(moneda) {
			columnas.push(`<th>${moneda.nombre}</th>`);
			consulta += `,case	
								when item_cobro_importe.id_moneda = ${moneda.id} then
									item_cobro_importe.importe
								else
									0
  								  end as ${moneda.nombre}`;
		})
		let columnaHead = `<tr>
								<th>COBRO</th>
								<th>FECHA</th>
								<th>CLIENTE</th>
								<th>TOTAL</th>
								<th>CONDICION</th>
								${columnas.join('')}
							</tr>`;
		//console.log(consulta);
		$('.head-tabla-cobros').html(columnaHead);
		hacerConsulta(consulta);
	}

	function hacerConsulta(consulta) {
		let idCobro;
		if ($('#registro').val().trim() === '') {
			idCobro = null;
		} else {
			idCobro = $('#registro').val()
		}

		let fechaDesde = $('#fechaDesde').val() ? moment($('#fechaDesde').val(), 'DD/MM/YYYY').format('YYYYMMDD') : null;
		let fechaHasta = $('#fechaHasta').val() ? moment($('#fechaHasta').val(), 'DD/MM/YYYY').format('YYYYMMDD') : null;
		let sql = `select 
					cobro.id as cobro
				    ,DATE_FORMAT(cobro.fecha, '%d/%m/%Y') as fecha
				    ,cliente.nombre as cliente
				    ,cobro.total
				    ,condicion.nombre as condicion${consulta} from cobro join cliente on cliente.id = cobro.id_cliente
					join item_cobro_importe on cobro.id = item_cobro_importe.id_cobro
				    join condicion on condicion.id = item_cobro_importe.id_condicion
					where cobro.fecha between(${fechaDesde})and(${fechaHasta} ) 
					and (cobro.id = ${idCobro} or ${idCobro} is null )`;
		//console.log(sql);
		$.ajax({
			url: '/reporteCobro/get-cobros-realizados',
			method: 'GET',
			data: { sql: sql },
			success: retornoCobroSuccess,
			error: function(error) {
				alert('Error recuperando cobros!');
				console.log('Error recuperando cobros: ' + error);
			}
		})
	}

	function retornoCobroSuccess(cobros) {
		//console.log(cobros[0].length)
		if (cobros.length>0) {
			let trCobros = [];
			let i = 0;
			let col = cobros[0].length;
			let sumaColumnas = new Array(col).fill(0); 
			
			cobros.forEach(function(cobro) {
				let contenidoTd = '';
				let listaCobros = '';
				for (i = 0; i < col; i++) {
					if (i < 3 || i === 4) {
						contenidoTd = cobro[i];
					} else {
						if (cobro[i] > 4) {
                        	sumaColumnas[i] += cobro[i];
                    	}
						contenidoTd = $.number(cobro[i], '2', ',', '.');
					}
					listaCobros += `<td>${contenidoTd}</td>`;
				}
				
				trCobros.push(`<tr>${listaCobros}</tr>`);

			})
			 let sumaColumnasHTML = '';
	        for (let i = 5; i < col; i++) {
	            sumaColumnasHTML += `<td>${$.number(sumaColumnas[i], '2', ',', '.')}</td>`;
	        }
	        trCobros.push(`<tr><td colspan="5"></td>${sumaColumnasHTML}</tr>`);
			//console.log(listaCobros);
			$('.body-tabla-cobros').html(trCobros);
		}else{
			$('.body-tabla-cobros').html(`<tr><td>No se encontraron cobros</td></tr>`);
		}


	}
})