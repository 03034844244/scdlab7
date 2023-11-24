import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPaletteApp extends JFrame {
    private JPanel colorPanel;
    private JButton[] colorButtons;
    private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE};

    public ColorPaletteApp() {
     
        setTitle("Color Palette");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        colorPanel = new JPanel();
        add(colorPanel, BorderLayout.CENTER);

       
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        colorButtons = new JButton[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colorButtons[i] = new JButton();
            colorButtons[i].setBackground(colors[i]);
            colorButtons[i].setPreferredSize(new Dimension(50, 50));
            colorButtons[i].addActionListener(new ColorButtonListener());
            buttonPanel.add(colorButtons[i]);
        }

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class ColorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            Color selectedColor = button.getBackground();
            colorPanel.setBackground(selectedColor);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ColorPaletteApp colorPaletteApp = new ColorPaletteApp();
                colorPaletteApp.setVisible(true);
            }
        });
    }
}
