package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_HomeSegretario;
import view.View_InserisciArticolo;

/**
 *
 * @author Bosky
 */
public class Listener_InserisciArticolo implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_InserisciArticolo.class);
    private View_HomeSegretario frame;

    public Listener_InserisciArticolo(View_HomeSegretario frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Utente user = this.frame.getUser();
        this.frame.dispose();
        View_InserisciArticolo view_InserisciArticolo = new View_InserisciArticolo(user);
        view_InserisciArticolo.setVisible(true);
    }
}
