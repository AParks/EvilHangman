import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

public class GUI_Loser implements ActionListener
{
    private JFrame parentFrame;
    private JFrame Frame;
    private JLabel secretWordLabel;
    private JLabel gameResultLabel;
    private JButton rtnBtn;

    public GUI_Loser(String Letters, JFrame frame)
    {
       
        parentFrame = frame;
        Frame = new JFrame("You are the loser!");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(new Dimension(300,470));
        Frame.setLayout(new FlowLayout());
        
        secretWordLabel = new JLabel("The answer is "+Letters+".");
        gameResultLabel = new JLabel("You are the Loser!");
        rtnBtn = new JButton("Return to the main menu");
        
        rtnBtn.addActionListener(this); 
        
        ImageIcon icon = new ImageIcon("loser.gif"); 
        JLabel loserPic = new JLabel(icon);

        
        Frame.add(secretWordLabel);
        Frame.add(gameResultLabel);
        Frame.add(rtnBtn);
        Frame.add(loserPic);
        
        Frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        Frame.dispose(); //close the window
        parentFrame.dispose(); // and the parent
    	new Start().createAndShowGUI(); // start over
    }
}