/**
 * 
 */
package com.deloitte.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author vbejjanki
 *
 */
public interface Node {

	public long getNodeId();
	
	public long getTaskId();
	
	public long getExecutionId();
	
	public LocalDateTime getStartTime();
	
	@JsonIgnore
	public boolean isReadyToRun();
	
	@JsonIgnore
	public String getNodeType();
	
	@JsonIgnore
	public List<Node> getChildrenNodesWithOrder();
	
	@JsonIgnore
	public boolean hasParent();
	
	@JsonIgnore
	public List<Node> getParentNodes();
	
	@JsonIgnore
	public boolean hasChildren();
	
	@JsonIgnore
	public boolean hasTimeConstraints();
	
	@JsonIgnore
	public List<String> getConstraints();
	
	@JsonIgnore
	public String getExecutionType();
	
	@JsonIgnore
	public boolean isManualJob();
	
	@JsonIgnore
	public String getExecutorInfo();
	
	@JsonIgnore
	public void run();
	
	@JsonIgnore
	public String getExecutionStatus();
	
	@JsonIgnore
	public CompletableFuture<Task> getFuture();
	
}
