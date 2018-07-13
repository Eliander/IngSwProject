package view;

import control.Listener_BackToHomeResponsabileButton;
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
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import model.Articolo;
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
public class View_CreaOrdine extends JFrame{
    private Utente user = null;
    private JLabel label_title;
    private JPanel btn_panel;
    private JButton button_back;
    private JPanel head_panel;
    private JList list;
    private JScrollPane list_scroller;
    private JPanel btn_panel2;
    private JButton button_add;
    private JButton button_confirm;
    private JSpinner spinner_qty;
    private JLabel label_qty;
    
    public View_CreaOrdine(Utente user) {
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
        label_title.setText("Articoli:");

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
        //ricavo da DAO (tramite query) la lista di Articoli del catalogo
        Articolo a1 = new Articolo("Scarpa ZX","Scarpa robusta","Running","Abbigliamento",new ArrayList<String>(),15);
        Articolo a2 = new Articolo("Pallone JF3","Pallone taglia 5","Calcio","Accessori",new ArrayList<String>(),8);
        ArrayList<Articolo> art = new ArrayList<>();
        art.add(a1);
        art.add(a2);
        Articolo[] articoli = new Articolo[art.size()];
        articoli = art.toArray(articoli);
        list = new JList(articoli); //passare come parametro l'array di oggetti da DB
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(10);
        list_scroller = new JScrollPane(list);
        list_scroller.setPreferredSize(new Dimension(100,100));
        contentPane.add(list_scroller, BorderLayout.CENTER);
        
        label_qty = new JLabel();
        label_qty.setText("Quantità:");
        
        SpinnerModel model = new SpinnerNumberModel(0,0,1000,1);
        spinner_qty = new JSpinner(model);
        
        button_add = new JButton();
        button_add.setText("Aggiungi");
        //button_add.addActionListener(new Listener_AddArticoloOrdineButton(this));
        
        button_confirm = new JButton();
        button_confirm.setText("Conferma");
        //button_confirm.addActionListener(new Listener_ConfermaOrdineButton(this));
        
        btn_panel2 = new JPanel();
        btn_panel2.add(label_qty);
        btn_panel2.add(spinner_qty);
        btn_panel2.add(button_add);
        btn_panel2.add(button_confirm);
        btn_panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contentPane.add(btn_panel2, BorderLayout.SOUTH);
        
        this.pack();
    }
    
    public Utente getUser(){
        return this.user;
    }
}
