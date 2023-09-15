package Controller;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import Model.*;
import View.*;

public class ProfessorController {

	public ProfessorPanel profpan;
	public JTextField gradeTextField;
	public TreeSet<Exam> gradedexams= new TreeSet<Exam>();
	public Exercise currentExercises;
	public Exam pe;
	
	public ProfessorController(ProfessorPanel pn) {
		this.profpan=pn;
		JButton seeprofexamsBTN=pn.seeprofexamsBTN;
		JButton backprofBTN=pn.backprofBTN;
		JButton correctexamBTN=pn.correctexamBTN;
		JButton profsubmitexamBTN=pn.profsubmitexamBTN;
		
		
		seeprofexamsBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pn.profcoursesCMB.setEnabled(false);
				pn.profsCMB.setEnabled(false);
				pn.profstCMB.setEnabled(false);
				
			 
			  pn.profexamsLSTMDL.clear();
			  
			  
			    Student selectedStudent = (Student) pn.profstCMB.getSelectedItem();
				Course selectedcourse=(Course) pn.profcoursesCMB.getSelectedItem();
				String directorypath= selectedcourse.toString()+"."+selectedStudent.sName+".Exams_directory";
				Set<Exam> examslist= loadAnswerFromFile(directorypath);
		       
		        
		        Iterator<Exam> itec=examslist.iterator();
		        
		        
		        while(itec.hasNext()) {
		        	 pn.profexamsLSTMDL.addElement(itec.next());
		        	}
		         	pn.profexamsLST.setModel(pn.profexamsLSTMDL);
		         	 pn.profexamsLST.setVisible(true);
		         	pn.correctexamBTN.setVisible(true);
						}});
		
		
		        backprofBTN.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		pn.profcoursesCMB.setEnabled(true);
						pn.profsCMB.setEnabled(true);
						pn.profstCMB.setEnabled(true);
						pn.profexamsLSTMDL.clear();
						pn.profexamsLST.setVisible(false);
						pn.correctexamBTN.setVisible(false);
						pn.profsubmitexamBTN.setVisible(false);
		        	}
		        });
		        
		        
		        correctexamBTN.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 pe= pn.profexamsLST.getSelectedValue();
						pn.profexamtab.removeAll();
						pn.profexamJSP.setVisible(true);
						
						pn.profexamtab.setLayout(new BoxLayout(pn.profexamtab, BoxLayout.Y_AXIS));
						
						for (Exercise exercise : pe.getExercises()) {
					        JLabel exerciseLabel = new JLabel("Exercise: " + exercise.exNum);
					        exerciseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
					        pn.profexamtab.add(exerciseLabel);
					        
					        for (Question question : exercise.questions) {
					            JLabel questionLabel = new JLabel("Question " + question.qnumber + ": " + question.text);
					         
					            pn.profexamtab.add(questionLabel);
					            
					            JLabel expectedanswer= new JLabel("Expected Answer: "+question.answer);
					            pn.profexamtab.add(expectedanswer);
					            
					            JLabel studentanswer= new JLabel("Student's Answer: "+ question.a.answertext);
					            pn.profexamtab.add(studentanswer);
					            
					            JPanel gradePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					            JLabel gradeLabel = new JLabel("Grade:");
					            gradeTextField= new JTextField(5);
					           gradePanel.add(gradeLabel);
					            gradePanel.add(gradeTextField);
					            pn.profexamtab.add(gradePanel);
					            question.setGradeTextField(gradeTextField);
					            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
					           pn.profexamtab.add(separator);
					           
					            } 
					}
						 gradedexams.clear();
						profsubmitexamBTN.setVisible(true);
					}
				});
						
		         pe= pn.profexamsLST.getSelectedValue();
						profsubmitexamBTN.addActionListener(new ActionListener() {
						    public void actionPerformed(ActionEvent e) {
						    	double ttgrade=0.0;
						      gradedexams.clear();
						    	Exam gradedexam= new Exam(pe.eModality,pe.session);
						    	
						    	for (Exercise exercise : pe.getExercises()) {
						    		Exercise gradedex= new Exercise(exercise.exNum);
					                for (Question question : exercise.questions) {
					                	JTextField gradetextfield= question.getGradeTextField();
					                	double grade = Double.parseDouble(gradetextfield.getText());
					                    question.setGrade(grade);
					                    gradedex.addQuestion(question);
					                    ttgrade += grade;
					                }
					               gradedexam.addExercise(gradedex); 
					               
					            }
						    	gradedexam.setfinalnote(ttgrade);
						    	System.out.println(ttgrade);
						    	gradedexams.add(gradedexam);
						    	
						    	if(gradedexams!=null) {
							        saveGradesToFile();
							        }
						    	
						    	pn.profexamtab.removeAll();
					           pn.profexamJSP.setVisible(false);
					            pn.profsubmitexamBTN.setVisible(false);
					        
					    }
					});
						


				
	
		
	}
	public Set<Exam> loadAnswerFromFile(String directoryPath){
		Set<Exam> examss= new HashSet<>();
		Student student= (Student)profpan.profstCMB.getSelectedItem();
		File directory= new File(directoryPath);
		File[] files= directory.listFiles();
		
		
		if (files != null) {
	        for (File file : files) {
	            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	            	String filename= file.getName();
	            	String[] nameParts= filename.split("_");
	            	if (nameParts.length > 1) {
	            		 
	            		    String examMod = nameParts[0]; 

	            		    String examType = nameParts[1]; 
	            	    SessionExam se= new SessionExam(examType);
	            	    Exam exam = new Exam(examMod, se);
	            	   
	            	    String line;
	                 

	                    while ((line = reader.readLine()) != null) {
	                        if (line.startsWith("Exercise ")) {
	                            String exerciseNum = line.substring(8).trim();
	                            currentExercises = new Exercise(exerciseNum);
	                            exam.addExercise(currentExercises);
	                        } else if (line.startsWith("Question ")) {
	                            String[] questionParts = line.split(" ", 3);
	                            String questionNum = questionParts[1];
	                            String questionText = questionParts[2];
	                            String expectedAnswer = reader.readLine().substring(16);
	                            String studentanswer= reader.readLine().substring(7);
	                            String maxGrade = reader.readLine().substring(10);

	                            double grade = Double.parseDouble(maxGrade);

	                            Question question = new Question(Integer.parseInt(questionNum), questionText, grade, expectedAnswer);
	                            Answer answer= new Answer(studentanswer,student,question);
	                            question.setAnswer(answer);
	                            currentExercises.addQuestion(question);
	                        }
	                       
	                    }
	            	    examss.add(exam);
	            	
	            	}}
	            catch (IOException e) {
	                System.out.println("An error occurred while loading exams: " + e.getMessage());
	            }
	        }
		}
		
		return examss;
		
	}
	
	public void saveGradesToFile() {
	    Student student = (Student) profpan.profstCMB.getSelectedItem();
	    Course course = (Course) profpan.profcoursesCMB.getSelectedItem();
	    String directoryPath = course.toString() + "." + student.sName + ".Grades_directory";
	    File directory = new File(directoryPath);
	    directory.mkdir();

	    for (Exam exam : gradedexams) {
	        String filename = directoryPath + "/" + exam.eModality + "_" + exam.session + "_Grades.txt";
	        try (FileWriter writer = new FileWriter(filename, true)) { 
	            writer.write("Grade " + exam.finalnote);
	            writer.write("\n");
	        } catch (IOException ex) {
	            
	        }
	    }gradedexams.clear();
	}
}
