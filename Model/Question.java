package Model;

import javax.swing.JTextField;

public class Question extends MyObservable implements Comparable<Question>{
	 public String text;  public int qnumber; static int nextQuestNum=1; public int qId;public double maxgrade; public  String answer;public Answer a; public double pgrade;
	 public JTextField answerTextField; public JTextField gradetextfield;
	 public Question(int qn,String t, double g,String a) {
		 qnumber=qn;
		 this.qId=nextQuestNum;
		 nextQuestNum++;
		 text=t;
		maxgrade=g;
		answer=a;
	 }
	 public JTextField getAnswerTextField() {
	        return answerTextField;
	    }

	    public void setAnswerTextField(JTextField answerTextField) {
	        this.answerTextField = answerTextField;
	    }
	    public JTextField getGradeTextField() {
	        return gradetextfield;
	    }

	    public void setGradeTextField(JTextField gradetextfield) {
	        this.gradetextfield = gradetextfield;
	    }
	 public int compareTo(Question q) {
		  return Integer.compare(this.qId, q.qId);
	  }
	 public Answer setAnswer(Answer aa) {
		
		setChanged();
        notifyObservers();
        return this.a=aa;
	 }
	 public double setGrade(double g) {
		
		 setChanged();
	      notifyObservers();
	      return this.pgrade=g;
	 }
	 
}
