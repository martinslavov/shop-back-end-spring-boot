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
 * Created by Martin Slavov on 01/08/2018.
 */
@Entity
@Table(name = "logs")
public class Log4j {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "logger")
    private String logger;
    
    @Column(name = "log_level")
    private String logLevel;
    
    @Column(name = "log_message")
    private String logMessages;
	
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "log_date", nullable = false, updatable=false,
    	    columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date created;
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }
    
    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }
    
    public String getLogMessages() {
        return logMessages;
    }

    public void setLogMessages(String logMessages) {
        this.logMessages = logMessages;
    }
}