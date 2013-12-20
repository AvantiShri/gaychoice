package gayleshapely;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Uses the factory pattern; the constructor is private.
 * @author avantis
 */
public class Student {
	
	//class related stuff here...
	static HashMap<String, Student> studentFactoryLookup = new HashMap<String,Student>();
	/**
	 * Creates a student with name student name, throws exception if already exists
	 * @param studentName
	 */
	static void createStudent(String studentName) {
		if (studentFactoryLookup.containsKey(studentName)) {
			throw new RuntimeException("A student with name "+studentName+" already exists");
		} else {
			studentFactoryLookup.put(studentName, new Student(studentName));
		}
	}
	
	/**
	 * Pulls precreated student with name studentName, throws exception if does not already exist
	 * @param studentName
	 * @return
	 */
	static Student getStudent(String studentName) {
		if (studentFactoryLookup.containsKey(studentName)) {
			return studentFactoryLookup.get(studentName);
		} else {
			throw new RuntimeException("Student with name "+studentName+" was not created");
		}
	}
	
	//instance-related stuff starts here...	
	String studentName;	
	Preferences<School> schoolPreferences;
	ArrayList<School> currentRankSchoolSet;
	int currentRankSchoolSetIndex;
	School engagedTo;
	School lastEngagedTo;
	private Student(String studentName) {
		this.studentName = studentName;
	}
	
	public void setSchoolPreferences(Preferences<School> schoolPreferences) {
		this.schoolPreferences = schoolPreferences;
	}
	
	/**
	 * If the student is not engaged to a school, and there's schools left on the student's
	 * preferences list, initiates proposal sequence
	 */
	public void proposeIfNecessary() {
		if (engagedTo == null) {
			School nextSchoolToProposeTo = getNextSchoolToProposeTo();
			if (nextSchoolToProposeTo != null) {
				nextSchoolToProposeTo.processApplication(this);
			}
		}
	}
	
	//returns null if we are at the bottom of the preferences list
	School getNextSchoolToProposeTo() {
		if (currentRankSchoolSetIndex < (currentRankSchoolSet.size()-1)) {
			++currentRankSchoolSetIndex;
		} else {
			currentRankSchoolSet = schoolPreferences.nextPreferred(currentRankSchoolSet.get(currentRankSchoolSetIndex));
			if (currentRankSchoolSet == null) {
				return null;
			}
			currentRankSchoolSetIndex = 0;
		}
		return currentRankSchoolSet.get(currentRankSchoolSetIndex);
	}
	
	public void jilt() {
		this.engagedTo = null;
	}
	
	public void getEngagedTo(School school) {
		this.engagedTo = school;
		this.lastEngagedTo = school;
	}
	
	@Override
	public String toString() {
		return studentName;
	}
	
}