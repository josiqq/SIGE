$(function(){
	window.onkeydown = presionaTecla;
	function presionaTecla(e){
		if(e.keyCode === 13){
			e.preventDefault();
		}
	};
	$('#cliente').click(()=>{
		$('#cliente').select();
	});
	
	$('#cliente').keyup((e)=>{
		
		if($('#cliente').val().length===0){
			$('#idCliente').val('');
		}
	})
})