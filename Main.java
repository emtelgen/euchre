import java.util.*;

public class Main {
    public static void main(String[] args) {

        boolean cont = true;
        boolean odd = true;
        while (cont) {
        //Make the deck
            ArrayList<Card> cardDeck = Deck.makeDeck();

            //Deal
            ArrayList<Card> dealerPile = Deck.makePile(cardDeck);
            
            ArrayList<Player> playerList = new ArrayList<>();

            for ( int i = 0; i < 4; i ++) {
                ArrayList<Card> playerHand = Deck.makeHand(cardDeck);
                if ((i % 2) == 0) {
                    if (odd) {
                        playerList.add(new Player(false, playerHand, Player.Team.ONE));
                    }
                    else {
                        playerList.add(new Player(false, playerHand, Player.Team.TWO));
                    }
                }
                else {
                    if (odd) {
                        playerList.add(new Player(false, playerHand, Player.Team.TWO));
                    }
                    else {
                        playerList.add(new Player(false, playerHand, Player.Team.ONE));
                    }
                }
            }

            Card topCard = dealerPile.get(0);
            System.out.println("The dealer top card is : " + topCard);


        
            Player dealer = playerList.get(0);
            Player.Team picker = Player.Team.ONE;
            dealer.setDealer(true);
            playerList.remove(0);
            playerList.add(dealer);
            //Go around table to determine if anyone wants the dealer to pick up the card
            boolean picked = false;
            Card.Suit trump = Card.Suit.CLUBS;
            for (Player player : playerList) {
                boolean playerChoice = player.makeChoice();
                if (playerChoice) {
                    picker = player.team;
                    trump = topCard.getSuit();
                    picked = true;
                    dealer.dealerChoice(topCard);
                    break;
                }
            }

            //Go around again to determine if anyone wants to declare trump
            if (!picked) {
                for (Player player : playerList) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Would you like to pick a suit for trump (Y or N): ");
                    String answer = scanner.nextLine();
                    if (answer.equals("Y")) {
                        System.out.print("What suit would you like to make trump (hearts, clubs, diamonds, spades): ");
                        String userTrump = scanner.nextLine();
                        if (userTrump.equals("hearts")) {
                            trump = Card.Suit.HEARTS;
                        }
                        else if (userTrump.equals("clubs")) {
                            trump = Card.Suit.CLUBS;
                        }
                        else if (userTrump.equals("diamonds")) {
                            trump = Card.Suit.DIAMONDS;
                        }
                        else if (userTrump.equals("spades")) {
                            trump = Card.Suit.DIAMONDS;
                        }
                        picker = player.team;
                        picked = true;
                        break;
                    }
                }
            }

            if (picked) {
                int[] playScore = {0, 0};
                for (int i = 0; i < 5; i++) {
                    ArrayList<Card> currentPlay = new ArrayList<>();
                    for (Player player : playerList) {
                        player.makePlay(currentPlay);
                        System.out.println();
                    }
                    int test = Card.getWinner(currentPlay, trump);
                    Player winner = playerList.get(test);
                    if (winner.getTeam().equals(Player.Team.ONE)) {
                        playScore[0] += 1;
                    }
                    else {
                        playScore[1] += 1;
                    }
                }

                if (playScore[0] > playScore[1]) {
                    if (picker.equals(Player.Team.ONE)) {
                        Player.Team.ONE.setScore(Player.Team.ONE.getScore()+1);
                    }
                    else {
                        Player.Team.ONE.setScore(Player.Team.ONE.getScore()+2);
                    }
                    if (Player.Team.ONE.getScore() >= 10) {
                        System.out.println("Team one has won");
                        cont = false;
                    }
                }
                else {
                    if (picker.equals(Player.Team.TWO)) {
                        Player.Team.TWO.setScore(Player.Team.TWO.getScore()+1);
                    }
                    else {
                        Player.Team.TWO.setScore(Player.Team.TWO.getScore()+2);
                    }
                    if (Player.Team.TWO.getScore() >= 10) {
                        System.out.println("Team two has won");
                        cont = false;
                    }
                }
            }
            System.out.println(Player.Team.ONE.getScore());
            System.out.println(Player.Team.TWO.getScore());
        }
        
    }
}
