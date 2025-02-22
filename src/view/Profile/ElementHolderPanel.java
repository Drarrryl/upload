package view.Profile;

import javax.swing.*;

public class ElementHolderPanel extends JPanel {
    public ElementHolderPanel(JComponent parent) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel topLine1 = new JPanel();
        topLine1.setLayout(new BoxLayout(topLine1, BoxLayout.X_AXIS));
        topLine1.add(Box.createHorizontalStrut(50));
        topLine1.add(new JSeparator(SwingConstants.HORIZONTAL));
        topLine1.add(Box.createHorizontalStrut(50));

        this.add(Box.createVerticalStrut(1));
        this.add(topLine1);
        this.add(Box.createVerticalStrut(1));
    }
}
