package view;

import control.Listener_AddArticoloUscitaButton;
import control.Listener_AddUscitaButton;
import control.Listener_BackToHomeMagazziniereButton;
import control.Listener_SelectionOrdine;
import control.Main;
import dao.DAOSettings;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import model.ArticoloMagazzino;
import model.ArticoloOrdinato;
import model.Ordine;
import model.Spedizioniere;
import model.Uscita;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class View_RegistraUscita extends JFrame{
    private Utente user = null;
    private Uscita uscita;
    
    private Box north_box;
    private Box central_box;
    private Box south_box;
    private JLabel label_title;
    private JButton button_back;
    private JPanel sel_ordine_panel;
    private JLabel label_sel_ordine;
    private JList list_ordini;
    private JScrollPane list_ordini_scroller;
    private JPanel art_ordinati_panel;
    private JLabel label_art_ordinati;
    private JList list_art_ordinati;
    private JScrollPane list_art_ordinati_scroller;
    private JPanel sel_articoli_panel;
    private JLabel label_sel_articoli;
    private JList list_articoli_mag;
    private JScrollPane list_articoli_mag_scroller;
    private JButton button_add_articolo;
    private JPanel btn_panel;
    private JList list_selected_art_mag;
    private JScrollPane list_selected_art_mag_scroller;
    private JPanel sel_spedizioniere_panel;
    private JLabel label_sel_spedizioniere;
    private JList list_spedizionieri;
    private JScrollPane list_spedizionieri_scroller;
    private JButton button_add_uscita;
    
    private final static Logger log = LogManager.getLogger(View_RegistraUscita.class);
    private static DAOSettings DAO = Main.getDAO();
    
    public View_RegistraUscita(Utente user) {
        this.user = user;
        this.uscita = new Uscita(new Date(), new ArrayList<>(), null, null);
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(Main.getWindowsSize());
        setResizable(false);
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        north_box = Box.createHorizontalBox();
        north_box.add(Box.createRigidArea(new Dimension(10,50)));
        label_title = new JLabel();
        label_title.setText("REGISTRA USCITA");
        north_box.add(label_title);
        
        contentPane.add(north_box, BorderLayout.NORTH);

        central_box = Box.createVerticalBox();
        
        sel_ordine_panel = new JPanel();
        sel_ordine_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label_sel_ordine = new JLabel();
        label_sel_ordine.setText("Seleziona ordine:");
        sel_ordine_panel.add(label_sel_ordine);
        central_box.add(sel_ordine_panel);
        
        //ricavo da DAO (tramite query) la lista di tutti gli Ordini
        ArrayList<Ordine> ord = DAO.getOrdineDAO().getOrdiniDaCompletare();
        Ordine[] ordini = new Ordine[ord.size()];
        ordini = ord.toArray(ordini);
        list_ordini = new JList(ordini);
        list_ordini.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_ordini.setLayoutOrientation(JList.VERTICAL);
        list_ordini.setVisibleRowCount(10);
        ListSelectionModel list_ordini_model = list_ordini.getSelectionModel();
        list_ordini_model.addListSelectionListener(new Listener_SelectionOrdine(this));
        list_ordini_scroller = new JScrollPane(list_ordini);
        list_ordini_scroller.setPreferredSize(new Dimension(100,100));
        central_box.add(list_ordini_scroller);
        
        art_ordinati_panel = new JPanel();
        art_ordinati_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label_art_ordinati = new JLabel();
        label_art_ordinati.setText("Articoli ordinati:");
        art_ordinati_panel.add(label_art_ordinati);
        central_box.add(art_ordinati_panel);
        
        list_art_ordinati = new JList();
        list_art_ordinati.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_art_ordinati.setLayoutOrientation(JList.VERTICAL);
        list_art_ordinati.setVisibleRowCount(10);
        list_art_ordinati_scroller = new JScrollPane(list_art_ordinati);
        list_art_ordinati_scroller.setPreferredSize(new Dimension(100,100));
        central_box.add(list_art_ordinati_scroller);
        
        sel_articoli_panel = new JPanel();
        sel_articoli_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label_sel_articoli = new JLabel();
        label_sel_articoli.setText("Seleziona articoli dell'uscita:");
        sel_articoli_panel.add(label_sel_articoli);
        central_box.add(sel_articoli_panel);
        
        //ricavo da DAO (tramite query) la lista di tutti gli ArticoloMagazzino
        ArrayList<ArticoloMagazzino> artmag = DAO.getArticoloMagazzinoDAO().getArticoliMagazzino();
        ArticoloMagazzino[] articolimag = new ArticoloMagazzino[artmag.size()];
        articolimag = artmag.toArray(articolimag);
        list_articoli_mag = new JList(articolimag);
        list_articoli_mag.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_articoli_mag.setLayoutOrientation(JList.VERTICAL);
        list_articoli_mag.setVisibleRowCount(10);
        list_articoli_mag_scroller = new JScrollPane(list_articoli_mag);
        list_articoli_mag_scroller.setPreferredSize(new Dimension(100,100));
        central_box.add(list_articoli_mag_scroller);
        
        button_add_articolo = new JButton();
        button_add_articolo.setText("Aggiungi articolo:");
        button_add_articolo.addActionListener(new Listener_AddArticoloUscitaButton(this));
        btn_panel = new JPanel();
        btn_panel.add(button_add_articolo);
        btn_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        central_box.add(btn_panel);
        
        list_selected_art_mag = new JList();
        list_selected_art_mag.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_selected_art_mag.setLayoutOrientation(JList.VERTICAL);
        list_selected_art_mag.setVisibleRowCount(10);
        list_selected_art_mag_scroller = new JScrollPane(list_selected_art_mag);
        list_selected_art_mag_scroller.setPreferredSize(new Dimension(100,100));
        central_box.add(list_selected_art_mag_scroller);
        
        sel_spedizioniere_panel = new JPanel();
        sel_spedizioniere_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label_sel_spedizioniere = new JLabel();
        label_sel_spedizioniere.setText("Seleziona spedizioniere:");
        sel_spedizioniere_panel.add(label_sel_spedizioniere);
        central_box.add(sel_spedizioniere_panel);
        
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
        central_box.add(list_spedizionieri_scroller);
        
        contentPane.add(central_box, BorderLayout.CENTER);
        
        south_box = Box.createHorizontalBox();
        south_box.add(Box.createRigidArea(new Dimension(10,50)));
        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToHomeMagazziniereButton(this));
        south_box.add(button_back);
        south_box.add(Box.createHorizontalGlue());
        button_add_uscita = new JButton();
        button_add_uscita.setText("Crea uscita");
        button_add_uscita.addActionListener(new Listener_AddUscitaButton(this));
        south_box.add(button_add_uscita);
        south_box.add(Box.createRigidArea(new Dimension(10,50)));
        
        contentPane.add(south_box, BorderLayout.SOUTH);
        
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
            log.info("Articolo aggiunto");
            Main.showPopup(this, "Articolo aggiunto");
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
    
    public void updateListArticoliOrdinati(ArticoloOrdinato[] art_ordinati){
        this.list_art_ordinati.setListData(art_ordinati);
    }
}
