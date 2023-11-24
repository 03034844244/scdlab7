import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressBarApp extends JFrame {
    private JProgressBar progressBar;
    private JButton startButton;
    private JButton resetButton;
    private Timer timer;
    private int progress;

    public ProgressBarApp() {
        setTitle("Progress Bar");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        progressBar = new JProgressBar(0, 100);
        progressBar.setPreferredSize(new Dimension(250, 30));
        progressBar.setStringPainted(true);
        add(progressBar);

        startButton = new JButton("Start");
        startButton.addActionListener(new StartButtonListener());
        add(startButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetButtonListener());
        add(resetButton);

        timer = new Timer(100, new TimerListener());
    }

    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            progress = 0;
            progressBar.setValue(progress);
            timer.start();
        }
    }

    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            progress = 0;
            progressBar.setValue(progress);
            timer.stop();
        }
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            progress++;
            progressBar.setValue(progress);
            if (progress == 100) {
                timer.stop();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ProgressBarApp progressBarApp = new ProgressBarApp();
                progressBarApp.setVisible(true);
            }
        });
    }
}
