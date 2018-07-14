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
        int codice = this.frame.getSelectedCodice();
        Date data = this.frame.getSelectedData();
        int scaffale = this.frame.getSelectedScaffale();
        int ripiano = this.frame.getSelectedRipiano();
        if(art != null && codice>0){
            ArticoloMagazzino artmag = new ArticoloMagazzino(art,codice,data,new Posizione(scaffale,ripiano),-1,-1);
            this.frame.addArticolo(artmag);
            System.out.println("Articolo aggiunto");
        }
    }
}
