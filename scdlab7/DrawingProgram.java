import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingProgram extends JFrame {
    private JPanel canvas;
    private ShapeType currentShape;

    public DrawingProgram() {
        // Set up the JFrame
        setTitle("Drawing Program");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the canvas panel
        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawShapes(g);
            }
        };
        canvas.addMouseListener(new CanvasMouseListener());
        add(canvas, BorderLayout.CENTER);

        // Create the shape selection buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton lineButton = new JButton("Line");
        lineButton.addActionListener(e -> setCurrentShape(ShapeType.LINE));
        buttonPanel.add(lineButton);

        JButton rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(e -> setCurrentShape(ShapeType.RECTANGLE));
        buttonPanel.add(rectangleButton);

        JButton ellipseButton = new JButton("Ellipse");
        ellipseButton.addActionListener(e -> setCurrentShape(ShapeType.ELLIPSE));
        buttonPanel.add(ellipseButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setCurrentShape(ShapeType shapeType) {
        currentShape = shapeType;
    }

    private void drawShapes(Graphics g) {
        // Draw previously drawn shapes
        // ...

        // Draw the current shape being drawn
        if (currentShape != null) {
            g.setColor(Color.BLACK);
            switch (currentShape) {
                case LINE:
                    // Draw line
                    // ...
                    break;
                case RECTANGLE:
                    // Draw rectangle
                    // ...
                    break;
                case ELLIPSE:
                    // Draw ellipse
                    // ...
                    break;
            }
        }
    }

    private class CanvasMouseListener extends MouseAdapter {
        private int startX, startY;

        @Override
        public void mousePressed(MouseEvent e) {
            startX = e.getX();
            startY = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int endX = e.getX();
            int endY = e.getY();

            // Create the shape based on the current shape type and the start and end coordinates
            // ...

            // Add the shape to the list of drawn shapes
            // ...

            // Repaint the canvas
            canvas.repaint();
        }
    }

    private enum ShapeType {
        LINE,
        RECTANGLE,
        ELLIPSE
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DrawingProgram drawingProgram = new DrawingProgram();
            drawingProgram.setVisible(true);
        });
    }
}

