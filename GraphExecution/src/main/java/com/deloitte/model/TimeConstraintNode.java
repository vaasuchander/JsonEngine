/**
 * 
 */
package com.deloitte.model;

/**
 * @author vbejjanki
 *
 */
public class TimeConstraintNode extends Node<String> {

	public void setNodeId(long value) {
		nodeId = value;
	}

	public long getNodeId() {
		return nodeId;
	}

	public String getConstraintDateandTime() {
		return value;
	}

	public void setConstraintDateandTime(String dateTime) {
		value = dateTime;
	}

}
