package view;

import control.Listener_AddArticoloOrdineButton;
import control.Listener_BackToHomeResponsabileButton;
import control.Listener_ConfermaOrdineButton;
import control.Main;
import dao.DAOSettings;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Box;
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
import model.Negozio;
import model.Ordine;
import model.Utente;

public class View_CreaOrdine extends JFrame{
    
    private Utente user = null;
    
    private Box north_box;
    private Box central_box;
    private JPanel add_panel;
    private Box south_box;
    private JLabel label_title;
    private JButton button_back;
    private JList list;
    private JScrollPane list_scroller;
    private JButton button_add;
    private JButton button_confirm;
    private JSpinner spinner_qty;
    private JLabel label_qty;
    private JList list_sel_articoli;
    private JScrollPane list_sel_articoli_scroller;
    
    private Ordine ordine;
    private static DAOSettings DAO = Main.getDAO();
    
    public View_CreaOrdine(Utente user) {
        this.user = user;
        //ricavo dal DAO il negozio dell'utente
        Negozio negozio = DAO.getNegozioDAO().getNegozioByResponsabile(user);
        ordine = new Ordine(new Date(), new ArrayList<>(), negozio);
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
        label_title.setText("CREA ORDINE");
        north_box.add(label_title);
        
        contentPane.add(north_box, BorderLayout.NORTH);

        central_box = Box.createVerticalBox();
        
        //ricavo da DAO (tramite query) la lista di Articoli del catalogo
        ArrayList<Articolo> art = DAO.getArticoloDAO().getAllArticoli();
        Articolo[] articoli = new Articolo[art.size()];
        articoli = art.toArray(articoli);
        list = new JList(articoli); //passare come parametro l'array di oggetti da DB
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(10);
        list_scroller = new JScrollPane(list);
        list_scroller.setPreferredSize(new Dimension(100,100));
        central_box.add(list_scroller);
        
        add_panel = new JPanel();
        add_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        label_qty = new JLabel();
        label_qty.setText("Quantità:");
        add_panel.add(label_qty);
        SpinnerModel model = new SpinnerNumberModel(0,0,1000,1);
        spinner_qty = new JSpinner(model);
        add_panel.add(spinner_qty);
        button_add = new JButton();
        button_add.setText("Aggiungi");
        button_add.addActionListener(new Listener_AddArticoloOrdineButton(this));
        add_panel.add(button_add);
        central_box.add(add_panel);

        list_sel_articoli = new JList();
        list_sel_articoli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_sel_articoli.setLayoutOrientation(JList.VERTICAL);
        list_sel_articoli.setVisibleRowCount(10);
        list_sel_articoli_scroller = new JScrollPane(list_sel_articoli);
        list_sel_articoli_scroller.setPreferredSize(new Dimension(100,100));
        central_box.add(list_sel_articoli_scroller);
        
        contentPane.add(central_box, BorderLayout.CENTER);
        
        south_box = Box.createHorizontalBox();
        south_box.add(Box.createRigidArea(new Dimension(10,50)));
        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToHomeResponsabileButton(this));
        south_box.add(button_back);
        south_box.add(Box.createHorizontalGlue());
        button_confirm = new JButton();
        button_confirm.setText("Conferma");
        button_confirm.addActionListener(new Listener_ConfermaOrdineButton(this));
        south_box.add(button_confirm);
        south_box.add(Box.createRigidArea(new Dimension(10,50)));
        
        contentPane.add(south_box, BorderLayout.SOUTH);
        
        this.pack();
    }
    
    public Utente getUser(){
        return this.user;
    }
    
    public Ordine getOrdine(){
        return this.ordine;
    }
    
    public void addArticoloOrdine(ArticoloOrdinato art){
        ArticoloOrdinato to_remove = null;
        for(ArticoloOrdinato artord : this.ordine.getArticoli()){
            if(((Articolo)artord).equals((Articolo)art)){
                art.setQuantita(art.getQuantita() + artord.getQuantita());
                to_remove = artord;
            }
        }
        this.ordine.removeArticolo(to_remove);
        this.ordine.addArticolo(art);
        //aggiorno la JList
        ArticoloOrdinato[] articoli_sel = new ArticoloOrdinato[this.ordine.getArticoli().size()];
        articoli_sel = this.ordine.getArticoli().toArray(articoli_sel);
        this.list_sel_articoli.setListData(articoli_sel);
    }
    
    public Articolo getSelectedArticolo(){
        return (Articolo)this.list.getSelectedValue();
    }
    
    public int getQuantity(){
        return (Integer)this.spinner_qty.getValue();
    }
    
    public JList getListSelArticoli(){
        return this.list_sel_articoli;
    }
}
