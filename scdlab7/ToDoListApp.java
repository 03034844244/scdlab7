import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListApp extends JFrame {
    private DefaultListModel<String> toDoListModel;
    private JList<String> toDoList;
    private JTextField taskField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton completeButton;

    public ToDoListApp() {
       
        setTitle("To-Do List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

     
        toDoListModel = new DefaultListModel<>();
        toDoList = new JList<>(toDoListModel);
        JScrollPane scrollPane = new JScrollPane(toDoList);
        add(scrollPane, BorderLayout.CENTER);

       
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        
        taskField = new JTextField(20);
        inputPanel.add(taskField);

      
        addButton = new JButton("Add");
        addButton.addActionListener(new AddButtonListener());
        inputPanel.add(addButton);

        
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new DeleteButtonListener());
        inputPanel.add(deleteButton);

       
        completeButton = new JButton("Complete");
        completeButton.addActionListener(new CompleteButtonListener());
        inputPanel.add(completeButton);

       
        add(inputPanel, BorderLayout.SOUTH);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String task = taskField.getText();
            if (!task.isEmpty()) {
                toDoListModel.addElement(task);
                taskField.setText("");
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = toDoList.getSelectedIndex();
            if (selectedIndex != -1) {
                toDoListModel.remove(selectedIndex);
            }
        }
    }

    private class CompleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = toDoList.getSelectedIndex();
            if (selectedIndex != -1) {
                String task = toDoListModel.getElementAt(selectedIndex);
                toDoListModel.setElementAt("[Completed] " + task, selectedIndex);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ToDoListApp toDoListApp = new ToDoListApp();
                toDoListApp.setVisible(true);
            }
        });
    }
}

