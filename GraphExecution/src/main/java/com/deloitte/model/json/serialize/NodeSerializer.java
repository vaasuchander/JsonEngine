/**
 * 
 */
package com.deloitte.model.json.serialize;

import java.io.IOException;

import com.deloitte.model.NoActionNode;
import com.deloitte.model.Node;
import com.deloitte.model.Task;
import com.deloitte.model.TaskExecutionNode;
import com.deloitte.model.TimeConstraintNode;
import com.deloitte.model.json.ExecutionNodeJson;
import com.deloitte.model.json.TimeConstraintNodeJson;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author vbejjanki
 *
 */
@SuppressWarnings({ "rawtypes", "serial" })
public class NodeSerializer extends StdSerializer<Node> {

	public NodeSerializer() {
		this(null);
	}

	protected NodeSerializer(Class<Node> node) {
		super(node);
	}

	@Override
	public void serialize(Node value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if (value instanceof TaskExecutionNode) {
			Task task = ((TaskExecutionNode) value).getTask();
			ExecutionNodeJson eJson = new ExecutionNodeJson();
			eJson.setId(task.getId());
			eJson.setTask(task);
			gen.writeObject(eJson);
		} else if (value instanceof TimeConstraintNode) {
			long nodeId = ((TimeConstraintNode) value).getNodeId();
			String dateTime = ((TimeConstraintNode) value).getConstraintDateandTime();
			TimeConstraintNodeJson tJson = new TimeConstraintNodeJson();
			tJson.setNodeId(nodeId);
			tJson.setConstraintDateandTime(dateTime);
			gen.writeObject(tJson);
		} else if (value instanceof NoActionNode) {
			long nodeId = ((NoActionNode) value).getNodeId();
			gen.writeStartObject();
			gen.writeNumberField("nodeId", nodeId);
			gen.writeEndObject();
		}

	}

}
