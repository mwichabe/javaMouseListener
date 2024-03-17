import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CircleMouseListener extends JFrame {
    private int centerX = -1;
    private int centerY = -1;
    private int clickX = -1;
    private int clickY = -1;

    public CircleMouseListener() {
        setTitle("Circle Mouse Listener");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (centerX != -1 && centerY != -1) {
                    g.drawOval(centerX - 50, centerY - 50, 100, 100);
                }
                if (clickX != -1 && clickY != -1 && centerX != -1 && centerY != -1) {
                    g.drawLine(centerX, centerY, clickX, clickY);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (centerX == -1 && centerY == -1) {
                    centerX = e.getX();
                    centerY = e.getY();
                    if (centerX < 50 || centerX > panel.getWidth() - 50 || centerY < 50 || centerY > panel.getHeight() - 50) {
                        JOptionPane.showMessageDialog(null, "Error: Circle not fully contained within panel dimensions. Please select another origin.");
                        centerX = -1;
                        centerY = -1;
                    }
                } else {
                    clickX = e.getX();
                    clickY = e.getY();
                    double distance = Math.sqrt(Math.pow(clickX - centerX, 2) + Math.pow(clickY - centerY, 2));
                    String message = "Distance from circle's origin: " + distance + "\n";
                    if (distance <= 50) {
                        message += "Mouse clicked INSIDE the circle.";
                    } else {
                        message += "Mouse clicked OUTSIDE the circle.";
                    }
                    JOptionPane.showMessageDialog(null, message);
                }
                panel.repaint();
            }
        });

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CircleMouseListener frame = new CircleMouseListener();
            frame.setVisible(true);
        });
    }
}
