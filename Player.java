import java.util.*;

public class Player {

    public enum Team { ONE(0), TWO(0); 
        private int score;

        Team(int score) {
            this.score = score;
        }

        public int getScore() { return this.score; }
        public void setScore(int score) { this.score = score; }
    }

    boolean dealer;
    ArrayList<Card> hand;
    Team team;

    public Player(boolean dealer, ArrayList<Card> hand, Team team) {
        this.dealer = dealer;
        this.hand = hand;
        this.team = team;
    }

    public void setDealer(boolean dealer) {
        this.dealer = dealer;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public Team getTeam() {
        return this.team;
    }

    public void addScore() {
        this.team.score += 1;
    }

    public boolean makeChoice() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Pass or Pick Up (N or Y): ");
        String answer = scanner.nextLine();

        if (answer.equals("N")) {
            return false;
        }
        return true;
    }

    public void dealerChoice(Card top) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your hand: " + this.hand);
        System.out.print("Select a card from your hand to remove (index number): ");
        int index = scanner.nextInt();
        this.hand.remove(index);
        this.hand.add(top);
        System.out.println("Your new hand is: " + this.hand);
    }
    
    public void makePlay(ArrayList<Card> currentPlay) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your hand: " + this.hand);
        System.out.print("Select the card you would like to play (index number): ");
        int index = scanner.nextInt();
        currentPlay.add(this.hand.get(index));
        this.hand.remove(index);
        System.out.println("Your new hand is: " + this.hand);
    }

    @Override public String toString() {
        return ("Dealer: " + this.dealer + "\nHand: " + this.hand + "\nTeam: " + this.team);
    }
}
