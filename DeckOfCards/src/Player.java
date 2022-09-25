import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Player {
    private ArrayList<Card> playerDeck;
    public String playerName;

    public Player(ArrayList<Card> pd, String playerName){
        this.playerName = playerName;
        this.playerDeck = pd;
        sortPlayerDeck();
    }

    // sort the player's deck
    private void sortPlayerDeck(){
        ArrayList<Card> sortedDeck = new ArrayList<>();
        ArrayList<Card> redCards = new ArrayList<>();
        ArrayList<Card> yellowCards = new ArrayList<>();
        ArrayList<Card> greenCards = new ArrayList<>();
        ArrayList<Card> blueCards = new ArrayList<>();

        // create mini ArrayLists for each color
        for(Card c : this.playerDeck){
            if(c.getColor().equals("r")){
                redCards.add(c);
            } else if(c.getColor().equals("y")){
                yellowCards.add(c);
            }else if(c.getColor().equals("g")){
                greenCards.add(c);
            } else if(c.getColor().equals("b")){
                blueCards.add(c);
            } else if(c.getColor().equals("m")){
                // add any plus4 cards to sortedDeck so they are first
                sortedDeck.add(c);
            }
        }

        // sort each mini ArrayList by value
        Collections.sort(redCards);
        Collections.sort(yellowCards);
        Collections.sort(greenCards);
        Collections.sort(blueCards);

        // add all the mini decks to the main deck
        for(Card c : redCards){
            sortedDeck.add(c);
        }
        for(Card c : yellowCards){
            sortedDeck.add(c);
        }
        for(Card c : greenCards){
            sortedDeck.add(c);
        }
        for(Card c : blueCards){
            sortedDeck.add(c);
        }

        // set playerDeck as the new sorted deck
        this.playerDeck = sortedDeck;
    }

    // allow the player to play a card
    public Card move(Card mv, Card currentCard){
        if(mv.getColor().equals(currentCard.getColor())){
            removeCard(mv);
            return mv;
        } else if(mv.getValue() == currentCard.getValue()){
            removeCard(mv);
            return mv;
        } else if(mv.getValue() == 13){
            removeCard(mv);
            return mv;
        } else {
            return null;
        }
    }

    // allow the player to draw a card on their turn
    public void move(Card drawnCard){
        // add card to deck and resort deck
        this.playerDeck.add(drawnCard);
        sortPlayerDeck();
    }

    // plus2 from another player's turn
    public void plus2(Card c1, Card c2){
        // add two cards to playerDeck
        addCard(c1);
        addCard(c2);
    }

    // plus4 from another player
    public void plus4(Card c1, Card c2, Card c3, Card c4){
        // add four cards to playerDeck
        addCard(c1);
        addCard(c2);
        addCard(c3);
        addCard(c4);
    }

    // add a card to the player's deck
    public void addCard(Card c){
        int i = 0;
        while(!playerDeck.get(i).getColor().equals(c.getColor())){
            i++;
        }
        while(playerDeck.get(i).getValue() < c.getValue()){
            i++;
        }
        playerDeck.add(i, c);
    }

    // remove a card from the player's deck
    public void removeCard(Card c){
        Iterator<Card> itr = this.playerDeck.iterator();
        String color = c.getColor();
        int value = c.getValue();
        while(itr.hasNext()){
            Card card = itr.next();
            if(card.getColor().equals(color) && card.getValue() == value){
                this.playerDeck.remove(card);
                break;
            }
        }
    }

    // print the player's deck
    public void printDeck(){
        for(Card c: this.playerDeck){
            System.out.println(c);
        }
    }

    // send player's deck
    public ArrayList<Card> getPlayerDeck(){
        return this.playerDeck;
    }
}
