package cl.prueba.mantenedor_backend.controller;

import cl.prueba.mantenedor_backend.model.Task;
import cl.prueba.mantenedor_backend.services.TaskService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private MainController mainController;

    @Test
    public void testHealthCheck() throws Exception {
        ResponseEntity<String> result = mainController.getHealthCheck();
        Assert.assertNotNull(result.getBody());
        Assert.assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    public void createTaskTest(){
        when(taskService.createTask(any(Task.class))).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
        ResponseEntity<?> result = mainController.createTask(Task.builder().build());
        Assert.assertEquals(HttpStatus.CREATED.value(), result.getStatusCodeValue());
    }

    @Test
    public void listTaskTest(){
        when(taskService.listTask()).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity<?> result = mainController.listTask();
        Assert.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    public void updateTaskTest(){
        when(taskService.updateTask(any(Task.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity<?> result = mainController.updateTask(Task.builder().build());
        Assert.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    public void deleteTaskTest(){
        when(taskService.deleteTask(any(Integer.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity<?> result = mainController.deleteTask(1);
        Assert.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

}
