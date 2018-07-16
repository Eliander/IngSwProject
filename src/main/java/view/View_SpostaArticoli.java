package view;

import control.Listener_BackToHomeMagazziniereButton;
import control.Listener_SpostaButton;
import control.Main;
import dao.DAOSettings;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
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
import model.ArticoloMagazzino;
import model.Utente;

/**
 *
 * @author Bosky
 */
public class View_SpostaArticoli extends JFrame{
    private Utente user = null;
    
    private JLabel label_title;
    private JPanel btn_panel;
    private JButton button_back;
    private JLabel label_sel_articolo;
    private JList list_articoli;
    private JScrollPane list_articoli_scroller;
    private JLabel label_scaffale;
    private JSpinner text_scaffale;
    private JLabel label_ripiano;
    private JSpinner text_ripiano;
    private JButton button_sposta;
    private JPanel btn_panel2;
    
    private static DAOSettings DAO = Main.getDAO();
    
    public View_SpostaArticoli(Utente user) {
        this.user = user;
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(Main.getWindowsSize());
        setResizable(false);
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(9,1));

        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToHomeMagazziniereButton(this));
        btn_panel = new JPanel();
        btn_panel.add(button_back);
        btn_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(btn_panel);
        
        label_title = new JLabel();
        label_title.setText("SPOSTA ARTICOLI");
        contentPane.add(label_title);
        
        label_sel_articolo = new JLabel();
        label_sel_articolo.setText("Seleziona articolo:");
        contentPane.add(label_sel_articolo);
        
        //ricavo da DAO (tramite query) la lista di tutti gli ArticoloMagazzino
        ArrayList<ArticoloMagazzino> artmag = DAO.getArticoloMagazzinoDAO().getAllArticoliMagazzino();
        ArticoloMagazzino[] articoli_mag = new ArticoloMagazzino[artmag.size()];
        articoli_mag = artmag.toArray(articoli_mag);
        list_articoli = new JList(articoli_mag);
        list_articoli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_articoli.setLayoutOrientation(JList.VERTICAL);
        list_articoli.setVisibleRowCount(10);
        list_articoli_scroller = new JScrollPane(list_articoli);
        list_articoli_scroller.setPreferredSize(new Dimension(100,100));
        contentPane.add(list_articoli_scroller);
        
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
        
        button_sposta = new JButton();
        button_sposta.setText("Sposta");
        button_sposta.addActionListener(new Listener_SpostaButton(this));
        btn_panel2 = new JPanel();
        btn_panel2.add(button_sposta);
        btn_panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contentPane.add(btn_panel2);
        
        this.pack();
    }
    
    public Utente getUser(){
        return this.user;
    }
    
    public ArticoloMagazzino getSelectedArticolo(){
        return (ArticoloMagazzino)this.list_articoli.getSelectedValue();
    }
    
    public int getSelectedScaffale(){
        return (Integer)this.text_scaffale.getValue();
    }
    
    public int getSelectedRipiano(){
        return (Integer)this.text_ripiano.getValue();
    }
}
