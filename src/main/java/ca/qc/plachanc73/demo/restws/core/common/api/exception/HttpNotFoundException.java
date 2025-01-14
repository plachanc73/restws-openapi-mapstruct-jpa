package ca.qc.plachanc73.demo.restws.core.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exception thrown when the HttpResponse status is 404 (Not Found)
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not Found")
public class HttpNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -4360749204591037779L;

    @Override
    public String getMessage() {
        return "Not found";
    }
}
