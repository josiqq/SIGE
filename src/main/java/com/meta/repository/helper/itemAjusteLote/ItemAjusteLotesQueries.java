package com.meta.repository.helper.itemAjusteLote;

import com.meta.modelo.ItemAjusteLote;
import java.util.List;

public interface ItemAjusteLotesQueries {
   void eliminarByAjusteLote(Long id);

   List<ItemAjusteLote> getItemsLotes(Long idAjusteLote);
}
