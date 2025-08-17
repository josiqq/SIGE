export function mensaje(titulo,mensaje,icono,tiempo){
	$.toast({
			heading: titulo,
			text: mensaje,
			showHideTransition: 'fade',
			icon: icono,
			position: 'top-right',
			hideAfter: tiempo
			
		})
}