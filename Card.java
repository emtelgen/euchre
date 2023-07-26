import java.util.*;

public class Card {

    public enum Color { RED, BLACK; }

    public enum Suit {
        HEARTS(Color.RED, "hearts"), DIAMONDS(Color.RED, "diamonds"), SPADES(Color.BLACK, "spades"), CLUBS(Color.BLACK, "clubs");
    
        private Color color;
        private String displayName;
    
        Suit(Color color, String displayName) {
            this.color = color;
            this.displayName = displayName;
        }
    
        public Color getColor() {
            return color;
        }
    
        public String getDisplayName() {
            return displayName;
        }
    }

    public enum Rank {
        NINE(9, "9"), TEN(10, "10"), JACK(11, "Jack"), QUEEN(12, "Queen"), KING(13, "King"), ACE(14, "Ace");
        private int value;
        private final String displayName;
    
        Rank(int value, String displayName) {
            this.value = value;
            this.displayName = displayName;
        }
    
        public int getValue() {
            return value;
        }
    
        public String getDisplayName() {
            return displayName;
        }
    
    }

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() { return this.suit; }
    public Rank getRank() { return this.rank; }

    public static Integer getWinner(ArrayList<Card> turnPile, Suit trump) {
        boolean trumpFound = false;
        ArrayList<Card> trumpPile = new ArrayList<>();
        ArrayList<Integer> maxValue = new ArrayList<>();

        for (Card card : turnPile) {
            if (card.getSuit().equals(trump)) {
                trumpFound = true;
                if (card.getRank().value == 11) {
                    card.rank.value = 16;
                }
                trumpPile.add(card);
            }
            else if (card.getRank().value == 11) {
                if (card.getSuit().color.equals(trump.color)) {
                    card.rank.value = 15;
                    trumpPile.add(card);
                }
            }
        }

        Card maxCard;
        if (trumpPile.size() > 0) {
            int maxRank = trumpPile.get(0).getRank().value;
            int maxIndex = 0;
            for (Card card : trumpPile) {
                if (card.getRank().value > maxRank) {
                    maxRank = card.getRank().value;
                    maxIndex = trumpPile.indexOf(card);
                }
            }
            maxCard = trumpPile.get(maxIndex);
        }
        
        else {
            int maxRank = turnPile.get(0).getRank().value;
            int maxIndex = 0;
            for (Card card : turnPile) {
                if ((card.getRank().value > maxRank) && card.getSuit().equals(turnPile.get(0).getSuit())) {
                    maxRank = card.getRank().value;
                    maxIndex = turnPile.indexOf(card);
                }
            }
            maxCard = turnPile.get(maxIndex);
        }

        System.out.println("Winner: " + maxCard);
        return turnPile.indexOf(maxCard);
    }

    @Override public String toString() {
        return (this.rank.getDisplayName() + ":" + this.suit.getDisplayName());
    }



    public static void main(String[] args) {
    
    }
}
