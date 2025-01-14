package ca.qc.plachanc73.demo.restws.core.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exception thrown when the HttpResponse status is 500 (Internal Server Error)
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
public class HttpInternalServerErrorException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -4360749204591037779L;

    public HttpInternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
