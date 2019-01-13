package com.shop.api.model;

import java.util.Map;

/**
 * Created by Martin Slavov on 01/08/2018.
 */

public interface CategoryOnly {
	  long getId();
	  int getCategoryId();
	  Boolean getEnabled();
	  Map<String, LocalizedTextEntity> getText();
}
