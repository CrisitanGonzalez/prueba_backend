package cl.prueba.mantenedor_backend.helper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RestBadRequestBuilder {
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String APPLICATION_VND_GG2_SPRING_VALIDATION_JSON = "application/vnd.gg2.spring-validation+json";
    HashMap<String, List<String>> errors;

    public RestBadRequestBuilder() {
        errors = new HashMap<>();
    }


    public RestBadRequestBuilder addError(String field, String msg) {
        if (errors.containsKey(field)) {
            errors.get(field).add(msg);
        } else {
            errors.put(field, Collections.singletonList(msg));
        }
        return this;
    }


    public ResponseEntity<BadRequestBody> buildBadRequest() {
        BadRequestBody body = new BadRequestBody();
        body.setErrors(errors);
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE_HEADER, APPLICATION_VND_GG2_SPRING_VALIDATION_JSON);
        return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);
    }
}
