package com.example.task_service.service;

import com.example.task_service.dtos.TaskCreateDto;
import com.example.task_service.dtos.TaskUpdateDto;
import com.example.task_service.error_handler.exceptions.TaskNotFoundException;
import com.example.task_service.models.Task;
import com.example.task_service.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(TaskCreateDto taskCreateDto) {
        Task task = Task.builder()
                .title(taskCreateDto.getTitle())
                .description(taskCreateDto.getDescription())
                .status(taskCreateDto.getStatus())
                .build();
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, TaskUpdateDto taskUpdateDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        // Check if fields in taskUpdateDto are non-null and update accordingly
        if (taskUpdateDto.getTitle() != null) {
            existingTask.setTitle(taskUpdateDto.getTitle());
        }

        if (taskUpdateDto.getDescription() != null) {
            existingTask.setDescription(taskUpdateDto.getDescription());
        }

        if (taskUpdateDto.getStatus() != null) {
            existingTask.setStatus(taskUpdateDto.getStatus());
        }

        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
