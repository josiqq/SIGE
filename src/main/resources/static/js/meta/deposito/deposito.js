$(function(){
	var url = new URL(window.location.href);
	var ruta = url.origin + url.pathname.split('/').slice(0, 2).join('/');
	
	$('#idDeposito').keydown((e)=>{
		if(e.keyCode === 13){
			e.preventDefault();
			var retornaDeposito = $.ajax({
				url:'/depositos/buscar/'+$('#idDeposito').val(),
				method:'GET'
			});
			retornaDeposito.done(llegoDeposito);
			
			function llegoDeposito (deposito){
			
					
				if(deposito.id != null){
					 window.location.href = ruta+'/'+deposito.id;
				}else{
					window.location.href = ruta;
				}
				
				
			}
		}
	});
	
	
});