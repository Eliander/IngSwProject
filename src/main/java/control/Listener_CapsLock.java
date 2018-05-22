package control;

import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;

/**
 *
 * @author Eliander
 */
public class Listener_CapsLock implements FocusListener {

    private JLabel capsLock = new JLabel("Bloc Maiusc attivato");

    public JLabel getCapsLock() {
        return capsLock;
    }

    public void setCapsLock(JLabel capsLock) {
        this.capsLock = capsLock;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)) {
            capsLock.setVisible(true);
        } else{
            capsLock.setVisible(false);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        focusGained(e);
    }

}
