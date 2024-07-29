package com.nexus.nexus.queue;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class TaskManager {
    private static final Logger logger = Logger.getLogger(TaskManager.class.getName());
    private PriorityQueue<Task> taskQueue;
    private Map<String, Task> taskMap;

    public TaskManager() {
        taskQueue = new PriorityQueue<>(Comparator.comparing(Task::getPriority));
        taskMap = new HashMap<>();
    }

    public void addTask(Task task) {
        logger.info("Adicionando tarefa: " + task);
        taskQueue.offer(task);
        taskMap.put(task.getName(), task);
    }

    public Task removeTask() {
        Task task = taskQueue.poll();
        if (task != null) {
            logger.info("Removendo tarefa: " + task);
            taskMap.remove(task.getName());
        }
        return task;
    }

    public Task removeTaskByName(String name) {
        Task task = taskMap.get(name);
        if (task != null) {
            logger.info("Removendo tarefa pelo nome: " + name);
            taskQueue.remove(task);
            taskMap.remove(name);
        }
        return task;
    }

    public List<Task> listTasks() {
        logger.info("Listando tarefas");
        return new ArrayList<>(taskQueue);
    }

    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }
}