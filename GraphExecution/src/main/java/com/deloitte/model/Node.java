/**
 * 
 */
package com.deloitte.model;

import com.deloitte.model.json.serialize.NodeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author vbejjanki
 *
 */
@JsonSerialize(using = NodeSerializer.class)
public class Node<T> {

	protected long nodeId;

	protected T value;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (nodeId ^ (nodeId >>> 32));
		return result;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (nodeId != other.nodeId)
			return false;
		return true;
	}

}
