import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

public class GUI_Winner implements ActionListener
{
    private JFrame parentFrame;
    private JFrame Congra;
    private JLabel Des;
    private JLabel SecretWord;
    private JLabel GameResult;
    private JButton ReturnBtn;
    private ImageIcon background;
    private JPanel imagePanel;
    public GUI_Winner(String Letters,JFrame frame)
    {
        parentFrame = frame;
        Congra = new JFrame("You are the winner!!!");
        bg(Congra);
        Des = new JLabel("The answer is ");
        
        SecretWord = new JLabel(Letters);
        SecretWord.setFont(new Font("Default",Font.PLAIN,23));
        SecretWord.setForeground(Color.red);
        GameResult = new JLabel("You are winner!");
        ReturnBtn = new JButton("Return to the main menu");

        ReturnBtn.addActionListener(this); 
        
        Congra.add(Des);
        Congra.add(SecretWord);
        Congra.add(GameResult);
        Congra.add(ReturnBtn);

        Congra.setVisible(true);

    }

    public void bg(JFrame frame)
    {
        background = new ImageIcon("Congrats.gif");
        JLabel label = new JLabel(background);
     
        label.setBounds(0, 0, background.getIconWidth(),
            background.getIconHeight());
   
        imagePanel = (JPanel) frame.getContentPane();
        imagePanel.setOpaque(false);

        imagePanel.setLayout(new FlowLayout());

        frame.getLayeredPane().setLayout(null);

        frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(background.getIconWidth(), background.getIconHeight());
        frame.setResizable(false);

    }

    public void actionPerformed(ActionEvent e)
    {
        Congra.dispose();
        parentFrame.dispose();
    	new Start().createAndShowGUI();
    }
}