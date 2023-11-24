import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageViewerApp extends JFrame {
    private JLabel imageLabel;
    private JButton previousButton;
    private JButton nextButton;
    private File[] imageFiles;
    private int currentIndex;

    public ImageViewerApp() {
       
        setTitle("Image Viewer");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

     
        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);

      
        previousButton = new JButton("Previous");
        previousButton.addActionListener(new PreviousButtonListener());
        add(previousButton, BorderLayout.WEST);

        nextButton = new JButton("Next");
        nextButton.addActionListener(new NextButtonListener());
        add(nextButton, BorderLayout.EAST);

       
        imageFiles = new File("path/to/images/directory").listFiles();
        currentIndex = 0;
        displayImage();
    }

    private void displayImage() {
        try {
            Image image = ImageIO.read(imageFiles[currentIndex]);
            imageLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class PreviousButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentIndex > 0) {
                currentIndex--;
                displayImage();
            }
        }
    }

    private class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentIndex < imageFiles.length - 1) {
                currentIndex++;
                displayImage();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ImageViewerApp imageViewerApp = new ImageViewerApp();
                imageViewerApp.setVisible(true);
            }
        });
    }
}
