package notes;

public class Patient {
	

private String Name;
private String Place;
private String Weeks;
private String Para;
private String Allergies;
private String Info;
private String Notes;


Patient(String aName, String aPlace, String aWeeks, String aPara, String aAllergies,
		String aInfo, String aNotes) 
{
	this.Name = aName;
	this.Place = aPlace;
	this.Weeks = aWeeks;
	this.Para = aPara;
	this.Allergies = aAllergies;
	this.Info = aInfo;
	this.Notes = aNotes;
}

public String getName()
{
	return this.Name;
}

public String getPlace()
{
return this.Place;
}

public String getWeeks()
{
return this.Weeks;
}

public String getPara()
{
 return this.Para;
}

public String getAllergies()
{
	return this.Allergies;
}

public String getInfo()
{
	return this.Info;
	}
public String getNotes()
{
	return this.Notes;
}
}


