package cl.prueba.mantenedor_backend.controller;


import cl.prueba.mantenedor_backend.model.Task;
import cl.prueba.mantenedor_backend.services.TaskService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController()
@Api(tags = "", value="")
@CrossOrigin(origins ="*", allowedHeaders = "*")
@Log4j2
public class MainController {
    @Autowired
    private TaskService taskService;
    @GetMapping(path = "/api/health-check")
    public ResponseEntity<String> getHealthCheck(){
        return ResponseEntity.ok("{\"status\":\"ok\"}");
    }

    @ApiOperation(value="create task", notes="")
    @ApiResponses({@ApiResponse(code = 201, message = "correct", response = HttpStatus.class),
            @ApiResponse(code = 409, message = "error", response = HttpStatus.class)})
    @PostMapping (value = "/v1/task")
    public ResponseEntity<?> createTask(@Valid @RequestBody Task task){
        return taskService.createTask(task);
    }

    @ApiOperation(value="list tasks", notes="")
    @ApiResponses({@ApiResponse(code = 200, message = "correct", response = HttpStatus.class),
            @ApiResponse(code = 409, message = "error", response = HttpStatus.class)})
    @GetMapping (value = "/v1/task")
    public ResponseEntity<?> listTask(){
        return taskService.listTask();
    }

    @ApiOperation(value="create task", notes="")
    @ApiResponses({@ApiResponse(code = 200, message = "correct", response = HttpStatus.class),
            @ApiResponse(code = 409, message = "error", response = HttpStatus.class)})
    @PutMapping (value = "/v1/task")
    public ResponseEntity<?> updateTask(@Valid @RequestBody Task task){
        return taskService.updateTask(task);
    }

    @ApiOperation(value="list tasks", notes="")
    @ApiResponses({@ApiResponse(code = 200, message = "correct", response = HttpStatus.class),
            @ApiResponse(code = 409, message = "error", response = HttpStatus.class)})
    @DeleteMapping(value = "/v1/task/{id}")
    public ResponseEntity<?> deleteTask( @PathVariable(name = "id", required = true) Integer id ){
        return taskService.deleteTask(id);
    }
}
