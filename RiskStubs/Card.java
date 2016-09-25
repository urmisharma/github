
public final class Card
{

    private final String type;
    private final Country country;

    public Card()
    {
	type = this.type;
	country = this.country;
    }

    public String getType()
    {
	return type;
    }

    public Country getCountry()
    {
	return country;
    }

    public String toString()
    {
	return country + "of type" + type;
    }

}

