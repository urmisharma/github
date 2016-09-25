import java.util.Random;

public class Deck
{

	private Card[] deck;
	private static final int numOfCards = 42;
	private int presentCard;
	private static final Random randomNumbers = new Random();
	/**
	* Creates all 42 cards, one for each territory. Each card has either 
	* a type of Infantry, Cavalry, or Artillery. Ensure that the number of
	* Infantry, Cavalry, and Artillery are the same
	**/
	public Deck()
	{       
		Country[] country;
		String[] type = {"Calvary","Infantry","Artillery"};
		deck = new Card[numOfCards];
		presentCard = 0;
	}

	public Card draw()//draws card from deck
	{
		if (presentCard < deck.length)
		{
			return deck[presentCard++]; //return current Card in array
		}
		else
		{
			return null; //if all cards were dealt and none remaining
		}
	}

	public void add(Card card) //adds card to deck
	{
		deck.add(card);
	}

	public void shuffle() //shuffles deck of cards
	{
		presentCard = 0;

		for(int firstCard = 0; firstCard < deck.length; firstCard++)
		{
			int secondCard = randomNumbers.nextInt( numOfCards);
			Card swap = deck[firstCard];
			deck[firstCard] = deck[secondCard];
			deck[secondCard] = swap;
		}
	}

}

