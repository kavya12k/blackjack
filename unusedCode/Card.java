
import java.util.*;

/**
 * Card, literally designed to represent a single card from a 
 * standard deck of playing cards.
 * 
 * @author Edwin Li
 * @author Kavya Krishnamurthy
 * @version 5/10/2023
 */
public class Card
{
    private char house; //h, d, s, or c
    private int number;
    private final char [] houseTypes = {'h', 'd', 's', 'c'};


    /**
     * Card constructor
     * 
     * Throws IllegalArgumentException if number or house invalid
     * 
     * @param number - the number of the card
     * @param house - the suit (accidentally named it house) of the card
     */
    public Card(int number, char house)
    {
        if (number < 1 && number > 13)
        {
            throw new IllegalArgumentException(
                "number argument passed into Card constructor is less than one or greater than thirteen: "
                    + number);
        }
        this.number = number;
       
        boolean valid = false;
        for (char c : houseTypes)
        {
            if (house == c)
            {
                valid = true;
            }
        }

        if (valid == false)
        {
            throw new IllegalArgumentException(
                "not a valid house type argument that you passed into the Card constructor: "
                    + house);
        }
        this.house = house;
    }

    public char getHouse()
    {
        return house;
    }

    public int getNumber()
    {
        return number;
    }

    public int getValue()
    {
        if (number <= 10) return number;
        else return 10; //face cards are 10
        // ace implementation will not be here, will be in both of the player and dealer classes
    }


    public String toString()
    {
        String ret = "";
        ret += house + " ";
        
        if (number <= 10 && number > 1) ret += number;
        else
        {
            if (number == 1)
            {
                ret += "Ace";
            }
            else
            {
                String [] faceCards = {"Jack", "Queen", "King", "Ace"};
                ret += faceCards[number - 11];
            }
            
        }

        return ret;
    }
}