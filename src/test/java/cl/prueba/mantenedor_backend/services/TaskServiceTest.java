package cl.prueba.mantenedor_backend.services;

import cl.prueba.mantenedor_backend.controller.MainController;
import cl.prueba.mantenedor_backend.model.Task;
import cl.prueba.mantenedor_backend.repository.DataBaseRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @Mock
    private DataBaseRepo repository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testCreateTask() {
        Task task = new Task();
        doNothing().when(repository).insertTask(any(Task.class));

        ResponseEntity<?> response = taskService.createTask(task);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(task, response.getBody());
        verify(repository, times(1)).insertTask(task);
    }

    @Test
    public void testListTask() {
        List<Task> tasks = new ArrayList<>();
        when(repository.listTask()).thenReturn(tasks);

        ResponseEntity<?> response = taskService.listTask();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
        verify(repository, times(1)).listTask();
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task();
        doNothing().when(repository).updateTask(any(Task.class));

        ResponseEntity<?> response = taskService.updateTask(task);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
        verify(repository, times(1)).updateTask(task);
    }

    @Test
    public void testDeleteTask() {
        Integer id = 1;
        doNothing().when(repository).deleteTask(id);

        ResponseEntity<?> response = taskService.deleteTask(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(repository, times(1)).deleteTask(id);
    }
}
