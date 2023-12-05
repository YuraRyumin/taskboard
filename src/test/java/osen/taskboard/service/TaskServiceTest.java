package osen.taskboard.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import osen.taskboard.domain.Task;
import osen.taskboard.domain.User;

public class TaskServiceTest {
    @InjectMocks
    TaskService taskService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void SaveTask(){
        Task task = new Task("Header", "Discription", null, "1111-11111111-1111", new User(), false);
        Assertions.assertDoesNotThrow(() -> taskService.saveTask(task));
    }

    @Test
    void EmptyDTOTest(){
        Assertions.assertEquals(taskService.getEmptyDTO().getId(), 0);
        Assertions.assertEquals(taskService.getEmptyDTO().getHeader(), "");
        Assertions.assertEquals(taskService.getEmptyDTO().getDescription(), "");
        Assertions.assertEquals(taskService.getEmptyDTO().getUuid(), "");
        Assertions.assertEquals(taskService.getEmptyDTO().getParent(), "");
        Assertions.assertEquals(taskService.getEmptyDTO().getUser(), "");
        Assertions.assertFalse(taskService.getEmptyDTO().isDone());
    }
}
