package Model;

import java.util.ArrayList;
import java.util.Iterator;

abstract class MyObservable{
	boolean changed;
	ArrayList<MyObserver>observers;
	public MyObservable() {
		changed=false;
		observers=new ArrayList<MyObserver>();
	}
	public void setChanged() {changed=true;}
	public void addObserver(MyObserver ob) {observers.add(ob);}
	public void notifyObservers() {
		// for(int i=0; i<observers.size();i++) observers.get(i).update();
		for(Iterator<MyObserver>it=observers.iterator(); it.hasNext();)
			((MyObserver) it.next()).update();
		changed=false;
	}
}