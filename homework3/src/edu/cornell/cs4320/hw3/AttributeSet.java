package edu.cornell.cs4320.hw3;

import java.util.*;

/**
 * An unordered set of Attributes. This could very easily be a Java collection,
 * but an important operation (namely examining the powerset) is not easily done
 * with the Java collection.
 **/
public class AttributeSet {

	// a list of the backing attributes
	private List<Attribute> attr;

	// construct an empty AttributeSet
	public AttributeSet() {
		attr = new ArrayList<Attribute>();
	}

	// copy constructor
	public AttributeSet(AttributeSet other) {
		attr = new ArrayList<Attribute>(other.attr);
	}

	public void addAttribute(Attribute a) {
		if (!attr.contains(a))
			attr.add(a);
	}

	public void addAttributeSet(AttributeSet a) { // add AttributeSet
		Iterator<Attribute> it = a.iterator();
		while (it.hasNext()) {
			Attribute x = it.next();
			if (!attr.contains(x)) {
				attr.add(x);
			}
		}
	}

	public void removeAttribute(Attribute a) { // remove a specific attribute
		if (attr.contains(a)) {
			attr.remove(a);
		}
	}

	public Attribute getAttribute(int x) { // get an attribute at specific position
		return attr.get(x);
	}

	public boolean contains(Attribute a) {
		return attr.contains(a);
	}

	public boolean contains(AttributeSet a) { // judge whether one AttributeSet contains another AttributeSet?
		Iterator<Attribute> it = a.iterator();
		while (it.hasNext()) {
			if (!this.contains(it.next()))
				return false;
		}
		return true;
	}

	public int size() {
		return attr.size();
	}

	public boolean equals(Object other) {
		if (other == null || !(other instanceof AttributeSet))
			return false;
		// TODO: you should probably implement this

		if (this.size() != ((AttributeSet) other).size()) // whether the size is same?
			return false;

		Iterator<Attribute> it = this.iterator();

		while (it.hasNext()) { // traverse every element
			if (!((AttributeSet) other).contains(it.next())) {
				return false;
			}
		}

		return true;
	}

	public Iterator<Attribute> iterator() {
		return attr.iterator();
	}

	public String toString() {
		String out = "";
		Iterator<Attribute> iter = iterator();
		while (iter.hasNext())
			out += iter.next() + "\t";

		return out;
	}
}
