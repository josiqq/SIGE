export function getPrecioByProductoPrecio(producto,precio){
    return new Promise(function(resolve,reject){
        $.ajax({
            url:'/precios/buscarPrecio/js/',
            method:'GET',
            data:{producto:producto,precio:precio},
            success:function(precioProducto){
                resolve(precioProducto);
            },
            error:function(error){
                reject(error);
            }
        })
    })
}