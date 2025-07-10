
	if($('.in-foto').val()){
		console.log('cargarFotoExistente')
		cargarFotoExistente();
		
	}
		
	 
	
	 export function cargarFotoExistente(){
		if($('.in-nueva').val()=='true'){
			var foto ='temp/'+$('.in-foto').val()
		}else{
			foto = $('.in-foto').val();
		}
		var htmlFotoTemplate1 = $('#foto-hbs').html();
		var template1 = Handlebars.compile(htmlFotoTemplate1);
		var htmlFoto1 = template1({nombreFoto:foto}); 
		$('#upload-drop').addClass('noMostrar');
	    $('.js-container-foto').append(htmlFoto1);
	    $('.js-remove-foto').click(quitarFoto);
	}

	UIkit.upload('.bw-upload', {
		
		url:'/fotos',
		allow: '*.(jpg|jpeg|png|BMP|GIF|PSD )',
		concurrent:'1',
		type: 'json',
		complete:completado
	})
	
	function completado(respuesta){
		$('.in-nueva').val('true');
		$('.in-foto').val(respuesta.response.nombre);
		$('.in-contentType').val(respuesta.response.contentType);
		var foto = 'temp/'+respuesta.response.nombre;
		var htmlFotoTemplate = $('#foto-hbs').html();
		 var template = Handlebars.compile(htmlFotoTemplate);
		 var htmlFoto = template({nombreFoto:foto});
		 
		 $('#upload-drop').addClass('noMostrar');
		  $('.js-container-foto').append(htmlFoto);
		  
		  $('.js-remove-foto').click(quitarFoto);
		  
	}
	
	function quitarFoto(){
		$('#upload-drop').removeClass('noMostrar');
		$('.js-foto-hbs').remove();
		$('.in-foto').val('');
		$('.in-contentType').val('');
		
	};
	

	
	
	