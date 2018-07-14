package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_DettagliOrdine;
import view.View_OrdiniPassati;

/**
 *
 * @author Bosky
 */
public class Listener_BackToOrdiniPassati implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_BackToOrdiniPassati.class);
    private View_DettagliOrdine frame;

    public Listener_BackToOrdiniPassati(View_DettagliOrdine frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Utente user = this.frame.getOrdine().getNegozio().getResponsabile();
        this.frame.dispose();
        View_OrdiniPassati view_OrdiniPassati = new View_OrdiniPassati(user);
        view_OrdiniPassati.setVisible(true);
    }
}
