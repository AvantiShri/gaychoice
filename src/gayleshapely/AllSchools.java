package gayleshapely;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AllSchools implements Iterable<School>{
	Set<School> schoolSet = new HashSet<School>();	
	
	public void addSchool(School aSchool) {
		schoolSet.add(aSchool);
	}
	@Override
	public Iterator<School> iterator() {
		return schoolSet.iterator();
	}
	
}
