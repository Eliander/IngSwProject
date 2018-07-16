package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Ordine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_DettagliOrdine;
import view.View_OrdiniPassati;

public class Listener_DettagliOrdineButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_DettagliOrdineButton.class);
    private View_OrdiniPassati frame;

    public Listener_DettagliOrdineButton(View_OrdiniPassati frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Ordine selected_ordine = this.frame.getSelectedOrdine();
        if(selected_ordine != null){
            this.frame.dispose();
            //passo solo l'ordine poichè da esso mi posso ricavare l'utente
            View_DettagliOrdine view_DettagliOrdine = new View_DettagliOrdine(selected_ordine);
            view_DettagliOrdine.setVisible(true);
        }
    }
}
