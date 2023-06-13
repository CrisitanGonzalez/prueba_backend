package cl.prueba.mantenedor_backend.helper;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorApi {
    private HttpStatus status;
    private String message;
    private String cause;

    public ErrorApi(HttpStatus status, String message, String cause) {
        super();
        this.setStatus(status);
        this.message = message;
        this.cause = cause;
    }
}
