package cl.prueba.mantenedor_backend.services;

import cl.prueba.mantenedor_backend.model.Task;
import cl.prueba.mantenedor_backend.repository.DataBaseRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class TaskService {

    @Autowired
    private DataBaseRepo repository;
    public ResponseEntity<?> createTask (Task task){
        try{
            repository.insertTask(task);
            return new ResponseEntity<>(task,HttpStatus.CREATED);
        }catch(Exception ex){
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> listTask(){
        try{
            List<Task> tasks = repository.listTask();
            return new ResponseEntity<>(tasks,HttpStatus.OK);
        }catch(Exception ex){
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> updateTask (Task task){
        try{
            repository.updateTask(task);
            return new ResponseEntity<>(task,HttpStatus.OK);
        }catch(Exception ex){
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    public ResponseEntity<?> deleteTask (Integer id){
        try{
            repository.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception ex){
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
