package ca.qc.plachanc73.demo.restws.core.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exception thrown when the HttpResponse status is 400 (Bad Request)
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad Request")
public class HttpBadRequestException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -241235080009425712L;

    @Override
    public String getMessage() {
        return "Bad Request";
    }
}
