package finalProj;
import jss2.*;
import jss2.exceptions.*;

import java.util.*;

/**
 * Represents a hand. Contains card in an ArraySet. Can be the player or
 * dealer's hand.
 */
public class Hand
{
  protected ArraySet<Card> inHand;
  protected int            handvalue, count;

  /**
   * Creates a hand object with an array set of cards in the hand intialized to
   * empty and the total value and count intialized to 0.
   */
  public Hand()
  {
    inHand = new ArraySet<Card>(12);
    handvalue = 0;
    count = 0;
  }


  /**
   * Applies ace attributes to a hand if applicable.
   * 
   * @param newCard
   *          the card added to the hand, changing the total value
   */
  private void reduceHand(Card newCard)
  {
    if ((handvalue) > 21)
    {
      if (aceInHand())
        handvalue -= 10;

    }
  }


  /**
   * checks for ace
   * 
   * @return true if an ace is present, false if it isn't
   */
  private boolean aceInHand()
  {
    boolean result = false;
    Card cardchk = null;
    Iterator<Card> scan = inHand.iterator();

    while (scan.hasNext() && !result)
    {
      cardchk = scan.next();
      if (cardchk.getvalue() == 11)
      {
        cardchk.setvalue(1);
        result = true;
      }

    }
    return result;
  }


  /**
   * Pops a card from the deck to add to the hand. Can represent the hit button.
   * 
   * @param currentdeck
   *          deck used in the round
   * @return card popped from deck and added to hand
   */
  public Card newCard(Deck currentdeck)
  {
    Card result;
    result = currentdeck.getCard();
    inHand.add(result);
    handvalue += result.getvalue();
    reduceHand(result);
    count++;

    return result;
  }


  /**
   * Getter method for hand's total value
   * 
   * @return total hand value
   */
  public int getHandValue()
  {
    return handvalue;
  }


  /**
   * TODO:
   * 
   * @return
   */
  public Iterator<Card> iterator()
  {
    return inHand.iterator();
  }


  /**
   * TODO:
   * 
   * @param crd
   * @return
   * @throws ElementNotFoundException
   */
  public Card remove(Card crd)
    throws ElementNotFoundException
  {
    return (inHand.remove(crd));
  }


  /**
   * To String method to represent all attributes of the hand in a string.
   */
  public String toString()
  {
    String result = "";

    Card cardstr = null;
    int i = 0;
    Iterator<Card> scan = inHand.iterator();
    while (scan.hasNext())
    {
      cardstr = scan.next();
      result += "card" + i + ": " + cardstr.getvalue() + "\n";
      i++;
    }

    return result;
  }

}
