package Model;

public class Student extends MyObservable implements Comparable<Student>{
	public String sId;  public String sName; public Major m;
	public Student (String si, String sn,Major sm ) {
		sId=si; sName=sn; m=sm;
	}
	public String toString() {
		return sId+ " "+ sName ;
	}
	public String getstudentid() {return sId;}
	public String getstudentname() {return sName;}
	public String setstudentid(String sid) {return this.sId=sid;}
	 public int compareTo(Student s) {
			return sId.compareTo(s.sId);
		}
	 public Major getMajor() {
		 return m;
	 }
	 public void setStudentId(String sId) {
	        this.sId = sId;
	        setChanged();
	        notifyObservers();
	    }
	 public void setStudentname(String sname) {
	        this.sName = sname;
	        setChanged();
	        notifyObservers();
	    }
}
