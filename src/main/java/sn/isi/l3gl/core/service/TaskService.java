package sn.isi.l3gl.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.isi.l3gl.core.entities.Task;
import sn.isi.l3gl.core.entities.TaskStatus;
import sn.isi.l3gl.core.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    public Task updateStatus(Long id, TaskStatus status) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    public long countCompletedTasks() {
        return taskRepository.countByStatus(TaskStatus.DONE);
    }
}
