package com.shop.api.model;

import javax.persistence.*;

/**
 * The Class LocalizedTextEntity.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
@Entity
@Table(name = "i18n")
public class LocalizedTextEntity {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    /** The locale. */
    @Column(name = "locale")
    private String locale;

    /** The title. */
    @Column(name = "title")
    private String title;
    
    /** The text. */
    @Column(name = "text")
    private String text;
    
    /** The description. */
    @Column(name = "description")
    private String description;
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Gets the locale.
     *
     * @return the locale
     */
    public String getlocale() {
        return locale;
    }

    /**
     * Sets the locale.
     *
     * @param locale the new locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }
    
    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Gets the text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text.
     *
     * @param text the new text
     */
    public void setText(String text) {
        this.text = text;
    }
}