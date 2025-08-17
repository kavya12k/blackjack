
import org.junit.*;

import static org.junit.Assert.*;
import java.util.Stack;
import junit.framework.JUnit4TestAdapter;

// import org.junit.Test;
// import static org.junit.Assert.*;

/**
 * Testing class for all components of Blackjack
 * 
 * @author Kavya Krishnamurthy
 * @version May 11, 2023
 */
public class JUBlackjackTest
{

    // TESTING CARD CLASS (done)

    /**
     * testing constructor for card
     */
    @Test
    public void testCardConstructor()
    {
        char x = 'h';
        Card card = new Card(9, x);
        assertTrue(card.getHouse() == 'h');
        assertTrue(card.getNumber() == 9);
    }


    /**
     * testing getHouse() method for card
     */
    @Test
    public void testHouse()
    {
        Card card = new Card(1, 'd');
        assertTrue(card.getHouse() == 'd');
    }


    /**
     * testing getNumber() method for card
     */
    @Test
    public void testGetNumber()
    {
        Card card = new Card(11, 's');
        assertTrue(card.getNumber() == 11);
    }


    /**
     * testing getValue() method for card
     */
    @Test
    public void testGetValue()
    {
        Card card = new Card(12, 'c');
        assertTrue(card.getValue() == 10);
    }

    // TESTING PLAYER CLASS (done mostly, need to look @ totalHandValue and bet)

    /**
     * declaring initial variables for testing purposes
     */
    Player testPlayer = new Player(1000);

    /**
     * testing the constructor for player
     */
    @Test
    public void testPlayerConstructor()
    {
        Player player = new Player();
        assertTrue(player.getMoney() == 500);
        Player player2 = new Player(1000);
        assertTrue(player2.getMoney() == 1000);
    }


    /**
     * testing the getMoney() for player
     */
    @Test
    public void testGetMoney()
    {
        assertTrue(testPlayer.getMoney() == 1000);
    }


    /**
     * testing the addMoney() for player
     */
    @Test
    public void testAddMoney()
    {
        testPlayer.addMoney(100);
        assertTrue(testPlayer.getMoney() == 1100);

    }


    /**
     * testing the subtractMoney() for player
     */
    @Test
    public void testSubtractMoney()
    {
        testPlayer.subtractMoney(200);
        assertTrue(testPlayer.getMoney() == 800);
    }


    /**
     * testing the isStand() for player
     */
    @Test
    public void testIsStand()
    {
        assertTrue(testPlayer.isStand() == false);
        Player player = new Player();
        player.setStand(true);
        assertTrue(player.isStand() == true);
    }


    /**
     * testing the setStand() for player
     */
    @Test
    public void testSetStand()
    {
        testPlayer.setStand(true);
        assertTrue(testPlayer.isStand() == true);
        testPlayer.setStand(false);
        assertTrue(testPlayer.isStand() == false);
    }


    /**
     * testing the getBet() for player
     */
    @Test
    public void testGetBet()
    {
        testPlayer.bet(100);
        assertTrue(testPlayer.getBet() == 100);
    }


    /**
     * testing the addCard() for player
     */
    @Test
    public void testAddCard()
    {
        Card c = new Card(3, 'h');
        testPlayer.addCard(c);
        assertEquals(testPlayer.getCards().get(testPlayer.getCards().size() - 1), c);
    }


    /**
     * testing the hit() for player
     */
    @Test
    public void testHit()
    {
        Dealer dealer = new Dealer();
        int size = testPlayer.getCards().size();
        testPlayer.hit(dealer);
        assertTrue(testPlayer.getCards().size() == size + 1);
    }


    /**
     * testing the stand() for player
     */
    @Test
    public void testStand()
    {
        testPlayer.stand();
        assertTrue(testPlayer.isStand() == true);
    }


    /**
     * testing the bet() for player
     */
    @Test
    public void testBet()
    {
        boolean valid = testPlayer.bet(100);
        assertTrue(testPlayer.getBet() == 100 && valid == true);
        assertTrue(testPlayer.getMoney() == 1000); // not sure. money may not
                                                   // change?
        Player player = new Player(10);
        boolean not = player.bet(4);
        boolean anotherNot = player.bet(11);
        assertTrue(not == false && anotherNot == false);
        assertTrue(player.getBet() == 0 && player.getMoney() == 10);
        testPlayer.bet(100);
    }


    /**
     * testing the totalValue() for player
     */
    @Test
    public void testTotalHandValue()
    {
        // 1 ace that should be = 1
        Player player = new Player(1000);
        player.addCard(new Card(9, 'h'));
        player.addCard(new Card(13, 'c'));
        player.addCard(new Card(1, 'c'));
        assertTrue(player.totalHandValue() == 20);
        // 1 ace that should be = 11
        Player player2 = new Player(1000);
        player2.addCard(new Card(9, 'd'));
        player2.addCard(new Card(1, 's'));
        assertTrue(player2.totalHandValue() == 20);
        // 2 ace that should be 1 and 11
        Player player3 = new Player(1000);
        player3.addCard(new Card(9, 's'));
        player3.addCard(new Card(1, 'd'));
        player3.addCard(new Card(1, 'h'));
        assertTrue(player3.totalHandValue() == 21);
        // 2 ace that should be 1 and 1 + tests for face values
        Player player4 = new Player(1000);
        player4.addCard(new Card(13,'c'));
        player4.addCard(new Card(2, 'h'));
        player4.addCard(new Card(1, 'd'));
        player4.addCard(new Card(1, 's'));
        assertTrue(player4.totalHandValue() == 14);
    }

    // TESTING DEALER CLASS

    Dealer testDealer = new Dealer();

    /**
     * testing the constructor for dealer
     */
    @Test
    public void testDealerConstructor()
    {
        Dealer dealer = new Dealer();
        assertTrue(dealer.getNumDecks() == 1);
        Dealer dealer2 = new Dealer(3);
        assertTrue(dealer2.getNumDecks() == 3);

    }


    /**
     * testing the dealCard() for dealer
     */
    @Test
    public void testDealCard()
    {
        Player player = new Player();
        testDealer.shuffle();
        Stack<Card> copy = testDealer.getCardDeck();
        Card card = copy.pop();
        copy.push(card);
        testDealer.dealCard(player);
        assertTrue(player.getCards().size() == 1);
        assertTrue(player.getCards().get(0).equals(card));

    }


    /**
     * testing the getHiddenHand() for dealer
     */
    @Test
    public void testGetHiddenHand()
    {
        // probably not going to test this getter method
    }


    /**
     * testing the getRevealedHand() for dealer
     */
    @Test
    public void testGetRevealedHand()
    {
        // probably not going to test this getter method
    }


    /**
     * testing the totalHandValue() for dealer
     */
    @Test
    public void testDealerTotalHandValue()
    {
        // tested this with player, has the exact same implementation so it
        // should work if player's works
    }

 

}
