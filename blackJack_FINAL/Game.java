import java.io.*;
import java.util.*;
import java.util.Scanner;
public class Game
{
    private ArrayList<Player> players = new ArrayList<Player>();
    private Deck gameDeck = new Deck();
    private int gameNum=0;
    private int pot;
    Scanner sc= new Scanner(System.in);
    private Player dealer = new Player ("Dealer");
    private Player temp;
    public Game()
    {
        while (gameNum!=-1){
            if (gameNum==0){
                initializePlayers();
            }
            bet();
            dealHands();
            play();
            determineWinner();
            System.out.println("Do you want to stop playing?");
            if (sc.next().equals("yes")){
                endGame();
            }
            
        }
    }
    public void bet(){
        for (Player p: players){
            //p.printHand();
            System.out.println("Enter the amount of money you are going to bet: ");
            
            int value = sc.nextInt();
            p.bet(value);
            pot+=value;
        }
    }
    public void initializePlayers(){
        System.out.println("Enter the number of players: ");
        int numPlayers = sc.nextInt();
        //Player player1= new Player();
        for(int i =0 ; i < numPlayers; i++) {
            //Create a player
            //System.out.println("Enter name: ");
            temp = new Player();
            //Add to arrayList
            
            
            players.add(temp);
        }
        System.out.println("Enter amount of money each player will have: ");
        int amt = sc.nextInt();
        for (Player p: players){
            p.setMoney(amt);
        }
    }
    public void dealHands(){
        for (Player p: players){
            p.getPocket(gameDeck.deal(), gameDeck.deal());
            p.printHand();
        }
        dealer.getPocket(gameDeck.deal(), gameDeck.deal());
        System.out.println("The dealer shows: " +dealer.getHand().get(0).toString());
    }
    public void play(){
        for (Player p: players){
            System.out.println("Hit or Stay?");
            String answer= sc.next();
            if (answer.equals("Hit") || answer.equals("hit")){
                p.hit(gameDeck.deal());
                p.printHand();
                while (p.getHandTotal()<21){
                    System.out.print("Hit again?");
                    String answer2= sc.next();
                    if (answer2.equals("Hit") || answer2.equals("hit") || answer2.equals("yes") || answer2.equals("Yes")){
                        p.hit(gameDeck.deal());
                        p.printHand();
                    }
                }
            }
            
        }
        while (dealer.getHandTotal()<17){
            dealer.hit(gameDeck.deal());
            
        }
        
    }
    public void determineWinner(){
        ArrayList<Player> winners = new ArrayList<Player>();
        Player winner = players.get(0);
        for (Player p: players){
            p.toString();
            if (p.toString().equals("Less than or equal to 21")){
                if (p.getHandTotal()>winner.getHandTotal()){
                    winner=p;
                }
                
            }
            
        }
        for (Player p: players){
            if (p.getHandTotal()==winner.getHandTotal()){
                winners.add(p);
                
            }
        }
        if (winners.size()>0 ){
            for (Player w: winners){
                if (w.getHandTotal()>dealer.getHandTotal()){
                    System.out.println(w.getName() + " tied for the win!");
                    w.updateWinnings(pot/2);
                }
                else if (w.getHandTotal()==dealer.getHandTotal()){
                    System.out.println(w.getName() + " tied with the dealer for the win!");
                    w.updateWinnings(pot/2);
                }
            }
        }
        else{
            if (winner.getHandTotal()>dealer.getHandTotal()){
                System.out.println(winner.getName() + " won!");
                winner.updateWinnings(pot);
            }
            else{
                System.out.print("The dealer won");
            }
        }
        gameNum++;
    }
    public void endGame(){
        ArrayList<Player> grandWinners = new ArrayList<Player>();
        Player grandWinner= players.get(0);
        for (Player p: players){
            if (p.getTotalMoney()>grandWinner.getTotalMoney()){
                grandWinner=p;
            }
        }
        for (Player p: players){
            if (p.getTotalMoney()==grandWinner.getTotalMoney()){
                grandWinners.add(p);
                
            }
        }
        if (grandWinners.size()>0){
            for (Player w: grandWinners){
                System.out.println(w.getName()+" tied for the most winnings");
            }
        }
        else{
            System.out.println(grandWinner.getName()+ " had the most winnings");
        }
        gameNum=-1;
        //System.exit(0);
    }
}
