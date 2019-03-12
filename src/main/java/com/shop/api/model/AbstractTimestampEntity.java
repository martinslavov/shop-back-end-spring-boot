package com.shop.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * The Class AbstractTimestampEntity.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
@MappedSuperclass
public abstract class AbstractTimestampEntity {

	/** The created. */
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, updatable=false,
    		columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on create CURRENT_TIMESTAMP")
    private Date created;

    /** The updated. */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false,
    	    columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date updated;

    /**
     * On create.
     */
    @PrePersist
    protected void onCreate() {
    updated = created = new Date();
    }

    /**
     * On update.
     */
    @PreUpdate
    protected void onUpdate() {
    updated = new Date();
    }
    
    /**
     * Gets the created.
     *
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Sets the created.
     *
     * @param created the new created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * Gets the updated.
     *
     * @return the updated
     */
    public Date getUpdated() {
        return updated;
    }
	
    /**
     * Sets the updated.
     *
     * @param updated the new updated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
