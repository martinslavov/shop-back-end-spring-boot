package com.shop.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

/**
 * The Class Log4j.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01
 */
@Entity
@Table(name = "logs")
public class Log4j {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
    /** The user id. */
    @Column(name = "user_id")
    private String userId;
    
    /** The logger. */
    @Column(name = "logger")
    private String logger;
    
    /** The log level. */
    @Column(name = "log_level")
    private String logLevel;
    
    /** The log messages. */
    @Column(name = "log_message")
    private String logMessages;
	
    /** The created. */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "log_date", nullable = false, updatable=false,
    	    columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date created;
    
    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * Gets the logger.
     *
     * @return the logger
     */
    public String getLogger() {
        return logger;
    }

    /**
     * Sets the logger.
     *
     * @param logger the new logger
     */
    public void setLogger(String logger) {
        this.logger = logger;
    }
    
    /**
     * Gets the log level.
     *
     * @return the log level
     */
    public String getLogLevel() {
        return logLevel;
    }

    /**
     * Sets the log level.
     *
     * @param logLevel the new log level
     */
    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }
    
    /**
     * Gets the log messages.
     *
     * @return the log messages
     */
    public String getLogMessages() {
        return logMessages;
    }

    /**
     * Sets the log messages.
     *
     * @param logMessages the new log messages
     */
    public void setLogMessages(String logMessages) {
        this.logMessages = logMessages;
    }
}