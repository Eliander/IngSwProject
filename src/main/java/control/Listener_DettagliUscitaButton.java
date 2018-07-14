package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Uscita;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_DettagliUscita;
import view.View_MovimentiMagazzino;

/**
 *
 * @author Bosky
 */
public class Listener_DettagliUscitaButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_DettagliUscitaButton.class);
    private View_MovimentiMagazzino frame;

    public Listener_DettagliUscitaButton(View_MovimentiMagazzino frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Uscita selected_uscita = this.frame.getSelectedUscita();
        if(selected_uscita != null){
            this.frame.dispose();
            Utente user = this.frame.getUser();
            View_DettagliUscita view_DettagliUscita = new View_DettagliUscita(user, selected_uscita);
            view_DettagliUscita.setVisible(true);
        }
    }
}
