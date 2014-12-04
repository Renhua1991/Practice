package edu.cornell.cs4320.hw3;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BCNFsol {
	/**
	 * Implement your algorithm here
	 **/
	public static Set<AttributeSetsol> BCNF(AttributeSetsol attrs,
			Set<FunctionalDependency> fds) {

		Iterator<AttributeSetsol> power = attrs.powerSet();

		while (power.hasNext()) {
			AttributeSetsol a = power.next();

			AttributeSetsol close = closure(new AttributeSetsol(a), fds)
					.intersect(attrs);

			if (!close.equals(attrs) && !close.equals(a) && close.size() != 0) {
				// seperate and recurse
				Set<AttributeSetsol> left = BCNF(close, fds);

				AttributeSetsol temp = attrs.minus(close);
				temp.addAll(a);
				Set<AttributeSetsol> right = BCNF(temp, fds);

				left.addAll(right);
				return left;
			}
		}

		HashSet<AttributeSetsol> out = new HashSet<AttributeSetsol>();
		out.add(attrs);
		return out;
	}

	/**
	 * Recommended helper method
	 **/
	public static AttributeSetsol closure(AttributeSetsol attrs,
			Set<FunctionalDependency> fds) {
		attrs = new AttributeSetsol(attrs);
		if (fds.size() == 0)
			return attrs;

		boolean changed = false;

		Iterator<FunctionalDependency> iter = fds.iterator();
		while (true) {
			while (!iter.hasNext()) {
				if (changed) {
					changed = false;
					iter = fds.iterator();
				} else {
					return attrs;
				}
			}

			FunctionalDependency fd = iter.next();

			if (attrs.contains(fd.independent())) {
				int oldSize = attrs.size();

				attrs.addAll(fd.dependent());

				if (oldSize != attrs.size())
					changed = true;
			}
		}
	}

	public static boolean isCorrect(AttributeSet attrs,
			Set<FunctionalDependency> fds) {
		Set<AttributeSet> output = BCNF.BCNF(attrs, fds);

		// check that all tables are completely decomposed
		Set<AttributeSetsol> result = new HashSet<AttributeSetsol>();
		for (AttributeSet as : output) {
			// copy all output to solution types
			Iterator<Attribute> iter = as.iterator();
			AttributeSetsol tas = new AttributeSetsol();
			while (iter.hasNext()) {
				tas.addAttribute(iter.next());
			}
			result.add(tas);

			if (!BCNF(tas, fds).contains(tas)) {
				System.out.println(as + " Further decomposable");
				return false;
			}
		}

		// copy original table to keep all types solution types
		AttributeSetsol table = new AttributeSetsol();
		{
			Iterator<Attribute> iter = attrs.iterator();
			while (iter.hasNext())
				table.addAttribute(iter.next());
		}

		// only check their tables are covering
		boolean[] seen = new boolean[table.size()];
		for (AttributeSetsol as : result) {
			Iterator<Attribute> iter = as.iterator();
			while (iter.hasNext()) {
				Attribute cur = iter.next();
				boolean goodattr = false;

				Iterator<Attribute> tableiter = table.iterator();
				int index = 0;
				while (tableiter.hasNext()) {
					if (cur.equals(tableiter.next())) {
						seen[index] = true;
						goodattr = true;
					}
					index++;
				}

				if (!goodattr)
					return false;
			}
		}

		for (int i = 0; i < seen.length; i++) {
			if (!seen[i])
				return false;
		}

		return true;

		// check that we haven't lost information
		/*
		 * List<AttributeSetsol> temp = new ArrayList<AttributeSetsol>(result);
		 * boolean changed; while(true) { changed = false;
		 * 
		 * for(int i = 0; i < temp.size(); i++) { AttributeSetsol as = new
		 * AttributeSetsol(temp.get(i));
		 * 
		 * Iterator<AttributeSetsol> iter = as.powerSet(); while(iter.hasNext())
		 * { //see what all subsets determine AttributeSetsol ind = iter.next();
		 * AttributeSetsol dep = closure(ind,fds);
		 * 
		 * Iterator<Attribute> depiter = dep.iterator();
		 * while(depiter.hasNext()) { Attribute d = depiter.next();
		 * if(as.contains(d)) { continue; } //if it is something interesting,
		 * see if some other table has // subset, determined in it, so that the
		 * value is uniquely determined for(int j = 0; j < temp.size(); j++) {
		 * if(temp.get(j).contains(ind) && temp.get(j).contains(d)) {
		 * as.addAttribute(d); j = temp.size(); } }
		 * 
		 * } }
		 * 
		 * if(!as.equals(temp.get(i))) { changed = true; } //if the full table
		 * determined, we are done if(as.equals(table)) { return true; }
		 * 
		 * temp.set(i, as); }
		 * 
		 * //loop until stability if(!changed) { return false; } }
		 */
	}
}
