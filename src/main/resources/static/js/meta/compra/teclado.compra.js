var proveedor = $('#proveedor');
var factura = $('#factura');
var fecha = $('#fecha');
var deposito = $('#deposito');
var producto = $('#producto');
var cantidad = $('#cantidad');
var precio = $('#precio');

$('input').click(function(){
	$(this).select();
})

proveedor.keydown((e) => {
	if (e.keyCode === 13) {
		e.preventDefault();
		factura.focus();
	}
});

factura.keydown((e) => {
	if (e.keyCode === 13) {
		e.preventDefault();
		fecha.select();
		fecha.focus();
	}
});
fecha.keydown((e) => {
	if (e.keyCode === 13) {
		e.preventDefault();
		deposito.focus();
	}
});

deposito.keydown((e) => {
	if (e.keyCode === 13) {
		e.preventDefault();
		$('#plazo').focus();
		$('#plazo').select();
	}
});

$('#plazo').keydown((e) => {
	if (e.keyCode === 13) {
		e.preventDefault();
		calcularVencimiento();
		producto.focus();
	}
});


producto.keydown((e) => {
	if (e.keyCode === 13) {
		e.preventDefault();

	}
});

cantidad.keydown((e) => {
	if (e.keyCode === 13) {
		e.preventDefault();
		if (cantidad.val().trim().length === 0) {
			cantidad.val(1);
		}
		precio.focus();
	}
});

precio.keydown((e) => {
	if (e.keyCode === 13) {
		e.preventDefault();
		producto.focus();
	}
});

fecha.click(() => {
	fecha.select();
})

function calcularVencimiento() {

let fechaInput = $('#fecha').val().split('/'); // Separar el string de fecha en partes
    let dia = fechaInput[0];
    let mes = fechaInput[1] - 1; // Restar 1 al mes, ya que en JavaScript los meses van de 0 a 11
    let año = fechaInput[2];
    
    let fechaInicial = new Date(año, mes, dia);
    fechaInicial.setDate(fechaInicial.getDate() + Number($('#plazo').val()));

    dia = fechaInicial.getDate();
    mes = fechaInicial.getMonth() + 1; // Sumar 1 al mes para obtener el formato de fecha deseado
    año = fechaInicial.getFullYear();

    if (dia < 10) {
        dia = '0' + dia;
    }
    if (mes < 10) {
        mes = '0' + mes;
    }

    let fechaFormateada = dia + "/" + mes + "/" + año;
    $('#vencimiento').val(fechaFormateada);

}