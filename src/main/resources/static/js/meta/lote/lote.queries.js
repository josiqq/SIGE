export function gerLoteByNumero(numeroLote,idProducto,deposito){
	return new Promise(function(resolve, reject){
		$.ajax({
			url:'/lotes/get-by-nro',
			method:'GET',
			data:{nroLote:numeroLote,idProducto:idProducto,deposito:deposito},
			success:function(lote){
				resolve(lote);
			},
			error:function(xhr){
				reject(xhr);
			}
		})
	})
}

export function getLoteBydepositoProucto(idProducto,deposito){
	return new Promise(function(resolve,reject){
		$.ajax({
			url:'/lotes/get-by-deposito-producto',
			method:'GET',
			data:{idProducto:idProducto,deposito:deposito},
			success:function(lotes){
				resolve(lotes);
			},
			error:function(xhr){
				reject(xhr);
			}
		})
	})
}

export function getItemLoteVentaByProducto(producto, uuid){
	return new Promise(function(resolve,reject){
		$.ajax({
				url:'/ventas/js/get-items-lotes-by-producto',
				method:'GET',
				data:{producto:producto,uuid:uuid},	
				success:function(items){
					resolve(items);
				},
				error:function(xhr){
					reject(xhr);
				}	
		})
	})
}