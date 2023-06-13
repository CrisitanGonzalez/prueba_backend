package cl.prueba.mantenedor_backend.repository;

import cl.prueba.mantenedor_backend.exception.PruebaException;
import cl.prueba.mantenedor_backend.mapper.DataMapper;
import cl.prueba.mantenedor_backend.model.Task;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Log4j2
@Repository
public class DataBaseRepo {
    @Autowired
    private DataMapper mapper;

    public void insertTask(Task task) {
        try{
            mapper.insertTask(task);
        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new PruebaException(ex);
        }
    }

    public List<Task> listTask(){
        try{
            return mapper.listTask();
        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new PruebaException(ex);
        }
    }

    public void updateTask(Task task){
        try{
            mapper.updateTask(task);
        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new PruebaException(ex);
        }
    }

    public void deleteTask(int id){
        try{
            mapper.deleteTask(id);
        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new PruebaException(ex);
        }
    }

}
