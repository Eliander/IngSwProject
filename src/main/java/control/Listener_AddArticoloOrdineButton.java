package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Articolo;
import model.ArticoloOrdinato;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_CreaOrdine;

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
            /*//Controllo per far sì che il responsabile non può ordinare una quantità di articoli non presente in magazzino
            int qtyInMagazzino = Main.getDAO().getArticoloMagazzinoDAO().countArticoloMagazzino(art);
            int oldQty = getQuantity(art);
            if ((qty + oldQty) <= qtyInMagazzino) {*/
                ArticoloOrdinato artord = new ArticoloOrdinato(art, qty);
                this.frame.addArticoloOrdine(artord);
                log.info("Articolo aggiunto");
            /*}
            else{
                log.error("ERRORE: La quantità selezionata non è presente in magazzino!");
            }*/
        }
        else{
            log.error("ERRORE: Selezionare un articolo e una quantità > 0");
        }
    }

    /*//Dato un articolo, ritorna la quantità da ordinare di quell'articolo presente nella lista degli articoli dell'ordine
    private int getQuantity(Articolo a) {
        JList listSelArticoli = frame.getListSelArticoli();
        for (int i = 0; i < listSelArticoli.getModel().getSize(); i++) {
            ArticoloOrdinato articolo = (ArticoloOrdinato) listSelArticoli.getModel().getElementAt(i);
            if (articolo.getNome().equals(a.getNome())) {
                return articolo.getQuantita();
            }
        }
        return 0;
    }*/
}
