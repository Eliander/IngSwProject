package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_DettagliUscita;
import view.View_MovimentiMagazzino;

/**
 *
 * @author Bosky
 */
public class Listener_BackToMovimentiMagazzino implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_BackToMovimentiMagazzino.class);
    private View_DettagliUscita frame;

    public Listener_BackToMovimentiMagazzino(View_DettagliUscita frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Utente user = this.frame.getUser();
        this.frame.dispose();
        View_MovimentiMagazzino view_MovimentiMagazzino = new View_MovimentiMagazzino(user);
        view_MovimentiMagazzino.setVisible(true);
    }
}
