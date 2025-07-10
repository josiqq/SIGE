$('.js-enviar-pdf').click(generatePdf);
const fechaActual = new Date();
	const dia = fechaActual.getDate();
	const mes = fechaActual.getMonth() + 1;
	const anio = fechaActual.getFullYear();
	const hora = fechaActual.getHours();
	const minutos = fechaActual.getMinutes();
	const segundos = fechaActual.getSeconds();
	const fecha = dia+'-'+mes+'-'+anio+'__'+hora+'_'+minutos+'_'+segundos;
window.jsPDF = window.jspdf.jsPDF;
        function generatePdf() {
            let jsPdf = new jsPDF('p', 'pt', 'letter');
            var htmlElement = document.getElementById('contenido-pdf');
            // you need to load html2canvas (and dompurify if you pass a string to html)
            const opt = {
                callback: function (jsPdf) {
                    jsPdf.save($('title').text()+fecha);
                    // to open the generated PDF in browser window
                    // window.open(jsPdf.output('bloburl'));
                },
                margin: [-15, 0, 0, -18],
                autoPaging: 'text',
                html2canvas: {
                    allowTaint: true,
                    dpi: 300,
                    letterRendering: true,
                    logging: false,
                    scale: 2.3
                }
            };

            jsPdf.html(htmlElement, opt);
        }