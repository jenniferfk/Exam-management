package Model;

import java.util.TreeSet;

public class AcademicYear extends MyObservable{
	 String year;TreeSet<SessionExam> sessionexam;
	 public AcademicYear(String y) {
		 year=y;
		 sessionexam=new TreeSet<SessionExam>();
	 }
	 public String toString() {
		 return year;
	 }
	 public void setYear(String year) {
	        this.year = year;
	        setChanged(); 
	        notifyObservers(); 
	    }
}
