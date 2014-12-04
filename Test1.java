package edu.cornell.cs4320.hw3;

import java.util.*;

import junit.framework.TestCase;

public class Test1 extends TestCase {

	/**
	 * Performs a basic test on a simple table.
	 * gives input (a,b,c), a->c
	 * and expects output (a,b),(a,c) or any reordering
	 **/
	public void testSimpleBCNF1() {
		//construct table
		AttributeSet attrs = new AttributeSet();
		attrs.addAttribute(new Attribute("a"));
		attrs.addAttribute(new Attribute("b"));
		attrs.addAttribute(new Attribute("c"));

		//create functional dependencies
		Set<FunctionalDependency> fds = new HashSet<FunctionalDependency>();
		AttributeSet ind = new AttributeSet();
		AttributeSet dep = new AttributeSet();
		ind.addAttribute(new Attribute("a"));
		dep.addAttribute(new Attribute("c"));
		FunctionalDependency fd = new FunctionalDependency(ind, dep);
		fds.add(fd);

		Set<AttributeSet> bcnf1 = BCNF.pickX(attrs, fds);
		System.out.println(bcnf1);
		
		//run client code
		Set<AttributeSet> bcnf = BCNF.BCNF(attrs, fds);

		//verify output
		assertEquals("Incorrect number of tables", 2, bcnf.size());
        
        System.out.println(bcnf);
		
        for(AttributeSet as : bcnf) {
			assertEquals("Incorrect number of attributes", 2, as.size());
			assertTrue("Incorrect table", as.contains(new Attribute("a")));
		}
	
	}
	
	public void testSimpleBCNF2() {
		//construct table
		AttributeSet attrs = new AttributeSet();
		attrs.addAttribute(new Attribute("a"));
		attrs.addAttribute(new Attribute("b"));
		attrs.addAttribute(new Attribute("c"));
		attrs.addAttribute(new Attribute("d"));
		attrs.addAttribute(new Attribute("e"));

		//create functional dependencies
		Set<FunctionalDependency> fds = new HashSet<FunctionalDependency>();
		AttributeSet ind = new AttributeSet();
		AttributeSet dep = new AttributeSet();
		ind.addAttribute(new Attribute("a"));
		ind.addAttribute(new Attribute("b"));
		dep.addAttribute(new Attribute("e"));
		FunctionalDependency fd = new FunctionalDependency(ind, dep);
		fds.add(fd);
		
		//run client code
		Set<AttributeSet> pick = BCNF.pickX(attrs, fds);
		System.out.println(pick);
		Set<AttributeSet> bcnf = BCNF.BCNF(attrs, fds);

		//verify output
        System.out.println(bcnf.size());
        System.out.println(bcnf);
	
	}
		
	public void testSimpleBCNF3() {                         
		//construct table
		AttributeSet attrs = new AttributeSet();
		attrs.addAttribute(new Attribute("a"));
		attrs.addAttribute(new Attribute("b"));
		attrs.addAttribute(new Attribute("c"));
		attrs.addAttribute(new Attribute("d"));
		attrs.addAttribute(new Attribute("e"));
		attrs.addAttribute(new Attribute("i"));

		//create functional dependencies
		Set<FunctionalDependency> fds = new HashSet<FunctionalDependency>();
		AttributeSet ind1 = new AttributeSet();
		AttributeSet dep1 = new AttributeSet();
		ind1.addAttribute(new Attribute("a"));
		dep1.addAttribute(new Attribute("d"));
		FunctionalDependency fd1 = new FunctionalDependency(ind1, dep1);
			
		AttributeSet ind2 = new AttributeSet();
		AttributeSet dep2 = new AttributeSet();
		ind2.addAttribute(new Attribute("a"));
		ind2.addAttribute(new Attribute("b"));
		dep2.addAttribute(new Attribute("e"));
		FunctionalDependency fd2 = new FunctionalDependency(ind2, dep2);

		AttributeSet ind3 = new AttributeSet();
		AttributeSet dep3 = new AttributeSet();
		ind3.addAttribute(new Attribute("b"));
		ind3.addAttribute(new Attribute("i"));
		dep3.addAttribute(new Attribute("e"));
		FunctionalDependency fd3 = new FunctionalDependency(ind3, dep3);
		
		AttributeSet ind4 = new AttributeSet();
		AttributeSet dep4 = new AttributeSet();
		ind4.addAttribute(new Attribute("c"));
		dep4.addAttribute(new Attribute("d"));
		dep4.addAttribute(new Attribute("i"));
		FunctionalDependency fd4 = new FunctionalDependency(ind4, dep4);
		
		AttributeSet ind5 = new AttributeSet();
		AttributeSet dep5 = new AttributeSet();
		ind5.addAttribute(new Attribute("e"));
		dep5.addAttribute(new Attribute("c"));
		FunctionalDependency fd5 = new FunctionalDependency(ind5, dep5);
		
		fds.add(fd1);
		fds.add(fd2);
		fds.add(fd3);
		fds.add(fd4);
		fds.add(fd5);
		
		Set<AttributeSet> pick = BCNF.pickX(attrs, fds);
		System.out.println(pick);

		Set<AttributeSet> bcnf = BCNF.BCNF(attrs, fds);
        System.out.println(bcnf.size());
        System.out.println(bcnf);
		
	}
	 
