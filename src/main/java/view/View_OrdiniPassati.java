package view;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import model.Utente;

/**
 *
 * @author Bosky
 */
public class View_OrdiniPassati extends JFrame{
    private Utente user = null;
    
    public View_OrdiniPassati(Utente user) {
        this.user = user;
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        setResizable(false);
    }
}
