package com.shop.api.service.impl;

import com.shop.api.model.LocalizedTextEntity;
import com.shop.api.repository.LocalizedTextEntityRepository;
import com.shop.api.service.LocalizedTextEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Martin Slavov on 01/08/2018.
 */

@Service(value = "localizedTextEntityService")
public class LocalizedTextEntityServiceImpl implements LocalizedTextEntityService {
	
	@Autowired
	private LocalizedTextEntityRepository localizedTextEntityRepository;

	@Override
    public LocalizedTextEntity save(LocalizedTextEntity localizedTextEntity) {	
        return localizedTextEntityRepository.save(localizedTextEntity);
    }
}
