/**
 * 
 */
package com.deloitte.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.deloitte.model.PlayBook;
import com.deloitte.model.Task;

/**
 * @author vbejjanki
 *
 */

@Service
public class CacheService {

	@PostConstruct
	public void setTasksList() {

		Task task1 = new Task();
		task1.setId(1L);
		Task task2 = new Task();
		task2.setId(2L);

		List<Task> tasks1 = new ArrayList<>();
		tasks1.add(task1);
		tasks1.add(task2);

		PlayBook playBook1 = new PlayBook();
		playBook1.setId(112);
		playBook1.setTasks(tasks1);
		
		setPlayBook(playBook1);
		
		Task task3 = new Task();
		task3.setId(5L);
		Task task4 = new Task();
		task4.setId(6L);
		task4.setParent(true);
		
		List<Task> tasks2 = new ArrayList<>();
		tasks2.add(task3);
		tasks2.add(task4);

		PlayBook playBook2 = new PlayBook();
		playBook2.setId(113L);
		playBook2.setTasks(tasks2);

		setPlayBook(playBook2);

	}

	private Map<Long, HashMap<Long, Task>> playBookTasksMap = new HashMap<>();

	private Map<Long, PlayBook> tasksMap = new HashMap<>();

	public void setPlayBookTasks(PlayBook playBook) {
		if (Objects.nonNull(playBook)) {
			playBookTasksMap.computeIfAbsent(playBook.getId(), tasksMap -> new HashMap<>())
					.putAll(playBook.getTasks().stream().filter(Objects::nonNull)
							.collect(Collectors.toMap(Task::getId, Function.identity(), (t1, t2) -> t1))

					);
		}
	}

	public void setPlayBook(PlayBook playBook) {
		tasksMap.computeIfAbsent(playBook.getId(), p -> playBook);
	}

	@Cacheable
	public Map<Long, Task> getMapTaskList(long playBookId) {
		return playBookTasksMap.getOrDefault(playBookId, new HashMap<>());
	}

	@Cacheable
	public PlayBook getTaskList(long playBookId) {
		return tasksMap.getOrDefault(playBookId, null);
	}

}
