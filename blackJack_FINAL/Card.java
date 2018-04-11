
public class Card
{
    String suit;
    String rank;
    int value;
    public Card(String s, String r, int v)
    {
        // initialise instance variables
        suit = s;
        rank = r;
        value = v;
    }

    public String getSuit(){
        return suit;
    }
    public String getRank(){
        return rank;
    }
    public int getValue(){
        return value;
    }
    public String toString(){
        return rank+" of "+suit;
    }
}
