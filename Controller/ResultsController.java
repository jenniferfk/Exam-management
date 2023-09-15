package Controller;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;

import Model.*;

public class ResultsController {
	
	public ResultsPanel resultpanel;
	
	public ResultsController(ResultsPanel rp) {
		this.resultpanel=rp;
		JButton seeresultsBTN= rp.seeresultsBTN;
		
		seeresultsBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Course course= (Course) rp.resultscoursesCMB.getSelectedItem();
				Student student= (Student) rp.resultsstCMB.getSelectedItem();
				rp.resultstabletab.setVisible(true);
				rp.resultsTBLMDL.setRowCount(0); 
				String directoryPath= course.toString()+"."+student.sName+".Grades_directory";
				
				double grade1= loadGradesFromFile(directoryPath,"Partiel");
				double grade2= loadGradesFromFile(directoryPath,"Final");
				double finalgradee= (grade1*0.3)+(grade2*0.7);
				
				
				rp.resultsTBLMDL.addRow(new Object[] {course.toString(),"P",grade1});
				rp.resultsTBLMDL.addRow(new Object[] {course.toString(),"F/R",grade2});
				rp.resultsTBLMDL.addRow(new Object[] {course.toString(),"TOTAL",finalgradee});
				
			}
		});
		
	}
	public double loadGradesFromFile(String directoryPath,String session){
		double grade=0;
		double gradea=0;
		File directory= new File(directoryPath);
		File[] files = directory.listFiles();
		
		if (directory.exists() && files != null) {
		    for (File file : files) {
		      
		        if (file.isFile() && file.getName().startsWith(session)) {
		        	//String filename= file.getName();
		        	 try (Scanner scanner = new Scanner(file)) {
		                 while (scanner.hasNextLine()) {
		                     String line = scanner.nextLine();
		                     line = line.trim(); // Remove leading/trailing whitespace

		                     // Extract the grade value
		                     if (line.startsWith("Grade")) {
		                         String gradeText = line.substring(line.indexOf(" ") + 1);
		                           gradea += Double.parseDouble(gradeText);
		                         
		                     }
		                     grade= gradea/2;
		                 }
		        		 }   catch (IOException e) {
	            }
		    }
		}
		
		}
		return grade;
		
		
		
		
	}
	
}
