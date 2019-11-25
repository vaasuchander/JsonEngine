package com.deloitte.model.json;

import java.util.HashMap;
import java.util.Map;

import com.deloitte.model.Task;

public class GraphJson {

	private GraphModel executionGraph;

	private Map<Long, Task> metadata = new HashMap<>();

	public GraphModel getExecutionGraph() {
		return executionGraph;
	}

	public void setExecutionGraph(GraphModel executionGraph) {
		this.executionGraph = executionGraph;
	}

	public Map<Long, Task> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<Long, Task> metadata) {
		this.metadata = metadata;
	}

}
