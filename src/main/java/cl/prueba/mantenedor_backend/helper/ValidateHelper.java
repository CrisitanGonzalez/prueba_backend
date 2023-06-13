package cl.prueba.mantenedor_backend.helper;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Log4j2
public class ValidateHelper {
    public static ResponseEntity<?> validate(String authorization) {
        try {
            if (authorization.length() < 8 || !authorization.startsWith("Bearer ")) {
                return new RestBadRequestBuilder()
                        .addError("authorization", "Se requiere encabezado Authorization Bearer.")
                        .buildBadRequest();
            }
            return null;
        } catch (Exception ex) {
            log.error("No se pudo autenticar utilizando JWT", ex);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
