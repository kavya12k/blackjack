import java.util.*;

/**
 * Dealer class, the one who deals out the cards and will be played
 * by a bot.
 * 
 * @author Edwin Li
 * @version 5/17/2023
 */
public class Dealer
{
    private ArrayList<Card> hand;
    private LinkedList<Player> playerList; //probably going to be deleted, Dealer should access playerlist from the driver (Round) class.
    private Stack<Card> cardDeck;


    public Dealer()
    {
        this(1);
    }
    
    public Dealer(int numOfStdDecks) //numOfStdDecks may never need to be used
    {
        cardDeck = new Stack<Card>(); //CHANGE: added this to make cardDeck not null
        char [] houseTypes = {'h', 'd', 's', 'c'};
        int i = 0;
        while (i < numOfStdDecks)
        {
            for (int hs = 0; hs < 4; hs++) //loops through house
            {
                for (int cNum = 1; cNum <= 13; cNum++)
                {
                    cardDeck.push(new Card(cNum, houseTypes[hs]));
                }
            }
            i++;
        }

        hand = new ArrayList<Card>();
        playerList = new LinkedList<Player>();
    }

    /**
     * for testing purposes
     * @return
     */
    public int getNumDecks()
    {
        return cardDeck.size() / 52;
    }

    /**
     * for testing purposes
     * @return
     */
    public Stack<Card> getCardDeck ()
    {
        return cardDeck;
    }

    /**
     * Shuffles the card deck with Collections.shuffle
     */
    public void shuffle()
    {
        Collections.shuffle(cardDeck);
    }

    /**
     * Adds a card to the players hand.
     * 
     * Will not deal a card to a player if they chose to stand
     * (player.isStand == true)
     * 
     * @param player - the player the card is being dealt to
     * @return boolean - false if didn't deal, true if dealt
     */
    public boolean dealCard(Player player)
    {
        if (player.isStand() == false)
        {
            player.addCard(cardDeck.pop());
            return true;
        }
        else
        {
            return false;
        }
        
    }

    /**
     * mainly for GUI purposes
     * Should return something that looks like "d 9; hidden"
     * (9 of diamonds; hidden card)
     * 
     * @return ret - a string in which will give the first card in
     *               the dealer's hand, and then "hidden."
     */
    public String getHiddenHand() 
    {
        String ret = "";
        ret += hand.get(0); //first card in the hand
        ret += "; hidden"; //second card is hidden
        return ret;
    }

    /**
     * also for GUI after all players chooses to stand, reveals all cards.
     * To be called on by Round class and for GUI to show all cards.
     * 
     * Should look something like "d 9; Ace"
     * 
     * @return ret - a String in which it has all cards of hand
     */
    public String getRevealedHand()
    {
        String ret = "";
        for (Card c : hand)
        {
            ret += c + "; ";
        }
        return ret;
    }

    /**
     * take a guess bro
     * 
     * @return
     */
    public int totalHandValue()
    {
        int total = 0;
        int aceStorage = 0; //the number of aces in the card hand
        for (Card c : hand)
        {
            if (c.getValue() != 1)
            {
                total += c.getValue();
            }
            else
            {
                aceStorage++; //to handle aces seperately outside this loop
            }
        }

        while (aceStorage > 0) //this implementation is really weird and sub-optimal, but it should work. 
        {
            if((total + 11 > 21) && (total + 1 > 21))
            {
                return 22; //busted, so any value above 21 will work anyway
            }

            if((total + 11 > 21) && (total + 1 <= 21))
            {
                total += 1;
            }

            if((total + 11 <= 21) && (total + 1 <= 21))
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