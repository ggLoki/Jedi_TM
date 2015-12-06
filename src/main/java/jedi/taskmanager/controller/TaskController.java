package jedi.taskmanager.controller;

import jedi.taskmanager.domain.Task;
import jedi.taskmanager.repository.TaskRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Main application controller. Using for all actions with tasks.
 * Created by loki on 06.12.2015.
 */
@RestController
@RequestMapping(value = "/api/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    @Inject
    TaskRepository taskRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Task> getAll() {
        return taskRepository.findAllByCompleted(false);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Task saveTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public void removeTask(@RequestBody int id) {
        taskRepository.delete(id);
    }
}
