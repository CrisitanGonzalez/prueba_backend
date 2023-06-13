package cl.prueba.mantenedor_backend.helper;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class BadRequestBody {
    private HashMap<String, List<String>> errors;

}
