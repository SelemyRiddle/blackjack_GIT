import java.io.*;
import java.util.*;
import java.util.Random;
public class Deck
{
    private ArrayList<Card> deck;
    public Deck()
    {
        deck= new ArrayList<Card>();
        initializeDeck();
    }
    public ArrayList<Card> initializeDeck(){
        String[] suits= { "Spades","Clubs","Hearts","Diamonds" };
        String[] ranks= { "Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King" };
        for (String s: suits){
            int value=1;
            for (String r: ranks){
                deck.add(new Card(s,r,value));
                value++;
            }
        }
        return deck;
    }
    public void printDeck(){
        for (Card c: deck){
            System.out.println(c);
        }
    } 
    public Card deal(){
        Random rand = new Random();
        int n = rand.nextInt(deck.size())+1;
        Card temp = deck.get(n);
        deck.remove(n);
        return temp;
    }
}