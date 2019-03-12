package com.shop.api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import lombok.*;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The Class ApiError.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01 
 */
@Data
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
class ApiError {

    /** The status. */
    private HttpStatus status;
    
    /** The timestamp. */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    
    /** The message. */
    private String message;
    
    /** The debug message. */
    private String debugMessage;
    
    /** The sub errors. */
    private List<ApiSubError> subErrors;
    
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Gets the debug message.
	 *
	 * @return the debug message
	 */
	public String getDebugMessage() {
		return debugMessage;
	}
	
	/**
	 * Sets the debug message.
	 *
	 * @param debugMessage the new debug message
	 */
	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;	
	}
    

    /**
     * Instantiates a new api error.
     */
    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    /**
     * Instantiates a new api error.
     *
     * @param status the status
     */
    ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    /**
     * Instantiates a new api error.
     *
     * @param status the status
     * @param ex the ex
     */
    ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    /**
     * Instantiates a new api error.
     *
     * @param status the status
     * @param message the message
     * @param ex the ex
     */
    ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    /**
     * Adds the sub error.
     *
     * @param subError the sub error
     */
    private void addSubError(ApiSubError subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

    /**
     * Adds the validation error.
     *
     * @param object the object
     * @param field the field
     * @param rejectedValue the rejected value
     * @param message the message
     */
    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationError(object, field));
    }

    /**
     * Adds the validation error.
     *
     * @param object the object
     * @param message the message
     */
    private void addValidationError(String object, String message) {
        addSubError(new ApiValidationError(object, message));
    }

    /**
     * Adds the validation error.
     *
     * @param fieldError the field error
     */
    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    /**
     * Adds the validation errors.
     *
     * @param fieldErrors the field errors
     */
    void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    /**
     * Adds the validation error.
     *
     * @param objectError the object error
     */
    private void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    /**
     * Adds the validation error.
     *
     * @param globalErrors the global errors
     */
    void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    /**
     * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.
     * @param cv the ConstraintViolation
     */
    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage());
    }

    /**
     * Adds the validation errors.
     *
     * @param constraintViolations the constraint violations
     */
    void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }



    /**
     * The Class ApiSubError.
     */
    abstract class ApiSubError {

    }

    /**
     * The Class ApiValidationError.
     */
    @Data
    @EqualsAndHashCode(callSuper = false)
    @AllArgsConstructor
    class ApiValidationError extends ApiSubError {
        
        /** The object. */
        private String object;
        
        /** The field. */
        private String field;
        
        /** The rejected value. */
        private Object rejectedValue;
        
        /** The message. */
        private String message;

    	/**
	     * Gets the object.
	     *
	     * @return the object
	     */
	    public String getObject() {
    		return object;
    	}

    	/**
	     * Sets the object.
	     *
	     * @param object the new object
	     */
	    public void setObject(String object) {
    		this.object = object;
    	}
    	
    	/**
	     * Gets the field.
	     *
	     * @return the field
	     */
	    public String getField() {
    		return field;
    	}

    	/**
	     * Sets the field.
	     *
	     * @param field the new field
	     */
	    public void setField(String field) {
    		this.field = field;
    	}
    	
    	/**
	     * Gets the rejected value.
	     *
	     * @return the rejected value
	     */
	    public Object getRejectedValue() {
    		return rejectedValue;
    	}

    	/**
	     * Sets the rejected value.
	     *
	     * @param rejectedValue the new rejected value
	     */
	    public void setRejectedValue(Object rejectedValue) {
    		this.rejectedValue = rejectedValue;
    	}
        
    	/**
	     * Gets the message.
	     *
	     * @return the message
	     */
	    public String getMessage() {
    		return message;
    	}

    	/**
	     * Sets the message.
	     *
	     * @param message the new message
	     */
	    public void setMessage(String message) {
    		this.message = message;
    	}
        
        /**
         * Instantiates a new api validation error.
         *
         * @param object the object
         * @param message the message
         */
        ApiValidationError(String object, String message) {
            this.object = object;
            this.message = message;
        }
    }

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}
}

/**
 * The Class LowerCaseClassNameResolver.
 */
class LowerCaseClassNameResolver extends TypeIdResolverBase {

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.jsontype.TypeIdResolver#idFromValue(java.lang.Object)
     */
    @Override
    public String idFromValue(Object value) {
        return value.getClass().getSimpleName().toLowerCase();
    }

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.jsontype.TypeIdResolver#idFromValueAndType(java.lang.Object, java.lang.Class)
     */
    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return idFromValue(value);
    }

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.jsontype.TypeIdResolver#getMechanism()
     */
    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}