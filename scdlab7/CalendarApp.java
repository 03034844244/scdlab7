import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalendarApp extends JFrame {
    private JLabel monthYearLabel;
    private JButton previousButton;
    private JButton nextButton;
    private JPanel calendarPanel;
    private List<JButton> dayButtons;
    private List<Event> events;
    private YearMonth currentYearMonth;

    public CalendarApp() {
        setTitle("Calendar");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());

        previousButton = new JButton("Previous");
        previousButton.addActionListener(new PreviousButtonListener());
        headerPanel.add(previousButton, BorderLayout.WEST);

        monthYearLabel = new JLabel();
        monthYearLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(monthYearLabel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.addActionListener(new NextButtonListener());
        headerPanel.add(nextButton, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(0, 7));

        dayButtons = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            JButton dayButton = new JButton();
            dayButton.setEnabled(false);
            calendarPanel.add(dayButton);
            dayButtons.add(dayButton);
        }

        add(calendarPanel, BorderLayout.CENTER);

        events = new ArrayList<>();

        currentYearMonth = YearMonth.now();
        updateCalendar();
    }

    private void updateCalendar() {
        String monthYear = currentYearMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
        monthYearLabel.setText(monthYear);

        for (JButton dayButton : dayButtons) {
            dayButton.setText("");
        }

        LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
        int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();

        int daysInMonth = currentYearMonth.lengthOfMonth();

        int day = 1;
        for (int i = firstDayOfWeek - 1; i < firstDayOfWeek - 1 + daysInMonth; i++) {
            JButton dayButton = dayButtons.get(i);
            dayButton.setText(String.valueOf(day));
            dayButton.setEnabled(true);
            dayButton.addActionListener(new DayButtonListener(day));
            day++;
        }

        for (int i = firstDayOfWeek - 1 + daysInMonth; i < dayButtons.size(); i++) {
            JButton dayButton = dayButtons.get(i);
            dayButton.setEnabled(false);
        }
    }

    private class PreviousButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentYearMonth = currentYearMonth.minusMonths(1);
            updateCalendar();
        }
    }

    private class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentYearMonth = currentYearMonth.plusMonths(1);
            updateCalendar();
        }
    }

    private class DayButtonListener implements ActionListener {
        private int day;

        public DayButtonListener(int day) {
            this.day = day;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            LocalDate selectedDate = currentYearMonth.atDay(day);
            EventDialog eventDialog = new EventDialog(selectedDate);
            eventDialog.setVisible(true);
            if (eventDialog.isEventCreated()) {
                events.add(eventDialog.getEvent());
            }
        }
    }

    private class EventDialog extends JDialog {
        private JTextField eventTextField;
        private JButton createButton;
        private JButton cancelButton;
        private boolean eventCreated;
        private Event event;

        public EventDialog(LocalDate date) {
            setTitle("Create Event");
            setSize(300, 150);
            setModal(true);
            setLayout(new BorderLayout());

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(2, 2));

            JLabel dateLabel = new JLabel("Date:");
            inputPanel.add(dateLabel);

            JLabel selectedDateLabel = new JLabel(date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
            inputPanel.add(selectedDateLabel);

            JLabel eventLabel = new JLabel("Event:");
            inputPanel.add(eventLabel);

            eventTextField = new JTextField();
            inputPanel.add(eventTextField);

            add(inputPanel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());

            createButton = new JButton("Create");
            createButton.addActionListener(new CreateButtonListener());
            buttonPanel.add(createButton);

            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new CancelButtonListener());
            buttonPanel.add(cancelButton);

            add(buttonPanel, BorderLayout.SOUTH);
        }

        public boolean isEventCreated() {
            return eventCreated;
        }

        public Event getEvent() {
            return event;
        }

        private class CreateButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = eventTextField.getText();
                event = new Event(eventName);
                eventCreated = true;
                dispose();
            }
        }

        private class CancelButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }
    }

    private class Event {
        private String name;

        public Event(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CalendarApp calendarApp = new CalendarApp();
                calendarApp.setVisible(true);
            }
        });
    }
}

