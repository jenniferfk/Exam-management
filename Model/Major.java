package Model;

import java.util.TreeSet;

public class Major extends MyObservable implements Comparable<Major> {
	 public String mName; TreeSet<Course>courses;
	public Major(String mn) {
		mName=mn; courses=new TreeSet<Course>();
	}
	public String toString()
	{
		return mName;
	}
	public void addCourse(Course c) {
		courses.add(c);
	}
  public int compareTo(Major m) {
		return mName.compareTo(m.mName);
	}
  public void setName(String name) {
      this.mName = name;
      setChanged(); 
      notifyObservers(); 
  }
}
