package osen.taskboard.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osen.taskboard.domain.Task;
import osen.taskboard.domain.User;
import osen.taskboard.dto.TaskDTO;
import osen.taskboard.repo.TaskRepo;
import osen.taskboard.repo.UserRepo;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Transactional(readOnly = true)
@Service
public class TaskService {
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    public TaskService(TaskRepo taskRepo, UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    public TaskDTO getEmptyDTO(){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setHeader("");
        taskDTO.setDescription("");
        taskDTO.setUuid("");
        taskDTO.setParent("");
        taskDTO.setUser("");
        taskDTO.setDone(false);
        return taskDTO;
    }

    public TaskDTO convertEntityToDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(taskDTO.getId());
        taskDTO.setHeader(taskDTO.getHeader());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setUuid(taskDTO.getUuid());
        taskDTO.setDone(task.isDone());
        if(task.getParent() != null) {
            taskDTO.setParent(task.getParent().getUuid());
        }
        if(task.getUser() != null) {
            taskDTO.setUser(task.getUser().getUuid());
        }
        return taskDTO;
    }

    public Iterable<TaskDTO> convertAllEntitysToDTO(Iterable<Task> tasks){
        return StreamSupport.stream(tasks.spliterator(), false).
                map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    public Iterable<TaskDTO> getAllTasks(){
        return convertAllEntitysToDTO(taskRepo.findAll());
    }

    public Iterable<TaskDTO> getAllTasksByUser(String userUuid){
        return convertAllEntitysToDTO(taskRepo.findAllByUserUuid(userUuid));
    }

    @Transactional
    public void saveTask(Task task){
        saveTask(task.getHeader(), task.getDescription(), task.getParent() == null ? "" : task.getParent().getUuid(),
                task.getUuid(), task.getUser() == null ? "" : task.getUser().getUuid(), task.isDone());
    }

    @Transactional
    public void saveTask(String header, String description, Task parent, String uuid, User user, boolean done){
        saveTask(header, description, parent == null ? "" : parent.getUuid(), uuid, user == null ? "" : user.getUuid(), done);
    }

    @Transactional
    public void saveTask(String header, String description, String parentUuid, String uuid, String userUuid, boolean done){
        Task thisTask = taskRepo.findFirstByUuid(uuid);
        if(thisTask == null){
            thisTask = new Task();
        }
        thisTask.setHeader(header);
        thisTask.setDescription(description);
        thisTask.setDone(done);
        if(uuid.isEmpty()){
            thisTask.setUuid(UUID.randomUUID().toString());
        } else {
            thisTask.setUuid(uuid);
        }
        Task parent = taskRepo.findFirstByUuid(parentUuid);
        if(parent != null) {
            thisTask.setParent(parent);
        }
        User user = userRepo.findFirstByUuid(userUuid);
        if(user != null){
            thisTask.setUser(user);
        }
        taskRepo.save(thisTask);
    }
}
