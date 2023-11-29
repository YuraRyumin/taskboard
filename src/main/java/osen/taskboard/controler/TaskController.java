package osen.taskboard.controler;

import org.springframework.web.bind.annotation.*;
import osen.taskboard.domain.Task;
import osen.taskboard.dto.TaskDTO;
import osen.taskboard.service.TaskService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/taskslist")
    public Iterable<TaskDTO> getTasks(){
        return taskService.getAllTasks();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createtask")
    public void createUser(@RequestBody Task task){
        taskService.saveTask(task);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/savetask")
    public void saveUser(@RequestBody Task task){
        taskService.saveTask(task);
    }
}
