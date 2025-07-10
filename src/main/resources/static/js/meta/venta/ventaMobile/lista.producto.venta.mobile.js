$(function(){
	let pagina = 0;
	let limite = 20;
	let productos = '';
	let nombreCodigo =$('#nobreCodigo') ;
	let btn_buscar =$('.btn-buscar-producto');
	let contenedor = $('.contenedor');
	let listaProductos = [];
	var finDeDatos = false;
	let deposito = $('#deposito');
	let precio_producto = $('#precio');
	
	deposito.change(()=>{
		pagina =0;
		listaProductos = [];
		cargarRegistros();
		
	});
	precio_producto.change(()=>{
		pagina =0;
		listaProductos = [];
		cargarRegistros();
	})
	
	nombreCodigo.focus();
	nombreCodigo.click(()=>{
		nombreCodigo.select();
	})
	nombreCodigo.keydown((e)=>{
		if(e.keyCode === 13){
			e.preventDefault();
			pagina =0;
			listaProductos = [];
			cargarRegistros();
		}
	});
	
	btn_buscar.click(()=>{
		pagina =0;
		listaProductos = [];
		cargarRegistros();
	 
	})  
  function cargarRegistros() {
	  let nombreCodigo = $('#nobreCodigo').val();
    $.ajax({
      url: '/productos/buscar/scroll', 
      type: 'GET',
      data: { 
        pagina: pagina,
        limite: limite,
        nombreCodigo: nombreCodigo,
        deposito:deposito.val(),
        precio:precio_producto.val()
      },
      success: getProductoSuccess,
      error: function(jqXHR, textStatus, errorThrown) {
        alert(`Error al recuperar productos: ${textStatus}  ${errorThrown}`);
      }
    });
  }

	function getProductoSuccess(productos){
		
		 if (productos.length > 0) {
         
          productos.forEach(function(producto) {
            if(producto.pesable === true){
					cantidad = $.number(producto.cantidad,'3',',','.');
				}else{
					cantidad = $.number(producto.cantidad,'0',',','.');
				}
				precio = $.number(producto.precio,'2',',','.');
				costo = $.number(producto.costo,'2',',','.');
				listaProductos.push(`
					
						<div class="producto col-xs-12 col-md-3 col-lg-4">
							<div class="producto-box">
								<div class="col-md-12 box-titulo">
									<span class="lista-producto-titulo">${producto.nombre}</span>
								</div>
								<div class="col-md-12 box-marca">
									<span class="lista-producto-marca">${producto.marca}</span>
								</div>
								<div class="col-md-12 contenedor-imagen-lista-producto">
									<img class="img-lista-producto" src="/fotos/${producto.foto}">
								</div>
								<div class="col-md-12 box-codigo">
									<span class="lista-producto-codigo">${producto.codigo}</span>
								</div>
								<div class="col-md-12 box-deposito">
									<span class="lista-producto-deposito-nombre">${producto.deposito}:</span>
									<span class="lista-producto-deposito-cantidad"> ${cantidad}</span>
								</div>
								<div class="col-md-12 box-precio">
									<span class="lista-producto-precio">G$ ${precio}</span>
								</div>
								<div class=" box-agregar">
									<div class=" row col-md-12 ">
										<div class="mt-1 col-6">
											<button type="button"  class="lista-producto-agregar"  data-nombre=${producto.nombre}
												data-id=${producto.id} data-precio=${precio} data-costo=${costo}
												>Agregar 
												<i class="bi bi-plus icono-agregar-lista-producto"></i>
											</button>
										</div>
										<div class="mt-1 col-6 ">
											<input type="number" class="form-control col-12 js-input-cantidad-venta-mobile" placeholder="1">
										</div>
									</div>
								</div>
							</div>
						</div>
					
				`);
          });
		
          $('#contenedor').html(listaProductos);
          adicionarItem();
          pagina = Number(pagina)+Number(limite);
        } else {
          
          finDeDatos = true;
        }
	}

 
  $(window).scroll(function() {
    if ($(window).scrollTop() + $(window).height() >= $(document).height() - 100 && !finDeDatos) {
      cargarRegistros();
    }
  });

 
  cargarRegistros();
	
})