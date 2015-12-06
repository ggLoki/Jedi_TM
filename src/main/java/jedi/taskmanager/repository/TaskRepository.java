package jedi.taskmanager.repository;

import jedi.taskmanager.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository,using to all DB actions for TaskRepository
 * Created by loki on 06.12.2015.
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findAll();
    List<Task> findAllByCompleted(boolean completed);
}
