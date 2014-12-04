package edu.cornell.cs4320.hw3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AttributeSetsol {

	private List<Attribute> attr;

	public AttributeSetsol() {
		attr = new ArrayList<Attribute>();
	}

	public AttributeSetsol(AttributeSetsol other) {
		attr = new ArrayList<Attribute>(other.attr);
	}

	public void addAttribute(Attribute a) {
		if (!attr.contains(a))
			attr.add(a);
	}

	public void addAll(AttributeSetsol other) {
		Iterator<Attribute> iter = other.iterator();
		while (iter.hasNext())
			addAttribute(iter.next());
	}

	public void addAll(AttributeSet other) {
		Iterator<Attribute> iter = other.iterator();
		while (iter.hasNext()) {
			addAttribute(iter.next());
		}
	}

	public AttributeSetsol intersect(AttributeSetsol other) {
		AttributeSetsol out = new AttributeSetsol();

		Iterator<Attribute> iter = other.iterator();
		while (iter.hasNext()) {
			Attribute a = iter.next();
			if (contains(a))
				out.addAttribute(a);
		}
		return out;
	}

	public boolean contains(Attribute a) {
		return attr.contains(a);
	}

	public boolean contains(AttributeSetsol as) {
		return attr.containsAll(as.attr);
	}

	public boolean contains(AttributeSet as) {
		Iterator<Attribute> iter = as.iterator();
		while (iter.hasNext()) {
			if (!contains(iter.next()))
				return false;
		}
		return true;
	}

	public int size() {
		return attr.size();
	}

	public boolean equals(Object other) {
		if (other == null || !(other instanceof AttributeSetsol))
			return false;
		List<Attribute> o = ((AttributeSetsol) other).attr;
		return attr.containsAll(o) && o.containsAll(attr);
	}

	public AttributeSetsol minus(AttributeSetsol other) {
		AttributeSetsol out = new AttributeSetsol(this);
		for (Attribute a : other.attr) {
			out.attr.remove(a);
		}
		return out;
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

	Iterator<AttributeSetsol> powerSet() {
		return new Iterator<AttributeSetsol>() {
			private long current = 0;
			private long size = (1L << (attr.size()));

			public boolean hasNext() {
				return current < size;
			}

			public AttributeSetsol next() {
				AttributeSetsol out = new AttributeSetsol();

				for (int i = 0; i < 64; i++) {
					long mask = 1L << i;
					if ((current & mask) != 0)
						out.attr.add(attr.get(i));
				}
				current++;

				return out;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
