package ch.epinon.boatappback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceToUpdateNotFoundException extends RuntimeException {
    public ResourceToUpdateNotFoundException(String message) {
        super(message);
    }
}
