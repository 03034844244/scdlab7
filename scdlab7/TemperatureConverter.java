import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {
    private JTextField inputField;
    private JComboBox<String> sourceUnitComboBox;
    private JComboBox<String> targetUnitComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    public TemperatureConverter() {
       
        setTitle("Temperature Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

      
        inputField = new JTextField(10);
        add(inputField);

       
        String[] units = {"Celsius", "Fahrenheit"};
        sourceUnitComboBox = new JComboBox<>(units);
        add(sourceUnitComboBox);

       
        targetUnitComboBox = new JComboBox<>(units);
        add(targetUnitComboBox);

        
        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertButtonListener());
        add(convertButton);

      
        resultLabel = new JLabel();
        add(resultLabel);
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double temperature = Double.parseDouble(inputField.getText());
                String sourceUnit = (String) sourceUnitComboBox.getSelectedItem();
                String targetUnit = (String) targetUnitComboBox.getSelectedItem();

                double result;
                if (sourceUnit.equals("Celsius") && targetUnit.equals("Fahrenheit")) {
                    result = (temperature * 9 / 5) + 32;
                } else if (sourceUnit.equals("Fahrenheit") && targetUnit.equals("Celsius")) {
                    result = (temperature - 32) * 5 / 9;
                } else {
                    result = temperature; 
                }

                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TemperatureConverter temperatureConverter = new TemperatureConverter();
                temperatureConverter.setVisible(true);
            }
        });
    }
}

