
// import java.util.Random;
import javax.swing.*;

/**
 * Represents one card. Each card has a value, a suit, and face. Cards can be
 * added to decks and hands of the dealer or player.
 * 
 * @author Adithri Sharma
 * @author Kavya Krishnamurthy
 * @author Edwin Li
 * @version May 30, 2023
 */
public class Card
{

  protected String    face;
  protected ImageIcon cardpic;
  protected int       value;
  protected String    suit;

  /**
   * Creates a card object with a face, cardpic, value, and suit, initialized
   * with null and 0 values.
   */
  public Card()
  {
    cardpic = null;
    value = 0;
    suit = null;
    face = null;
  }


  /**
   * Creates a card object with a face, cardpic, value, and suit, initialized
   * with the parameters of x, val, s, and f.
   * 
   * @param x
   *          ImageIcon of a card's pciture
   * @param val
   *          value of the card
   * @param s
   *          suit of the card
   * @param f
   *          face of the card
   */
  public Card(ImageIcon x, int val, String s, String f)
  {
    cardpic = x;
    value = val;
    face = f;
    suit = s;
  }


  /**
   * Getter method for the card's picture
   * @return ImageIcon cardpic
   */
  public ImageIcon getimage()
  {
    return cardpic;
  }


  /**
   * Getter method for the card's value
   * @return value of the card
   */
  public int getvalue()
  {
    return value;
  }


  /**
   * Setter method for the value. Set's value to parameter v
   * @param v value of card given
   */
  public void setvalue(int v)
  {
    value = v;
  }


  /**
   * Getter method for the card's suit.
   * @return suit of card 
   */
  public String getsuit()
  {
    return suit;
  }


  /**
   * Getter method for the card's face
   * @return face of card
   */
  public String getface()
  {
    return face;
  }


  /**
   * To String method for card, including all of its attributes.
   */
  public String toString()
  {
    return "face: " + face + " suit" + suit + " value: " + value;
  }

}
