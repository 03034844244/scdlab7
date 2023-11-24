import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickCounter extends JFrame {
    private JButton button;
    private JLabel label;
    private int clickCount;

    public ClickCounter() {
       
        setTitle("Click Counter");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

       
        button = new JButton("Click Me");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickCount++;
                label.setText("Button Clicked: " + clickCount + " times");
            }
        });

     
        label = new JLabel("Button Clicked: 0 times");

        
        add(button);
        add(label);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClickCounter clickCounter = new ClickCounter();
                clickCounter.setVisible(true);
            }
        });
    }
}
