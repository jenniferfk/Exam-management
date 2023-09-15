package Controller;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import Model.*;
import View.*;
public class StudentController {

	public StudentsPanel studentpanel;
	public TreeSet<Exam> doneexams= new TreeSet<Exam>();
	public JTextField answerTextField;
	public Exercise currentExercise;
	
	
	public StudentController(StudentsPanel sp) {
		this.studentpanel=sp;
		
		JButton seeavexamsBTN= sp.seeavexamsBTN;
		JButton doexamBTN= sp.doexamBTN;
		JButton studentsubmitexamBTN= sp.studentsubmitexamBTN;
		
		
		seeavexamsBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        Course selectedCourse = (Course) sp.stcoursesCMB.getSelectedItem();
		        sp.examsLSTMDL.clear();
		        
		        String directorypath= selectedCourse.toString()+".Exams_directory";
		        Set<Exam> availableExams =loadExamsFromFile(directorypath);
		        
		        Iterator<Exam> itae = availableExams.iterator();
				while(itae.hasNext()) { sp.examsLSTMDL.addElement(itae.next());}
		        sp.examsLST.setVisible(true);  
		        doexamBTN.setVisible(true);
			}
		});
		
		doexamBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exam selectedexam= sp.examsLST.getSelectedValue();
				sp.stexamtab.removeAll();
				   
			    sp.stexamJSP.setVisible(true);
			    
			    for (Exercise exercise : selectedexam.getExercises()) {
			        JLabel exerciseLabel = new JLabel("Exercise: " + exercise.exNum);
			        	Font font = exerciseLabel.getFont();
			        	Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
			        	exerciseLabel.setFont(boldFont);

			        sp.stexamtab.add(exerciseLabel);
			        
			        for (Question question : exercise.questions) {
			            JLabel questionLabel = new JLabel("Question " + question.qnumber + ": " + question.text);
			            exerciseLabel.setFont(exerciseLabel.getFont().deriveFont(Font.BOLD));
			           
			            sp.stexamtab.add(questionLabel);
			            answerTextField = new JTextField(50);
			            question.setAnswerTextField(answerTextField);
			            sp.stexamtab.add(answerTextField);
			           
			            }
			        
			}
				studentsubmitexamBTN.setVisible(true);
				
				
				studentsubmitexamBTN.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        Student s = (Student) sp.studentsCMB.getSelectedItem();
				        Exam doneexamm = new Exam(selectedexam.eModality, selectedexam.session);

				        for (Exercise exercise : selectedexam.getExercises()) {
				            Exercise doneExercise = new Exercise(exercise.exNum);

				            for (Question question : exercise.questions) {
				               
				                JTextField answerTextField = question.getAnswerTextField();
				                String answerText = answerTextField.getText();

				                Answer answer = new Answer(answerText, s, question);

				                question.setAnswer(answer);
				                doneExercise.addQuestion(question);
				            }

				            doneexamm.addExercise(doneExercise);
				        }

				        doneexams.add(doneexamm);
				        if (doneexams != null) {
				            saveAnswersToFile();
				        }
				        sp.stexamJSP.setVisible(false);
				    }
			
				});
			}
		});
	}
	public Set<Exam> loadExamsFromFile(String directoryPath){
		Set<Exam> exams= new HashSet<>();
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
                            currentExercise = new Exercise(exerciseNum);
                            exam.addExercise(currentExercise);
                        } else if (line.startsWith("Question ")) {
                            String[] questionParts = line.split(" ", 3);
                            String questionNum = questionParts[1];
                            String questionText = questionParts[2];
                            String expectedAnswer = reader.readLine().substring(16);
                            String maxGrade = reader.readLine().substring(10);

                            double grade = Double.parseDouble(maxGrade);

                            Question question = new Question(Integer.parseInt(questionNum), questionText, grade, expectedAnswer);
                            currentExercise.addQuestion(question);
                        }
                    }

	                exams.add(exam);}
	            	
	            } catch (IOException e) {
	                System.out.println("An error occurred while loading exams: " + e.getMessage());
	            }
	        }
	    }
		
		
		return exams;
	}
public void saveAnswersToFile() {
		
		Student student= (Student)studentpanel.studentsCMB.getSelectedItem();
		Course course= (Course) studentpanel.stcoursesCMB.getSelectedItem();
		String directoryPath=course.toString()+"."+student.sName+".Exams_directory";
		File directory= new File(directoryPath);
		directory.mkdir();
		
		for(Exam exam: doneexams) {
		
			String filename=directoryPath+"/"+exam.eModality+"_"+exam.session+"_examen.txt";
			try(FileWriter writer= new FileWriter(filename)){
				for (Exercise exercise : exam.getExercises()) {
	            	 writer.write("Exercise " + exercise.exNum);
	            	
	            	  writer.write("\n");
	                for (Question question : exercise.questions) {
	                	 Answer answer = new Answer(question.a.answertext, question.a.s, question);
	                    writer.write("Question " + question.qnumber+" "+question.text);
	                    writer.write("\n");
	                    writer.write("Expected Answer:"+question.answer );
	                    writer.write("\n");
	                    writer.write("Answer: " + answer.answertext);
	                    writer.write("\n");
	                    writer.write("MaxGrade: "+ question.maxgrade);
	                    
	                    writer.write("\n");
	                    writer.write("\n");
	                }
	            }
	        } catch (IOException ex) {}
		}  
			
	}
}
