package com.shop.api.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The Class AbstractTimestampEntity.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
@Entity
@Table(name = "products")
public class Product extends AbstractTimestampEntity {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	/** The quantity. */
	@Column(name = "quantity")
	private int quantity;

	/** The price. */
	@Column(name = "price")
	private double price;
	
	/** The discount. */
	@Column(name = "discount",columnDefinition = "double default 0")
	private double discount;

	/** The href. */
	@Column(name = "href")
	private String href;

	/** The image. */
	@Column(name = "image")
	private String image;

	/** The categories. */
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JsonBackReference
	@JoinTable(name = "categories_products", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
	private Set<Category> categories = new HashSet<Category>();

	/** The text. */
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "i18n_products", joinColumns = @JoinColumn(name = "text_id"), inverseJoinColumns = @JoinColumn(name = "i18n_id"))
	@MapKey(name = "locale")
	private Map<String, LocalizedTextEntity> text = new HashMap<>();

	/** The enabled. */
	@Basic
	@Column(name = "active", columnDefinition = "BIT", length = 1)
	private Boolean enabled;

	/** The sale. */
	@Basic
	@Column(name = "sale", columnDefinition = "BIT", length = 1)
	private Boolean sale;

	/** The best saller. */
	@Basic
	@Column(name = "best_saller", columnDefinition = "BIT", length = 1)
	private Boolean bestSaller;
	
	/** The alt. */
	@Column(name = "alt")
	private String alt;
	

	/**
	 * Instantiates a new product.
	 */
	public Product() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * Sets the discount.
	 *
	 * @param discount the new discount
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * Gets the href.
	 *
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * Sets the href.
	 *
	 * @param href the new href
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * Gets the alt.
	 *
	 * @return the alt
	 */
	public String getAlt() {
		return alt;
	}

	/**
	 * Sets the alt.
	 *
	 * @param alt the new alt
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}

	/**
	 * Gets the categories.
	 *
	 * @return the categories
	 */
	public Set<Category> getCategories() {
		return categories;
	}

	/**
	 * Sets the categories.
	 *
	 * @param categories the new categories
	 */
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public Map<String, LocalizedTextEntity> getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the text
	 */
	public void setText(Map<String, LocalizedTextEntity> text) {
		this.text = text;
	}

	/**
	 * Gets the enabled.
	 *
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets the sale.
	 *
	 * @return the sale
	 */
	public Boolean getSale() {
		return sale;
	}

	/**
	 * Sets the sale.
	 *
	 * @param sale the new sale
	 */
	public void setSale(boolean sale) {
		this.sale = sale;
	}

	/**
	 * Gets the best saller.
	 *
	 * @return the best saller
	 */
	public Boolean getBestSaller() {
		return bestSaller;
	}

	/**
	 * Sets the best saller.
	 *
	 * @param bestSaller the new best saller
	 */
	public void setBestSaller(boolean bestSaller) {
		this.bestSaller = bestSaller;
	}
}