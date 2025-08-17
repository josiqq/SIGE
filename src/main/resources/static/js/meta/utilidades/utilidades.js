export function fecha_actual() {
	let fecha = new Date();
	let year = fecha.getFullYear();
	let month = ('0' + (fecha.getMonth() + 1)).slice(-2);
	let day = ('0' + fecha.getDate()).slice(-2);
	let fechaFormateada = year + '-' + month + '-' + day;
	return fechaFormateada;
}

export function hora_actual() {
	let fecha = new Date();
	let hours = ('0' + fecha.getHours()).slice(-2);
	let minutes = ('0' + fecha.getMinutes()).slice(-2);
	let seconds = ('0' + fecha.getSeconds()).slice(-2);
	let horaFormateada = hours + ':' + minutes + ':' + seconds;
	return horaFormateada;
}

export function cantidad_decimales(numero) {
	// Convertimos el número a cadena para poder utilizar la función split()
	let numeroComoCadena = numero.toString();
	// Dividimos el número en dos partes, una antes y otra después del punto decimal
	let partes = numeroComoCadena.split('.');
	// Obtenemos la parte después del punto decimal
	let parteDecimal = partes[1];
	// Si hay parte decimal, contamos la longitud de la cadena para saber cuántos dígitos hay
	if (parteDecimal) {
		let cantidadDigitosDespuesDelPunto = parteDecimal.length;
		return cantidadDigitosDespuesDelPunto;
	} else {
		return 0;
	}

}

export function generarUUID() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
    var r = Math.random() * 16 | 0,
        v = c == 'x' ? r : (r & 0x3 | 0x8);
    return v.toString(16);
  });
}