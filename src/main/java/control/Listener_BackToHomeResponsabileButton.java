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
public class Listener_BackToHomeResponsabileButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_BackToHomeResponsabileButton.class);
    private View_OrdiniPassati frame;

    public Listener_BackToHomeResponsabileButton(View_OrdiniPassati frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Utente user = this.frame.getUser();
        this.frame.dispose();
        View_HomeResponsabile view_HomeResponsabile = new View_HomeResponsabile(user);
        view_HomeResponsabile.setVisible(true);
    }
}
