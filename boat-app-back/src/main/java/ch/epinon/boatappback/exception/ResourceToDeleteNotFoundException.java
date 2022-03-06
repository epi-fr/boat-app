package ch.epinon.boatappback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceToDeleteNotFoundException extends RuntimeException {
    public ResourceToDeleteNotFoundException(String message) {
        super(message);
    }
}
