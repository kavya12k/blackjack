import java.util.*;

/**
 * A player. Multiple instances may be created for a single round to be put into
 * a LinkedList
 * 
 * @author Edwin Li
 * @version 5/17/2023
 */
public class Player
{
    private int             totalMoney;
    private ArrayList<Card> cardHand;
    private boolean         isStand;
    private int             currBet;

    // constructors
    public Player()
    {
        this(500);
    }


    public Player(int startingMoney)
    {
        this.totalMoney = startingMoney;
        cardHand = new ArrayList<Card>();
        isStand = false;
        currBet = 0;
    }


    // accessors
    public int getMoney()
    {
        return totalMoney;
    }

    // /**
    // * sets the current amount of money, currently unneccessary
    // * @param m
    // */
    // public void setMoney(int m)
    // {
    // totalMoney = m;
    // }


    /**
     * Adds an integer amount of money
     * 
     * @param m
     *            amount of money
     */
    public void addMoney(int m)
    {
        totalMoney += m;
    }


    /**
     * Subtracts an integer amount of money
     * 
     * @param m
     */
    public void subtractMoney(int m)
    {
        totalMoney -= m;
    }


    /**
     * Getter method, checks to see if this player chose to Stand
     * 
     * @return
     */
    public boolean isStand()
    {
        return isStand;
    }


    /**
     * Setter method, sets the status of whether or not player is Stand-ing;
     * 
     * @param bool
     */
    public void setStand(boolean bool)
    {
        isStand = bool;
    }


    /**
     * Returns the current bet set by the player.
     * 
     * @return
     */
    public int getBet()
    {
        return currBet;
    }


    /**
     * Returns the player's cards in an ArrayList.
     * 
     * @return
     */
    public ArrayList<Card> getCards()
    {
        return cardHand;
    }


    // real functions
    /**
     * Adds a card to this player's current hand
     * 
     * @param card
     */
    public void addCard(Card card)
    {
        cardHand.add(card);
    }


    /**
     * Requests a card from the dealer to be added to this player's hand
     * 
     * @param dealer
     */
    public void hit(Dealer dealer)
    {
        dealer.dealCard(this);
    }


    /**
     * Player chooses to stand, sets isStand to true.
     */
    public void stand()
    {
        isStand = true;
    }


    /**
     * Player bets an amount of money.
     * 
     * @param theBet
     *            amount of money wanted to bet
     * @return true if bet is valid, false otherwise
     */
    public boolean bet(int theBet)
    {
        if (theBet > totalMoney || theBet < 5)
            return false;
        else
        {
            currBet = theBet;
            return true;
        }
    }


    /**
     * take a guess bro handles the value of the ace
     * 
     * @return total value
     */
    public int totalHandValue()
    {
        int total = 0;
        int aceStorage = 0; // the number of aces in the card hand
        for (Card c : cardHand)
        {
            if (c.getValue() != 1)
            {
                total += c.getValue();
            }
            else
            {
                aceStorage++; // to handle aces seperately outside this loop
            }
        }

        while (aceStorage > 0) // this implementation is really weird and
                               // sub-optimal, but it should work.
        {
            if ((total + 11 > 21) && (total + 1 > 21))
            {
                return 22; // busted, so any value above 21 will work anyway
            }

            if ((total + 11 > 21) && (total + 1 <= 21))
            {
                total += 1;
            }

            if ((total + 11 <= 21) && (total + 1 <= 21))
            {
                if (aceStorage == 1)
                {
                    total += 11;
                }
                else
                {
                    total += 1;
                }
            }
            aceStorage--;
        }
        return total;
    }

}
