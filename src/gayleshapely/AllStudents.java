package gayleshapely;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AllStudents implements Iterable<Student>{
	Set<Student> studentSet = new HashSet<Student>();	
	ArrayList<Student> proposalOrder;
	
	public void addStudent(Student aStudent) {
		studentSet.add(aStudent);
	}
	
	public boolean contains(Student aStudent) {
		return studentSet.contains(aStudent);
	}
	
	public int size() {
		return studentSet.size();
	}
		
	/**
	 * Initializes the proposal order if null, then randomizes it
	 */
	public void randomizeProposalOrder() {
		if (proposalOrder == null) {
			proposalOrder = new ArrayList<Student>();
			proposalOrder.addAll(studentSet);
		}
		proposalOrder = Utility.randomise(proposalOrder);
	}

	@Override
	public Iterator<Student> iterator() {
		return proposalOrder.iterator();
	}
	
}
