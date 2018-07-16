package view;

import control.Listener_AddArticoloUscitaButton;
import control.Listener_AddUscitaButton;
import control.Listener_BackToHomeMagazziniereButton;
import control.Main;
import dao.DAOSettings;
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
import model.ArticoloMagazzino;
import model.Ordine;
import model.Spedizioniere;
import model.Uscita;
import model.Utente;

/**
 *
 * @author Bosky
 */
public class View_RegistraUscita extends JFrame{
    private Utente user = null;
    private Uscita uscita;
    
    private JLabel label_title;
    private JPanel btn_panel;
    private JButton button_back;
    private JLabel label_sel_ordine;
    private JList list_ordini;
    private JScrollPane list_ordini_scroller;
    private JLabel label_sel_articoli;
    private JList list_articoli_mag;
    private JScrollPane list_articoli_mag_scroller;
    private JButton button_add_articolo;
    private JPanel btn_panel2;
    private JList list_selected_art_mag;
    private JScrollPane list_selected_art_mag_scroller;
    private JLabel label_sel_spedizioniere;
    private JList list_spedizionieri;
    private JScrollPane list_spedizionieri_scroller;
    private JButton button_add_uscita;
    private JPanel btn_panel3;
    
    private static DAOSettings DAO = Main.getDAO();
    
    public View_RegistraUscita(Utente user) {
        this.user = user;
        this.uscita = new Uscita(new Date(), new ArrayList<>(), null, null);
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        setResizable(false);
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(11,1));

        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToHomeMagazziniereButton(this));
        btn_panel = new JPanel();
        btn_panel.add(button_back);
        btn_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(btn_panel);
        
        label_title = new JLabel();
        label_title.setText("AGGIUNGI USCITA");
        contentPane.add(label_title);
        
        label_sel_ordine = new JLabel();
        label_sel_ordine.setText("Seleziona ordine:");
        contentPane.add(label_sel_ordine);
        
        //ricavo da DAO (tramite query) la lista di tutti gli Ordini
        ArrayList<Ordine> ord = DAO.getOrdineDAO().getOrdiniDaCompletare();
        Ordine[] ordini = new Ordine[ord.size()];
        ordini = ord.toArray(ordini);
        list_ordini = new JList(ordini);
        list_ordini.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_ordini.setLayoutOrientation(JList.VERTICAL);
        list_ordini.setVisibleRowCount(10);
        list_ordini_scroller = new JScrollPane(list_ordini);
        list_ordini_scroller.setPreferredSize(new Dimension(100,100));
        contentPane.add(list_ordini_scroller);
        
        label_sel_articoli = new JLabel();
        label_sel_articoli.setText("Seleziona articoli dell'uscita:");
        contentPane.add(label_sel_articoli);
        
        //ricavo da DAO (tramite query) la lista di tutti gli ArticoloMagazzino
        ArrayList<ArticoloMagazzino> artmag = DAO.getArticoloMagazzinoDAO().getAllArticoliMagazzino();
        ArticoloMagazzino[] articolimag = new ArticoloMagazzino[artmag.size()];
        articolimag = artmag.toArray(articolimag);
        list_articoli_mag = new JList(articolimag);
        list_articoli_mag.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_articoli_mag.setLayoutOrientation(JList.VERTICAL);
        list_articoli_mag.setVisibleRowCount(10);
        list_articoli_mag_scroller = new JScrollPane(list_articoli_mag);
        list_articoli_mag_scroller.setPreferredSize(new Dimension(100,100));
        contentPane.add(list_articoli_mag_scroller);
        
        button_add_articolo = new JButton();
        button_add_articolo.setText("Aggiungi articolo");
        button_add_articolo.addActionListener(new Listener_AddArticoloUscitaButton(this));
        btn_panel2 = new JPanel();
        btn_panel2.add(button_add_articolo);
        btn_panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(btn_panel2);
        
        list_selected_art_mag = new JList();
        list_selected_art_mag.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_selected_art_mag.setLayoutOrientation(JList.VERTICAL);
        list_selected_art_mag.setVisibleRowCount(10);
        list_selected_art_mag_scroller = new JScrollPane(list_selected_art_mag);
        list_selected_art_mag_scroller.setPreferredSize(new Dimension(100,100));
        contentPane.add(list_selected_art_mag_scroller);
        
        label_sel_spedizioniere = new JLabel();
        label_sel_spedizioniere.setText("Seleziona spedizioniere:");
        contentPane.add(label_sel_spedizioniere);
        
        //ricavo da DAO (tramite query) la lista di tutti i Spedizioniere
        ArrayList<Spedizioniere> sped = DAO.getSpedizioniereDAO().getSpedizionieri();
        Spedizioniere[] spedizionieri = new Spedizioniere[sped.size()];
        spedizionieri = sped.toArray(spedizionieri);
        list_spedizionieri = new JList(spedizionieri); //passare come parametro l'array spedizionieri
        list_spedizionieri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_spedizionieri.setLayoutOrientation(JList.VERTICAL);
        list_spedizionieri.setVisibleRowCount(10);
        list_spedizionieri_scroller = new JScrollPane(list_spedizionieri);
        list_spedizionieri_scroller.setPreferredSize(new Dimension(100,100));
        contentPane.add(list_spedizionieri_scroller);
        
        button_add_uscita = new JButton();
        button_add_uscita.setText("Crea uscita");
        button_add_uscita.addActionListener(new Listener_AddUscitaButton(this));
        btn_panel3 = new JPanel();
        btn_panel3.add(button_add_uscita);
        btn_panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contentPane.add(btn_panel3);
        
        this.pack();
    }
    
    public Utente getUser(){
        return this.user;
    }
    
    public Uscita getUscita(){
        return this.uscita;
    }
    
    public ArticoloMagazzino getSelectedArticolo(){
        return (ArticoloMagazzino)this.list_articoli_mag.getSelectedValue();
    }
    
    public void addArticolo(ArticoloMagazzino artmag){
        if(!this.uscita.getArticoli().contains(artmag)){
            this.uscita.addArticolo(artmag);
            System.out.println("Articolo aggiunto");
        }
        //aggiorno la lista degli articoli aggiunti
        ArticoloMagazzino[] articoli_sel = new ArticoloMagazzino[this.uscita.getArticoli().size()];
        articoli_sel = this.uscita.getArticoli().toArray(articoli_sel);
        this.list_selected_art_mag.setListData(articoli_sel);
    }
    
    public Ordine getSelectedOrdine(){
        return (Ordine)this.list_ordini.getSelectedValue();
    }
    
    public Spedizioniere getSelectedSpedizioniere(){
        return (Spedizioniere)this.list_spedizionieri.getSelectedValue();
    }
}
