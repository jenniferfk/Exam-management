package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Controller.MainController;
import Controller.StudentController;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import Model.*;
import View.*;

public class StudentsPanel extends JTabbedPane implements MyObserver{
	public JPanel takeexamtab,stexamtab;
	
	public JLabel studentsLBL,stcoursesLBL;
	public DefaultComboBoxModel<Course> stcoursesCMBMDL;
	public JComboBox<Course> stcoursesCMB;
	public DefaultComboBoxModel<Student> studentsCMBMDL;
	public JComboBox<Student> studentsCMB;
	public JButton seeavexamsBTN,doexamBTN,studentsubmitexamBTN;
	public JList<Exam> examsLST;
	public DefaultListModel<Exam> examsLSTMDL;
	public JScrollPane stexamJSP;
	public  MainFrame mf;
	public ProfessorPanel pn;
	public EmployeePanel ep;
	public ResultsPanel rp;
	
	public StudentsPanel(MainFrame mf) {
		setVisible(true);
		this.mf=mf;
		takeexamtab= new JPanel();
	    takeexamtab.setSize(800,600); 
	    takeexamtab.setLayout(null);
	    addTab("Take exams", takeexamtab);
	    
	    studentsCMBMDL=new DefaultComboBoxModel<Student>();
		studentsLBL=new JLabel("Student");
		studentsCMB=new JComboBox<Student>();
		studentsLBL.setBounds(20,30,85,25);
		studentsCMB.setBounds(80,30,160,25);
		takeexamtab.add(studentsLBL);
		takeexamtab.add(studentsCMB);
		

		
	    stcoursesCMBMDL=new DefaultComboBoxModel<Course>();
		stcoursesLBL=new JLabel("Courses");
		stcoursesCMB=new JComboBox<Course>();
		stcoursesLBL.setBounds(260,30,85,25);
		stcoursesCMB.setBounds(320,30,110,25);
		takeexamtab.add(stcoursesLBL);
		takeexamtab.add(stcoursesCMB);
		populatestCourse();
		populateStudents();
		
		mf.majorCMB.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		        stcoursesCMBMDL.removeAllElements();
		        studentsCMBMDL.removeAllElements();
		    
				Major selectedmajor= (Major) mf.majorCMB.getSelectedItem();
				String directoryPath= selectedmajor.toString()+"_directory";
				Set<Course> coursesSET = loadCoursesFromFile(directoryPath);
					
				Iterator<Course> itec = coursesSET.iterator();
				while(itec.hasNext()) { stcoursesCMBMDL.addElement(itec.next());}
				stcoursesCMB.setModel(stcoursesCMBMDL);
		        
				String directoryPathh= selectedmajor.toString()+".students_directory";
				Set<Student> studentsSET = loadStudentsFromFile(directoryPathh);
				Iterator<Student> itsc = studentsSET.iterator();
				while(itsc.hasNext()) { studentsCMBMDL.addElement(itsc.next());}
				studentsCMB.setModel(studentsCMBMDL);

		        stcoursesCMB.setModel(stcoursesCMBMDL);
		        studentsCMB.setModel(studentsCMBMDL);
		    }
		});
		
		seeavexamsBTN= new JButton("see available exams");
		seeavexamsBTN.setBounds(450,30,200,25);
		takeexamtab.add(seeavexamsBTN);
			
	    examsLSTMDL = new DefaultListModel<Exam>();
		examsLST = new JList<Exam>(examsLSTMDL);
		takeexamtab.add(examsLST).setBounds(50,80,300,300);
		examsLST.setVisible(false);
		
		doexamBTN= new JButton("Do Exam");
		doexamBTN.setBounds(100,400,85,25);
		takeexamtab.add(doexamBTN);
		doexamBTN.setVisible(false);
		studentsubmitexamBTN= new JButton("Submit");
		studentsubmitexamBTN.setBounds(200,400,85,25);
		takeexamtab.add(studentsubmitexamBTN);
		studentsubmitexamBTN.setVisible(false);
		
		stexamtab = new JPanel();
		stexamtab.setBounds(450, 80, 600, 7000);
		stexamtab.setLayout(new GridLayout(0, 1, 0, 20));
		takeexamtab.add(stexamtab);
		stexamJSP = new JScrollPane(stexamtab);
		stexamJSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		takeexamtab.add(stexamJSP).setBounds(450, 80, 500, 400);

		stexamJSP.setVisible(false);
		MainController controller = new MainController(mf,ep,rp,pn,this);
		StudentController scon= new StudentController(this);
	}
	public void populatestCourse() {
		 stcoursesCMBMDL.removeAllElements();
		 Major selectedmajor= (Major) mf.majorCMB.getSelectedItem();
		
			String directoryPath= selectedmajor.toString()+"_directory";
			Set<Course> coursesSET = loadCoursesFromFile(directoryPath);
				
			Iterator<Course> itec = coursesSET.iterator();
			while(itec.hasNext()) { stcoursesCMBMDL.addElement(itec.next());}
			stcoursesCMB.setModel(stcoursesCMBMDL);
		 
			
	}
	public void populateStudents() {
		studentsCMBMDL.removeAllElements();
		Major selectedmajor= (Major) mf.majorCMB.getSelectedItem();
		if(selectedmajor!=null) {
		String directoryPathh= selectedmajor.toString()+".students_directory";
		Set<Student> studentsSET = loadStudentsFromFile(directoryPathh);
		Iterator<Student> itsc = studentsSET.iterator();
		while(itsc.hasNext()) { studentsCMBMDL.addElement(itsc.next());}
		studentsCMB.setModel(studentsCMBMDL);
	}}
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
	public void update() {
		populatestCourse();
		populateStudents();
		
	}
	
}
