let imagenA = $('.img-equipo-a');
let imagenB = $('.img-equipo-b');
let equipoA = $('.js-equipo-a');
let equipoB = $('.js-equipo-b');
let sumarA = $('.js-sumar-a');
let restarA = $('.js-restar-a');
let marcadorA = $('.js-marcador-a');
let sumarB = $('.js-sumar-b');
let restarB = $('.js-restar-b');
let marcadorB = $('.js-marcador-b');
let idMarcacion = $('.js-id-marcacion');
let btnTerminar = $('.js-btn-terminar');
let terminado = $('.js-terminado');
if(!idMarcacion.val()){
	imagenA.attr('src','/fotos/sinfoto.png');
	imagenB.attr('src','/fotos/sinfoto.png');
	sumarA.prop('disabled','true');
	sumarB.prop('disabled','true');
	restarA.prop('disabled','true');
	restarB.prop('disabled','true');
}
equipoA.change((e)=>{
	var elegido = $(e.target).val();
	
	var respuestaImagenA=$.ajax({
		url:'/equipos/buscarFoto',
		method:'GET',
		data:{id:elegido},
		contentType:'application/json'
	});
	
	respuestaImagenA.done((e)=>{
		if(e){
			imagenA.attr('src','/fotos/'+e);	
		}else{
			imagenA.attr('src','/fotos/sinfoto.png');
		}
		
	})
});

equipoB.change((e)=>{
	var elegido = $(e.target).val();
	
	var respuestaImagenB=$.ajax({
		url:'/equipos/buscarFoto',
		method:'GET',
		data:{id:elegido},
		contentType:'application/json'
	});
	
	respuestaImagenB.done((e)=>{
		if(e){
			imagenB.attr('src','/fotos/'+e);	
		}else{
			imagenB.attr('src','/fotos/sinfoto.png');
		}
		
	})
});

sumarA.click((e)=>{
	e.preventDefault();
	marcadorA.val(Number(marcadorA.val())+1);
	$.ajax({
		url:'marcaciones/marcadorA',
		data:{puntoA:marcadorA.val()},
		method:'POST'
	});
	
});

restarA.click((e)=>{
	e.preventDefault();
	if(Number(marcadorA.val())>0){
		marcadorA.val(Number(marcadorA.val())-1);
		$.ajax({
		url:'marcaciones/marcadorA',
		data:{puntoA:marcadorA.val()},
		method:'POST'
	});
	}
});

sumarB.click((e)=>{
	e.preventDefault();
	marcadorB.val(Number(marcadorB.val())+1);
	$.ajax({
		url:'marcaciones/marcadorB',
		data:{puntoB:marcadorB.val()},
		method:'POST'
	});
	
});

restarB.click((e)=>{
	e.preventDefault();
	if(Number(marcadorB.val())>0){
		marcadorB.val(Number(marcadorB.val())-1);
		$.ajax({
		url:'marcaciones/marcadorB',
		data:{puntoB:marcadorB.val()},
		method:'POST'
	});
	}
})

btnTerminar.click(()=>{
	terminado.val(1);
})

$('.js-btn-terminarGame1').click(()=>{
	$('.js-terminado-game-uno').val(1);
});
	
$('.js-btn-terminarGame2').click(()=>{
	$('.js-terminado-game-dos').val(1);
});
$('.js-btn-terminarGame3').click(()=>{
	$('.js-terminado-game-tres').val(1);
})