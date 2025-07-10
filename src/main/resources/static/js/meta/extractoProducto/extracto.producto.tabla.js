$(function(){
	//inicio
	var currentRow = 0;
	//para que pueda pintar la fila al presionar la flecha del teclado
	
	
    $("#tb-extracto-producto tr").eq(currentRow).addClass("colocar-medio-gris");
  
    $(document).keydown(function(e) {
		
      if (e.keyCode == 38) { // flecha arriba
        if (currentRow > 0) {
          currentRow--;
          $("#tb-extracto-producto tr").removeClass("colocar-medio-gris");
          $("#tb-extracto-producto tr").eq(currentRow).addClass("colocar-medio-gris");
          
        }
      } else if (e.keyCode == 40) { // flecha abajo
        if (currentRow < $("#tb-extracto-producto tr").length - 1) {
          currentRow++;
          $("#tb-extracto-producto tr").removeClass("colocar-medio-gris");
          $("#tb-extracto-producto tr").eq(currentRow).addClass("colocar-medio-gris");
         
        }
      }
    });
	//fin
})