package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_CreaOrdine;
import view.View_HomeResponsabile;

/**
 *
 * @author Bosky
 */
public class Listener_CreaOrdineButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_CreaOrdineButton.class);
    private View_HomeResponsabile frame;

    public Listener_CreaOrdineButton(View_HomeResponsabile frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Utente user = this.frame.getUser();
        this.frame.dispose();
        View_CreaOrdine view_CreaOrdine = new View_CreaOrdine(user);
        view_CreaOrdine.setVisible(true);
    }
}
