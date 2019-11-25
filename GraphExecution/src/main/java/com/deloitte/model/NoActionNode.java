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
public class NoActionNode implements Node {

	private long nodeId;

	private long taskId;

	private String taskName;

	private long executionId;

	private LocalDateTime startTime;

	private boolean isReadyToRun;

	private String nodeType;

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

	public NoActionNode(long nodeId,long executionId) {
		this.nodeId = nodeId;
		this.executionId = executionId;
	}

	public long getNodeId() {
		return nodeId;
	}

	public void setNodeId(long nodeId) {
		this.nodeId = nodeId;
	}

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

	public boolean isReadyToRun() {
		return isReadyToRun;
	}

	public void setReadyToRun(boolean isReadyToRun) {
		this.isReadyToRun = isReadyToRun;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public List<Node> getChildrenNodesWithOrder() {
		return childrenNodesWithOrder;
	}

	public void setChildrenNodesWithOrder(List<Node> childrenNodesWithOrder) {
		this.childrenNodesWithOrder = childrenNodesWithOrder;
	}

	public void setHasParent(boolean hasParent) {
		this.hasParent = hasParent;
	}

	public List<Node> getParentNodes() {
		return parentNodes;
	}

	public void setParentNodes(List<Node> parentNodes) {
		this.parentNodes = parentNodes;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public void setHasTimeConstarint(boolean hasTimeConstarint) {
		this.hasTimeConstarint = hasTimeConstarint;
	}

	public List<String> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<String> constraints) {
		this.constraints = constraints;
	}

	public String getExecutionType() {
		return executionType;
	}

	public void setExecutionType(String executionType) {
		this.executionType = executionType;
	}

	public String getExecutorInfo() {
		return executorInfo;
	}

	public void setExecutorInfo(String executorInfo) {
		this.executorInfo = executorInfo;
	}

	public String getExecutionStatus() {
		return executionStatus;
	}

	public void setExecutionStatus(String executionStatus) {
		this.executionStatus = executionStatus;
	}

	@Override
	public boolean hasParent() {
		return hasParent;
	}

	@Override
	public boolean hasChildren() {
		return hasChildren;
	}

	@Override
	public boolean hasTimeConstraints() {
		return hasTimeConstarint;
	}

	@Override
	public boolean isManualJob() {
		return isManualJob;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public CompletableFuture<Task> getFuture() {
		return future;
	}

}
