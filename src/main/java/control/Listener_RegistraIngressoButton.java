package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_HomeMagazziniere;
import view.View_RegistraIngresso;

/**
 *
 * @author Bosky
 */
public class Listener_RegistraIngressoButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_RegistraIngressoButton.class);
    private View_HomeMagazziniere frame;

    public Listener_RegistraIngressoButton(View_HomeMagazziniere frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Utente user = this.frame.getUser();
        this.frame.dispose();
        View_RegistraIngresso view_RegistraIngresso = new View_RegistraIngresso(user);
        view_RegistraIngresso.setVisible(true);
    }
}
