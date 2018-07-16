package view;

import control.Listener_InserisciArticolo;
import control.Listener_LogoutButton;
import control.Listener_MovimentiMagazzinoButton;
import control.Main;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import model.Utente;

/**
 *
 * @author Bosky
 */
public class View_HomeSegretario extends JFrame{
    private JPanel head_panel;
    private JPanel buttons_panel;
    //scritta che dice "ciao, nomeSegretario"
    private JLabel label_nome;
    //bottone che visualizza i movimenti di magazzino
    private JButton button_movimentiMagazzino;
    //bottone che permette di aggiungere un articolo nel catalogo
    private JButton button_inserisciArticolo;
    //bottone per il logout
    private JButton button_logout;
    //nome preso da db
    private Utente user = null;
    
    public View_HomeSegretario(Utente user) {
        this.user = user;
        initComponents();
    }
                     
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(Main.getWindowsSize());
        setResizable(false);
        
        head_panel = new JPanel();
        buttons_panel = new JPanel();
        label_nome = new JLabel();
        button_movimentiMagazzino = new JButton();
        button_inserisciArticolo = new JButton();
        button_logout = new JButton();

        label_nome.setText("Ciao " + user.getNome());

        button_movimentiMagazzino.setText("Movimenti di magazzino");
        button_movimentiMagazzino.addActionListener(new Listener_MovimentiMagazzinoButton(this));

        button_inserisciArticolo.setText("Aggiungi articolo");
        button_inserisciArticolo.addActionListener(new Listener_InserisciArticolo(this));

        button_logout.setText("LOGOUT");
        button_logout.addActionListener(new Listener_LogoutButton(this));

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        head_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
        head_panel.add(label_nome);
        head_panel.add(button_logout);
        contentPane.add(head_panel, BorderLayout.NORTH);
        
        buttons_panel.setLayout(new GridLayout(2,1));
        buttons_panel.add(button_movimentiMagazzino);
        buttons_panel.add(button_inserisciArticolo);
        contentPane.add(buttons_panel, BorderLayout.CENTER);
        
        this.pack();
    }
    
    public Utente getUser(){
        return this.user;
    }
}
