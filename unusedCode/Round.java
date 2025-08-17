import java.util.Scanner;

/**
 * Implementation heavily dependent on GUI, TODO
 * 
 * @author Edwin Li
 * @version 5/17/2023
 */
public class Round
{

    /**
     * Does comparisons and updates. Apparently when theres a tie nothing
     * happens except when both bust Sets isStand to false in players
     * 
     * @param player
     * @param dealer
     * @return 0 if player wins, 1 if dealer wins/player loses, 2 if they tie, 3 for smth else (won't happen)
     */
    private int winCondition(Player player, Dealer dealer, int bet)
    {
        if (player.totalHandValue() > 21)
        {
            player.setStand(false);
            return 1;
        } // player loses, regardless if dealer busted
        if (dealer.totalHandValue() > 21)
        {
            player.addMoney(bet);
            player.setStand(false);
            return 0; // player wins if player didn't bust and dealer busts
        }
        if (player.totalHandValue() > dealer.totalHandValue())
        {
            player.addMoney(bet);
            player.setStand(false);
            return 0;
        }
        if (player.totalHandValue() < dealer.totalHandValue())
        {
            player.subtractMoney(bet);
            player.setStand(false);
            return 1;
        }
        if (player.totalHandValue() == dealer.totalHandValue())
        {
            player.setStand(false);
            return 2; // returns a loss, but its really a tie, need to find
                          // a way to handle this
        }
        else
            return 3;

    }


    /**
     * unfinished driver class, probably not going to be implemented
     */
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in); // System.in needs to be replaced
                                             // by a GUI input
        Dealer d = new Dealer(); // GUI may ask for an input of a number of
                                 // decks wanted to be played with
        Player p = new Player();

        int moneyBet = sc.nextInt();
        p.bet(moneyBet);

    }
}
