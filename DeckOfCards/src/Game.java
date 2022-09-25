import java.util.ArrayList;
import java.util.Scanner;

public class Game{
    // create scanner
    Scanner sc = new Scanner(System.in);
    // create deck
    Deck d = new Deck();
    // create list of players
    ArrayList<Player> p = new ArrayList<>();
    // create trackers
    String winner = null; // to hold the name of the winning player
    Boolean noWinner = true; // no current winner
    int playerIndex = 0; //index of starting player
    int direction = 1;  //direction of the game 1 = clckws, -1 = rvrs
    int plus2Count = 0; //keep track of consecutive plus2 plays
    int plus4Count = 0; //keep track of consecutive plus4 plays
    String currentSkip = null;   //keep track of used skip
    String currentRvrs = null;   //keep track of used reverse
    String currentPlus2 = null;     //keep track of used plus2
    String currentPlus4 = null;     //keep track of used plus4
    Card currentCard = null;

    public void game(){
        // shuffle the deck
        d.shuffle();

        // create the players
        createPlayers();

        // start the game
        startGame();

        // play the game

        gameLoop();
        // end the game
    }

    public void createPlayers(){
        System.out.println("Enter number of players");
        int numPlayers = sc.nextInt();
        for(int i = 0; i < numPlayers; i++){
            System.out.println("What is player #" + (i + 1) + "'s name?");
            p.add(new Player(d.deal(), sc.next()));
        }
    }

    public void startGame(){
        // get first card
        currentCard = d.getTopCard();
        // make sure current card is not a special card
        while(currentCard.getValue() == 10 || currentCard.getValue() == 11
                || currentCard.getValue() == 12 || currentCard.getValue() == 13){
            d.add(currentCard);
            currentCard = d.getTopCard();
        }
    }

    public void gameLoop(){
        while(noWinner){
            // print current card and current player's deck
            System.out.println("Current card: " + currentCard);
            System.out.println("Player " + p.get(playerIndex).playerName);
            for(Card c : p.get(playerIndex).getPlayerDeck()){
                System.out.println(c.getColor() + " of " + c.getValue());
            }

            // see if current card is a plus2
            if(currentCard.getValue() == 12 && !currentCard.getColor().equals(currentPlus2)){
                // see if player has plus2 to play
                // else give 2
                if(hasPlus2(p.get(playerIndex).getPlayerDeck())){
                    System.out.println("Play a card");
                    Card tmp = new Card(sc.next(), sc.nextInt());
                    tmp = p.get(playerIndex).move(tmp, currentCard);
                    if(tmp.getValue() == 12){ // played plus2
                        currentCard = tmp; // update currentCard
                        plus2Count++; //update plus2 counter
                    }
                } else {
                    // plus2 to current player
                    p.get(playerIndex).plus2(d.getTopCard(), d.getTopCard());
                    // update current plus2
                    currentPlus2 = currentCard.getColor();
                }
            } else if(currentCard.getValue() == 13 && !currentCard.getColor().equals(currentPlus4)){ // see if current card is a plus4
                // see if player has plus4 to play
                // else give 4
                if(hasPlus4(p.get(playerIndex).getPlayerDeck())){
                    System.out.println("Play a card");
                    String tmpCol = sc.next();
                    int tmpVal = sc.nextInt();
                    Card tmp = new Card(sc.next(), sc.nextInt());
                    tmp = p.get(playerIndex).move(tmp, currentCard);
                    if(tmp.getValue() == 13){ // played plus2
                        System.out.println("What color would you like?");
                        tmpCol = sc.next();
                        currentCard = new Card(tmpCol, tmpVal); // update currentCard
                        plus4Count++; //update plus2 counter
                    }
                } else {
                    // plus4 to current player
                    p.get(playerIndex).plus4(d.getTopCard(), d.getTopCard(), d.getTopCard(), d.getTopCard());
                    // update current plus2
                    currentPlus4 = currentCard.getColor();
                }
            } else {
                // let player make move
                //get player's move
                System.out.println("Play a card");
                Card playerMove = new Card(sc.next(), sc.nextInt());
                Card tmp = p.get(playerIndex).move(playerMove, currentCard);
                // see if move was legal
                if (tmp == null){ // move was illegal
                    // make player draw card
                    p.get(playerIndex).move(d.getTopCard());
                } else { // move was legal
                    // set move as current cardcurrentCard = tmp;
                    // if player played plus4, ask for next color
                    //if(currentCard.getValue() == 13){
                      //  System.out.println("What color would you like?");
                      //  currentCard = new Card(sc.next(), 13);
                    //}
                    // if player played a reverse, reverse direction
                    if(tmp.getValue() == 10){
                        // reverse direction
                        direction *= -1;
                    }
                    // if player played a skip, skip next player
                    if(tmp.getValue() == 11){
                        // skip next player
                        goToNextPlayer();
                    }
                    // if player played a plus4, get the color
                    if(tmp.getValue() == 13){
                        System.out.println("What color would you like?");
                        String tmpCol = sc.next();
                        tmp = new Card(tmpCol, 13);
                    }

                    // set current card to tmp
                    currentCard = tmp;
                }
            }

            //check for winner
            if(p.get(playerIndex).getPlayerDeck().size() == 0){
                noWinner = false;
                winner = p.get(playerIndex).playerName;
            }

            // continue to next player
            goToNextPlayer();
        }
    }

    public void goToNextPlayer(){
        // check direction
        if(direction == 1){ // clockwise
            // check if player is last
            if(playerIndex == (p.size() - 1)){ // player is last
                // go to first player
                playerIndex = 0;
            } else {  // player is not last
                // go to next player
                playerIndex++;
            }
        } else { // counterclockwise
            // check if player is first
            if(playerIndex == 0){
                // go to last player
                playerIndex = (p.size() - 1);
            } else { // player is not first
                // go back a player
                playerIndex--;
            }
        }
    }

    public boolean hasPlus2(ArrayList<Card> cards){
        for(Card c : cards){
            if(c.getValue() == 12){
                return true;
            }
        }
        return false;
    }

    public boolean hasPlus4(ArrayList<Card> cards){
        for(Card c : cards){
            if(c.getValue() == 13){
                return true;
            }
        }
        return false;
    }
}