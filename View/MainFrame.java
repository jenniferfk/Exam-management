package View;

import javax.swing.*;

import Controller.MainController;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import Model.*;
import View.*;

public class MainFrame extends JFrame implements MyObserver {
     public JComboBox<AcademicYear> ayCMB;
     public DefaultComboBoxModel<AcademicYear> ayCMBMDL;
     public JComboBox<Major> majorCMB;
     public DefaultComboBoxModel<Major> majorCMBMDL;
     public JLabel ayLBL, majorLBL;
     public JRadioButton studentRDB, professorRDB, employeeRDB, resultsRDB;
     public JButton newBTN;
	 public JButton loginBTN;
     public ButtonGroup personBG;
     public JPanel contentPanel;
     public TreeSet<Major> majorsset=new TreeSet<Major>();
     public StudentsPanel sp;
     public ProfessorPanel pn;
     public ResultsPanel rp;
     public EmployeePanel ep;
     
     
    public MainFrame() {
        super("Gestion d'examens");
        setSize(1100, 800);
        setLayout(null);
       
        // Academic Year
        ayCMBMDL = new DefaultComboBoxModel<>();
        ayLBL = new JLabel("Academic Year");
        ayCMB = new JComboBox<>(ayCMBMDL);
        ayLBL.setBounds(10, 10, 100, 30);
        ayCMB.setBounds(100, 10, 80, 25);
        add(ayLBL);
        add(ayCMB);
       
		populateay();
        // Major
        majorCMBMDL = new DefaultComboBoxModel<>();
        majorLBL = new JLabel("Major");
        majorCMB = new JComboBox<>(majorCMBMDL);
        add(majorLBL).setBounds(10, 45, 100, 30);
        add(majorCMB).setBounds(100, 45, 160, 25);
        Major m1= new Major("");
	   	Major m2= new Major("Biologie");
		Major m3= new Major("Informatique");
		majorsset.add(m1);majorsset.add(m2);majorsset.add(m3);
		populatemajors();
		
        studentRDB = new JRadioButton("Student", true);
        add(studentRDB).setBounds(30, 80, 80, 30);

        professorRDB = new JRadioButton("Professor");
        add(professorRDB).setBounds(110, 80, 90, 30);

        employeeRDB = new JRadioButton("Employee");
        add(employeeRDB).setBounds(200, 80, 90, 30);

        resultsRDB = new JRadioButton("Results");
        add(resultsRDB).setBounds(290, 80, 90, 30);

        personBG = new ButtonGroup();
        personBG.add(studentRDB);
        personBG.add(professorRDB);
        personBG.add(employeeRDB);
        personBG.add(resultsRDB);

       
        newBTN = new JButton("New");
        add(newBTN).setBounds(550, 50, 100, 50);
        loginBTN = new JButton("Log in");
        add(loginBTN).setBounds(700, 50, 100, 50);
       
       
        
        sp=new StudentsPanel(this);
        pn= new ProfessorPanel(this);
        ep= new EmployeePanel(this);
        rp=new ResultsPanel(this);
        
        sp.setVisible(false);
        pn.setVisible(false);
        ep.setVisible(false);
        rp.setVisible(false);
        
        
        add(sp).setBounds(20,120,1000,600);;
        add(pn).setBounds(20,120,1000,600);;
        add(ep).setBounds(20,120,1000,600);;
        add(rp).setBounds(20,120,1000,600);;
        
        
       
        
        MainController controller = new MainController(this,ep,rp,pn,sp);
    }
    

    public Set<Major> loadMajorsFromFile(String directoryPath) {
	    Set<Major> majors = new HashSet<>();
	    File directory = new File(directoryPath);
	    File[] files = directory.listFiles();

	    if (files != null) {
	        for (File file : files) {
	            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	                String majorName = reader.readLine();
	                Major major = new Major(majorName);
	                majors.add(major);
	            } catch (IOException e) {
	                System.out.println("An error occurred while loading majors: " + e.getMessage());
	            }
	        }
	    }

	    return majors;
	}

    

    
    public void populateay() {
    	 AcademicYear[]ay= new AcademicYear[2];
 		ay[0]= new AcademicYear(null);
 		ay[1]=new AcademicYear("2022-2023");
 		for(int k=0;k<ay.length;k++) {
    		ayCMBMDL.addElement(ay[k]);
    	}
    }
    public void populatemajors() {
    	String directoryPath = "majors_directory"; 
	    File directory = new File(directoryPath);
	    if(!directory.exists()) {
	    	Iterator<Major> itm = majorsset.iterator();
			while(itm.hasNext()) majorCMBMDL.addElement(itm.next());
			majorCMB.setModel(majorCMBMDL);
	    }
        Set<Major> loadedMajors = loadMajorsFromFile(directoryPath);
        for (Major major : loadedMajors) {
            majorCMBMDL.addElement(major);
        }

        majorCMB.setModel(majorCMBMDL);
    }
	
	public void update() {
		populateay();
		populatemajors();
		
	}
	public static void main(String[]args) {
		MainFrame m= new MainFrame();
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setVisible(true);
	}
}
