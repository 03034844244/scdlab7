import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JTextField display;

    public Calculator() {
      
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

       
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

       
        for (int i = 1; i <= 9; i++) {
            JButton digitButton = new JButton(String.valueOf(i));
            digitButton.addActionListener(new DigitButtonListener());
            buttonPanel.add(digitButton);
        }

       
        String[] operations = {"+", "-", "*", "/"};
        for (String operation : operations) {
            JButton operationButton = new JButton(operation);
            operationButton.addActionListener(new OperationButtonListener());
            buttonPanel.add(operationButton);
        }

       
        JButton equalsButton = new JButton("=");
        equalsButton.addActionListener(new EqualsButtonListener());
        buttonPanel.add(equalsButton);

       
        JButton clearButton = new JButton("C");
        clearButton.addActionListener(new ClearButtonListener());
        buttonPanel.add(clearButton);

      
        add(buttonPanel, BorderLayout.CENTER);
    }

    private class DigitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String digit = button.getText();
            display.setText(display.getText() + digit);
        }
    }

    private class OperationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String operation = button.getText();
            display.setText(display.getText() + " " + operation + " ");
        }
    }

    private class EqualsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String expression = display.getText();
            String[] parts = expression.split(" ");
            double result = Double.parseDouble(parts[0]);

            for (int i = 1; i < parts.length; i += 2) {
                String operation = parts[i];
                double operand = Double.parseDouble(parts[i + 1]);

                switch (operation) {
                    case "+":
                        result += operand;
                        break;
                    case "-":
                        result -= operand;
                        break;
                    case "*":
                        result *= operand;
                        break;
                    case "/":
                        result /= operand;
                        break;
                }
            }

            display.setText(String.valueOf(result));
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Calculator calculator = new Calculator();
                calculator.setVisible(true);
            }
        });
    }
}

