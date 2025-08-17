package finalProj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Creates visual for a game
 * 
 * @author Adithri Sharma
 * @author Kavya Krishnamurthy
 * @version May 30, 2023
 */
public class BlackjackGUI
  extends JPanel
{
  JPanel    topPanel        = new JPanel();
  JPanel    dcardPanel      = new JPanel();
  JPanel    pcardPanel      = new JPanel();
  JTextPane winlosebox      = new JTextPane();
  JButton   hitbutton       = new JButton();
  JButton   dealbutton      = new JButton();
  JButton   staybutton      = new JButton();
  JButton   playagainbutton = new JButton();
  JLabel    dealerlabel     = new JLabel();
  JLabel    playerlabel     = new JLabel();

  Hand      dealer          = new Hand();
  Hand      player          = new Hand();
  Blackjack game            = new Blackjack(dealer, player);

  // labels to represent cards of the game
  JLabel    playercard1;
  JLabel    playercard2;
  JLabel    playercardhit;
  JLabel    dealercard0;
  JLabel    dealercard2;
  JLabel    dealercard1;
  JLabel    dealercardhit;

  /**
   * Creates a panel of Blackjack, initializes GUI components
   */
  public BlackjackGUI()
  {

    topPanel.setBackground(new Color(0, 100, 0));
    dcardPanel.setBackground(new Color(0, 100, 0));
    pcardPanel.setBackground(new Color(0, 100, 0));

    topPanel.setLayout(new FlowLayout());
    winlosebox.setText(" ");
    winlosebox.setFont(new java.awt.Font("Times New Roman", 1, 20));
    dealbutton.setText("  Deal");
    dealbutton.addActionListener(new dealbutton());
    hitbutton.setText("  Hit");
    hitbutton.addActionListener(new hitbutton());
    hitbutton.setEnabled(false);
    staybutton.setText("  Stand");
    staybutton.addActionListener(new staybutton());
    staybutton.setEnabled(false);
    playagainbutton.setText("  Play Again");
    playagainbutton.addActionListener(new playagainbutton());
    playagainbutton.setEnabled(false);

    dealerlabel.setText("  Dealer:  ");
    playerlabel.setText("  Player:  ");

    topPanel.add(winlosebox);
    topPanel.add(dealbutton);
    topPanel.add(hitbutton);
    topPanel.add(staybutton);
    topPanel.add(playagainbutton);
    pcardPanel.add(playerlabel);
    dcardPanel.add(dealerlabel);

    setLayout(new BorderLayout());
    add(topPanel, BorderLayout.NORTH);
    add(dcardPanel, BorderLayout.CENTER);
    add(pcardPanel, BorderLayout.SOUTH);

  }// end BlackjackGUI


  /**
   * Displays screen for users
   */
  public void display()
  {
    JFrame myFrame = new JFrame("BlackJack");
    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myFrame.setContentPane(this);
    myFrame.setPreferredSize(new Dimension(700, 550));

    // Display the window.
    myFrame.pack();
    myFrame.setVisible(true);

  }

  /**
   * Deal button, listens for a click and calls the method to deal initial cards
   */
  class dealbutton
    implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {

      dcardPanel.add(dealerlabel);
      pcardPanel.add(playerlabel);

      dealercard0 = new JLabel(new ImageIcon("back.jpg"));

      game.dealInitialCards();

      // to iterate set and get current dealer cards
      Card dcard = null;
      Iterator<Card> dscan = (dealer.inHand).iterator();
      int count = 0;
      while (dscan.hasNext())
      {
        dcard = dscan.next();
        if (count == 0)
          dealercard1 = new JLabel(dcard.getimage());
        else
          dealercard2 = new JLabel(dcard.getimage());

        count++;
      }

      // to iterate set and get current player cards
      Iterator<Card> pscan = (player.inHand).iterator();
      count = 0;
      while (pscan.hasNext())
      {
        Card pcard = pscan.next();
        if (count == 0)
          playercard1 = new JLabel(pcard.getimage());
        else
          playercard2 = new JLabel(pcard.getimage());

        count++;
      }

      dcardPanel.add(dealercard0);
      dcardPanel.add(dealercard2);

      pcardPanel.add(playercard1);
      pcardPanel.add(playercard2);

      dealerlabel.setText("  Dealer:  " + dcard.getvalue());
      playerlabel.setText("  Player:  " + game.handValue(player));

      hitbutton.setEnabled(true);
      staybutton.setEnabled(true);
      dealbutton.setEnabled(false);

      if (game.blackj())
      {
        hitbutton.setEnabled(false);
        staybutton.setEnabled(false);
        dealbutton.setEnabled(false);
        playagainbutton.setEnabled(true);
        winlosebox.setText("BlackJack");
      }

      add(dcardPanel, BorderLayout.CENTER);
      add(pcardPanel, BorderLayout.SOUTH);

    }
  }


  /**
   * Hit button, listens for a click and does all things hit related, checking
   * whether there's a bust or not.
   */
  class hitbutton
    implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {

      Card hitcard = game.hit(player);
      playercardhit = new JLabel(hitcard.getimage());
      pcardPanel.add(playercardhit);
      pcardPanel.repaint();

      if (game.bust(player))
      {
        winlosebox.setText("Bust");
        hitbutton.setEnabled(false);
        dealbutton.setEnabled(false);
        staybutton.setEnabled(false);
        playagainbutton.setEnabled(true);
      }

      playerlabel.setText("  Player:   " + game.handValue(player));

    }
  }


  /**
   * Stand button, listens for a click and does all things stand related like
   * checking if user won and revealing what dealer's cards are
   */
  class staybutton
    implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {

      dcardPanel.remove(dealercard0);
      dcardPanel.add(dealercard1);

      dealer = game.dealerPlays();
      dcardPanel.removeAll();
      dcardPanel.add(dealerlabel);
      dealerlabel.setText(" " + dealerlabel.getText());

      // iterate through cards and re-display
      Card dhitcard = null;
      Iterator<Card> scan = (dealer.inHand).iterator();
      while (scan.hasNext())
      {
        dhitcard = scan.next();
        dealercardhit = new JLabel(dhitcard.getimage());
        dcardPanel.add(dealercardhit);
      }

      dealerlabel.setText("Dealer: " + game.handValue(dealer));
      playerlabel.setText("Player: " + game.handValue(player));

      winlosebox.setText(game.winner());
      hitbutton.setEnabled(false);
      staybutton.setEnabled(false);

      playagainbutton.setEnabled(true);

    }
  }


  /**
   * Play again button, listens for a click and resets the board, disabling all
   * other buttons but deal
   */
  class playagainbutton
    implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {

      dealerlabel.setText("Dealer: ");
      playerlabel.setText("Player: ");
      winlosebox.setText("");
      dealer = new Hand();
      player = new Hand();
      game = new Blackjack(dealer, player);

      dcardPanel.removeAll();
      pcardPanel.removeAll();

      hitbutton.setEnabled(false);
      staybutton.setEnabled(false);
      playagainbutton.setEnabled(false);
      dealbutton.setEnabled(true);

    }
  }
}
