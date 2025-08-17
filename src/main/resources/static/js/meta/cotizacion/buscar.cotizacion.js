$(function(){
	$('.date').mask('00/00/0000');
	$('.money').mask("#.##0", {reverse: true});
	$('.money2').mask("#.##0,00", {reverse: true});
	$('input').click(function(){
		$(this).select();
	})
});