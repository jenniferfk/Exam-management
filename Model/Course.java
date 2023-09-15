package Model;

import java.util.TreeSet;

public class Course extends MyObservable implements Comparable<Course> {
	  public String cName; public TreeSet<Exam>exams;
	public Course(String cn) {
		 cName=cn;
		 this.exams=new TreeSet<>();
	}	 
	 public void addexam(Exam e) {
		 exams.add(e);
	 }
	 public String toString() {
		 return cName;
	 }
	 public int compareTo(Course c) {
			return cName.compareTo(c.cName);
		}
	 public void setName(String name) {
	        this.cName = name;
	        setChanged(); 
	        notifyObservers(); 
	    }
}