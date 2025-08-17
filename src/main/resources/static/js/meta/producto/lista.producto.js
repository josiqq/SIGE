let pagina = 0;
let limite = 20;
let productos = '';
let ultimoProducto;
let cantidadObject;

const buscarCantidad = async() =>{
	const cantiRespuesta = await fetch(`/productos/pagina/cantidad`);
	const datoCantidad = await cantiRespuesta.json();
	cantidadObject = datoCantidad;
}
buscarCantidad();
let observador = new IntersectionObserver((entradas, observador) => {
	if(pagina<cantidadObject){
		entradas.forEach(entrada => {
			if(entrada.isIntersecting){
				pagina = pagina+limite;
				cargarProductos();
			}
		});
	}
	
}, {
	rootMargin: '0px 0px 200px 0px',
	threshold: 1.0
});


const cargarProductos = async() =>{

	const respuesta = await fetch(`/productos/pagina?pagina=${pagina}&limit=${limite}`);
	
	if(respuesta.status === 200){
		
		const datos = await respuesta.json();
			
			datos.forEach(producto => {
				if(producto.foto ===''){
					producto.foto = "sinfoto.png";
				}
				productos += `
					<div class="producto">
						<img class="poster" src="/fotos/${producto.foto}">
						<h3 class="titulo">${producto.nombre}<span>${producto.id}</span></h3>
					</div>
				`;
			});
		document.getElementById('contenedor').innerHTML = productos;
	}
	
	const productosEnPantalla = document.querySelectorAll('.contenedor .producto');
				ultimoProducto = productosEnPantalla[productosEnPantalla.length - 1];
				observador.observe(ultimoProducto);
};

cargarProductos();
