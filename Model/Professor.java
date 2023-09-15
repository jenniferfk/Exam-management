package Model;

public class Professor extends MyObservable implements Comparable<Professor>{
	 public String pId;  public String pName;
	 public double note;
	public Professor (String pi, String pn ) {
		pId=pi; pName=pn; 
	}
	public String toString() {
		return pId+ " "+ pName ;
	}
	public int compareTo(Professor p) {
		return pId.compareTo(p.pId);
	}
	public void setNote(double note) {
        this.note = note;
        setChanged();
        notifyObservers();
    }
	public void setname(String name) {
        this.pName = name;
        setChanged();
        notifyObservers();
    }
	public void setid(String id) {
        this.pId = id;
        setChanged();
        notifyObservers();
    }
	
}