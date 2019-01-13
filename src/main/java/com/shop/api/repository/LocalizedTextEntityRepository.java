package com.shop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.api.model.LocalizedTextEntity;

/**
 * Created by Martin Slavov on 01/08/2018.
 */

@Repository
public interface LocalizedTextEntityRepository extends JpaRepository<LocalizedTextEntity, Long> {
	
	@SuppressWarnings("unchecked")
	LocalizedTextEntity save(LocalizedTextEntity localizedTextEntity);
	
}
