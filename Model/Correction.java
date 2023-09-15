package Model;

public class Correction extends MyObservable {
	
	Professor p1,p2;
	double notefinal=0;
	public Correction(double n1, double n2) {
		
		n1=p1.note;
		n2=p2.note;
		
		calculateGrade();
	}
	public double calculateGrade() {
		notefinal = 0;
		notefinal= (p1.note+p2.note)/2;
        return notefinal;
            
        }
}
