// utilidades.js

export function formatearNumeroInput(input) {
      $(input).on('input', function(){
        var valor = $(this).val();
        var esNegativo = valor.startsWith('-');
        var numero = valor.replace(/[^\d,.]/g, ''); // Remover todos los caracteres que no sean números, puntos o comas

        if (esNegativo) {
            numero = '-' + numero;
        }

        var partes = numero.split(','); // Dividir el número en partes antes y después de la coma

        var parteEntera = partes[0].replace(/\./g, ''); // Remover los puntos de la parte entera
        var parteDecimal = partes[1]; // Mantener la parte decimal tal cual
        let juntados = '';

        if(parteEntera.length > 3){
            parteEntera = parteEntera.replace(/\B(?=(\d{3})+(?!\d))/g, "."); // Agregar puntos cada tres dígitos en la parte entera
        }
        if(typeof parteDecimal !== 'undefined' ){
            juntados = parteEntera + ',' + parteDecimal
        } else {
            juntados = parteEntera
        }

        $(this).val(juntados);
    });
}
