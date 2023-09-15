package Model;

public class Answer extends MyObservable{
	 public String answertext;public Student s;  Question question;
	public Answer(String at, Student ss,Question q) {
		answertext=at; s=ss; question=q;
	}
	public String toString() {
		return answertext;
	}
	
	public void updateAnswerText(String newAnswerText) {
        this.answertext = newAnswerText;
        setChanged();
        notifyObservers();
    }
}
