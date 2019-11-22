/**
 * 
 */
package com.deloitte.model.json;

import com.deloitte.model.Task;

/**
 * @author vbejjanki
 *
 */
public class ExecutionNodeJson {

	private long id;

	private Task task;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
