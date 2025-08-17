package finalProj;
import jss2.exceptions.*;
import java.util.*;

/**
 * 
 * @author Adithri Sharma
 * @author Kavya Krishnamurthy
 * @author Edwin Li
 * @version May 30, 2023
 */
public class Blackjack
{
  Hand dealer;
  Hand player;
  Deck newdeck;

  /**
   * Initializes both the dealer and player's hand. Creats a new deck for a
   * game.
   * 
   * @param dlr
   *          dealer's hand
   * @param plr
   *          player's hand
   */
  public Blackjack(Hand dlr, Hand plr)
  {
    dealer = dlr;
    player = plr;
    newdeck = new Deck();
  }


  /**
   * called at beginning, deals two cards to both the dealer and player
   */
  public void dealInitialCards()
  {
    dealer.newCard(newdeck);
    dealer.newCard(newdeck);
    player.newCard(newdeck);
    player.newCard(newdeck);

  }


  /**
   * Called when someone hits, deals a card to whichever hand hit and returns it
   * 
   * @param whohit
   *          whichever hand hit
   * @return top card from the deck
   */
  public Card hit(Hand whohit)
  {
    Card result = whohit.newCard(newdeck);

    return result;

  }


  /**
   * counts up all values of cards in hand and returns the total
   * 
   * @param whohand
   *          dealer or player's hand
   * @return total hand value
   */
  public int handValue(Hand whohand)
  {
    int result = whohand.getHandValue();

    return result;

  }


  /**
   * TODO: 
   * @param whodis dealer or player's hand
   * @param discrd 
   * @throws ElementNotFoundException
   */
  public void discard(Hand whodis, Card discrd)
    throws ElementNotFoundException
  {
    Card card = null;
    boolean found = false;
    Iterator<Card> scan = whodis.iterator();
    while (scan.hasNext() && !found)
    {
      card = scan.next();
      if (discrd.equals(card))
      {
        whodis.remove(card);
        found = true;
      }
    }
    if (!found)
      throw new ElementNotFoundException("BlackJack");

  }


  /**
   * represents whether hand value equals a blackjack
   * @return whether the first two cards create a blackjack
   */
  public boolean blackj()
  {
    boolean result = false;

    if (player.getHandValue() == 21)
      result = true;

    return result;

  }


  /**
   * checks if hand value is over 21 and returns true if it's over 21, false otherwise
   * @param whobust whichever's hand busted
   * @return true or false if it is a bust
   */
  public boolean bust(Hand whobust)
  {
    boolean result = false;

    if (whobust.getHandValue() > 21)
      result = true;

    return result;

  }


  /**
   * adds cards to the dealer's hand until their value is 17 or greater
   * @return new hand after adding cards
   */
  public Hand dealerPlays()
  {
    Hand result = dealer;

    while (dealer.getHandValue() <= 16)
    {
      dealer.newCard(newdeck);
    }

    return result;

  }


  /**
   * compares the dealer and player's hand values to check resutl of round
   * @return result of round
   */
  public String winner()
  {
    String result = "";
    if ((player.getHandValue() < dealer.getHandValue()) && dealer.getHandValue() <= 21)
      result = "Lose";
    else if ((player.getHandValue() == dealer.getHandValue()) && dealer.getHandValue() <= 21)
      result = "Push";
    else
      result = "Win";

    return result;

  }

}
