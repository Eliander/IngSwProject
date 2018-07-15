package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_HomeMagazziniere;
import view.View_RegistraUscita;

/**
 *
 * @author Bosky
 */
public class Listener_RegistraUscitaButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_RegistraUscitaButton.class);
    private View_HomeMagazziniere frame;

    public Listener_RegistraUscitaButton(View_HomeMagazziniere frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Utente user = this.frame.getUser();
        this.frame.dispose();
        View_RegistraUscita view_RegistraUscita = new View_RegistraUscita(user);
        view_RegistraUscita.setVisible(true);
    }
}
