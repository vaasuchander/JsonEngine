/**
 * 
 */
package com.deloitte.model;

/**
 * @author vbejjanki
 *
 */

public class PredecessorConstraint {

	private long id;

	private int lag;

	private PredecessorConstraintType predecessorConstraintType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getLag() {
		return lag;
	}

	public void setLag(int lag) {
		this.lag = lag;
	}

	public PredecessorConstraintType getPredecessorConstraintType() {
		return predecessorConstraintType;
	}

	public void setPredecessorConstraintType(PredecessorConstraintType predecessorConstraintType) {
		this.predecessorConstraintType = predecessorConstraintType;
	}

}
