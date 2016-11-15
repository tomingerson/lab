package de.fh_kiel.person.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception indicating that we could not lookup an entity.
 * @author Created by tom on 13.11.2016.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityMalformedException extends RuntimeException {
    public EntityMalformedException(String message, Throwable cause) {
        super(message, cause);
    }
}
