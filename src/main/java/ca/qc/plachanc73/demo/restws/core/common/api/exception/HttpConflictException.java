package ca.qc.plachanc73.demo.restws.core.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exception thrown when the HttpResponse status is 409 (Conflict)
 */
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Conflict")
public class HttpConflictException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 7350078203529717953L;

    @Override
    public String getMessage() {
        return "Conflict";
    }
}
