package Model;

import java.util.TreeSet;

public class Exam extends MyObservable implements Comparable<Exam>{
	  static int nextExamId = 1;
	     public int examId;
	       public String eModality;  double examcoefnote;  TreeSet<Exercise> exercises; public SessionExam session;
	       Student s; public double finalnote;
	       
	 public Exam ( String em,SessionExam s) {
		 this.examId = nextExamId;
	        nextExamId++; // Increment the nextExamId for the next Exam
		
		 eModality=em;
		 session=s;
		 this.exercises = new TreeSet<>();
		 calculateTotalGrade();
	 }
	 public double setexamcoefgrade(double excn) {return this.examcoefnote=excn;}
	 public TreeSet<Exercise> getExercises(){
		 return exercises;
	 }
	 public int compareTo(Exam e) {
		    //return Integer.compare(this.examId, e.examId);
		 return examId - e.examId;
		}
	 
	 public void addExercise(Exercise exercise) {
		    exercises.add(exercise);
		    setChanged();
		    notifyObservers();
		}
	 public String toString() {
		
			 return "Examen "+session+" "+eModality;
		 
	 }
	 public double setfinalnote(double fn) {
		 return this.finalnote=fn;
	 }
	
	 public double calculateTotalGrade() {
	        examcoefnote = 0;
	        
	        
	            int examcoefnote = 0;
	            for (Exercise exercise : exercises) {
	                int exerciseTotalGrade = 0;
	                for (Question question : exercise.questions) {
	                    exerciseTotalGrade += question.maxgrade;
	                }
	                exercise.setTotalGrade(exerciseTotalGrade);
	                examcoefnote += exerciseTotalGrade;
	            }
	            setChanged();
	            notifyObservers();
	            return examcoefnote;
	        }
}