package control;

import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.ArticoloOrdinato;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_RegistraUscita;

/**
 *
 * @author Bosky
 */
public class Listener_SelectionOrdine implements ListSelectionListener{
    
    private final static Logger log = LogManager.getLogger(Listener_SelectionOrdine.class);
    private View_RegistraUscita frame;
    
    public Listener_SelectionOrdine(View_RegistraUscita frame){
        this.frame = frame;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        ArrayList<ArticoloOrdinato> artord = this.frame.getSelectedOrdine().getArticoli();
        ArticoloOrdinato[] art_ordinati = new ArticoloOrdinato[artord.size()];
        art_ordinati = artord.toArray(art_ordinati);
        this.frame.updateListArticoliOrdinati(art_ordinati);
    }
}
