package com.shop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.api.model.LocalizedTextEntity;

/**
 * The Interface LocalizedTextEntityRepository.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01 
 */
@Repository
public interface LocalizedTextEntityRepository extends JpaRepository<LocalizedTextEntity, Long> {
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@SuppressWarnings("unchecked")
	LocalizedTextEntity save(LocalizedTextEntity localizedTextEntity);
	
}
