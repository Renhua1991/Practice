package edu.cornell.cs4320.hw3;

import java.util.*;

/**
 * Represents a functional dependency, namely the dependent attributes are
 * determined by the independent set.
 * 
 * Is mostly just an Immutable tuple with named fields.
 **/
public class FunctionalDependency {

	private AttributeSet ind;
	private AttributeSet dep;

	// this FD represents ind -> dep

	public FunctionalDependency(AttributeSet ind, AttributeSet dep) {
		this.ind = new AttributeSet(ind);
		this.dep = new AttributeSet(dep);
	}

	public AttributeSet independent() {
		return new AttributeSet(ind);
	}

	public AttributeSet dependent() {
		return new AttributeSet(dep);
	}
}
