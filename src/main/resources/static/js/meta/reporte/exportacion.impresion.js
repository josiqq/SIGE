$(function(){
    $('.date').mask('00/00/0000');
	$('.js-pdf-exportar').click(exportarPdf);
	$('.js-excel-exportar').click(exportarExcel);
	$('.js-imprimir').click(imprimri);
	$('.money2').mask("#.##0,00", {reverse: true});
	function configFecha(){
		const fechaActual = new Date();
		const dia = fechaActual.getDate();
		const mes = fechaActual.getMonth() + 1;
		const anio = fechaActual.getFullYear();
		const hora = fechaActual.getHours();
		const minutos = fechaActual.getMinutes();
		const segundos = fechaActual.getSeconds();
		const fecha = dia+'-'+mes+'-'+anio+'__'+hora+'_'+minutos+'_'+segundos;
		return fecha;
	}
	//esta parte de exportar a pdf no le voy a colocar porque la funcion de imprimir ya permite la exportacion a pdf y creo que lo hace mejor 
	function exportarPdf(){
		let fecha = configFecha();
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
		let fecha = configFecha();
		console.log('Exporta excel..'+$('title').text()+fecha+'.xls' );
		 $('.js-tabla').table2excel({
			
    		filename: $('title').text()+fecha+'.xls' 
  			});
	}
	
	function imprimri(){
		 window.print();
	}
	})