package com.shop.api.test.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.shop.api.model.Category;
import com.shop.api.repository.CategoryRepository;
import com.shop.api.service.impl.CategoryServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The Class CategoryServiceTest.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2019-03-13
 */
@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

	/** The category service. */
	@InjectMocks
	CategoryServiceImpl categoryService;
	
	/** The category repository. */
	@Mock
	CategoryRepository categoryRepository;
	
	/** The Constant ID. */
	private static final long ID = 1;

	/**
	 * When category is present then it should return true.
	 */
	@Test
	public void whenCategoryIsPresentThenItShouldReturnTrue() {
		when(categoryRepository.existsById(ID)).thenReturn(true);
		assertTrue(categoryService.existsById(ID));
	}

	/**
	 * When id is given then it should return category.
	 */
	@Test
	public void whenIdIsGivenThenItShouldReturnCategory() {
		Category mockCategory = mock(Category.class);

		when(categoryRepository.findById((Long)ID)).thenReturn(Optional.of(mockCategory));
		assertEquals(mockCategory, categoryService.findById(ID));
	}

	/**
	 * When category is present then it should return category list.
	 */
	@Test
	public void whenCategoryIsPresentThenItShouldReturnCategoryList() {
		List<Category> category = new ArrayList<>();
		Category mockCategory = mock(Category.class);
		category.add(mockCategory);

		when(mockCategory.getId()).thenReturn(ID);
		when(mockCategory.getEnabled()).thenReturn(true);
		when(categoryRepository.findAll()).thenReturn( category );

		List<Category> retrievedmockCategories = categoryService.findAll();
		assertEquals(retrievedmockCategories.size(), category.size());
		assertEquals(retrievedmockCategories.get(0), category.get(0));
	}

	/**
	 * When adding category then added category should return.
	 */
	@Test
	public void whenAddingCategoryThenAddedCategoryShouldReturn() {
		Category mockCategory = mock(Category.class);

		when(mockCategory.getId()).thenReturn(ID);
		when(mockCategory.getEnabled()).thenReturn(true);
		when(categoryRepository.save(mockCategory)).thenReturn(mockCategory);

		assertEquals(mockCategory, categoryService.save(mockCategory));
	}

	/**
	 * When updating category then updated category should return.
	 */
	@Test
	public void whenUpdatingCategoryThenUpdatedCategoryShouldReturn() {
		Category mockCategory = mock(Category.class);
		mockCategory.setId(ID);
		
		when(mockCategory.getId()).thenReturn(ID);
		when(mockCategory.getEnabled()).thenReturn(true);
		when(categoryRepository.findById((Long)ID)).thenReturn(Optional.of(mockCategory));
		when(categoryRepository.save(mockCategory)).thenReturn(mockCategory);

		assertEquals(mockCategory, categoryService.save(mockCategory));
	}

	/**
	 * When deleting category delete method should invoked.
	 */
	@Test
	public void whenDeletingCategoryDeleteMethodShouldInvoked() {
		Category mockCategory = mock(Category.class);

		when(categoryRepository.findById((Long)ID)).thenReturn(Optional.of(mockCategory));

		categoryService.delete(ID);
		verify(categoryRepository, times(1)).deleteById(ID);
	}
}