package view.Profile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ElementPanel extends JPanel {
    public JButton button;
    public int space;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    public ElementPanel(String label, String buttonLabel, BufferedImage enterIcon) {
        JLabel newLabel = new JLabel(label);
        if (enterIcon != null) {
            button = new JButton(new ImageIcon(enterIcon));
        } else {
            button = new JButton(buttonLabel);
        }
        newLabel.setVerticalAlignment(SwingConstants.TOP);

        JPanel line = new JPanel();
        line.setLayout(new BoxLayout(line, BoxLayout.Y_AXIS));
        line.add(Box.createVerticalStrut(25));
        line.add(new JSeparator(SwingConstants.VERTICAL));
        line.add(Box.createVerticalStrut(25));

        JPanel spacing = new JPanel();
        spacing.setLayout(new BoxLayout(spacing, BoxLayout.X_AXIS));
        spacing.add(Box.createHorizontalStrut(space));
        spacing.add(line);
        spacing.add(Box.createHorizontalStrut(1));

        addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("space")) {
                    System.out.println("Space event!");
                    spacing.remove(0);
                    spacing.add(Box.createHorizontalStrut(space), 0);
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.add(newLabel);
        this.add(spacing);
        this.add(button);
        //this.setBorder(new LineBorder(Color.WHITE));
    }

    public void setSpace(int newSpace) {
        int oldSpace = space;
        space = newSpace;
        pcs.firePropertyChange("space", oldSpace, newSpace);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
