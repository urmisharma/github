import java.util.ArrayList;
import java.util.HashMap;


public class Player
{

    private String name;
    private int color; //color player uses in GUI
    private int numberArmies;
    private HashMap<String,Country> countriesHeld;
    private HashMap<String,Continent> continentsHeld;
    private ArrayList<Card> riskCards;

    public Player( String name, int numberArmies, int c )
    {
	this.countriesHeld = new HashMap< String,Country >();
	this.continentsHeld = new HashMap< String,Continent >();
	this.riskCards = new ArrayList< Card >();
	name = this.name;
	numberArmies = this.numberArmies;
	color = c;
	
    }

    /**
     * Decreases the count of the number of armies the player has on the board
     * This count has to reflect the actual number of armies the players has on 
     * the board
     **/
    public String toString()
    {
	return name;
    }

    public void decrementArmies( int numArmiesLost )
    {
	this.numberArmies = this.numberArmies - numArmiesLost;
    }


    /**
     * Increases the count of the number of armies the player has on the board
     * This count has to reflect the actual number of armies the players has on 
     * the board
     **/
    public void incrementArmies( int numArmiesGained )
    {
	
	this.numberArmies = this.numberArmies + numArmiesGained;
    }


    /**
     * When a player conquers a country the country needs to be added to a data structure
     * that keeps track of all the countries the player occupies
     **/
    public void addCountry( Country country )
    {
	this.countriesHeld.put("country", country);
    }


    /**
     * Works like addCountry above, but can be used to add multiple countries at once
     **/
    public void addCountry( ArrayList<Country> countriesList )
    {
	this.countriesHeld.put(countriesList);
    }


    /**
     * When a player loses a country, the country must be removed from the data structure
     * tracking which countries the player occupies
     **/
    public void removeCountry( String countryName )
    {
	this.countriesHeld.remove(countryName);
    }


    /**
     * When a player occupies all the countries on a continent the continent must be added to
     * a data structure that tracks which continents the player occupies
     **/
    public void addContinent( Continent continent )
    {
	this.continentsHeld.put("continent", continent);
    }

    /**
     * Removes a continent from the data structure to reflect that the player no longer controls
     * the whole continent
     **/
    public void removeContinent( String continentName )
    {
	this.continentsHeld.remove(continentName);
    }

    
    /**
     * Adds a risk card to the players hand
     **/
    public void addRiskCard( Card riskCard )
    {
	this.riskCards.add(riskCard);
    }


    /**
     * Removed a set of risk cards from the players hand to reflect risk cards being turned in
     **/
    public void removeCards( int[] cardsTurnedInIndex )
    {
	this.riskCards.remove(cardsTurnedInIndex);
    }
    
    public int getColor() 
    {
	return color;
    }

    public void setColor(int c)
    {
	color = c;
    }
}

