$(function(){
    
    var columnIndex; // Definimos columnIndex aquí para que esté disponible en toda la función

    // Función para ordenar las filas de la tabla por el valor de una columna
    function ordenarTabla(table, compareFunction) {
        var rows = table.find('tr:gt(0)').toArray().sort(compareFunction);
        table.find('th').removeClass('asc desc');
        if (!compareFunction.asc) {
            compareFunction.asc = true;
            table.find('th').eq(columnIndex).addClass('asc');
        } else {
            compareFunction.asc = false;
            table.find('th').eq(columnIndex).addClass('desc');
            rows.reverse();
        }
        for (var i = 0; i < rows.length; i++) {
            table.append(rows[i]);
        }
    }

    // Cuando se hace clic en el encabezado de la columna de nombre
    $('th').click(function() {
       
        var table = $('table.tabla-general'); // Cambia "tu-tabla" al id de tu tabla
        columnIndex = $(this).index();
        if ($(this).hasClass('asc') || !$(this).hasClass('desc')) {
            ordenarTabla(table, compararAscendente);
        } else {
            ordenarTabla(table, compararDescendente);
        }
    });

    // Función para comparar filas de manera ascendente
    function compararAscendente(a, b) {
        var valueA = obtenerValorDeCelda(a);
        var valueB = obtenerValorDeCelda(b);
        if (isNaN(valueA) || isNaN(valueB)) {
            return valueA.localeCompare(valueB);
        } else {
            return valueA - valueB;
        }
    }

    // Función para comparar filas de manera descendente
    function compararDescendente(a, b) {
        var valueA = obtenerValorDeCelda(a);
        var valueB = obtenerValorDeCelda(b);
        if (isNaN(valueA) || isNaN(valueB)) {
            return valueB.localeCompare(valueA);
        } else {
            return valueB - valueA;
        }
    }

    // Función para obtener el valor de la celda en la columna especificada
    function obtenerValorDeCelda(row) {
        return $(row).find('td').eq(columnIndex).text().trim();
    }
});
