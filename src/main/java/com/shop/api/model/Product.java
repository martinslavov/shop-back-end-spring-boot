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
 * Created by Martin Slavov on 01/08/2018.
 */

@Entity
@Table(name = "products")
public class Product extends AbstractTimestampEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "price")
	private double price;
	
	@Column(name = "discount",columnDefinition = "double default 0")
	private double discount;

	@Column(name = "href")
	private String href;

	@Column(name = "image")
	private String image;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JsonBackReference
	@JoinTable(name = "categories_products", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
	private Set<Category> categories = new HashSet<Category>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "i18n_products", joinColumns = @JoinColumn(name = "text_id"), inverseJoinColumns = @JoinColumn(name = "i18n_id"))
	@MapKey(name = "locale")
	private Map<String, LocalizedTextEntity> text = new HashMap<>();

	@Basic
	@Column(name = "active", columnDefinition = "BIT", length = 1)
	private Boolean enabled;

	@Basic
	@Column(name = "sale", columnDefinition = "BIT", length = 1)
	private Boolean sale;

	@Basic
	@Column(name = "best_saller", columnDefinition = "BIT", length = 1)
	private Boolean bestSaller;
	
	@Column(name = "alt")
	private String alt;
	

	public Product() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Map<String, LocalizedTextEntity> getText() {
		return text;
	}

	public void setText(Map<String, LocalizedTextEntity> text) {
		this.text = text;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getSale() {
		return sale;
	}

	public void setSale(boolean sale) {
		this.sale = sale;
	}

	public Boolean getBestSaller() {
		return bestSaller;
	}

	public void setBestSaller(boolean bestSaller) {
		this.bestSaller = bestSaller;
	}
}
