package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_InserisciArticolo;

/**
 *
 * @author Bosky
 */
public class Listener_AddMaterialeButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_AddMaterialeButton.class);
    private View_InserisciArticolo frame;

    public Listener_AddMaterialeButton(View_InserisciArticolo frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String materiale = this.frame.getSelectedMateriale();
        this.frame.addMateriale(materiale);
    }
}
