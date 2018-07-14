package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_HomeSegretario;
import view.View_InserisciArticolo;
import view.View_MovimentiMagazzino;

/**
 *
 * @author Bosky
 */
public class Listener_BackToHomeSegretarioButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_BackToHomeSegretarioButton.class);
    private JFrame frame;

    public Listener_BackToHomeSegretarioButton(View_MovimentiMagazzino frame) {
        this.frame = frame;
    }
    
    public Listener_BackToHomeSegretarioButton(View_InserisciArticolo frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Utente user;
        if(this.frame instanceof View_MovimentiMagazzino){
            user = ((View_MovimentiMagazzino)this.frame).getUser();
        }
        else{
            user = ((View_InserisciArticolo)this.frame).getUser();
        }
        this.frame.dispose();
        View_HomeSegretario view_HomeSegretario = new View_HomeSegretario(user);
        view_HomeSegretario.setVisible(true);
    }
}
