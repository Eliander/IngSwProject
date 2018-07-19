package view;

import control.Listener_AddArticoloIngressoButton;
import control.Listener_AddIngressoButton;
import control.Listener_BackToHomeMagazziniereButton;
import control.Main;
import dao.DAOSettings;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.DateFormatter;
import model.Articolo;
import model.ArticoloMagazzino;
import model.Ingresso;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class View_RegistraIngresso extends JFrame{
    private Utente user = null;
    private Ingresso ingresso;
    
    private Box north_box;
    private Box central_box;
    private Box south_box;
    private JLabel label_title;
    private JButton button_back;
    private JList list_articoli;
    private JScrollPane list_articoli_scroller;
    private JPanel data_panel;
    private JLabel label_data;
    private JFormattedTextField text_data;
    private JPanel scaffale_panel;
    private JLabel label_scaffale;
    private JSpinner text_scaffale;
    private JPanel ripiano_panel;
    private JLabel label_ripiano;
    private JSpinner text_ripiano;
    private JButton button_addArticolo;
    private JPanel btn_panel;
    private JList list_articoli_aggiunti;
    private JScrollPane list_articoli_aggiunti_scroller;
    private JButton button_addIngresso;
    
    private final static Logger log = LogManager.getLogger(View_RegistraIngresso.class);
    private static DAOSettings DAO = Main.getDAO();
    
    public View_RegistraIngresso(Utente user) {
        this.user = user;
        this.ingresso = new Ingresso(0, new Date(), new ArrayList<>());
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
        label_title.setText("REGISTRA INGRESSO");
        north_box.add(label_title);
        
        contentPane.add(north_box, BorderLayout.NORTH);

        central_box = Box.createVerticalBox();

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
        central_box.add(list_articoli_scroller);
        
        data_panel = new JPanel();
        data_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label_data = new JLabel();
        label_data.setText("Data di produzione:");
        data_panel.add(label_data);
        DateFormat format = new SimpleDateFormat("dd/MM/yy");
        DateFormatter formatter = new DateFormatter(format);
        format.setLenient(false);
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        text_data = new JFormattedTextField(formatter);
        text_data.setValue(new Date());
        data_panel.add(text_data);
        central_box.add(data_panel);
        
        scaffale_panel = new JPanel();
        scaffale_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label_scaffale = new JLabel();
        label_scaffale.setText("Scaffale:");
        scaffale_panel.add(label_scaffale);
        SpinnerModel model = new SpinnerNumberModel(1,1,1000,1);
        text_scaffale = new JSpinner(model);
        scaffale_panel.add(text_scaffale);
        central_box.add(scaffale_panel);
        
        ripiano_panel = new JPanel();
        ripiano_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label_ripiano = new JLabel();
        label_ripiano.setText("Ripiano:");
        ripiano_panel.add(label_ripiano);
        SpinnerModel model2 = new SpinnerNumberModel(1,1,1000,1);
        text_ripiano = new JSpinner(model2);
        ripiano_panel.add(text_ripiano);
        central_box.add(ripiano_panel);
        
        button_addArticolo = new JButton();
        button_addArticolo.setText("Aggiungi articolo");
        button_addArticolo.addActionListener(new Listener_AddArticoloIngressoButton(this));
        btn_panel = new JPanel();
        btn_panel.add(button_addArticolo);
        btn_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        central_box.add(btn_panel);
        
        list_articoli_aggiunti = new JList(); //passare come parametro l'array di oggetti da DB
        list_articoli_aggiunti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_articoli_aggiunti.setLayoutOrientation(JList.VERTICAL);
        list_articoli_aggiunti.setVisibleRowCount(10);
        list_articoli_aggiunti_scroller = new JScrollPane(list_articoli_aggiunti);
        list_articoli_aggiunti_scroller.setPreferredSize(new Dimension(100,100));
        central_box.add(list_articoli_aggiunti_scroller);
        
        contentPane.add(central_box, BorderLayout.CENTER);
        
        south_box = Box.createHorizontalBox();
        south_box.add(Box.createRigidArea(new Dimension(10,50)));
        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToHomeMagazziniereButton(this));
        south_box.add(button_back);
        south_box.add(Box.createHorizontalGlue());
        button_addIngresso = new JButton();
        button_addIngresso.setText("Crea ingresso");
        button_addIngresso.addActionListener(new Listener_AddIngressoButton(this));
        south_box.add(button_addIngresso);
        south_box.add(Box.createRigidArea(new Dimension(10,50)));
        
        contentPane.add(south_box, BorderLayout.SOUTH);
        
        this.pack();
    }
    
    public Utente getUser(){
        return this.user;
    }
    
    public Ingresso getIngresso(){
        return this.ingresso;
    }
    
    public Articolo getSelectedArticolo(){
        return (Articolo)this.list_articoli.getSelectedValue();
    }
    
    public Date getSelectedData(){
        try{
            DateFormat format = new SimpleDateFormat("dd/MM/yy");
            Date data = format.parse(this.text_data.getText());
            return data;
        }
        catch(Exception ex){
            return new Date();
        }
    }
    
    public int getSelectedScaffale(){
        return (Integer)this.text_scaffale.getValue();
    }
    
    public int getSelectedRipiano(){
        return (Integer)this.text_ripiano.getValue();
    }
    
    public void addArticolo(ArticoloMagazzino artmag){
        if((!this.ingresso.getArticoli().contains(artmag)) && DAO.getPosizioneDAO().checkPosizioneLibera(artmag.getPosizione())){
            this.ingresso.addArticolo(artmag);
            log.info("Articolo aggiunto");
            Main.showPopup(this, "Articolo aggiunto");
        }
        else{
            log.error("ERRORE: E' già presente un articolo in questa posizione!");
            Main.showPopup(this, "ERRORE: E' già presente un articolo in questa posizione!");
        }
        //aggiorno la lista degli articoli aggiunti
        ArticoloMagazzino[] articoli_sel = new ArticoloMagazzino[this.ingresso.getArticoli().size()];
        articoli_sel = this.ingresso.getArticoli().toArray(articoli_sel);
        this.list_articoli_aggiunti.setListData(articoli_sel);
    }
}
