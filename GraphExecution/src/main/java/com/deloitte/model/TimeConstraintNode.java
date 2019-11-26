/**
 * 
 */
package com.deloitte.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author vbejjanki
 *
 */

public class TimeConstraintNode implements Node {

	private long nodeId;

	private long taskId;
	
	private LocalDateTime startTime;
	
	private LocalDateTime constraintDateTime;

	private boolean isReadyToRun;

	private String nodeType;

	private long executionId;

	private List<Node> childrenNodesWithOrder;

	private boolean hasParent;

	private List<Node> parentNodes;

	private boolean hasChildren;

	private boolean hasTimeConstarint;

	private List<String> constraints;

	private String executionType;

	private boolean isManualJob;

	private String executorInfo;

	private String executionStatus;

	private CompletableFuture<Task> future;
	
	public TimeConstraintNode(long nodeId,long taskId,long executionId) {
		this.nodeId = nodeId;
		this.taskId = taskId;
		this.executionId = executionId;
	}
	
	public boolean hasParent() {
		return hasParent;
	}

	public void setHasParent(boolean hasParent) {
		this.hasParent = hasParent;
	}

	public boolean hasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public boolean isHasTimeConstarint() {
		return hasTimeConstarint;
	}

	public void setHasTimeConstarint(boolean hasTimeConstarint) {
		this.hasTimeConstarint = hasTimeConstarint;
	}

	public boolean isManual() {
		return isManualJob;
	}

	public void setReadyToRun(boolean isReadyToRun) {
		this.isReadyToRun = isReadyToRun;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public void setChildrenNodesWithOrder(List<Node> childrenNodesWithOrder) {
		this.childrenNodesWithOrder = childrenNodesWithOrder;
	}

	public void setParentNodes(List<Node> parentNodes) {
		this.parentNodes = parentNodes;
	}

	public void setConstraints(List<String> constraints) {
		this.constraints = constraints;
	}

	public void setExecutionType(String executionType) {
		this.executionType = executionType;
	}

	public void setExecutorInfo(String executorInfo) {
		this.executorInfo = executorInfo;
	}

	public void setExecutionStatus(String executionStatus) {
		this.executionStatus = executionStatus;
	}

	public void setFuture(CompletableFuture<Task> future) {
		this.future = future;
	}

	@Override
	public long getNodeId() {
		return nodeId;
	}

	@Override
	public long getTaskId() {
		return taskId;
	}

	@Override
	public long getExecutionId() {
		return executionId;
	}

	@Override
	public boolean isReadyToRun() {
		return isReadyToRun;
	}

	@Override
	public String getNodeType() {
		return nodeType;
	}

	@Override
	public List<Node> getChildrenNodesWithOrder() {
		return childrenNodesWithOrder;
	}

	@Override
	public List<Node> getParentNodes() {
		return parentNodes;
	}

	@Override
	public List<String> getConstraints() {
		return constraints;
	}

	@Override
	public String getExecutionType() {
		return executionType;
	}

	@Override
	public boolean isManualJob() {
		return isManualJob;
	}

	@Override
	public String getExecutorInfo() {
		return executorInfo;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getExecutionStatus() {
		return executionStatus;
	}

	public LocalDateTime getConstraintDateTime() {
		return constraintDateTime;
	}

	public void setConstraintDateTime(LocalDateTime constraintDateTime) {
		this.constraintDateTime = constraintDateTime;
	}

	public void setNodeId(long nodeId) {
		this.nodeId = nodeId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	@Override
	public boolean hasTimeConstraints() {
		return hasTimeConstarint;
	}

	@Override
	public CompletableFuture<Task> getFuture() {
		return future;
	}

	@Override
	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

}
