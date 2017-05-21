package notes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetPatient {
	
// tällä yhdistyy sql potilailla
		
		private static final String URL = "jdbc:mysql://localhost:3306/notes";
		private static final String USERNAME = "root";
		private static final String PASSWORD = "";

		private Connection connection = null;
		private PreparedStatement selectAllPatient = null;
		private PreparedStatement insertPatient = null;
		private PreparedStatement chosenPatient = null;
		private PreparedStatement deletePatient = null;
		private PreparedStatement editPatient = null;
		
		public GetPatient()
		{
			try
			{
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // Starts a connection to the database
				selectAllPatient = connection.prepareStatement("SELECT * FROM patients"); // Prepare the select query that gets all fromdatabase
				chosenPatient = connection.prepareStatement("SELECT * FROM patients WHERE name =?");
				// Here you will need to prepare the insert query
				insertPatient = connection.prepareStatement("INSERT INTO patients VALUES (?,?,?,?,?,?,?)");
				deletePatient = connection.prepareStatement("DELETE FROM patients WHERE name =?");
				editPatient = connection.prepareStatement("UPDATE patients SET Place = ?, Weeks = ?, Para =?, Allergies = ?, Info = ?, Notes = ? WHERE name = ?");
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
				System.exit(1);
			}
		}
		
		public ArrayList<Patient> getAllPatient() //arraylist?
		{
			ArrayList<Patient> results = null;
			ResultSet resultSet = null;
			
			try
			{
				resultSet = selectAllPatient.executeQuery(); // Here is where we actually execute the select query. resultSet contains the rows returned by the query
				results = new ArrayList<Patient>();
			
				while(resultSet.next()) // for each row returned by the select query...
				{
					results.add(new Patient(
						resultSet.getString("Name"),
						resultSet.getString("Place"),
						resultSet.getString("Weeks"),
						resultSet.getString("Para"), 
						resultSet.getString("Allergies"),
						resultSet.getString("Info"),
						resultSet.getString("Notes")));
				}
			} // end try
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
			finally
			{
				try
				{
					resultSet.close();
				}
				catch (SQLException sqlException)
				{
					sqlException.printStackTrace();
				}
			} // end finally
			
			return results;
		} // end method getAllPatients
		
		
		
		
		 //Method that inserts a new Patient in the database
		 
		public int addPatient(String Name, String Place, String Weeks, String Para, 
				String Allergies, String Info, String Notes)
		{
			int result = 0;
			
			try
			{
				// Setting the values for the question marks '?' in the prepared statement
				insertPatient.setString(1, Name);
				insertPatient.setString(2, Place);
				insertPatient.setString(3, Weeks);
				insertPatient.setString(4, Para);
				insertPatient.setString(5, Allergies);
				insertPatient.setString(6, Info);
				insertPatient.setString(7, Notes);
				
				result = insertPatient.executeUpdate(); 
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
			return result;
		}
		
		//choose a patient from database
		public ArrayList<Patient> getAPatient(String name)
		{
			ArrayList<Patient> results = null;
			ResultSet resultSet = null;
			
			try
			{
			chosenPatient.setString(1, name);
			
			resultSet = chosenPatient.executeQuery();
			
			results = new ArrayList<Patient>();
			
			while (resultSet.next())
			{
				results.add(new Patient(
						resultSet.getString("name"), 
						resultSet.getString("place"), 
						resultSet.getString("weeks"), 
						resultSet.getString("para"), 
						resultSet.getString("allergies"),
						resultSet.getString("info"),
						resultSet.getString("notes")));
			}
	}
	catch (SQLException sqlException)
	{
		sqlException.printStackTrace();
	}
	finally
	{
		try
		{
			resultSet.close();
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	} 
	return results;
} 
		
		//remove a patient
		public int deletePatient (String name)
		{
				int result = 0;
			
			try
			{
				deletePatient.setString(1,name);
				deletePatient.executeUpdate();
				
				result = deletePatient.executeUpdate();
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
			return result;
		}
		
 public int editPatient (String place, String weeks, String para, 
		 String allergies, String info, String notes, String name)
 {
	 int result = 0;
	 try
		{
			// Setting the values for the question marks '?' in the prepared statement
			editPatient.setString(1, place);
			editPatient.setString(2, weeks);
			editPatient.setString(3, para);
			editPatient.setString(4, allergies);
			editPatient.setString(5, info);
			editPatient.setString(6, notes);
			editPatient.setString(7, name);
				
			result = editPatient.executeUpdate(); 
			}
	 catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		return result;
	}
	 
 }
	
		
	

