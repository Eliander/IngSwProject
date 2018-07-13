package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_HomeResponsabile;
import view.View_OrdiniPassati;

/**
 *
 * @author Bosky
 */
public class Listener_OrdiniPassatiButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_OrdiniPassatiButton.class);
    private View_HomeResponsabile frame;

    public Listener_OrdiniPassatiButton(View_HomeResponsabile frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Utente user = this.frame.getUser();
        this.frame.dispose();
        View_OrdiniPassati view_OrdiniPassati = new View_OrdiniPassati(user);
        view_OrdiniPassati.setVisible(true);
    }
}
