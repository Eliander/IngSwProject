package view;

import control.Listener_BackToHomeResponsabileButton;
import control.Listener_DettagliOrdineButton;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import model.ArticoloOrdinato;
import model.Indirizzo;
import model.Negozio;
import model.Ordine;
import model.Responsabile;
import model.Utente;

/**
 *
 * @author Bosky
 */
public class View_OrdiniPassati extends JFrame{
    private Utente user = null;
    private JPanel head_panel;
    private JLabel label_title;
    private JPanel btn_panel;
    private JButton button_back;
    private JPanel btn_panel2;
    private JButton button_details;
    private JList list;
    private JScrollPane list_scroller;
    
    public View_OrdiniPassati(Utente user) {
        this.user = user;
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        setResizable(false);
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        label_title = new JLabel();
        label_title.setText("Lista ordini:");

        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToHomeResponsabileButton(this));
        
        head_panel = new JPanel();
        head_panel.setLayout(new GridLayout(2,1));
        btn_panel = new JPanel();
        btn_panel.add(button_back);
        btn_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        head_panel.add(btn_panel);
        head_panel.add(label_title);
        contentPane.add(head_panel, BorderLayout.NORTH);
        
        //TO DO
        //ricavo da DAO (tramite query) il Negozio dell'utente
        //ricavo da DAO la lista di Ordini del Negozio
        Ordine o1 = new Ordine(2345, new Date(),new ArrayList<ArticoloOrdinato>(), new Negozio("DJBKFK23","Bricoman",new Indirizzo("vr","via cc",4),(Responsabile)user));
        Ordine o2 = new Ordine(6364, new Date(),new ArrayList<ArticoloOrdinato>(), new Negozio("DJBKFK23","Bricoman",new Indirizzo("vr","via cc",4),(Responsabile)user));
        Ordine[] ordini = {o1,o2};
        list = new JList(ordini); //passare come parametro l'array di oggetti da DB
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(10);
        list_scroller = new JScrollPane(list);
        list_scroller.setPreferredSize(new Dimension(100,100));
        contentPane.add(list_scroller, BorderLayout.CENTER);
        
        button_details = new JButton();
        button_details.setText("Dettagli ordine");
        button_details.addActionListener(new Listener_DettagliOrdineButton(this));
        
        btn_panel2 = new JPanel();
        btn_panel2.add(button_details);
        btn_panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contentPane.add(btn_panel2, BorderLayout.SOUTH);
        
        this.pack();
    }
    
    public Utente getUser(){
        return this.user;
    }
    
    public Ordine getSelectedOrdine(){
        return (Ordine)this.list.getSelectedValue(); //null se non è selezionato niente
    }
}
