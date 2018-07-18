package view;

import control.Listener_CreaOrdineButton;
import control.Listener_LogoutButton;
import control.Listener_OrdiniPassatiButton;
import control.Main;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.WindowConstants;
import model.Utente;

public class View_HomeResponsabile extends JFrame{
    
    private Utente user = null;
    
    private Box head_box;
    private JPanel head_panel;
    private JPanel buttons_panel;
    //scritta che dice "ciao, nomeResponsabile"
    private JLabel label_nome;
    //bottone che visualizza gli ordini passati
    private JButton button_ordiniPassati;
    //bottone che da accesso alle funzioni di uscita
    private JButton button_creaOrdine;
    //bottone per il logout
    private JButton button_logout;
    
    public View_HomeResponsabile(Utente user) {
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
        button_ordiniPassati = new JButton();
        button_creaOrdine = new JButton();
        button_logout = new JButton();

        label_nome.setText("Ciao " + user.getNome());

        button_ordiniPassati.setText("Ordini passati");
        button_ordiniPassati.addActionListener(new Listener_OrdiniPassatiButton(this));

        button_creaOrdine.setText("Crea ordine");
        button_creaOrdine.addActionListener(new Listener_CreaOrdineButton(this));

        button_logout.setText("LOGOUT");
        button_logout.addActionListener(new Listener_LogoutButton(this));

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        head_box = Box.createHorizontalBox();
        head_box.add(Box.createRigidArea(new Dimension(10,50)));
        head_box.add(label_nome);
        head_box.add(Box.createHorizontalGlue());
        head_box.add(button_logout);
        head_box.add(Box.createRigidArea(new Dimension(10,50)));
        contentPane.add(head_box, BorderLayout.NORTH);
        
        buttons_panel.setLayout(new GridLayout(2,1));
        buttons_panel.add(button_ordiniPassati);
        buttons_panel.add(button_creaOrdine);
        contentPane.add(buttons_panel, BorderLayout.CENTER);
        
        this.pack();
    }
    
    public Utente getUser(){
        return this.user;
    }
}
