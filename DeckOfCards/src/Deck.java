import java.util.*;

public class Deck {
    private ArrayList<Card> deck;
    private final int NUM_OF_CARDS = 52;
    private final int CARDS_ON_DEAL = 7;

    // create a standard deck of uno cards
    public Deck(){
        List<String> colors = Card.getCardColors();
        List<Integer> values = Card.getCardValues();

        // create empty deck
        deck = new ArrayList<>();

        // add all the normal cards
        for (String c : colors){
            for (int v : values){
                deck.add(new Card(c,v));
            }
        }

        // add four +4 cards
        for (int i = 0; i < 4; i++){
            deck.add(new Card("m",13));
        }

        // add deck to instance variable deck
        this.deck = deck;
    }

    // shuffle the deck
    public void shuffle(){
        Random rand = new Random();
        int j;

        for(int i = 0; i < NUM_OF_CARDS; i++){
            j = rand.nextInt(NUM_OF_CARDS);
            Collections.swap(deck, i, j);
        }
    }

    // get top card
    public Card getTopCard(){
        Card tmp = deck.get(0);
        deck.remove(0);
        return tmp;
    }

    // deal to a player
    public ArrayList<Card> deal(){
        ArrayList<Card> dealt = new ArrayList<>();
        for(int i = 0; i < CARDS_ON_DEAL; i++){
            dealt.add(getTopCard());
        }
        return dealt;
    }

    // remove a card from the deck
    public void remove(Card c){
        Iterator<Card> itr = deck.iterator();
        String color = c.getColor();
        int value = c.getValue();
        while(itr.hasNext()){
            Card card = itr.next();
            if(card.getColor().equals(color) && card.getValue() == value){
                deck.remove(card);
                break;
            }
        }
    }

    // add a card to the deck
    public void add(Card c){
        deck.add(c);
    }

    // print the deck
    public void printDeck(){
        for(Card c: deck){
            System.out.println(c);
        }
    }
}
