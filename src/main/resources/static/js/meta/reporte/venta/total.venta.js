$(function(){
    $('.date').mask('00/00/0000');
	$('.js-pdf-exportar').click(exportarPdf);
	$('.js-excel-exportar').click(exportarExcel);
	$('.js-imprimir').click(imprimri);
	$('.money2').mask("#.##0,00", {reverse: true});
	const fechaActual = new Date();
	const dia = fechaActual.getDate();
	const mes = fechaActual.getMonth() + 1;
	const anio = fechaActual.getFullYear();
	const hora = fechaActual.getHours();
	const minutos = fechaActual.getMinutes();
	const segundos = fechaActual.getSeconds();
	const fecha = dia+'-'+mes+'-'+anio+'__'+hora+'_'+minutos+'_'+segundos;
	//esta parte de exportar a pdf no le voy a colocar porque la funcion de imprimir ya permite la exportacion a pdf y creo que lo hace mejor 
	function exportarPdf(){
		console.log('tamaño de la anchura =>',document.body.scrollWidth);
		var specialElementHandlers = {
			  '#editor': function (element, renderer) {
			    return true;
			  }
			};
		console.log('enviar a pdf...');
		var element = $('.js-tabla').html();
		var doc = new jsPDF('p', 'pt' ,'letter');
		var margin = 10;
		var scale = (doc.internal.pageSize.width - margin * 2)/document.body.scrollWidth;
		
		doc.html(element,{
			x:20,
			y:20,
			html2canvas:{
				scale:scale
			},
			callback:function(doc){
				doc.output('dataurlnewwindow',{filename:$('title').text()+fecha+'.pdf'});
			}
		});
		doc.text('Totales de ventas', 15, 15);
	};
	//fin de exportar a pdf
	function exportarExcel(){
		 $('.js-tabla').table2excel({
			
    		filename: $('title').text()+fecha+'.xls' 
  			});
	}
	
	function imprimri(){
		 window.print();
	}
	
	$('.tabla-totales-venta').each(function(){
		let costo_venta = $(this).find('.costo-venta').text();
		let precio_venta = $(this).find('.precio-venta').text();
		let utilidad_venta = $(this).find('.utilidad-venta').text();
		
		$(this).find('.costo-venta').text($.number(costo_venta,'2',',','.'));
		$(this).find('.precio-venta').text($.number(precio_venta,'2',',','.'));
		$(this).find('.utilidad-venta').text($.number(utilidad_venta,'2',',','.'));
		
	});
	let total_costo = $(this).find('.total-costo-venta').text();
	let total_precio = $(this).find('.total-precio-venta').text();
	let total_utilidad =$(this).find('.total-utilidad-venta').text();
	$(this).find('.total-costo-venta').text($.number(total_costo,'2',',','.'));
	$(this).find('.total-precio-venta').text($.number(total_precio,'2',',','.'));
	$(this).find('.total-utilidad-venta').text($.number(total_utilidad,'2',',','.'));
	})