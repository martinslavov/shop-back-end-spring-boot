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

@Entity
@Table(name = "categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "category_id")
	private int categoryId;
	
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
	@JoinTable(name = "categories_products", 
		joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
	private Set<Product> product = new HashSet<Product>();

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "i18n_categories",
            joinColumns = @JoinColumn(name = "text_id"),
            inverseJoinColumns = @JoinColumn(name = "i18n_id"))
    @MapKey(name = "locale")
    private Map<String, LocalizedTextEntity> text = new HashMap<String, LocalizedTextEntity>();
	
	@Basic
    @Column(name = "active", columnDefinition = "BIT", length = 1)
    private Boolean enabled;

    public Category() {

    }

    public Category(long id, int categoryId, boolean enabled) {
    	this.id = id;
    	this.categoryId = categoryId;
    	this.enabled = enabled;    
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
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

}