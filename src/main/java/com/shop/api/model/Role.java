package com.shop.api.model;

import javax.persistence.*;

/**
 * The Class Role.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
@Entity
@Table(name = "role")
public class Role {

    /** The id. */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    /** The name. */
    @Column
    private String name;

    /** The description. */
    @Column
    private String description;

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
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
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
}