	public void testSimpleBCNF4() {                             //test closure       
		AttributeSet attrs = new AttributeSet();
		attrs.addAttribute(new Attribute("a"));
		attrs.addAttribute(new Attribute("d"));
		
		//create functional dependencies
		Set<FunctionalDependency> fds = new HashSet<FunctionalDependency>();
		AttributeSet ind1 = new AttributeSet();
		AttributeSet dep1 = new AttributeSet();
		ind1.addAttribute(new Attribute("a"));
		dep1.addAttribute(new Attribute("d"));
		FunctionalDependency fd1 = new FunctionalDependency(ind1, dep1);
			
		AttributeSet ind2 = new AttributeSet();
		AttributeSet dep2 = new AttributeSet();
		ind2.addAttribute(new Attribute("a"));
		ind2.addAttribute(new Attribute("b"));
		dep2.addAttribute(new Attribute("e"));
		FunctionalDependency fd2 = new FunctionalDependency(ind2, dep2);

		AttributeSet ind3 = new AttributeSet();
		AttributeSet dep3 = new AttributeSet();
		ind3.addAttribute(new Attribute("b"));
		ind3.addAttribute(new Attribute("i"));
		dep3.addAttribute(new Attribute("e"));
		FunctionalDependency fd3 = new FunctionalDependency(ind3, dep3);
		
		AttributeSet ind4 = new AttributeSet();
		AttributeSet dep4 = new AttributeSet();
		ind4.addAttribute(new Attribute("c"));
		dep4.addAttribute(new Attribute("d"));
		dep4.addAttribute(new Attribute("i"));
		FunctionalDependency fd4 = new FunctionalDependency(ind4, dep4);
		
		AttributeSet ind5 = new AttributeSet();
		AttributeSet dep5 = new AttributeSet();
		ind5.addAttribute(new Attribute("e"));
		dep5.addAttribute(new Attribute("c"));
		FunctionalDependency fd5 = new FunctionalDependency(ind5, dep5);
		
		fds.add(fd1);
		fds.add(fd2);
		fds.add(fd3);
		fds.add(fd4);
		fds.add(fd5);
		
		AttributeSet closure = BCNF.closure(attrs, fds);
		System.out.println(closure);
		
	}
	
	public void testSimpleBCNF5() {                             //test equals     
		AttributeSet attrs1 = new AttributeSet();
		attrs1.addAttribute(new Attribute("a"));
		attrs1.addAttribute(new Attribute("d"));
		attrs1.addAttribute(new Attribute("c"));
		attrs1.addAttribute(new Attribute("d"));
		
		AttributeSet attrs2 = new AttributeSet();
		attrs2.addAttribute(new Attribute("a"));
		attrs2.addAttribute(new Attribute("d"));
		attrs2.addAttribute(new Attribute("e"));
		
		AttributeSet attrs3 = new AttributeSet();
		attrs3.addAttribute(new Attribute("b"));
		attrs3.addAttribute(new Attribute("d"));
		attrs3.addAttribute(new Attribute("c"));
		attrs3.addAttribute(new Attribute("a"));
		
		AttributeSet attrs4 = new AttributeSet();
		attrs4.addAttribute(new Attribute("e"));
		attrs4.addAttribute(new Attribute("f"));
		attrs4.addAttribute(new Attribute("w"));
		attrs4.addAttribute(new Attribute("a"));
		
		System.out.println(attrs1.equals(attrs2));
		System.out.println(attrs1.equals(attrs3));
		System.out.println(attrs1.equals(attrs4));
		
	}
	
	

	
	
	
}
