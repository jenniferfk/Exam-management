package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Controller.MainController;
import Controller.ResultsController;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import Model.*;
import View.*;


public class ResultsPanel extends JTabbedPane implements MyObserver {
	public JPanel resultstabletab,resultstab;
	public DefaultComboBoxModel<Course> resultscoursesCMBMDL;
	public JComboBox<Course> resultscoursesCMB;
	public JLabel resultscoursesLBL,resultsstLBL;
	public DefaultComboBoxModel<Student> resultsstCMBMDL;
	public JComboBox<Student> resultsstCMB;
	//public JTabbedPane resultsjtp;
	public JTable resultsTBL;
	public DefaultTableModel resultsTBLMDL;
	public JButton seeresultsBTN;
	public MainFrame mf;
	public EmployeePanel ep;
	public StudentsPanel sp;
	public ProfessorPanel pn;

	public ResultsPanel(MainFrame mf){
		
		//setBounds(20,120,1000,600);
		
		this.mf=mf;
		resultstab=new JPanel();
	    resultstab.setSize(1500,700); 
	    resultstab.setLayout(null);
	    resultstabletab= new JPanel();
	    resultstabletab.setLayout(null);
	    resultstabletab.setSize(1000, 700);
	    resultstabletab.setBounds(20,50,1000,600);
	    
	    resultscoursesCMBMDL=new DefaultComboBoxModel<Course>();
		resultscoursesLBL=new JLabel("Courses");
		resultscoursesCMB=new JComboBox<Course>();
		resultscoursesLBL.setBounds(20,30,85,25);
		resultscoursesCMB.setBounds(80,30,110,25);
		resultstab.add(resultscoursesLBL);
		resultstab.add(resultscoursesCMB);
		
		 resultsstCMBMDL=new DefaultComboBoxModel<Student>();
		 resultsstLBL=new JLabel("Student");
		 resultsstCMB=new JComboBox<Student>();
		 resultsstLBL.setBounds(450,30,85,25);
		 resultsstCMB.setBounds(520,30,160,25);
		 resultstab.add(resultsstLBL);
		 resultstab.add(resultsstCMB);
	    populateCourse();
	    populateStudents();
	    
	    mf.majorCMB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				resultscoursesCMBMDL.removeAllElements();
				resultsstCMBMDL.removeAllElements();
				
				Major selectedmajor= (Major) mf.majorCMB.getSelectedItem();
				String directoryPath= selectedmajor.toString()+"_directory";
				Set<Course> coursesSET = loadCoursesFromFile(directoryPath);
					
					Iterator<Course> itec = coursesSET.iterator();
					while(itec.hasNext()) { resultscoursesCMBMDL.addElement(itec.next());}
					resultscoursesCMB.setModel(resultscoursesCMBMDL);
					
					String directoryPathh= selectedmajor.toString()+".students_directory";
					Set<Student> studentsSET = loadStudentsFromFile(directoryPathh);
					
					Iterator<Student> itsc = studentsSET.iterator();
					while(itsc.hasNext()) { resultsstCMBMDL.addElement(itsc.next());}
					resultsstCMB.setModel(resultsstCMBMDL);
			}
		});
		 
	   addTab("Results", resultstab);
	    
		Object[] resultscolumns = { "Course name","Type","Note"};
		Object[][] resultsdata = {};
		resultsTBLMDL = new DefaultTableModel(resultsdata, resultscolumns);
		resultsTBL = new JTable(resultsTBLMDL);
		resultstabletab.add(resultsTBL);
		JScrollPane resultssp = new JScrollPane(resultsTBL);
		resultstabletab.add(resultssp, BorderLayout.CENTER);
		resultssp.setBounds(0, 50, 950, 400);
		
        TableColumn rescolumn1 = resultsTBL.getColumnModel().getColumn(0);
        rescolumn1.setPreferredWidth(100);
        TableColumn rescolumn2 = resultsTBL.getColumnModel().getColumn(1);
        rescolumn2.setPreferredWidth(50);
        TableColumn rescolumn3 = resultsTBL.getColumnModel().getColumn(2);
        rescolumn3.setPreferredWidth(50);
        
        resultstab.add(resultstabletab);
        resultstabletab.setVisible(false);
        
        seeresultsBTN = new JButton("See Results");
		resultstab.add(seeresultsBTN).setBounds(800, 30, 120, 25);
		
		MainController controller = new MainController(mf,ep,this,pn,sp);
		ResultsController rescont= new ResultsController(this);
		
	}
	public void populateCourse() {
		resultscoursesCMBMDL.removeAllElements();
		
		
		Major selectedmajor= (Major)  mf.majorCMB.getSelectedItem();
		String directoryPath= selectedmajor.toString()+"_directory";
		Set<Course> coursesSET = loadCoursesFromFile(directoryPath);
			
			Iterator<Course> itec = coursesSET.iterator();
			while(itec.hasNext()) { resultscoursesCMBMDL.addElement(itec.next());}
			resultscoursesCMB.setModel(resultscoursesCMBMDL);
			
			
	}
	
	public void populateStudents() {
		resultsstCMBMDL.removeAllElements();
		Major selectedmajor= (Major) mf.majorCMB.getSelectedItem();
		String directoryPathh= selectedmajor.toString()+".students_directory";
		Set<Student> studentsSET = loadStudentsFromFile(directoryPathh);
		
		Iterator<Student> itsc = studentsSET.iterator();
		while(itsc.hasNext()) { resultsstCMBMDL.addElement(itsc.next());}
		resultsstCMB.setModel(resultsstCMBMDL);
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
	

	public void update() {
		populateCourse();
		populateStudents();
		
	}
	
}
