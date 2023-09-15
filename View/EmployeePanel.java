package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Controller.EmployeeController;
import Controller.MainController;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import Model.*;
import View.*;

public class EmployeePanel extends JTabbedPane implements MyObserver {
	
			public JPanel createexamtab,addobjectstab,examtab;
			//public JTabbedPane employeejtp;
			public JButton addmajorBTN,addcourseBTN,addstudentBTN,addprofBTN,backBTN,addthemajorBTN,
						   addthecourseBTN,addthestudentBTN,addtheprofBTN,addexerciseBTN,addquesBTN,
						   doneBTN,addtoexamBTN,createexamBTN;
			public JTextField addmajorTXT,addcourseTXT,addstudentidTXT,addstudentnameTXT,addprofidTXT,
							  addprofnameTXT,exerciseTXT,questionnoTXT,questionTXT,examanswerTXT,
							  gradeTXT;
			public JLabel addstudentidLBL,addstudentnameLBL,addprofidLBL,addprofnameLBL,examcoursesLBL,
						  sessionexamLBL,modalityLBL,exerciseLBL,questionnoLBL,questionLBL,examanswerLBL,
						  gradeLBL,errorLBL;
			public DefaultComboBoxModel<Course> examcoursesCMBMDL;
			public JComboBox<Course> examcoursesCMB;
			public DefaultComboBoxModel<SessionExam> sessionexamCMBMDL;
			public JComboBox<SessionExam> sessionexamCMB;
			public DefaultComboBoxModel<String> modalityCMBMDL;
			public JComboBox<String> modalityCMB;
			public JTable examTBL;
			public DefaultTableModel examTBLMDL;
			public  MainFrame mf;
			public ResultsPanel rp;
			public ProfessorPanel pn;
			public StudentsPanel stp;
	
