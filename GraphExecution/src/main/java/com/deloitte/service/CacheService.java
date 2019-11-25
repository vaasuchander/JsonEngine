/**
 * 
 */
package com.deloitte.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

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

	private Map<Long, HashMap<Long, Task>> playBookTasksMap = new HashMap<>();
	
	public void setPlayBookTasks(PlayBook playBook) {
		if (Objects.nonNull(playBook)) {
			playBookTasksMap.computeIfAbsent(playBook.getId(), tasksMap -> new HashMap<>())
					.putAll(playBook.getTasks().stream().filter(Objects::nonNull)
							.collect(Collectors.toMap(Task::getId, Function.identity(), (t1, t2) -> t1))

					);
		}
	}

	@Cacheable
	public Map<Long, Task> getMapTaskList(long playBookId) {
		return playBookTasksMap.getOrDefault(playBookId,new HashMap<>());
	}

}
