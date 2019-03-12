package com.shop.api.service.impl;

import com.shop.api.model.LocalizedTextEntity;
import com.shop.api.repository.LocalizedTextEntityRepository;
import com.shop.api.service.LocalizedTextEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class LocalizedTextEntityServiceImpl.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
@Service(value = "localizedTextEntityService")
public class LocalizedTextEntityServiceImpl implements LocalizedTextEntityService {
	
	/** The localized text entity repository. */
	@Autowired
	private LocalizedTextEntityRepository localizedTextEntityRepository;

	/* (non-Javadoc)
	 * @see com.shop.api.service.LocalizedTextEntityService#save(com.shop.api.model.LocalizedTextEntity)
	 */
	@Override
    public LocalizedTextEntity save(LocalizedTextEntity localizedTextEntity) {	
        return localizedTextEntityRepository.save(localizedTextEntity);
    }
}
