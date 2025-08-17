export function mensajeError(mensaje) {
	let html = `<div class="alert alert-danger alert-dismissible fade show" role="alert">
				<div>
					<i class="fa fa-exclamation-circle me-2"></i> 
					<span>${mensaje}</span>
				</div>
				<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
			</div>`;
	$('.mensaje-emergente').html(html);
	setTimeout(function() {
		$('.mensaje-emergente').html('');
	}, 10000)
}

export function mensajeExito(mensaje) {
	let html = `<div class="alert alert-primary alert-dismissible fade show"
							role="alert" >
							<i class="fa fa-exclamation-circle me-2"></i> <span>${mensaje}</span>
							<button type="button" class="btn-close" data-bs-dismiss="alert"
								aria-label="Close"></button>
						</div>`;
	$('.mensaje-emergente').append(html);
	setTimeout(function() {
		$('.mensaje-emergente').html('');
	}, 10000)
}

export function mensajeAviso(mensaje) {
	let html = `<div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <i class="fa fa-exclamation-circle me-2"></i><span>${mensaje}</span>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>`;
	$('.mensaje-emergente').append(html);
	setTimeout(function() {
		$('.mensaje-emergente').html('');
	}, 10000)
}

