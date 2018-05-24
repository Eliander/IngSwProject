package control;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Eliander
 */
public class Listener_CapsLock extends AbstractAction {

    private JLabel caps = new JLabel("Bloc Maiusc attivato");

    public Listener_CapsLock() {
        caps.setForeground(Color.red);
    }

    public JLabel getCaps() {
        return caps;
    }

    public void setCaps(JLabel caps) {
        this.caps = caps;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JPanel panel = (JPanel) e.getSource();
            Component[] components = panel.getComponents();
            for (Component c : components) {
                if (c instanceof JLabel) {
                    if (((JLabel) c).getText().equals(caps.getText())) {
                        if (Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)) {
                            c.setVisible(true);
                        } else {
                            c.setVisible(false);
                        }
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println(e);
        }
    }

}
