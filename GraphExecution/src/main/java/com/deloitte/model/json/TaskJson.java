/**
 * 
 */
package com.deloitte.model.json;

import java.time.LocalDateTime;

/**
 * @author vbejjanki
 *
 */
public class TaskJson {

	private long taskId;

	private String taskName;

	private long executionId;

	private LocalDateTime startTime;

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public long getExecutionId() {
		return executionId;
	}

	public void setExecutionId(long executionId) {
		this.executionId = executionId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

}