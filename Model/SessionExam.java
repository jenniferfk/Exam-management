package Model;

public class SessionExam extends MyObservable{
	 private String typesession;
	 public SessionExam(String s) {
		 typesession=s;
	 }
	 public String toString() {
		 return typesession;
	 }
}
