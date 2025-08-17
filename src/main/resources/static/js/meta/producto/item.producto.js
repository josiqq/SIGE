
	adicionarItemSuccess();
	$('.js-adicionar-codigo-alternativo').click(() => {
		if ($('#codigoAlternativo').val().trim().length > 0) {
			adicionarItem();
		}
	});

	$('#codigoAlternativo').keydown((e) => {
		if (e.keyCode === 13 || e.keyCode === 9) {
			e.preventDefault();
			if ($('#codigoAlternativo').val().trim().length > 0) {
				adicionarItem();
			}


		}
	});

	function adicionarItem() {
		let codigoAlternativo = $('#codigoAlternativo').val();
		let uuid = $('#uuid').val();
		
		$.ajax({
			url: '/productos/js/adiciona/item',
			method: 'POST',
			data: { codigoAlternativo: codigoAlternativo, uuid: uuid },
			success: adicionarItemSuccess,
			complete: adicionarItemComplete,
			error: function() {
				alert('Error adicionando codigo alternativo!!');
			}
		});
	}

	function adicionarItemSuccess(html) {
		$('.item-producto-codigo').html(html);

		$('.btn-item-quitar').off('click').click(eliminarItem);
	}

	function adicionarItemComplete() {
		$('#codigoAlternativo').val('');
	}

  function eliminarItem() {
		let indice = $(this).data('indice');
		let uuid = $('#uuid').val();
		
		$.ajax({
			url: '/productos/js/eliminar/item',
			method: 'DELETE',
			data: { indice: indice, uuid: uuid },
			success: adicionarItemSuccess,
			error: function() {
				alert('Error quitando código');
			}
		})
	}
