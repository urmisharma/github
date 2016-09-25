import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Board
{
	private HashMap<String, Country>  countries;
	private HashMap<String, Continent>  continents;

	public Board()
	{
		this.countries = new HashMap< String, Country >();
		this.continents = new HashMap< String, Continent >();
	}


    /**
     * Loads the information needed to contruct the board and constructs the country and continent obects
     * needed for the board from three files.  The first file lists all the countries.  The second file lists 
     * all of the continents and which countries are on a given continent.  The third file lists the adjacencies 
     * for each country.
     **/
	public boolean loadBoard( String countriesFile, String continentsFile, String adjacenciesFile ) throws FileNotFoundException
	{
		try
		{
			Scanner input = new Scanner( new File( "continentsFile" ) );
			input.useDelimiter( "Continent, BonusArmies, Country \n" );
			System.out.println(input.next());
			System.out.println(input.nextInt());
			System.out.println(input.skip("Country"));
			input.close();
		
		}
		catch ( FileNotFoundException fileNotFoundException )
		{
			System.err.println( "File cannot be found." );
		}
		try
		{
			Scanner input = new Scanner( new File( "countriesFile" ) );
			input.useDelimiter( "Country \n" );
			System.out.println(input.next());
			input.close();
		}
		catch ( FileNotFoundException fileNotFoundException )
		{
			System.err.println( "File cannot be found." );
		}
		try
		{	//open file to read
			Scanner input = new Scanner( new File( "adjacenciesFile" ) );
			input.useDelimiter( "Country, adjacentCountries \n " );
			System.out.println(input.next());
			System.out.println(input.skip("adjacentCountries"));
			input.close();


		}
		catch ( FileNotFoundException fileNotFoundException )
		{
			System.err.println( "File cannot be found." );
		}

	}
    
         //Returns a list containing the continent objects the board has 
	public ArrayList<Continent> getContinents()
	{
		ArrayList<Continent> continents = new ArrayList<Continent>();
		return continents;
	}


    /**
     * Returns the continent object whose name is the string continentName
     **/
        public Continent getContinentByName( String name )
        {
		Continent continent;
		return continent;
        }


    /**
     * Returns the number of bonus armies awarded to a player for controlling all the countries in
     * the continent whose name is the string continentName
     **/
        public int getBonusArmies( String name )
	{
		int bonusArmies = 0;
		return bonusArmies;
        }


    /**
     * Returns a list of the country objects that are in the continent specified 
     * by the string continentName
     **/
	public ArrayList<Country> getMemeberCountries( String name )
        {
		ArrayList<Country> memberCountries = new ArrayList<Country>();
		return memberCountries;
        }


    /**
     * Returns a list of all of the country objects in the board
     **/
       public ArrayList<Country> getCountries()
       {
		ArrayList<Country> countries = new ArrayList<Country>();
    		return countries;
       }


    /**
     * Returns the country object for the country specified by the string
     * countryName
     **/
       public Country getCountryByName( String name )
       {
		return name.getName();
       }


    /**
     * Sets the occupant of the country object specified by the string countryName
     * to be the player object supplied as an argument.
     **/
	public void setOccupant( String name,  Player occupant )
	{
		name = occupant;
	}	


    /**
     * Returns the player object that currently occupies the country specified by
     * string countryName
     **/
	public Player getOccupant( String name )
	{
		Player occupant;
		return occupant;
	}


    /**
     * Sets the number of armies currently in the country specified by the string
     * countryName to the integer supplied as an argument
     **/
	public void setNumberArmies( String countryName, int numberArmies )
	{
		countryName = numberArmies;
	}


    /**
     * Returns the number of armies currently in the country specified by the string
     * countryName
     **/
	public int getNumberArmies( String countryName )
	{
		int numberArmies = 0;
		return numberArmies;
	}


    /**
     * Returns a list of the country objects that are the countries adjacent to the country
     * specified by the string countryName on the board
     **/
	public ArrayList<Country> getAdjacencies( String countryName )
	{
		ArrayList<Country> adjacencies = new ArrayList<Country>();
		return adjacencies;
	}

}

