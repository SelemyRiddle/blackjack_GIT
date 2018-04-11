import java.io.*;
import java.util.*;
import java.util.Scanner;
public class Player
{
    String name;
    int totalMoney;
    int handTotal;
    private ArrayList<Card> hand = new ArrayList<Card>();
    boolean allIn;
    Scanner sc= new Scanner(System.in);
    
    public Player() {
        setName();
        //name=nm;
    }
    public Player(String n) {
        name = n;
        //name=nm;
    }
    public void setName(){
        System.out.println("Enter your name: ");
        name=sc.next();
    }
    public int getTotalMoney(){
        return totalMoney;
    }
    public void setMoney(int m){
        totalMoney=m;
    }
    public ArrayList getHand(){
        return hand;
    }
    public void printHand(){
        for (Card c: hand){
            System.out.println(c.toString());
        }
    }
    public void bet(int amnt){
        totalMoney-=amnt;
        if ((totalMoney-=amnt) > 0){
            allIn=false;
        }
        else{
            allIn=true;
        }
    }
    public void hit(Card c){
        hand.add(c);
        
    }
    public String getName(){
        return name;
    }
    public void getPocket(Card c, Card d) {
        hand.add(c);
        hand.add(d);
    }   
    public int getHandTotal(){
        
        handTotal=0;
        for (Card c: hand){
            if (c.getValue()!=1){
                handTotal+= c.getValue();
            }
            else{
                System.out.println("Play Ace as 1 or 11?");
                int aceValue= sc.nextInt();
                handTotal+= aceValue;
            }
        }
        return handTotal;
    }
    public void updateWinnings(int pot){
        totalMoney+=pot;
    }
    public String toString(){
        if (getHandTotal()<22){
            return "Less than or equal to 21";
        }
        else{
            return "You busted";
        }
    }
}
