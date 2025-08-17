let imagenEquipoA = $('.img-equipo-a');
let imagenEquipoB = $('.img-equipo-b');
let nombreEquipoA = $('.nombre-equipo-a');
let puntoEquipoA = $('.punto-equipo-a');
let nombreEquipoB = $('.nombre-equipo-b');
let puntoEquipoB = $('.punto-equipo-b');
setInterval(buscarMarcaciones,1000);

function buscarMarcaciones(){
	var respuestaMarcacion = $.ajax({
									url:'/marcaciones/abiertas',
									method:'GET'
								});
		respuestaMarcacion .done(llegoRespuesta);
		
		function llegoRespuesta(marcacion){
			
			if(marcacion.id!=null){
				puntoEquipoA.removeClass('noMostrar');
				puntoEquipoB.removeClass('noMostrar');
				imagenEquipoA.attr('src','/fotos/'+marcacion.equipoA.foto);
				imagenEquipoB.attr('src','/fotos/'+marcacion.equipoB.foto);
				nombreEquipoA.val(marcacion.equipoA.nombreCorto);
				puntoEquipoA.val(marcacion.puntoA);
				nombreEquipoB.val(marcacion.equipoB.nombreCorto);
				puntoEquipoB.val(marcacion.puntoB);	
				$('.js-game-uno-a').val(marcacion.gameUnoA);
				$('.js-game-uno-b').val(marcacion.gameUnoB);
				$('.js-game-dos-a').val(marcacion.gameDosA);
				$('.js-game-dos-b').val(marcacion.gameDosB);
				$('.js-game-tres-a').val(marcacion.gameTresA);
				$('.js-game-tres-b').val(marcacion.gameTresB);
			}else{
				
				$.ajax({
					url:'/marcaciones/terminado',
					method:'GET',
					success:terminadoSuccess,
					error:terminadoError
				});
				
				function terminadoSuccess(marcacion){
					if(marcacion.id!=null){
						puntoEquipoA.addClass('noMostrar');
						puntoEquipoB.addClass('noMostrar');
					
						imagenEquipoA.attr('src','/fotos/'+marcacion.equipoA.foto);
						imagenEquipoB.attr('src','/fotos/'+marcacion.equipoB.foto);
						nombreEquipoA.val(marcacion.equipoA.nombreCorto);
						nombreEquipoB.val(marcacion.equipoB.nombreCorto);
						$('.js-game-uno-a').val(marcacion.gameUnoA);
						$('.js-game-uno-b').val(marcacion.gameUnoB);
						$('.js-game-dos-a').val(marcacion.gameDosA);
						$('.js-game-dos-b').val(marcacion.gameDosB);
						$('.js-game-tres-a').val(marcacion.gameTresA);
						$('.js-game-tres-b').val(marcacion.gameTresB);
					}else{
						imagenEquipoA.attr('src','/fotos/sinfoto.png');
						imagenEquipoB.attr('src','/fotos/sinfoto.png');
						nombreEquipoA.val('Libre');
						puntoEquipoA.val(0);
						nombreEquipoB.val('Libre');
						puntoEquipoB.val(0);
						$('.js-game-uno-a').val(0);
						$('.js-game-uno-b').val(0);
						$('.js-game-dos-a').val(0);
						$('.js-game-dos-b').val(0);
						$('.js-game-tres-a').val(0);
						$('.js-game-tres-b').val(0);
					}
				};
				function terminadoError(error){
					alert(`retorno de marcacion terminada con error: ${error}`);
				}
				
			}
			
			
		}
}