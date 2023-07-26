import java.util.*;

public class Deck {
    private static final int deckLength = 24;

    public static ArrayList<Card> makeDeck() {
        ArrayList<Card> deck = new ArrayList<>();

        int arrayIndex = 0;
        for( int i = 0; i < Card.Suit.values().length; i++) {
            for (int j = 0; j < Card.Rank.values().length; j++) {
                deck.add(new Card(Card.Suit.values()[i], Card.Rank.values()[j]));
                arrayIndex += 1;
            }
        }
        
        
        return deck;
    }

    public static ArrayList<Card> makePile(ArrayList<Card> deck) {
        ArrayList<Card> pile = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int index = (int)(Math.random() * deck.size());
            pile.add(deck.get(index));
            deck.remove(index);
        }
        
        return pile;
    }

    public static ArrayList<Card> makeHand(ArrayList<Card> deck) {
        ArrayList<Card> hand = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int index = (int)(Math.random() * deck.size());
            hand.add(deck.get(index));
            deck.remove(index);
        }


        return hand;
    }

    public static void main(String[] args) {
        ArrayList<Card> deck = makeDeck();
        ArrayList<Card> pile = makePile(deck);
        makeHand(deck);
    }
}
