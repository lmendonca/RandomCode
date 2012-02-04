package org.roo.inaction.taskmanager.service;

import java.util.List;
import org.roo.inaction.taskmanager.model.Task;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { org.roo.inaction.taskmanager.model.Task.class })
public interface TaskService {

	public abstract long countAllTasks();


	public abstract void deleteTask(Task task);


	public abstract Task findTask(Long id);


	public abstract List<Task> findAllTasks();


	public abstract List<Task> findTaskEntries(int firstResult, int maxResults);


	public abstract void saveTask(Task task);


	public abstract Task updateTask(Task task);

}
