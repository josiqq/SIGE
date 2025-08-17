package com.sige.repository.helper.ajusteLote;

import java.util.List;

import com.sige.model.AjusteLote;
import com.sige.repository.filter.AjusteLoteFilter;

public interface AjusteLotesQueries {
   List<AjusteLote> getAjusteLotes(AjusteLoteFilter filter);
}
