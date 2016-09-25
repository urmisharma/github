import java.util.ArrayList;


public class Continent
{

    private String name;
    private int bonusArmies;
    private ArrayList<Country> memberCountries;

    public Continent( String name, int bonusArmies, ArrayList<Country> memberCountries )
    {
	name = this.name;
	bonusArmies = this.bonusArmies;
	this.memberCountries = new ArrayList<Country>();
    }

    public String getName()
    {
	return name;
    }

     //Returns the number of bonus armies a player gets per round when the player controls this continent 
    public int getBonusArmies()
    {
	return bonusArmies;
    }

     //Returns a list of the country objects that are on this continent
    public ArrayList<Country> getMemberCountries()
    {
	return memberCountries;
    }

}

