import java.util.Random;

/**
 * Wrapper class for class Random to make handeling dice rolling easier
 **/
public class Dice
{

    private static final Random randomNumbers;

    public Dice()
    {
	Random randomNumbers = new Random(); //random number generator
    }


    /**
     * Returns an integer array of values between 1 and 6 representing the
     * outcome of rolling the dice.  The number of values in the array should be
     * betwen 1 and 3, depending on the number of dice rolled by the player.  The 
     * number of dice rolled by the player is specified by the argument numberOfDice
     **/
    public int[] rollDice( int numberOfDice )
    {
	int[] dice = new int[numberOfDice];

	for (int index1 = 0; index1 < numberOfDice; index1++) 
	{
		dice[index1] = randomNumbers.nextInt( 6 );
	}

	// Sorts the array
	for (int index2 = 0; index2 < numberOfDice - 1; index2++) 
	{
		int temp;
		int position = index2;

		for(int index1 = index2 + 1; index1 < numberOfDice; index1++)
		{
			if(dice[index1] > dice[position])
				position = index1;
		temp = dice[index2];
		dice[index2] = dice[position];
		dice[position] = temp;
		}
	}
	return dice;

    }


}

