package ca.qc.plachanc73.demo.restws.core.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exception thrown when the HttpResponse status is 408 (Request Timeout)
 */
@ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT, reason = "Request Timeout")
public class HttpRequestTimeoutException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -4360749204591037779L;

    @Override
    public String getMessage() {
        return "Request Timeout";
    }
}
