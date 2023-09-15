package Model;

import java.util.TreeSet;

public class Exercise extends MyObservable implements Comparable<Exercise>{
	public String exNum;  public TreeSet<Question> questions; public String exn; public double totalgrade;
	 static int nextExId = 1;
    int exId;
	  public Exercise(String exnn) {
		  exNum=exnn;  this.questions=new TreeSet<>();
		  this.exId = nextExId;
	        nextExId++;
	  }
	  public void setTotalGrade(int grade) {
	        totalgrade = grade;
	    }
	 
	  public void addQuestion(Question question) {
			 questions.add(question);
			 setChanged();
			 notifyObservers();
}
	  
	  public int compareTo(Exercise e) {
		  return Integer.compare(this.exId, e.exId);
	  }
	  public String toString() {
		  return exNum;
	  }
	  
	  
	  }

