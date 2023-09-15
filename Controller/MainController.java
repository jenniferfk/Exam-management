package Controller;
import View.*;

import java.awt.event.*;

import javax.swing.*;



public class MainController {
	public MainFrame main;
	public EmployeePanel epp;
	public ResultsPanel rpp;
	public ProfessorPanel pnn;
	public StudentsPanel spp;
	
	
	public MainController(MainFrame main,EmployeePanel ep,ResultsPanel rp,ProfessorPanel pn,StudentsPanel sp) {
        this.main=main;
        JButton newBTN= main.newBTN;
         this.epp=ep;
         this.rpp=rp;
         this.pnn=pn;
         this.spp=sp;
        
        
        
        newBTN.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			 if (sp != null && pn!=null && rp!=null && ep!=null) {
    			ep.setVisible(false);
    			pn.setVisible(false);
    			rp.setVisible(false);
    			sp.setVisible(false);}
    			main.studentRDB.setSelected(true);
    			main.majorCMB.setEnabled(true);
    			main.ayCMB.setEnabled(true);
    			main.studentRDB.setEnabled(true);
    			main.professorRDB.setEnabled(true);
    			main.employeeRDB.setEnabled(true);
    			main.resultsRDB.setEnabled(true);
    			main.majorCMB.setSelectedIndex(0);
    			main.ayCMB.setSelectedIndex(0);
    			
    			
    		}
    	});
        JButton loginBTN=main.loginBTN;
        loginBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( main.majorCMB.getSelectedItem()!=null && main.ayCMB.getSelectedItem()!=null) {
				if(main.studentRDB.isSelected()) {
					 if (sp != null) {
		                    sp.setVisible(true);
		                    
		                }
					
				}
				else if(main.professorRDB.isSelected()) {
					if (pn != null) {
	                    pn.setVisible(true);
	                   
	                    
	                }
					
				}
				else if(main.employeeRDB.isSelected()){
					if (ep != null) {
	                    ep.setVisible(true);
	                    
	                    
	                }
					
				}
				else if(main.resultsRDB.isSelected()){
					if (rp != null) {
	                    rp.setVisible(true);
	                    
	                    
	                }
				}
			
				main.majorCMB.setEnabled(false);
				main.ayCMB.setEnabled(false);
				main.studentRDB.setEnabled(false);
				main.professorRDB.setEnabled(false);
				main.employeeRDB.setEnabled(false);
				main.resultsRDB.setEnabled(false);
				}		
				
			}
		});

    }
	
	
}
