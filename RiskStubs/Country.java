import java.util.ArrayList;


public class Country
{

    private String name;
    private Player occupant;
    private int numOfArmies;
    private ArrayList<Country> adjacencies;

    public Country( String name )
    {
	name = this.name;
	Player occupant = this.occupant;
	int numOfArmies = this.numOfArmies;
	adjacencies = new ArrayList<Country>();
    }

     //Used only when constructing the country object, it should not be called after the board is initialized
    public void addAdjacencies( ArrayList<Country> adjacencies )
    {
	Country country;
	country.add(adjacencies)

    }

    public String getName()
    {
	return name;
    }

     //When a player conquers a country the player object is set as the occupant of the country
    public void setOccupant( Player currentOccupant )
    {
	this.occupant = currentOccupant;
    }

     //Returns the player object who currently occupies the country
    public Player getOccupant()
    {
	return occupant;
    }

     //Used to set the number of armies currently stationed in this country
    public void setNumArmies( int numArmies )
    {
	this.numOfArmies = numArmies;
    }

     //Returns the number of armies currently stationed in this country
    public int getNumArmies()
    {
	return numOfArmies;
    }

    //adds multiple armies at once
    public void addArmies(int n) 
    {
	this.numOfArmies = this.numOfArmies + n;
    }

    //removes defeated armies
    public void removeArmies(int lessArmies)
    {
	this.numOfArmies = this.numOfArmies - lessArmies;
    }
    
    public int getArmies() 
    {
	return this.numOfArmies;
    }
     //Returns a list of the country objects that are adjacent to this country on the board
    public ArrayList<Country> getAdjacencies()
    {
	return adjacencies;
    }
}

