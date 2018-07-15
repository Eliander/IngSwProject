package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ArticoloMagazzino;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_RegistraUscita;

/**
 *
 * @author Bosky
 */
public class Listener_AddArticoloUscitaButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_AddArticoloUscitaButton.class);
    private View_RegistraUscita frame;

    public Listener_AddArticoloUscitaButton(View_RegistraUscita frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ArticoloMagazzino artmag = this.frame.getSelectedArticolo();
        if(artmag != null){
            this.frame.addArticolo(artmag);
        }
    }
}
