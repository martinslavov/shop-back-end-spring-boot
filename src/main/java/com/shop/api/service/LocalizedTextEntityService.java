package com.shop.api.service;

import com.shop.api.model.LocalizedTextEntity;

/**
 * The Interface LocalizedTextEntityService.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
public interface LocalizedTextEntityService {

	/**
	 * Save.
	 *
	 * @param LocalizedTextEntity the localized text entity
	 * @return the localized text entity
	 */
	LocalizedTextEntity save(LocalizedTextEntity LocalizedTextEntity);
	
}
