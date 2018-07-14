package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_HomeMagazziniere;
import view.View_SpostaArticoli;

/**
 *
 * @author Bosky
 */
public class Listener_SpostaArticoliButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_SpostaArticoliButton.class);
    private View_HomeMagazziniere frame;

    public Listener_SpostaArticoliButton(View_HomeMagazziniere frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Utente user = this.frame.getUser();
        this.frame.dispose();
        View_SpostaArticoli view_SpostaArticoli = new View_SpostaArticoli(user);
        view_SpostaArticoli.setVisible(true);
    }
}
