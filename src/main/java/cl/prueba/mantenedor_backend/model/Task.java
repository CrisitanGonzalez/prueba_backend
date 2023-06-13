package cl.prueba.mantenedor_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private  int id;
    @NotNull
    private String description;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm" )
    private Date date;
    @NotNull
    private boolean enabled;
}
