$(function(){
//inicio
	
	//inicio de definicion de variables
	var registro = $('#registro').val();
	var contenedorCobro = $('.contenedor-cobro');
	var itemCobro = $('.js-item-cobro');
	var btnCancelar = $('.btn-cancelar-cobro');
	var UUID = $('#uuid').val();
	var idCajeroCobro = $('#cajero-cobro-id');
	var cajeroCobro = $('#cajero-cobro');
	var fechaCobro = $('#fecha-cobro');
	var itemCobroImporte = $('.js-item-cobro-importe');
	let cuentaUsuario = $('#cuenta-usuario').val();
	let factura = $('#factura-retorno');
	let cuenta_identificador = $('#cuenta-identificador').val();
	let condicion_venta = $('#condicion-venta').val();
	//variables para parametro de venta 
	var es_generico;
	var es_cinco_cm;
	// fin de variables para parametro de venta 
	//fin de definicion de variables
	console.log('Esta es la condicion de venta',condicion_venta);
	if(registro){
		buscarParametroVenta();
	} 
	function buscarParametroVenta(){
		$.ajax({
			url:'/parametroVentas/js/getParametroVenta',
			method:'GET',
			success:getParametroVentaSuccess,
			errorr:getParametroVentaError
		});
	
	}
	
	function getParametroVentaSuccess(data){
		es_generico = data.ticketGenerico;
		es_cinco_cm = data.ticketCinco;
		if(data.cobroVenta === true){
			if(condicion_venta === "CONTADO"){
				abrirVentanaDeCobro();
			}else{
				if(cuenta_identificador === localStorage.getItem("identificador") && $('#usuario-cajero-nombre').val()&&factura.val() === 'true'){
					//hacer algoritmo para agregar factura y timbrado
					$.ajax({
						url:'/ventas/js/agregar/timbrado',
						method:'POST',
						data:{uuid:localStorage.getItem("identificador"),venta:registro},
						success:agregarTimbradoSuccess,
						error:agregarTimbradoError
					});
				}
				if(factura.val() === 'false'){
					Swal.fire({
					  title: 'Desea imprmir?',
					  text: "Se mandará una impresion de la venta!",
					  icon: 'info',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  confirmButtonText: 'Si, imprimir!'
					}).then((result) => {
					  if (result.isConfirmed) {
						  if(data.ticketGenerico === true){
					   			imprimirNotaComun(registro);
					   	  }else{
								if(data.ticketCinco){
									impresionVentaTermicaVentaCinco(registro);
								}
						  }
					  }
					})
					
				}
			}
		}else{
			
			if(factura.val() === 'false'){
				Swal.fire({
				  title: 'Desea imprmir?',
				  text: "Se mandará una impresion de la venta!",
				  icon: 'info',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Si, imprimir!'
				}).then((result) => {
				  if (result.isConfirmed) {
				   	imprimirNotaComun(registro);
				  }
				})
				
			}	
			
		}
	}
	
	function agregarTimbradoSuccess(data){
		console.log('retorno',data);
		if(factura.val() === 'false'){
					Swal.fire({
					  title: 'Desea imprmir?',
					  text: "Se mandará una impresion de la venta!",
					  icon: 'info',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  confirmButtonText: 'Si, imprimir!'
					}).then((result) => {
					  if (result.isConfirmed) {
						  imprimirFactura(registro);
					  }
					})
					
				}
		
	}
	
	function agregarTimbradoError(error){
		alert(`Error al agregar numero de factura: ${error}`);
	}
	
	function getParametroVentaError(error){
		alert(`Error al retornar parametros de ventas: ${error}`);
	}
	
	
	//buscar cuentas por cobrar con el registro 
	function abrirVentanaDeCobro(){
	
		$.ajax({
			url:'/cuentaPorCobrars/buscar/cuenta',
			method:'GET',
			data:{venta:registro},
			success:cuentaPorCobrarSuccess,
			error:cuentaPorCobrarError
		})
	;}
	// fin buscar cuentas por cobrar con el registro
	 
	//inicio de retorno de cuentas a cobrar con exito
	function cuentaPorCobrarSuccess(cuenta){
		if(cuenta.id!=null){
			
			if(cuenta_identificador === localStorage.getItem("identificador") && $('#usuario-cajero-nombre').val()){
				contenedorCobro.removeClass('noMostrar');
			$('.btn-guardar').attr('disabled','disabled');
			//carcar datos para cobro
				var fecha = new Date();
				var fechaHoy = moment(fecha).format('DD/MM/YYYY');
				idCajeroCobro.val($('#usuario-id-cajero').val());
				cajeroCobro.val($('#usuario-cajero-nombre').val());
				fechaCobro.val(fechaHoy);
			//fin de cargar datos para cobro	
			//activar boton cancelar
			btnCancelar.click(()=>{
				contenedorCobro.addClass('noMostrar');
				$('.btn-guardar').removeAttr('disabled');
			})
			//fin de activar boton cancelar
			
			//cargar item cobro
			$.ajax({
				url:'/cobros/item/cobro',
				method:'POST',
				data:{
					venta:cuenta.venta.id,
					fecha:moment(cuenta.fecha).format('DD/MM/YYYY'),
					importe:cuenta.importe-cuenta.importeCobrado,
					uuid:UUID
				},
				success:itemCobroSuccess,
				error:itemCobroError
				
			})
			//fin de cargar item de cobro
			}else{
				Swal.fire({
				  title: 'Desea imprmir?',
				  text: "Se mandará una impresion de la venta!",
				  icon: 'info',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Si, imprimir!'
				}).then((result) => {
				  if (result.isConfirmed) {
				   	imprimirNotaComun(cuenta.venta.id);
				  }
				})
				
			}
		}
		
		
	};
	//fin de retorno de cuentas a cobrar con exito
	
	function cuentaPorCobrarError(error){
		alert(`Tiene error=>${error}`);
	}
	
	//inicio de carga de itemCobro con exito
	function itemCobroSuccess(html){
		itemCobro.html(html);
		//agregar mascaras
		$('.money').mask("#.##0", {reverse: true});
		//fin agregar mascaras
		$('.js-total-cabecera-cobro').text($.number($('.js-total-cobro').data('total'),'0',',','.'));
		
		//adicionar item cobro importe
		$.ajax({
			url:'/cobros/item/importe',
			method:'POST',
			data:{
				cuenta:cuentaUsuario,
				importe:$('.js-total-cobro').data('total'),
				importeCobrado: 0,
				vuelto:0,
				uuid:UUID
			},
			success: itemCobroImporteSuccess,
			error: itemCobroImporteError,
		})
		//fin adicionar item cobro importe
	
	}
	//fin de carga de itemCobro con exito
	
	function itemCobroError(error){
		alert(`Tiene error al retornar cuentas=>${error}`);
	}
	
	//item cobro importe con exito
	function itemCobroImporteSuccess(html){
		itemCobroImporte.html(html);
		if(Number($('.item-cobro-importe-tr').data('total-importe'))>=0){
			$('.js-total-importe').text($.number($('.item-cobro-importe-tr').data('total-importe'),'0',',','.'));	
		}else{
			$('.js-total-importe').text(0);
		}
		
		//agregar mascaras
		$('.money').mask("#.##0", {reverse: true});
		//fin de agregar mascaras
		$('.cobro-item-cuenta').focus();
		//presiona tecla en cuenta
		$('.cobro-item-cuenta').keydown((e)=>{
			if(e.keyCode === 13 ){
				e.currentTarget.parentNode.parentNode.children[2].children[0].focus();
				e.currentTarget.parentNode.parentNode.children[2].children[0].select();
			}else if(e.keyCode === 116){
				e.preventDefault();
				$('.btn-procesar-cobro').click();
			}else if(e.keyCode === 9){
				e.currentTarget.parentNode.parentNode.children[1].children[0].focus();
				e.currentTarget.parentNode.parentNode.children[1].children[0].select();
			}
		});
		//fin de presiona tecla en cuenta
		//presiona tecla en importe cobrado
		$('.item-cobro-importe-importe-cobrado').keydown((e)=>{
			if(e.keyCode === 13 || e.keyCode === 9){
				//inicio de validacion de contenido de importe cobrado
				if(e.currentTarget.parentNode.parentNode.children[0].children[0].value === ''){
					Swal.fire({
					  icon: 'error',
					  title: 'Oops...',
					  text: 'Debe informar la cuenta!',
					})
				};
				if(Number(e.currentTarget.value) === Number(0) || e.currentTarget.value === ''){
					Swal.fire({
					  icon: 'error',
					  title: 'Oops...',
					  text: 'Debe informar el importe!',
					})
				}else{
					//desarrollar algoritmo para modificar linea de detalle 
					//quitar mascaras de importe e importe cobrado para calcular
					$('.item-cobro-importe-importe').unmask();
					$('.item-cobro-importe-importe-cobrado').unmask();
					//fin de desenmascarar
					var esteImporte = e.currentTarget.parentNode.parentNode.children[1].children[0].value;
					var esteImporteCobrado = e.currentTarget.value;
					
					e.currentTarget.parentNode.parentNode.children[3].children[0].value= $.number(calcularVuelto(esteImporte,esteImporteCobrado),'0',',','.');
					var esteVuelto =e.currentTarget.parentNode.parentNode.children[3].children[0].value= calcularVuelto(esteImporte,esteImporteCobrado)>=0 ? $.number(calcularVuelto(esteImporte,esteImporteCobrado),'0',',','.'):0;
					var estaCuenta = e.currentTarget.parentNode.parentNode.children[0].children[0].value
					modificarItem(estaCuenta,esteImporte,esteImporteCobrado,esteVuelto,UUID);
					//volver a colocar la mascara
					$('.money').mask("#.##0", {reverse: true});
					//fin de poner mascaras
				}
				//fin de validacion de importe cobrado
			}
			
			if(e.keyCode === 116){
				e.preventDefault();
				$('.btn-procesar-cobro').click();
			}
		});
		//fin de presiona tecla en importe cobrado
		//cambio en importe cobrado
		$('.item-cobro-importe-importe-cobrado').change((e)=>{
			//inicio de validacion de contenido de importe cobrado
				if(e.currentTarget.parentNode.parentNode.children[0].children[0].value === ''){
					Swal.fire({
					  icon: 'error',
					  title: 'Oops...',
					  text: 'Debe informar la cuenta!',
					})
				};
				if(Number(e.currentTarget.value) === Number(0) || e.currentTarget.value === ''){
					Swal.fire({
					  icon: 'error',
					  title: 'Oops...',
					  text: 'Debe informar el importe!',
					})
				}else{
					//desarrollar algoritmo para modificar linea de detalle sin retorno
					//quitar mascaras de importe e importe cobrado para calcular
					$('.item-cobro-importe-importe').unmask();
					$('.item-cobro-importe-importe-cobrado').unmask();
					//fin de desenmascarar
					var esteImporte = e.currentTarget.parentNode.parentNode.children[1].children[0].value;
					var esteImporteCobrado = e.currentTarget.value;
					
					e.currentTarget.parentNode.parentNode.children[3].children[0].value= $.number(calcularVuelto(esteImporte,esteImporteCobrado),'0',',','.');
					var esteVuelto = 	e.currentTarget.parentNode.parentNode.children[3].children[0].value= calcularVuelto(esteImporte,esteImporteCobrado)>=0 ? $.number(calcularVuelto(esteImporte,esteImporteCobrado),'0',',','.'):0;
					var estaCuenta = e.currentTarget.parentNode.parentNode.children[0].children[0].value
					modificarItem(estaCuenta,esteImporte,esteImporteCobrado,esteVuelto,UUID);
					//volver a colocar la mascara
					$('.money').mask("#.##0", {reverse: true});
					//fin de poner mascaras
				}
				//fin de validacion de importe cobrado
		});
		//fin en cambio en importe cobrado 
	}
	//fin de item cobro importe con exito
	
	function itemCobroImporteError(error){
		alert(`Tiene error=>${error}`);
	}
	
	function calcularVuelto(esteImporte,esteImporteCobrado){
		return Number(esteImporteCobrado)-Number(esteImporte);
	}
	
	function modificarItem(estaCuenta,esteImporte,esteImporteCobrado,esteVuelto,uuid){
		
		$.ajax({
			url:'/cobros/item/importe/modificar',
			method:'PUT',
			data:{
				cuenta:estaCuenta,
				importe:esteImporte,
				importeCobrado:esteImporteCobrado,
				vuelto:esteVuelto,
				uuid:uuid
			},
			success:itemCobroImporteSuccess,
			error:modificarItemImporteError
		});
	}
	
	function modificarItemImporteError(error){
		alert(`Tiene este error=>${error}`);
	}
	//boron de cobro
	$('.btn-procesar-cobro').click(()=>{
		if(Number($('.js-total-importe').text())>0){
			Swal.fire({
					  icon: 'error',
					  title: 'Oops...',
					  text: 'Todavia faltan '+$('.js-total-importe').text()+' Gs',
					})
		}else if(Number($('.js-total-importe').text())===0){
			let total = $('.js-total-cabecera-cobro').text();
			let cajero ={id:idCajeroCobro.val()};
			let usuario ={id:$("#usuario").val()};
			let cuenta ={id:$('#cuenta-usuario').val()};
			let cobro = {
					cajero:cajero,
					fecha:fechaCobro.val(),
					total:parseFloat(total.replace(/[^0-9,-]+/g,"")),
					usuario:usuario,
					cuenta:cuenta,
					uuid:UUID
					}
			$.ajax({
				url:'/cobros/venta',
				method:'POST',
				contentType:'application/json',
				data:JSON.stringify(cobro),
				success:guardarCobroSuccess,
				error:guardarCobroError
				
			});	
		};
		
	});
	// fin de boton de cobro
	
	function guardarCobroSuccess(sucess){
		Swal.fire({
		  position: 'top-end',
		  icon: 'success',
		  title: 'Venta cobrada con éxito!',
		  showConfirmButton: false,
		  timer: 1500
		});
		if(factura.val()=== 'true'){
			Swal.fire({
				  title: 'Desea imprmir?',
				  text: "Se mandará una impresion de la venta!",
				  icon: 'info',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Si, imprimir!'
				}).then((result) => {
				  if (result.isConfirmed) {
					  if(es_generico){
						imprimirFactura(registro);	  
					  }else{
						  if(es_cinco_cm){
							  impresionVentaTermicaVentaCinco(registro);
						  }
					  }
				   	
				  }
				})
			
		}else{
			Swal.fire({
				  title: 'Desea imprmir?',
				  text: "Se mandará una impresion de la venta!",
				  icon: 'info',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Si, imprimir!'
				}).then((result) => {
				  if (result.isConfirmed) {
					  if(es_generico){
				   		imprimirNotaComun(registro);
				   	  }else{
							 if(es_cinco_cm){
								 impresionVentaTermicaVentaCinco(registro);
							 }
						 }
				  }
				})
			
		}
		contenedorCobro.addClass('noMostrar');
		$('.btn-guardar').removeAttr('disabled');
		$('#producto').focus();
	};
	function guardarCobroError(error){
		alert(`tiene error=>${error}`);
	} 
//fin
})