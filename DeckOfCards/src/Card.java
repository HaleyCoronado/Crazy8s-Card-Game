import java.util.Arrays;
import java.util.List;

public class Card implements Comparable<Card> {
    private String color;
    private int value;

    // for +4 cards
    private String p4Color = "m";
    private int p4Value = 13;

    public Card(String color, int value){
        setColor(color);
        setValue(value);

    }

    // set color
    private void setColor(String color){
        // get list of possible colors
        List<String> cardColors = getCardColors();
        color = color.toLowerCase();

        // check that color is valid
        if(cardColors.contains(color) || color.equals(p4Color)){
            this.color = color;
        } else {
            throw new IllegalArgumentException("Invalid Color");
        }
    }

    // set value
    private void setValue(int value){
        // get list of possible values
        List<Integer> cardValues = getCardValues();
        this.value = value;

        // check that value is valid
        if(cardValues.contains(value) || value == p4Value){
            this.value = value;
        } else {
            throw new IllegalArgumentException("Invalid Value");
        }
    }

    public String getColor(){
        return color;
    }

    public Integer getValue(){
        return value;
    }

    // creates list of possible colors
    public static List<String> getCardColors(){
        return Arrays.asList("r","y","g","b");
    }

    // create list of possible values
    public static List<Integer> getCardValues(){
        return Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);
    }
    // 10 = reverse; 11 = skip; 12 = +2

    // format as a string
    public String toString(){
        return String.format("%s of %s", color, value);
    }

    // comparator to compare card values
    public int compareTo(Card c){
        if(value == c.getValue()){
            return 0;
        } else if(value > c.getValue()){
            return 1;
        } else {
            return -1;
        }
    }
}
