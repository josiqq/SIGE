package com.meta.repository.helper.ajusteLote;

import com.meta.modelo.AjusteLote;
import com.meta.repository.filter.AjusteLoteFilter;
import java.util.List;

public interface AjusteLotesQueries {
   List<AjusteLote> getAjusteLotes(AjusteLoteFilter filter);
}
