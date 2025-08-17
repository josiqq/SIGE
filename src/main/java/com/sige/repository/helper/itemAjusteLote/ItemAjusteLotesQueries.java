package com.sige.repository.helper.itemAjusteLote;

import java.util.List;

import com.sige.model.ItemAjusteLote;

public interface ItemAjusteLotesQueries {
   void eliminarByAjusteLote(Long id);

   List<ItemAjusteLote> getItemsLotes(Long idAjusteLote);
}
