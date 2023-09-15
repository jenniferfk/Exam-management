package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Controller.MainController;
import Controller.ProfessorController;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import Model.*;
import View.*;

public class ProfessorPanel extends JTabbedPane implements MyObserver{
		public JPanel correctexamtab,profexamtab;
		//public JTabbedPane profjtp;
		public JLabel profsLBL,profcoursesLBL,profstLBL;
		public DefaultComboBoxModel<Course> profcoursesCMBMDL;
		public JComboBox<Course> profcoursesCMB;
		public DefaultComboBoxModel<Student> profstCMBMDL;
		public JComboBox<Student> profstCMB;
		public DefaultComboBoxModel<Professor> profsCMBMDL;
		public JComboBox<Professor> profsCMB;
		public JButton seeprofexamsBTN,backprofBTN,correctexamBTN,profsubmitexamBTN;
		public JList<Exam> profexamsLST;
		public DefaultListModel<Exam> profexamsLSTMDL;
		public JScrollPane profexamJSP;
		public  MainFrame mf;
		public EmployeePanel ep;
		public ResultsPanel rp;
		public StudentsPanel sp;
		
	public ProfessorPanel(MainFrame mf) {
		correctexamtab=new JPanel();
		 correctexamtab.setSize(800,600); 
		 correctexamtab.setLayout(null);
		 //profjtp=new JTabbedPane();
		//setBounds(20,120,1000,600);
		addTab("Correct Exam",correctexamtab);
		this.mf=mf;
		 
		 profsCMBMDL=new DefaultComboBoxModel<Professor>();
			profsLBL=new JLabel("Prof");
			profsCMB=new JComboBox<Professor>();
			profsLBL.setBounds(20,30,85,25);
			profsCMB.setBounds(80,30,160,25);
			correctexamtab.add(profsLBL);
			correctexamtab.add(profsCMB);
			
			
		 profcoursesCMBMDL=new DefaultComboBoxModel<Course>();
		 profcoursesLBL=new JLabel("Courses");
		 profcoursesCMB=new JComboBox<Course>();
		 profcoursesLBL.setBounds(260,30,85,25);
		 profcoursesCMB.setBounds(320,30,110,25);
		 correctexamtab.add(profcoursesLBL);
		 correctexamtab.add(profcoursesCMB);
		 
		 seeprofexamsBTN= new JButton("exams to correct");
		 seeprofexamsBTN.setBounds(720,30,150,25);
		 correctexamtab.add(seeprofexamsBTN);
		 backprofBTN=new JButton("Back");
		 backprofBTN.setBounds(890,30,90,25);
		 correctexamtab.add(backprofBTN);
			
		 profstCMBMDL=new DefaultComboBoxModel<Student>();
		 profstLBL=new JLabel("Student");
		 profstCMB=new JComboBox<Student>();
		 profstLBL.setBounds(450,30,85,25);
		 profstCMB.setBounds(520,30,160,25);
		 correctexamtab.add(profstLBL);
		 correctexamtab.add(profstCMB);
		 	populateCourses();
			populatestudents();
			populateprofs();
			 
		 mf.majorCMB.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					
					profcoursesCMBMDL.removeAllElements();
					profstCMBMDL.removeAllElements();
					
					Major selectedmajor= (Major) mf.majorCMB.getSelectedItem();
					String directoryPath= selectedmajor.toString()+"_directory";
					Set<Course> coursesSET = loadCoursesFromFile(directoryPath);
						
						Iterator<Course> itec = coursesSET.iterator();
						while(itec.hasNext()) { profcoursesCMBMDL.addElement(itec.next());}
						profcoursesCMB.setModel(profcoursesCMBMDL);
						
						String directoryPathh= selectedmajor.toString()+".students_directory";
						Set<Student> studentsSET = loadStudentsFromFile(directoryPathh);
						
						Iterator<Student> itsc = studentsSET.iterator();
						while(itsc.hasNext()) { profstCMBMDL.addElement(itsc.next());}
						profstCMB.setModel(profstCMBMDL);

				       
				       
						
				}
			});  
		 
		 correctexamBTN= new JButton("Correct Exam");
			correctexamBTN.setBounds(50,400,130,25);
			correctexamtab.add(correctexamBTN);
			correctexamBTN.setVisible(false);
			profsubmitexamBTN= new JButton("Submit");
			profsubmitexamBTN.setBounds(200,400,85,25);
			correctexamtab.add(profsubmitexamBTN);
			profsubmitexamBTN.setVisible(false);
			
			profexamtab = new JPanel();
			profexamtab.setBounds(450, 80, 600, 7000);
			profexamtab.setLayout(new GridLayout(0, 1, 0, 20)); 
			correctexamtab.add(profexamtab);
			profexamJSP = new JScrollPane(profexamtab);
			profexamJSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			correctexamtab.add(profexamJSP).setBounds(450, 80, 500, 400);
			
			profexamsLSTMDL = new DefaultListModel<Exam>();
			profexamsLST = new JList<Exam>(profexamsLSTMDL);
			correctexamtab.add(profexamsLST).setBounds(50,80,300,300);
			profexamsLST.setVisible(false);

			profexamJSP.setVisible(false);
			
		
			 
			 MainController controller = new MainController(mf,ep,rp,this,sp);
			 ProfessorController profcont= new ProfessorController(this);
			
	}
	
	public void populateCourses() {
		profcoursesCMBMDL.removeAllElements();

		Major selectedmajor= (Major) mf.majorCMB.getSelectedItem();
		String directoryPath= selectedmajor.toString()+"_directory";
		Set<Course> coursesSET = loadCoursesFromFile(directoryPath);
			
			Iterator<Course> itec = coursesSET.iterator();
			while(itec.hasNext()) { profcoursesCMBMDL.addElement(itec.next());}
			profcoursesCMB.setModel(profcoursesCMBMDL);
	}
	
	
	public void populatestudents() {
		profstCMBMDL.removeAllElements();
		Major selectedmajor= (Major) mf.majorCMB.getSelectedItem();
		
		String directoryPathh= selectedmajor.toString()+".students_directory";
		Set<Student> studentsSET = loadStudentsFromFile(directoryPathh);
		
		Iterator<Student> itsc = studentsSET.iterator();
		while(itsc.hasNext()) { profstCMBMDL.addElement(itsc.next());}
		profstCMB.setModel(profstCMBMDL);
	}
	
	
	public void populateprofs() {
		String directoryPath = "profs_directory";
    	Set<Professor> profs= loadProfsFromFile(directoryPath);
    	Iterator<Professor> itpc = profs.iterator();
		while(itpc.hasNext()) {
			profsCMBMDL.addElement(itpc.next());
		}
		profsCMB.setModel(profsCMBMDL);
		
	}
	
	public Set<Course> loadCoursesFromFile(String directoryPath) {
	    Set<Course> courses = new HashSet<>();
	    File directory = new File(directoryPath);
	    File[] files = directory.listFiles();

	    if (files != null) {
	        for (File file : files) {
	            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	                String courseName = reader.readLine();
	                Course course = new Course(courseName);
	                courses.add(course);
	            } catch (IOException e) {
	                System.out.println("An error occurred while loading courses: " + e.getMessage());
	            }
	        }
	    }

	    return courses;
	}
	public Set<Student> loadStudentsFromFile(String directoryPath) {
	    Set<Student> students = new HashSet<>();
	    File directory = new File(directoryPath);
	    File[] files = directory.listFiles();

	    if (files != null) {
	        for (File file : files) {
	            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	                String studentId = reader.readLine();
	                String studentName = reader.readLine();
	                String majorName = directoryPath.split("_")[0];
	                Major major = new Major(majorName);
	                Student student = new Student(studentId, studentName, major);
	                students.add(student);
	            } catch (IOException e) {
	                System.out.println("An error occurred while loading students: " + e.getMessage());
	            }
	        }
	    }

	    return students;
	}
	
	public Set<Professor> loadProfsFromFile(String directoryPath) {
	    Set<Professor> profs = new HashSet<>();
	    File directory = new File(directoryPath);
	    File[] files = directory.listFiles();

	    if (files != null) {
	        for (File file : files) {
	            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	                String profId = reader.readLine();
	                String profName = reader.readLine();
	                Professor profss = new Professor(profId, profName);
	                profs.add(profss);
	            } catch (IOException e) {
	                System.out.println("An error occurred while loading profs: " + e.getMessage());
	            }
	        }
	    }

	    return profs;
	}
	
	public void update() {
		populateCourses();
		populatestudents();
		 populateprofs();
		
	}
	
}
