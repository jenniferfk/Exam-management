package Controller;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import Model.*;
import View.*;


public class EmployeeController {
	public int j;
	public EmployeePanel emppan;
	public MainFrame mf;
	public ProfessorPanel pp;
	public ResultsPanel rp;
	public StudentsPanel sp;
	public TreeSet<Major> majorsset=new TreeSet<Major>();
	public Map<Major,HashSet<Course>>crsmaj= new TreeMap<Major,HashSet<Course>>();
	public static TreeSet<Course> crs=new TreeSet<Course>();
	public TreeSet<Student> studentsSET= new TreeSet<Student>();
	public TreeSet<Professor> professorsSET= new TreeSet<Professor>();
	public TreeSet<Exam> examsSET=new TreeSet<Exam>();
	public TreeSet<Exercise> exerciseSET=new TreeSet<Exercise>();
	public TreeSet<Question> questionSET=new TreeSet<Question>();
	
	
	public EmployeeController(EmployeePanel ep,MainFrame mainf) {
		this.emppan=ep;
		this.mf=mainf;
		pp= new ProfessorPanel(mainf);
		rp=new ResultsPanel(mainf);
		sp=new StudentsPanel(mainf);
		JButton addmajorBTN= ep.addmajorBTN;
		JButton addcourseBTN= ep.addcourseBTN;
		JButton addstudentBTN= ep.addstudentBTN;
		JButton addprofBTN= ep.addprofBTN;
		JButton backBTN= ep.backBTN;
		JButton createexamBTN= ep.createexamBTN;
		JButton addexerciseBTN=ep.addexerciseBTN;
		JButton addquesBTN=ep.addquesBTN;
		JButton doneBTN=ep.doneBTN;
		
		
		 addmajorBTN.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	ep.addmajorTXT.setVisible(true);
	            	ep.addthemajorBTN.setVisible(true);
	            	addcourseBTN.setEnabled(false);
	            	addstudentBTN.setEnabled(false);
	            	addprofBTN.setEnabled(false);
	            	ep.addthemajorBTN.addActionListener(new ActionListener() {
	            		public void actionPerformed(ActionEvent e) {
	            			if(!ep.addmajorTXT.getText().isEmpty()) {
	            			Major newmajor= new Major(ep.addmajorTXT.getText());
	            			majorsset.add(newmajor);
	            			mf.majorCMBMDL.addElement(newmajor);
	            			ep.addmajorTXT.setText("");
	            			saveMajorsToFile();
	            			}}
	            	});
	            }
		 });
		 
		 addcourseBTN.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	addmajorBTN.setEnabled(false);
    			addstudentBTN.setEnabled(false);
    			addprofBTN.setEnabled(false);
    			
    			ep.addcourseTXT.setVisible(true);
	            	ep.addthecourseBTN.setVisible(true);
	            	ep.addthecourseBTN.addActionListener(new ActionListener() {
	            		public void actionPerformed(ActionEvent e) {
	            			if(!ep.addcourseTXT.getText().isEmpty()) {
	            			Major selectedmajor= (Major) mf.majorCMBMDL.getSelectedItem();
	            			Course newCourse= new Course(ep.addcourseTXT.getText());
	            			 HashSet<Course> crs3 = crsmaj.get(selectedmajor);
	                         if (crs3 == null) {
	                             crs3 = new HashSet<Course>();
	                             crs3.add(newCourse);
	                             crsmaj.put(selectedmajor, crs3);
	                         }
	            			
	            			crs.add(newCourse);
	            			selectedmajor.addCourse(newCourse);
	            			ep.addcourseTXT.setText("");
	            			ep.examcoursesCMBMDL.addElement(newCourse);
	            			pp.profcoursesCMBMDL.addElement(newCourse);
	            			rp.resultscoursesCMBMDL.addElement(newCourse);
	            			sp.stcoursesCMBMDL.addElement(newCourse);
	            			saveCoursesToFile();
	            			}}
	            	});
    			
	            }
		 });
		 
		 addstudentBTN.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	addcourseBTN.setEnabled(false);
	            	addmajorBTN.setEnabled(false);
	            	addprofBTN.setEnabled(false);
	            	ep.addstudentidTXT.setVisible(true);
	           	 	ep.addstudentnameTXT.setVisible(true);
	           	 	ep.addthestudentBTN.setVisible(true);
	           	 	ep.addstudentidLBL.setVisible(true);
	           	 	ep.addstudentnameLBL.setVisible(true);
	           	 	ep.addthestudentBTN.addActionListener(new ActionListener() {
	            		public void actionPerformed(ActionEvent e) {
	            			if(!ep.addstudentidTXT.getText().isEmpty() && !ep.addstudentnameTXT.getText().isEmpty()) {
	            				Major selectedmajor= (Major) mf.majorCMBMDL.getSelectedItem();
	            				Student s= new Student(ep.addstudentidTXT.getText(),ep.addstudentnameTXT.getText(),selectedmajor);
	            				studentsSET.add(s);
	            				
	            				
	            				ep.addstudentidTXT.setText("");
	            				ep.addstudentnameTXT.setText("");
	            				saveStudentsToFile();
	            			}
	            			
	            			}
	            	});
	           	 	
	            }
		 });
		 
		 addprofBTN.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	addcourseBTN.setEnabled(false);
	            	addstudentBTN.setEnabled(false);
	            	addmajorBTN.setEnabled(false);
	            	ep.addprofidTXT.setVisible(true);
	           	 	ep.addprofnameTXT.setVisible(true);
	           	 	ep.addtheprofBTN.setVisible(true);
	           	 	ep.addprofidLBL.setVisible(true);
	           	 	ep.addprofnameLBL.setVisible(true);
	           	 	ep.addtheprofBTN.addActionListener(new ActionListener() {
	            		public void actionPerformed(ActionEvent e) {
	            			if(!ep.addprofidTXT.getText().isEmpty() && !ep.addprofnameTXT.getText().isEmpty()) {
	            				Professor p= new Professor(ep.addprofidTXT.getText(),ep.addprofnameTXT.getText());
	            				professorsSET.add(p);
	            				
	            				ep.addprofidTXT.setText("");
	            				ep.addprofnameTXT.setText("");
	            				saveProfsToFile();
	            			}
	            			
	            			}
	            	});
	            }
		 });
		 backBTN.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	addcourseBTN.setEnabled(true);
	       			addstudentBTN.setEnabled(true);
	       			addprofBTN.setEnabled(true);
	       			addmajorBTN.setEnabled(true);
	       			
	       			ep.addthemajorBTN.setVisible(false);
	       			ep.addthecourseBTN.setVisible(false);
	       			ep.addmajorTXT.setVisible(false);
	       			ep.addcourseTXT.setVisible(false);
	       			ep.addstudentidTXT.setVisible(false);
	       			ep.addstudentnameTXT.setVisible(false);
	       			ep.addprofidTXT.setVisible(false);
	       			ep.addprofnameTXT.setVisible(false);
	       			ep.addthestudentBTN.setVisible(false);
	       			ep.addtheprofBTN.setVisible(false);
	       			ep.addprofnameLBL.setVisible(false);
	       			ep.addprofidLBL.setVisible(false);
	       			ep.addstudentidLBL.setVisible(false);
	       			ep.addstudentnameLBL.setVisible(false);
	       			
	       		 
	       		 
	            }
		 });
		 
		 createexamBTN.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			    	Course c = (Course) ep.examcoursesCMB.getSelectedItem();
			        Exam Exam = new Exam( (String) ep.modalityCMB.getSelectedItem(),(SessionExam) ep.sessionexamCMB.getSelectedItem());
			        
		             c.addexam(Exam);
		             examsSET.add(Exam);
		             ep.examtab.setVisible(true);

			        addexerciseBTN.setVisible(true);
			        
			        
			        addexerciseBTN.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			                 j = 1; // Initialize exercise number to 1 for each new exercise
			             
			                Exercise ex = new Exercise(ep.exerciseTXT.getText());
		   	                Exam.addExercise(ex);
		   	                exerciseSET.add(ex);
			                ep.exerciseTXT.setText("");
			                ep.exerciseLBL.setVisible(true);
			                ep.exerciseTXT.setVisible(true);
			                ep.questionnoLBL.setVisible(true);
			                ep.questionLBL.setVisible(true);
			                ep.questionnoTXT.setVisible(true);
			                ep.questionnoTXT.setText(String.valueOf(j));
			                ep.questionTXT.setVisible(true);
			                ep.examanswerLBL.setVisible(true);
			    			ep.examanswerTXT.setVisible(true);
			                ep.gradeLBL.setVisible(true);
			                ep.gradeTXT.setVisible(true);
			                addquesBTN.setVisible(true);
			                
			                
			                
			                addquesBTN.addActionListener(new ActionListener() {
			                	public void actionPerformed(ActionEvent e) {
			                		 if (!ep.questionTXT.getText().isEmpty() && ep.gradeTXT.getText().matches("\\d*") && !ep.exerciseTXT.getText().isEmpty()) {
			                			 
			         	            double sum=Double.parseDouble(ep.gradeTXT.getText());
			                		Question q = new Question(j, ep.questionTXT.getText(),sum,ep.examanswerTXT.getText());
			                		ex.addQuestion(q);
			                		questionSET.add(q);
			                		double total=0;
			                		total+=sum;
			                		ep.examTBLMDL.addRow(new Object[]{ep.exerciseTXT.getText(), q.qnumber, q.text, q.maxgrade});
			                		
			                		ep.questionTXT.setText("");
		                            ep.gradeTXT.setText("");
		                            ep.examanswerTXT.setText("");
		                            ep.errorLBL.setVisible(false);
		                            j++;
		                            ep.questionnoTXT.setText(String.valueOf(j));
		                            Exam.setexamcoefgrade(total);
		                            
		                            
			        doneBTN.setVisible(true);
			        doneBTN.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			            	saveExamToFile();
	                        
			                ep.exerciseLBL.setVisible(false);
			                addexerciseBTN.setVisible(false);
			                addquesBTN.setVisible(false);
			                ep.exerciseTXT.setVisible(false);
			                ep.questionnoTXT.setVisible(false);
			                ep.questionTXT.setVisible(false);
			                ep.gradeTXT.setVisible(false);
			                ep.questionnoLBL.setVisible(false);
			                ep.questionLBL.setVisible(false);
			                ep.gradeLBL.setVisible(false);
			                ep.addtoexamBTN.setVisible(false);
			                ep.errorLBL.setVisible(false);
			                doneBTN.setVisible(false);
			                ep.examtab.setVisible(false);
			                ep.examanswerLBL.setVisible(false);
			    			ep.examanswerTXT.setVisible(false);
			            	ep.examTBLMDL.setRowCount(0); 
			                ep.exerciseTXT.setText(""); 
			          
			            }
			        });
			                	}
			                	} });
			    	                } 
			    	        });
			    }
			});
		 
	}
	public void saveStudentsToFile() {
		Major selectedMajor = (Major) mf.majorCMB.getSelectedItem();
	    String directoryPath = selectedMajor.toString()+".students_directory";
	    File directory = new File(directoryPath);
	    directory.mkdir();

	    for (Student student : studentsSET) {
	        String fileName = directoryPath + "/" + student.sName + ".txt";
	        try (FileWriter writer = new FileWriter(fileName)) {
	            writer.write(student.sId);
	            writer.write("\n");
	            writer.write(student.sName);
	          
	        } catch (IOException e) {
	            System.out.println("An error occurred while saving Student " + student.sId + ": " + e.getMessage());
	        }
	    }
	}

	public void saveMajorsToFile() {
	    String directoryPath = "majors_directory"; 
	    File directory = new File(directoryPath);
	    directory.mkdir();

	    for (Major major : majorsset) {
	        String fileName = directoryPath + "/" + major.mName + ".txt";
	        try (FileWriter writer = new FileWriter(fileName)) {
	            writer.write(major.mName);
	            
	        } catch (IOException e) {
	            System.out.println("An error occurred while saving Major " + major.mName + ": " + e.getMessage());
	        }
	    }
	}
	public void saveProfsToFile() {
	    String directoryPath = "profs_directory"; 
	    File directory = new File(directoryPath);
	    directory.mkdir();

	    for (Professor prof : professorsSET) {
	        String fileName = directoryPath + "/" + prof.pName + ".txt";
	        try (FileWriter writer = new FileWriter(fileName)) {
	            writer.write(prof.pId);
	            writer.write("\n");
	            writer.write(prof.pName);
	           
	        } catch (IOException e) {
	            System.out.println("An error occurred while saving Major " + prof.pName + ": " + e.getMessage());
	        }
	    }
	}
	public void saveExamToFile() {
		Course selectedCourse= (Course)emppan.examcoursesCMBMDL.getSelectedItem();
		String directoryPath= selectedCourse.toString()+".Exams_directory";
		
		File directory = new File(directoryPath);
	    directory.mkdir();
	    for (Exam exam: examsSET) {
	    	String filename= directoryPath+"/"+ exam.session+"_"+ exam.eModality+"_Exam"+".txt";
	    	 try (FileWriter writer = new FileWriter(filename)) {
	    		 for (Exercise exercise : exam.getExercises()) {
	    			 writer.write("Exercise "+exercise.exNum);
		                for (Question question : exercise.questions) {
		                	writer.write("\n");
		                	writer.write("Question "+question.qnumber+" "+ question.text);
		                	writer.write("\n");
		                	writer.write("Expected Answer: "+ question.answer);
		                	writer.write("\n");
		                	writer.write("Max Grade: "+question.maxgrade);
		                	writer.write("\n");
		                }
	    		 }
		        } catch (IOException e) {
		            System.out.println("An error occurred while saving Major " + exam.examId  + ": " + e.getMessage());
		        }
	    	
	    }
	    
	}
	public void saveCoursesToFile() {
	    Major selectedMajor = (Major) mf.majorCMB.getSelectedItem();
	    String directoryPath = selectedMajor.toString() + "_directory";
	    File directory = new File(directoryPath);
	    directory.mkdir();

	    for (Course course : crs) {
	        String fileName = directoryPath + "/" + course.cName + ".txt";
	        try (FileWriter writer = new FileWriter(fileName)) {
	            writer.write(course.cName);
	           
	        } catch (IOException e) {
	            System.out.println("An error occurred while saving course " + course.cName + ": " + e.getMessage());
	        }
	    }
	}
	
}
