package ca.qc.plachanc73.demo.restws.core.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exception thrown when the HttpResponse status is 403 (Forbidden)
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Forbidden")
public class HttpForbiddenException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 7350078203529717752L;

    @Override
    public String getMessage() {
        return "Forbidden";
    }
}
