package view;

import control.Listener_BackToHomeMagazziniereButton;
import control.Main;
import dao.DAOSettings;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import model.Articolo;
import model.Ingresso;
import model.Utente;

/**
 *
 * @author Bosky
 */
public class View_RegistraIngresso extends JFrame{
    private Utente user = null;
    private Ingresso ingresso;
    
    private JLabel label_title;
    private JPanel btn_panel;
    private JButton button_back;
    private JList list_articoli;
    private JScrollPane list_articoli_scroller;
    private JLabel label_codice;
    private JTextField text_codice;
    private JLabel label_data;
    private JFormattedTextField text_data;
    private JLabel label_scaffale;
    private JSpinner text_scaffale;
    private JLabel label_ripiano;
    private JSpinner text_ripiano;
    private JButton button_addArticolo;
    private JPanel btn_panel2;
    private JList list_articoli_aggiunti;
    private JScrollPane list_articoli_aggiunti_scroller;
    private JButton button_addIngresso;
    private JPanel btn_panel3;
    
    private static DAOSettings DAO = Main.getDAO();
    
    public View_RegistraIngresso(Utente user) {
        this.user = user;
        this.ingresso = new Ingresso(0, new Date(), new ArrayList<>());
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        setResizable(false);
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(14,1));

        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToHomeMagazziniereButton(this));
        btn_panel = new JPanel();
        btn_panel.add(button_back);
        btn_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(btn_panel);
        
        label_title = new JLabel();
        label_title.setText("AGGIUNGI ARTICOLI INGRESSO");
        contentPane.add(label_title);
        
        //ricavo da DAO (tramite query) la lista di Articoli del catalogo
        ArrayList<Articolo> art = DAO.getArticoloDAO().getAllArticoli();
        Articolo[] articoli = new Articolo[art.size()];
        articoli = art.toArray(articoli);
        list_articoli = new JList(articoli); //passare come parametro l'array di oggetti da DB
        list_articoli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_articoli.setLayoutOrientation(JList.VERTICAL);
        list_articoli.setVisibleRowCount(10);
        list_articoli_scroller = new JScrollPane(list_articoli);
        list_articoli_scroller.setPreferredSize(new Dimension(100,100));
        contentPane.add(list_articoli_scroller);
        
        label_codice = new JLabel();
        label_codice.setText("Codice:");
        contentPane.add(label_codice);
        text_codice = new JTextField();
        contentPane.add(text_codice);
        
        label_data = new JLabel();
        label_data.setText("Data di produzione:");
        contentPane.add(label_data);
        DateFormat format = new SimpleDateFormat("yyyy--MMMM--dd");
        text_data = new JFormattedTextField(format);
        contentPane.add(text_data);
        
        label_scaffale = new JLabel();
        label_scaffale.setText("Scaffale:");
        contentPane.add(label_scaffale);
        SpinnerModel model = new SpinnerNumberModel(1,1,1000,1);
        text_scaffale = new JSpinner(model);
        contentPane.add(text_scaffale);
        
        label_ripiano = new JLabel();
        label_ripiano.setText("Ripiano:");
        contentPane.add(label_ripiano);
        SpinnerModel model2 = new SpinnerNumberModel(1,1,1000,1);
        text_ripiano = new JSpinner(model2);
        contentPane.add(text_ripiano);
        
        button_addArticolo = new JButton();
        button_addArticolo.setText("Aggiungi articolo");
        //button_addArticolo.addActionListener(new Listener_AddArticoloIngressoButton(this));
        btn_panel2 = new JPanel();
        btn_panel2.add(button_addArticolo);
        btn_panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contentPane.add(btn_panel2);
        
        list_articoli_aggiunti = new JList(); //passare come parametro l'array di oggetti da DB
        list_articoli_aggiunti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_articoli_aggiunti.setLayoutOrientation(JList.VERTICAL);
        list_articoli_aggiunti.setVisibleRowCount(10);
        list_articoli_aggiunti_scroller = new JScrollPane(list_articoli_aggiunti);
        list_articoli_aggiunti_scroller.setPreferredSize(new Dimension(100,100));
        contentPane.add(list_articoli_aggiunti_scroller);
        
        button_addIngresso = new JButton();
        button_addIngresso.setText("Crea ingresso");
        //button_addIngresso.addActionListener(new Listener_AddIngressoButton(this));
        btn_panel3 = new JPanel();
        btn_panel3.add(button_addIngresso);
        btn_panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contentPane.add(btn_panel3);
        
        this.pack();
    }
    
    public Utente getUser(){
        return this.user;
    }
}
