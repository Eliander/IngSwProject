package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import model.Articolo;
import model.ArticoloOrdinato;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_CreaOrdine;

/**
 *
 * @author Bosky
 */
public class Listener_AddArticoloOrdineButton implements ActionListener {

    private final static Logger log = LogManager.getLogger(Listener_AddArticoloOrdineButton.class);
    private View_CreaOrdine frame;

    public Listener_AddArticoloOrdineButton(View_CreaOrdine frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Articolo art = this.frame.getSelectedArticolo();
        int qty = this.frame.getQuantity();
        if (art != null && qty > 0) {
            int qtyInMagazzino = Main.getDAO().getArticoloMagazzinoDAO().countArticoloMagazzino(art);
            int oldQty = getQuantity(art);
            if ((qty + oldQty) <= qtyInMagazzino) {
                ArticoloOrdinato artord = new ArticoloOrdinato(art, qty);
                this.frame.addArticoloOrdine(artord);
                System.out.println("Articolo aggiunto");
            }
        }
    }

    private int getQuantity(Articolo a) {
        JList listSelArticoli = frame.getListSelArticoli();
        for (int i = 0; i < listSelArticoli.getModel().getSize(); i++) {
            ArticoloOrdinato articolo = (ArticoloOrdinato) listSelArticoli.getModel().getElementAt(i);
            if (articolo.getNome().equals(a.getNome())) {
                return articolo.getQuantita();
            }
        }
        return 0;
    }
}
