package cl.prueba.mantenedor_backend.mapper;

import cl.prueba.mantenedor_backend.model.Task;

import java.util.List;

public interface DataMapper {
    void insertTask(Task task);
    List<Task> listTask();

    void updateTask(Task task);
    void deleteTask(int id);

}
