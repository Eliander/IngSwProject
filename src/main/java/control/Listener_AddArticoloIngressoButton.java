package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import model.Articolo;
import model.ArticoloMagazzino;
import model.ArticoloOrdinato;
import model.Posizione;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_RegistraIngresso;

/**
 *
 * @author Bosky
 */
public class Listener_AddArticoloIngressoButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_AddArticoloIngressoButton.class);
    private View_RegistraIngresso frame;

    public Listener_AddArticoloIngressoButton(View_RegistraIngresso frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Articolo art = this.frame.getSelectedArticolo();
        Date data = this.frame.getSelectedData();
        int scaffale = this.frame.getSelectedScaffale();
        int ripiano = this.frame.getSelectedRipiano();
        if(art != null){
            ArticoloMagazzino artmag = new ArticoloMagazzino(art,0,data,new Posizione(scaffale,ripiano));
            this.frame.addArticolo(artmag);
        }
    }
}
