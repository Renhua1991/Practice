package edu.cornell.cs4320.hw3;

import java.util.*;

public class BCNF {

	/**
	 * Implement your algorithm here
	 **/
	public static Set<AttributeSet> BCNF(AttributeSet attrs, Set<FunctionalDependency> fds) {

		Set<AttributeSet> x = pickX(attrs, fds);

		if (x.isEmpty()) { // recursion base
			Set<AttributeSet> setX = new HashSet<AttributeSet>();
			setX.add(attrs);
			return setX;
		} else {
			List<AttributeSet> listTransfer = new ArrayList<AttributeSet>();
			listTransfer.addAll(x);
			AttributeSet randomX = listTransfer.get((int) Math.round(Math
					.random() * (x.size() - 1)));
			//System.out.println(randomX);

			AttributeSet closure = closure(randomX, fds); // find closure x+
			//System.out.println(closure);

			Set<AttributeSet> result = new HashSet<AttributeSet>();

			AttributeSet a = new AttributeSet(closure); // one subtable of attrs: x+

			AttributeSet b = new AttributeSet(attrs);
			Iterator<Attribute> it1 = closure.iterator();
			Iterator<Attribute> it2 = randomX.iterator();
			while (it1.hasNext()) { // another subtable of attrs: x U (x+)c
				b.removeAttribute(it1.next());
			}
			while (it2.hasNext()) {
				b.addAttribute(it2.next());
			}

			// change fds;
			Set<FunctionalDependency> fds1 = new HashSet<FunctionalDependency>();
			for (FunctionalDependency everyFD1 : fds) {
				if (a.contains(everyFD1.dependent())
						&& a.contains(everyFD1.independent())) {
					fds1.add(everyFD1);
				}
			}

			Set<FunctionalDependency> fds2 = new HashSet<FunctionalDependency>();
			for (FunctionalDependency everyFD2 : fds) {
				if (b.contains(everyFD2.dependent())
						&& b.contains(everyFD2.independent())) {
					fds2.add(everyFD2);
				}
			}

			result.addAll(BCNF(a, fds1));
			result.addAll(BCNF(b, fds2));

			return result;
		}

	}

	/**
	 * Recommended helper method
	 **/
	public static AttributeSet closure(AttributeSet attrs, Set<FunctionalDependency> fds) {
		AttributeSet olddep = new AttributeSet();
		AttributeSet newdep = new AttributeSet(attrs);

		while (!newdep.equals(olddep)) { // while new != old
			olddep = new AttributeSet(newdep);

			for (FunctionalDependency fd : fds) { // for every FD in fds
				if (newdep.contains(fd.independent())) { // if w belongs to new,  then new = new union z
					newdep.addAttributeSet(fd.dependent());
				}
			}
		}
		return newdep;
	}

	/**
	 * helper method: pick all qualified subsets
	 */
	public static Set<AttributeSet> pickX(AttributeSet attrs, Set<FunctionalDependency> fds) {

		Set<Attribute> set = new HashSet<Attribute>();
		Iterator<Attribute> it = attrs.iterator(); // transfer attrs into Set<Attribute>
		while (it.hasNext()) {
			set.add(it.next());
		}
		Set<Set<Attribute>> subSets = powerSet(set); // get all subSets of set(attrs)
		Set<AttributeSet> subSets1 = new HashSet<AttributeSet>();

		Iterator<Set<Attribute>> it1 = subSets.iterator(); // traverse all subsets of attrs

		while (it1.hasNext()) {
			Set<Attribute> iter = it1.next();

			AttributeSet a = new AttributeSet(); // transfer set into AttributeSet --- a
			for (Attribute i : iter) {
				a.addAttribute(i);
			}

			AttributeSet closure = closure(a, fds); // get the closure of every subset

			if (!iter.isEmpty() && !closure.equals(a) && !closure.equals(attrs)) {
				subSets1.add(a);
			}
		}

		if (subSets.isEmpty()) {
			return null;
		} else
			return subSets1; // return all subSets(Set<Set<Attribute>>)
		
	}
	
	/**
	 * helper method: pick powerset
	 */
	public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
		Set<Set<T>> sets = new HashSet<Set<T>>();
		if (originalSet.isEmpty()) {
			sets.add(new HashSet<T>());
			return sets;
		}
		List<T> list = new ArrayList<T>(originalSet);
		T head = list.get(0);
		Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
		for (Set<T> set : powerSet(rest)) {
			Set<T> newSet = new HashSet<T>();
			newSet.add(head);
			newSet.addAll(set);
			sets.add(newSet);
			sets.add(set);
		}
		return sets;
	}

}
