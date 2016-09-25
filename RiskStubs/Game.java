import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class RiskGame implements Serializable
{
	public final static int STATE_NEW_GAME        = 0;
	public final static int STATE_TRADE_CARDS     = 1;
	public final static int STATE_PLACE_ARMIES    = 2;
	public final static int STATE_ATTACKING       = 3;
	public final static int STATE_ROLLING         = 4;
	public final static int STATE_BATTLE_WON      = 5;
	public final static int STATE_FORTIFYING      = 6;
	public final static int STATE_END_TURN        = 7;
	public final static int STATE_GAME_OVER       = 8;
	public final static int STATE_DEFEND_YOURSELF = 9;

	private int gameState; //gameState is game action

	private Country attacker; //attacker is a kind of player
	private Country defender;// defender is a kind of player

	private int attackerDice; //dice for attacker
	private int defenderDice;// dice for deffender
	private int maxDefendDice; //maximum of dice defenser can use
	private Player currentPlayer;// setting currentplayer
	private ArrayList<Card> riskCards;//arraylist of riskCards

	public RiskGame()
	{
		riskCards = new ArrayList<Card>();//initializing riskcards
		Player currentPlayer = "";// setting currentplayer null
	}

	
	public void newRiskGame()
	{
		
	// here will be GUI work for loading new game with the settings of GUI

	}
	
	
	public void startRiskGame()
	{
		// GUI work for staring game and taking maps		
		//loads map and cards
		//Give players their set number of cards and all that GUI things.
	}
	
	//loading  a already saved game of type 
	public static RiskGame loadRiskGame(String file) throws Exception
	{
		RiskGame game = "";// making game default null
		try// try block for reading saved game
		{
			InputStream is = RiskUtil.getLoadFileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(is);
			game = (RiskGame) ois.readObject();
			ois.close();
		}//end of try block
		catch(Exception e)//catch the exception
		{
 		 	e.printStackTrace();
 		}//end of catch block
		
		return game;

	}//end of loadRiskGame method
	
	//mathod for closing current game
	public static RiskGame closeRiskGame() 
	{
		RiskGame game = "";
		return game;
	}//end of close method
	
	//adding players to the Game as Game starts
	// there will be GUI work
	// addplayer method of type boolean which takes name and color for players
	public boolean addPlayer(String name, int color) 
	{
	//if game satatus is new game then choosing players,colors		
		if (gameState==STATE_NEW_GAME ) 
		{ 
	//making for loop unless counter is less than players size will add 		//players and their colors 
		for (int counter = 0; counter < Players.size() ; counter++) 
		{
			if (( name.equals(((Player)Players.elementAt(counter)).getName() )) || (color == ((Player)Players.elementAt(counter)).getColor() ))
				{
					return false;
				}
		}

			Player player = new Player(name, color);
			Players.add(player);
			return true;
		}
		else 
		{
			return false;
		}
	}

	// boolean method for deleting the player from the game which takes string
	public boolean deletePlayer(String name)
	{
		if (gameState==STATE_NEW_GAME)
		{
			int n = -1;

		for (int counter = 0; counter < Players.size() ; counter++)
			{
			if (name.equals( ((Player)Players.elementA(counter)).getName() ))
				{
					n = counter;
				}
			}
			else if  (n == -1) 
			{
				return false;
			}
			else if 
			{
				Players.removeElementAt(n);
				Players.trimToSize();
				//System.out.print("Player removed\n"); // testing
				return true;
			}
		}
		else
		{
			return false;
		}

	}// end of delete player method

	// method which returns num of players playing game
	public int getNumberOfPlayers() 
	{
		return Players.size();
	}// end of getNumberOfPlayers method

	//Sets the current player in the game out of the number of players total
	public Player setCurrentPlayer(int numOfPlayers) 
	{
                currentPlayer = (Player)Players.get(numOfPlayers);
		
		return currentPlayer;

	}// end of setCurrentPlayer method
	
	// returns the state of the game
	public int getGameState() 
	{
		return gameState;
	}

	/**
	*May only attack a country that is adjacent.
	*Must always have AT LEAST 2 armies in the territory you're attacking from
	*Can attack as many territories you like during 1 turn.
	*first: Announce the territory you're attacking and attacking from
	*second: choose the # of dice you roll(must have 1 more army than # of dice
	*the defender will role 1/2 white dice(must have 2 armies to roll 2 dice)
	*determine who wins and controls country (if tie defender wins)
	*end by passing dice or fortifying position then passing dice
	**/
	public boolean attack(Country territory1, Country terrritory2)
	{
		boolean result = false;
		if(gameState == STATE_ATTACKING)
		{
			if(territory1 != null && territory2 != null territory1.getOccupant() == currentPlayer && territory2.getOccupant()!= currentPlayer && 
			   territory1.getAdjacencies(territory2) && territory1.getArmies() > 1 )
			{
				result=true;

				attacker = terrritory1;
				defender = territory2;
				gameState=STATE_ROLLING;
			}
		}
		return result;
		
	}
	
	/**
	*move as many armies as you like from ONE of your territories into ONE other
	*must leave at least one army behind
	**/
	public boolean endAttack()
	{
		if (gameState == STATE_ATTACKING) 
		{ // if we were in the attack phase

			gameState = STATE_FORTIFYING; // go to move phase
			return true;
		}
		return false;
	}

	public boolean moveArmy(Country t1, Country t2, int numOfArmies) 
	{
		if (gameState==STATE_FORTIFYING)
		{
		// do they exist //check if they belong to the player //check if they are neighbours //check if there are enough troops in country1
			if (t1!=null && t2!=null && t1.getOccupant() == currentPlayer && t2.getOccupant() == currentPlayer t1.getAdjacencies(t2) && 
			    t1.getArmies() > numOfArmies && numOfArmies > 0) 
			{
				t1.removeArmies(numOfArmies);
				t2.addArmies(numOfArmies);
				gameState=STATE_END_TURN;

				checkIfPlayerWon();
				return true;

			}
		}
		return false;

	}

	  //Choosing not to use the tactical move and moves to the end phase
	public boolean noMove() {

		if (gameState==STATE_FORTIFYING) 
		{ // if we were in the move phase
			gameState=STATE_END_TURN; // go to end phase
			return true;
		}
		else return false;

	}
	
	public boolean rollAttackerDice(int dice1) 
	{

		if (gameState==STATE_ROLLING) 
		{ // if we were in the attacking phase

			if ( attacker.getArmies() > 4 ) 
			{
				if (dice1 <= 0 || dice1 > 3)
				{ 
				return false;
				}
			}
			else 
			{
				if (dice1 <= 0 || dice1 > (attacker.getArmies()-1) ) 
				{
					return false;
				}
			}

			attackerDice = dice1; // 5 2 0

			currentPlayer = defender.getOccupant();
			gameState = STATE_DEFEND_YOURSELF;
			return true;

		}
		else return false;

	}

	public boolean rollDefenderDice(int dice2) 
	{
		if (gameState == STATE_DEFEND_YOURSELF) 
		{ // if we were in the defending phase

			if ( defender.getArmies() > maxDefendDice )
			{
				if (dice2 <= 0 || dice2 > maxDefendDice)
				{
					return false;
				}
			}
			else 
			{
				if (dice2<=0 || dice2> (defender.getArmies()) )
				{
					return false;
				}
			}

			currentPlayer=attacker.getOwner();
			defenderDice = dice2; // 4 3
			return true;
		}
		else
		{
			return false;
		}

	}

	public int getAttackerDice() 
	{
		return attackerDice;
	}// end returning attacker dice

	public int getDefenderDice() 
	{
		return defenderDice;
	}//end of returning defender dice

	/**
	*returns a boolean if player has won by conquering all 42 countries
	*42 territories
	**/
	public boolean checkIfPlayerWon()
	{
		
		boolean result=false;

		// check if the player has won
		int won=0;
		for (int counter = 0; counter < Continents.length; counter++) 
		{

			if ( Continents[counter].getOccupant(currentPlayer) ) 
			{
				won++;
			}// end of if statement

		}// end of for loop
		if (won == Continents.length ) 
		{

			result=true;
		}
		else
		{
			result = false;
		}
	}// end of checking winner method
	
	//Saves Game at set point (work with GUI)
	public void saveRiskGame(OutputStream file) throws Exception 
	{ 
            ObjectOutputStream oos = new ObjectOutputStream(file);
            writeObject(this);
            close();
	}
}
