package notes;

	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;

	import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
	import javax.swing.JPanel;
	import javax.swing.JOptionPane;

	import java.util.ArrayList;
	import java.util.Date;
import java.util.List;

import javax.swing.JTabbedPane;
	import javax.swing.JInternalFrame;
	import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
	
	public class Notesapp extends JFrame
	{
	
		private GetPatient getPatient;
		private ArrayList<Patient> results;
		private Patient currentPatient;
		private int numberOfPatient =0;
		private int currentNumberPatient;
		
		//private RemovePatient removePatient;
		//private AddNotes addNotes;
		
		final static int MAX_QTY=10;

		static JLabel date;
		
		private JTable tablePatient;
		static JButton removePJButton;
		private JButton addPJButton;
		private JButton btnAddchange;
		
		
		private DefaultTableModel myPTableModel;
		private JPanel patients;
		private JPanel insertPat;
		private JLabel lblNewLabel;
		private JPanel removePat;
		
		private JTextField newName;
		private JTextField newPlace;
		private JTextField newWeeks;
		private JTextField newPara;
		private JTextField newAllergies;
		private JTextField newInfo;
		private JTextField newNotes;
		
		private JTextField newName2;
		private JTextField newPlace2;
		private JTextField newWeeks2;
		private JTextField newPara2;
		private JTextField newAllergies2;
		private JTextField newInfo2;
		private JTextField newNotes2;
		
		private JLabel lblName;
		private JLabel lblPlace;
		private JLabel lblWeeks;
		private JLabel lblPara;
		private JLabel lblAllergies;
		private JLabel lblInfo;
		private JLabel lblNotes;
		
		private JButton btnSave;
		private JButton btnInsertback;
		private JButton btnremoveP;
		private JButton btnBack;
		
		
		
	public Notesapp(){
				super("Patients");
				
		
				getPatient = new GetPatient();//this brings in the db connection
				getContentPane().setLayout(new CardLayout(0, 0));
				setBounds(200,200,677,640);//frame size, should it be smaller?
				setLocationRelativeTo(null);
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				
				patients = new JPanel();
				getContentPane().add(patients, "name_158265438115737");
				tablePatient = new JTable();
				tablePatient.setBounds(19, 49, 640, 370);
				
				
				
				//show todays date on the top
				Date today = new Date();
				patients.setLayout(null);
				date = new JLabel ("Today is: " + today.toString());
				date.setBounds(207, 17, 298, 20);
				patients.add(date);
				
				
				//creates the table for patients
				tablePatient.setModel(createPTableModel());
				// Mouselistner for table
				tablePatient.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent evt) {
						if(evt.getClickCount() ==2) {
							doubleClick(evt);
						}
					}
				});
				patients.add(tablePatient);
				
				//creates the button to get window to add patients
				addPJButton= new JButton();
				addPJButton.setBounds(261, 477, 145, 47);//siirrä
				addPJButton.setText("Add Patient");
				addPJButton.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent event2){
							if(results.size() < MAX_QTY){
								insertPat.setVisible(true);
								patients.setVisible(false);
								removePat.setVisible(false);
								}
								else{
									JOptionPane.showMessageDialog(null, "The Ward is full", "Info", JOptionPane.INFORMATION_MESSAGE);
									}
								}
			 			
			 		});
				addPJButton.setBackground(new Color(238, 238, 238));
				patients.add(addPJButton);
				
				lblNewLabel = new JLabel("To remove or edit a patient, double click patients name.");
				lblNewLabel.setBounds(160, 536, 404, 16);
				patients.add(lblNewLabel);
				
				insertPat = new JPanel();
				insertPat.setBounds(0, 0, 400, 483);
				getContentPane().add(insertPat, "name_158265449539768");
				insertPat.setLayout(null);
				
				newName = new JTextField(30);
				newName.setBounds(255, 57, 370, 26);
				insertPat.add(newName);
				
				newPlace = new JTextField(30);
				newPlace.setBounds(255, 99, 370, 26);
				insertPat.add(newPlace);
				
				newWeeks = new JTextField(30);
				newWeeks.setBounds(255, 143, 370, 26);
				insertPat.add(newWeeks);
				
				newPara = new JTextField(30);
				newPara.setBounds(255, 185, 370, 26);
				insertPat.add(newPara);
				
				newAllergies = new JTextField(30);
				newAllergies.setBounds(255, 226, 370, 26);
				insertPat.add(newAllergies);
				
				newInfo = new JTextField(30);
				newInfo.setBounds(255, 273, 370, 26);
				insertPat.add(newInfo);
				
				newNotes = new JTextField(30);
				newNotes.setBounds(255,319, 370, 26);
				insertPat.add(newNotes);
			
				
				lblName = new JLabel("Patients name here:");
				lblName.setBounds(22, 62, 131, 16);
				insertPat.add(lblName);
				
				
				lblPlace = new JLabel("Patiens place (1-10)");
				lblPlace.setBounds(22, 104, 126, 16);
				insertPat.add(lblPlace);
				
				lblWeeks = new JLabel("Weeks:");
				lblWeeks.setBounds(22, 148, 48, 16);
				insertPat.add(lblWeeks);
				
				
				lblPara = new JLabel("Para:");
				lblPara.setBounds(22, 190, 48, 16);
				insertPat.add(lblPara);
				
				
				lblAllergies = new JLabel("Allergies:");
				lblAllergies.setBounds(22, 231, 69, 16);
				insertPat.add(lblAllergies);
				
				
				lblInfo = new JLabel("Additional info:");
				lblInfo.setBounds(22, 278, 131, 16);
				insertPat.add(lblInfo);
				
				lblNotes = new JLabel("Notes:");
				lblNotes.setBounds(22, 324, 130, 16 );
				insertPat.add(lblNotes);
				
				//saves a new patient to the DB
				btnSave = new JButton("Save Patient info");
				btnSave.setBounds(255, 393, 145, 47);
				btnSave.addActionListener(
						new ActionListener()
						{
							public void actionPerformed(ActionEvent evt)
							{ 
								btnSaveActionPerformed (evt);
							}
						});
				insertPat.add(btnSave);
				
				//Goes back to main page
				btnInsertback = new JButton("Back");
				btnInsertback.setBounds(480, 393,145, 47);
				btnInsertback.addActionListener(
						new ActionListener()
						{
							public void actionPerformed(ActionEvent evt)
							{
								patients.setVisible(true);
								insertPat.setVisible(false);
								removePat.setVisible(false);
							}
						});
				insertPat.add(btnInsertback);
				
				JLabel lbladdInfo = new JLabel("Add patients information");
				lbladdInfo.setBounds(257, 16, 165, 16);
				insertPat.add(lbladdInfo);
				
				
				removePat = new JPanel();
				removePat.setBounds(0,0, 400, 500);
				getContentPane().add(removePat, "name_158265459834873");
				removePat.setLayout(null);
				
				removePat.setBounds(0, 0, 400, 483);
				getContentPane().add(removePat, "name_158265449539768");
				removePat.setLayout(null);
				
				newName2 = new JTextField(30);
				newName2.setBounds(168, 57, 370, 26);
				removePat.add(newName2);
				
				newPlace2 = new JTextField(30);
				newPlace2.setBounds(168, 98, 370, 26);
				removePat.add(newPlace2);
				
				newWeeks2 = new JTextField(30);
				newWeeks2.setBounds(168, 141, 370, 26);
				removePat.add(newWeeks2);
				
				newPara2 = new JTextField(30);
				newPara2.setBounds(168, 187, 370, 26);
				removePat.add(newPara2);
				
				newAllergies2 = new JTextField(30);
				newAllergies2.setBounds(168, 222, 370, 26);
				removePat.add(newAllergies2);
				
				newInfo2 = new JTextField(30);
				newInfo2.setBounds(168, 262, 370, 26);
				removePat.add(newInfo2);
				
				newNotes2 = new JTextField(30);
				newNotes2.setBounds(168,308, 370, 26);
				removePat.add(newNotes2);
			
				
				lblName = new JLabel("Patients name:");
				lblName.setBounds(22, 62, 131, 16);
				removePat.add(lblName);
				
				
				lblPlace = new JLabel("Patiens place (1-10)");
				lblPlace.setBounds(22, 103, 126, 16);
				removePat.add(lblPlace);
				
				
				lblWeeks = new JLabel("Weeks:");
				lblWeeks.setBounds(22, 146, 48, 16);
				removePat.add(lblWeeks);
				
				
				lblPara = new JLabel("Para:");
				lblPara.setBounds(22, 192, 48, 16);
				removePat.add(lblPara);
				
				
				lblAllergies = new JLabel("Allergies:");
				lblAllergies.setBounds(22, 227, 69, 16);
				removePat.add(lblAllergies);
				
				
				lblInfo = new JLabel("Additional info:");
				lblInfo.setBounds(22, 267, 131, 16);
				removePat.add(lblInfo);
				
				lblNotes = new JLabel("Notes:");
				lblNotes.setBounds(22, 313, 130, 16 );
				removePat.add(lblNotes);
				
				//Removes patient from the table and DB
				btnremoveP = new JButton("Remove patient");
				btnremoveP.setBounds(62, 370, 131, 47);
				btnremoveP.addActionListener(
						new ActionListener()
						{
							public void actionPerformed(ActionEvent evt)
							{
								removeButtonPerformed(evt);
								
							}
						}
						);
				removePat.add(btnremoveP);
				
				//Goes back to main page
				btnBack = new JButton("Back");
				btnBack.setBounds(398, 370,131, 47);
				btnBack.addActionListener(
						new ActionListener()
						{
							public void actionPerformed(ActionEvent evt)
							{
								patients.setVisible(true);
								insertPat.setVisible(false);
								removePat.setVisible(false);
							}
						});
				removePat.add(btnBack);
				
				//updates patient info
				btnAddchange = new JButton("Save changes");
				btnAddchange.setBounds(231, 370, 131, 47);
				btnAddchange.addActionListener(
						new ActionListener()
						{
							public void actionPerformed(ActionEvent evt)
							{
								changePerformed(evt);
							}
						});
				removePat.add(btnAddchange);
				
				JLabel lblRemoveOrUpdate = new JLabel("Remove patient or update patient information here");
				lblRemoveOrUpdate.setBounds(184, 17, 327, 16);
				removePat.add(lblRemoveOrUpdate);
				
				
				
				
	}
				

	private void btnSaveActionPerformed(ActionEvent evt)
				{
					//this saves the info from the insertPat window and when it's saved it empties the fields
					int result = getPatient.addPatient(newName.getText(), 
													newPlace.getText(), 
													newWeeks.getText(), 
													newPara.getText(), 
													newAllergies.getText(), 
													newInfo.getText(),
													newNotes.getText());
				   if (result == 1)
					   JOptionPane.showMessageDialog(this,"Patient added.", "Patient added",
							   JOptionPane.PLAIN_MESSAGE);
				   else JOptionPane.showMessageDialog(this, "Something went wrong, patient not added", 
										"something went wrong", JOptionPane.PLAIN_MESSAGE);
					
					tablePatient.setModel(createPTableModel());
					newName.setText("");
					newPlace.setText("");
					newWeeks.setText("");
					newPara.setText("");
					newAllergies.setText("");
					newInfo.setText("");
					newNotes.setText("");
					//makes the front page visible again
					patients.setVisible(true);
					insertPat.setVisible(false);
					removePat.setVisible(false);
				
				}
							
				
			//Opens the remove/update patient window(removePat) and brings the chosen patients info.
			private void doubleClick(MouseEvent evt)
			{
				patients.setVisible(false);
				insertPat.setVisible(false);
				removePat.setVisible(true);
				
				int row = tablePatient.getSelectedRow();
				int col = tablePatient.getSelectedColumn();
				results = getPatient.getAPatient(tablePatient.getValueAt(row,col).toString());
				numberOfPatient = results.size();
				
			if(numberOfPatient !=0)
			{
				currentNumberPatient = 0;
				currentPatient = results.get(currentNumberPatient);
				newName2.setText("" + currentPatient.getName());
				newPlace2.setText("" + currentPatient.getPlace());
				newWeeks2.setText("" + currentPatient.getWeeks());
				newPara2.setText("" + currentPatient.getPara());
				newAllergies2.setText("" + currentPatient.getAllergies());
				newInfo2.setText("" + currentPatient.getInfo());
				newNotes2.setText("" + currentPatient.getNotes());
				}
			else JOptionPane.showMessageDialog(this,"Try again", "Error", JOptionPane.PLAIN_MESSAGE);
				}
			
			
			//removes a patiet when remove button is pressed
			private void removeButtonPerformed(ActionEvent evt)
			{
					int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete?");
					if (result == JOptionPane.OK_OPTION)
					{
						String name = newName2.getText();
						getPatient.deletePatient(name);
						
						tablePatient.setModel(createPTableModel());
						patients.setVisible(true);
						insertPat.setVisible(false);
						removePat.setVisible(false);
						
					}
				}
			
			private void changePerformed(ActionEvent evt)
			{
				int result = getPatient.editPatient(
						newPlace2.getText(), 
						newWeeks2.getText(), 
						newPara2.getText(), 
						newAllergies2.getText(), 
						newInfo2.getText(),
						newNotes2.getText(),
						newName2.getText());
              
				if (result == 1)
                    JOptionPane.showMessageDialog(this,"Update done.", "Update added",
                    JOptionPane.PLAIN_MESSAGE);
               else JOptionPane.showMessageDialog(this, "Something went wrong", 
			            "something went wrong", JOptionPane.PLAIN_MESSAGE);

               		tablePatient.setModel(createPTableModel());
               		newName2.setText("");
               		newPlace2.setText("");
               		newWeeks2.setText("");
               		newPara2.setText("");
               		newAllergies2.setText("");
               		newInfo2.setText("");
               		newNotes2.setText("");
               		//makes the front page visible again
               		patients.setVisible(true);
               		insertPat.setVisible(false);
               		removePat.setVisible(false);

}
			
			private DefaultTableModel createPTableModel()
			{
				results = getPatient.getAllPatient();
				
				Object[][] data = new Object[results.size()][10];
				String[] columns = new String[] {"Name", "Place", "Weeks", "Para", "Allergies", "Info", "Notes"};
				
				for (int row=0; row<results.size(); row++){
					
					currentPatient = results.get(row);
					
					data[row][0] = currentPatient.getName();
					data[row][1] = currentPatient.getPlace();
					data[row][2] = currentPatient.getWeeks();
					data[row][3] = currentPatient.getPara();
					data[row][4] = currentPatient.getAllergies();
					data[row][5] = currentPatient.getInfo();
					data[row][6] = currentPatient.getNotes();
				}
				
				myPTableModel = new DefaultTableModel(data, columns)
						{
					@Override
					public boolean isCellEditable(int row, int column) //this disables cell editing
					{
						return false;
					}
					
				};
				
				return myPTableModel;
				
				}
					
					
				public static void main(String[] args) {
					EventQueue.invokeLater(new Runnable(){
						public void run(){
							try{
								Notesapp window = new Notesapp();//Notesapp
								window.setVisible(true);
							} catch(Exception e) {
								e.printStackTrace();
								}
						}
					});
				}
}