	public EmployeePanel(MainFrame mf) {
		
		this.mf=mf;
		createexamtab=new JPanel();
	    addobjectstab=new JPanel();
	    createexamtab.setSize(800,600); 
	    createexamtab.setLayout(null);
	    addobjectstab.setSize(800,600); 
	    addobjectstab.setLayout(null);
	    
	    addTab("Set Exam", createexamtab);
		addTab("ADD", addobjectstab);
		
		
		addmajorBTN = new JButton("Add Major");
		 addobjectstab.add(addmajorBTN).setBounds(20, 30, 120, 25);
		 
		 addcourseBTN = new JButton("Add Course");
		 addobjectstab.add(addcourseBTN).setBounds(150, 30, 120, 25);
		 
		 addstudentBTN = new JButton("Add Student");
		 addobjectstab.add(addstudentBTN).setBounds(280, 30, 120, 25);
		 
		 addprofBTN = new JButton("Add Professor");
		 addobjectstab.add(addprofBTN).setBounds(410, 30, 120, 25);
		 
		 
		 backBTN = new JButton("BACK");
		 addobjectstab.add(backBTN).setBounds(600, 30, 140, 25);
		 
		 
		 addmajorTXT= new JTextField(20);
    	 addmajorTXT.setBounds(100,100,200,25);
    	 addobjectstab.add(addmajorTXT);
    	 addmajorTXT.setVisible(false);
    	 addthemajorBTN = new JButton("Add");
		 addobjectstab.add(addthemajorBTN).setBounds(100, 140, 120, 25);
		 addthemajorBTN.setVisible(false);
		 
		 addcourseTXT= new JTextField(20);
    	 addcourseTXT.setBounds(100,100,200,25);
    	 addobjectstab.add(addcourseTXT);
    	 addcourseTXT.setVisible(false);
    	 addthecourseBTN = new JButton("Add");
		 addobjectstab.add(addthecourseBTN).setBounds(100, 140, 120, 25);
		 addthecourseBTN.setVisible(false);
		 
		 addstudentidTXT= new JTextField(20);
    	 addstudentidTXT.setBounds(110,100,200,25);
    	 addstudentidLBL= new JLabel("Student ID");
    	 addobjectstab.add(addstudentidLBL).setBounds(20, 100, 120, 25);
    	 addstudentnameTXT= new JTextField(20);
    	 addstudentnameTXT.setBounds(110,140,200,25);
    	 addstudentnameLBL= new JLabel("Student Name");
    	 addobjectstab.add(addstudentnameLBL).setBounds(20, 140, 120, 25);
    	 addobjectstab.add(addstudentnameTXT);
    	 addobjectstab.add(addstudentidTXT);
    	 addstudentidTXT.setVisible(false);
    	 addstudentnameTXT.setVisible(false);
    	 addthestudentBTN = new JButton("Add");
		 addobjectstab.add(addthestudentBTN).setBounds(110, 180, 120, 25);
		 addthestudentBTN.setVisible(false);
		 addstudentidLBL.setVisible(false);
		 addstudentnameLBL.setVisible(false);
		 
		 addprofidTXT= new JTextField(20);
    	 addprofidTXT.setBounds(110,100,200,25);
    	 addprofidLBL= new JLabel("Prof ID");
    	 addobjectstab.add(addprofidLBL).setBounds(20, 100, 120, 25);
    	 addprofnameTXT= new JTextField(20);
    	 addprofnameTXT.setBounds(110,140,200,25);
    	 addprofnameLBL= new JLabel("Prof Name");
    	 addobjectstab.add(addprofnameLBL).setBounds(20, 140, 120, 25);
    	 addobjectstab.add(addprofnameTXT);
    	 addobjectstab.add(addprofidTXT);
    	 addprofidTXT.setVisible(false);
    	 addprofnameTXT.setVisible(false);
    	 addtheprofBTN = new JButton("Add");
		 addobjectstab.add(addtheprofBTN).setBounds(110, 180, 120, 25);
		 addtheprofBTN.setVisible(false);
		 addprofidLBL.setVisible(false);
		 addprofnameLBL.setVisible(false);
		 
		 examcoursesCMBMDL=new DefaultComboBoxModel<Course>();
			examcoursesLBL=new JLabel("Courses");
			examcoursesCMB=new JComboBox<Course>();
			examcoursesLBL.setBounds(20,30,85,25);
			examcoursesCMB.setBounds(80,30,110,25);
			createexamtab.add(examcoursesLBL);
			createexamtab.add(examcoursesCMB);
			 
		  populateempcourses();
				    	
			mf.majorCMB.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					
					examcoursesCMBMDL.removeAllElements();
					
					Major selectedmajor= (Major) mf.majorCMB.getSelectedItem();
					String directoryPath= selectedmajor.toString()+"_directory";
						Set<Course> coursesSET = loadCoursesFromFile(directoryPath);
						
						Iterator<Course> itec = coursesSET.iterator();
						while(itec.hasNext()) { examcoursesCMBMDL.addElement(itec.next());}
						examcoursesCMB.setModel(examcoursesCMBMDL);
			    	
				}
			});  
			 
			sessionexamCMBMDL= new DefaultComboBoxModel<SessionExam>();
			sessionexamLBL= new JLabel("Session");
			sessionexamCMB= new JComboBox<SessionExam>(sessionexamCMBMDL);
		    sessionexamLBL.setBounds(220,30,85,25);
		    sessionexamCMB.setBounds(270,30,110,25);
		    createexamtab.add(sessionexamLBL);
		    createexamtab.add(sessionexamCMB);
		    populatesessionex();
		    
		     modalityCMBMDL= new DefaultComboBoxModel<String>();
			 modalityLBL= new JLabel("Modalité");
			 modalityCMB= new JComboBox<String>(modalityCMBMDL);
			 modalityLBL.setBounds(400,30,85,25);
			 modalityCMB.setBounds(460,30,110,25);
			 createexamtab.add(modalityLBL);
			 createexamtab.add(modalityCMB);
			 populatemod();
			 
			 addexerciseBTN= new JButton("New Exercise");
				addquesBTN= new JButton("Add to Exam");
				doneBTN= new JButton("Done creating");
				addtoexamBTN= new JButton("View on list");
				addexerciseBTN.setBounds(20,100,130,25);
				addquesBTN.setBounds(170,100,130,25);
				doneBTN.setBounds(320,100,130,25);
				addtoexamBTN.setBounds(110,290,130,25);
				createexamtab.add(addexerciseBTN);
				createexamtab.add(addquesBTN);
				createexamtab.add(doneBTN);
				createexamtab.add(addtoexamBTN);
				
				exerciseLBL = new JLabel("Exercise"); exerciseLBL.setBounds(20,150,100,25);
				exerciseTXT = new JTextField(); exerciseTXT.setBounds(110,150,180,25);
				createexamtab.add(exerciseLBL);
				createexamtab.add(exerciseTXT);
				
				questionnoLBL= new JLabel("Question no:"); questionnoLBL.setBounds(20,180,100,25);
				questionnoTXT=new JTextField();questionnoTXT.setEnabled(false); questionnoTXT.setBounds(110,180,180,25);;
				createexamtab.add(questionnoLBL);
				createexamtab.add(questionnoTXT);
				
				questionLBL= new JLabel("Question"); questionLBL.setBounds(20,210,100,25);
				questionTXT=new JTextField(); questionTXT.setBounds(110,210,180,25);;
				createexamtab.add(questionLBL);
				createexamtab.add(questionTXT);
				
				examanswerLBL= new JLabel("Exp Answer"); examanswerLBL.setBounds(20,240,100,25);
				examanswerTXT=new JTextField(); examanswerTXT.setBounds(110,240,180,25);;
				createexamtab.add(examanswerLBL);
				createexamtab.add(examanswerTXT);
				
				gradeLBL= new JLabel("Grade"); gradeLBL.setBounds(20,270,100,25);
				gradeTXT=new JTextField(); gradeTXT.setBounds(110,270,70,25);;
				createexamtab.add(gradeLBL);
				createexamtab.add(gradeTXT);
				
				examtab= new JPanel();
				examtab.setSize(700,300);
			    examtab.setBounds(500,110,500,300);
			    
				Object[] columnNames = {"Ex name", "Quest no", "Question", "Max Grade"};
				Object[][] dataRows = {};
				examTBLMDL = new DefaultTableModel(dataRows, columnNames);
				examTBL = new JTable(examTBLMDL);
				examtab.add(examTBL);
				JScrollPane sp = new JScrollPane(examTBL);
				examtab.add(sp, BorderLayout.CENTER);
				TableColumn column1 = examTBL.getColumnModel().getColumn(0);
		        column1.setPreferredWidth(100);
		        TableColumn column2 = examTBL.getColumnModel().getColumn(1);
		        column2.setPreferredWidth(65);
		        TableColumn column3 = examTBL.getColumnModel().getColumn(2);
		        column3.setPreferredWidth(345);
		        TableColumn column4 = examTBL.getColumnModel().getColumn(3);
		        column4.setPreferredWidth(85);
				createexamtab.add(examtab);
				
				errorLBL= new JLabel("Please fill in all fields with the right characters.");
				errorLBL.setForeground(Color.RED);
				 Font font = new Font(errorLBL.getFont().getName(), Font.BOLD + Font.ITALIC, 14);
				 errorLBL.setFont(font);
				createexamtab.add(errorLBL).setBounds(65,310,400,50);
				errorLBL.setVisible(false);
				
				examtab.setVisible(false);
				addexerciseBTN.setVisible(false);
				addquesBTN.setVisible(false);
				doneBTN.setVisible(false);
				addtoexamBTN.setVisible(false);
				exerciseLBL.setVisible(false);
				exerciseTXT.setVisible(false);
				questionnoLBL.setVisible(false);
				questionLBL.setVisible(false);
				questionnoTXT.setVisible(false);
				questionTXT.setVisible(false);
				examanswerLBL.setVisible(false);
				examanswerTXT.setVisible(false);
				gradeLBL.setVisible(false);
				gradeTXT.setVisible(false);
				errorLBL.setVisible(false);
				
			 createexamBTN = new JButton("Set exam");
			 createexamtab.add(createexamBTN).setBounds(800, 30, 120, 25);
			 
			 MainController controller = new MainController(mf,this,rp,pn,stp);
			 EmployeeController emcont= new EmployeeController(this,mf);
			 
			 
	}
 public void  populateempcourses(){
	 
	 
	 examcoursesCMBMDL.removeAllElements();
		
		Major selectedmajor= (Major) mf.majorCMB.getSelectedItem();
		String directoryPath= selectedmajor.toString()+"_directory";
			Set<Course> coursesSET = loadCoursesFromFile(directoryPath);
			
			Iterator<Course> itec = coursesSET.iterator();
			while(itec.hasNext()) { examcoursesCMBMDL.addElement(itec.next());}
			examcoursesCMB.setModel(examcoursesCMBMDL);
 	
 }
 public void populatesessionex(){
	 SessionExam[] se= new SessionExam[3];
		se[0]= new SessionExam("Partiel");
		se[1]= new SessionExam("Final");
		se[2]= new SessionExam("Ratrappage");
		for(int i=0;i<se.length;i++) {
    		sessionexamCMBMDL.addElement(se[i]);
    	}
 }
 public void  populatemod(){
	 String[]mod= new String[3];
		mod[0]= new String("Ecrit");
		mod[1]= new String("Devoir");
		mod[2]= new String("Project");
		for(int i=0; i<mod.length;i++) {
    		modalityCMBMDL.addElement(mod[i]);
    	}
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
 
 public void update() {
	    populateempcourses();
		populatesessionex();
		populatemod();
		
	}
	
}
