import java.util.ArrayList;

public class Hand
{

	private ArrayList<Card> hand;

	/**
	* No-arg constructor. Instantiate Deck.
	**/
	public Hand()
	{
		hand = new ArrayList< Card >();
	}

	/**
	* Adds the card to the hand 
	**/
	public Card add(Card card)
	{
		hand.add(card);
		
		return card;
	}
	/**
	* Removes the cards at the given indices from the hand
	**/
	public ArrayList<Card> removeCardsFromHand(int index1, int index2, int index3)
	{
		hand.remove(index1,index2,index3);
		
		return hand;
	}

	/**
	* returns true if the player can turn in cards
	**/
	public boolean canTurnInCards()
	{
		if //three cards of same design in hand
		{
			
		}
		else if //one of each design in hand
		{

		}
			return true;
	}

	/**
	* Returns true if the player must turn in cards
	**/
	public boolean mustTurnInCards()
	{
		if( hand >= 5 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	* Returns the hand
	**/
	public ArrayList<Card> getHand()
	{
		return hand;
	}
}

