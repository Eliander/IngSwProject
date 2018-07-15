package view;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import model.Utente;

/**
 *
 * @author Bosky
 */
public class View_SpostaArticoli extends JFrame{
    private Utente user = null;
    
    public View_SpostaArticoli(Utente user) {
        this.user = user;
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        setResizable(false);
        
        this.pack();
    }
    
    public Utente getUser(){
        return this.user;
    }
}
