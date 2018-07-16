package view;

import control.Listener_AddArticoloButton;
import control.Listener_AddMaterialeButton;
import control.Listener_BackToHomeSegretarioButton;
import control.Listener_SelectionArticolo;
import control.Main;
import dao.DAOSettings;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import model.Articolo;
import model.Utente;

public class View_InserisciArticolo extends JFrame{
    private Utente user = null;
    private Articolo articolo; //articolo da creare
    
    private JPanel north_panel;
    private JPanel south_panel;
    private JLabel label_title;
    private JPanel btn_panel;
    private JButton button_back;
    private JPanel form_panel;
    private JLabel label_nome;
    private JTextField text_nome;
    private JLabel label_desc;
    private JTextField text_desc;
    private JLabel label_sport;
    private JComboBox text_sport;
    private JLabel label_categoria;
    private JComboBox text_categoria;
    private JLabel label_materiali;
    private JPanel materiali_panel;
    private JComboBox text_materiale;
    private JButton button_add_materiale;
    private JList list_materiali;
    private JScrollPane list_materiali_scroller;
    private JLabel label_prezzo;
    private JTextField text_prezzo;
    private JButton button_add;
    private JPanel btn_panel2;
    private JLabel label_articoli;
    private JList list_articoli;
    private JScrollPane list_articoli_scroller;
    private JLabel label_statistiche;
    
    private static DAOSettings DAO = Main.getDAO();
    
    public View_InserisciArticolo(Utente user) {
        this.user = user;
        articolo = new Articolo("","","","",new ArrayList<>(),0);
        initComponents();
    }
                     
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(Main.getWindowsSize());
        setResizable(false);
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        north_panel = new JPanel();
        north_panel.setLayout(new GridLayout(2,1));
        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToHomeSegretarioButton(this));
        btn_panel = new JPanel();
        btn_panel.add(button_back);
        btn_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        north_panel.add(btn_panel);
        
        label_title = new JLabel();
        label_title.setText("AGGIUNGI ARTICOLO");
        north_panel.add(label_title);
        
        contentPane.add(north_panel, BorderLayout.NORTH);
        
        form_panel = new JPanel();
        form_panel.setLayout(new GridLayout(6,2));
        
        label_nome = new JLabel();
        label_nome.setText("Nome: ");
        form_panel.add(label_nome);
        text_nome = new JTextField();
        form_panel.add(text_nome);
        
        label_desc = new JLabel();
        label_desc.setText("Descrizione: ");
        form_panel.add(label_desc);
        text_desc = new JTextField();
        form_panel.add(text_desc);
        
        label_sport = new JLabel();
        label_sport.setText("Sport: ");
        form_panel.add(label_sport);
        //ricavo da DAO la lista degli sport
        String[] sports = DAO.getSportDAO().getSport();
        text_sport = new JComboBox(sports);
        form_panel.add(text_sport);
        
        label_categoria = new JLabel();
        label_categoria.setText("Categoria: ");
        form_panel.add(label_categoria);
        //ricavo da DAO la lista delle categorie
        String[] categorie = DAO.getCategoriaDAO().getCategorie();
        text_categoria = new JComboBox(categorie);
        form_panel.add(text_categoria);
        
        label_materiali = new JLabel();
        label_materiali.setText("Materiali: ");
        form_panel.add(label_materiali);
        materiali_panel = new JPanel();
        materiali_panel.setLayout(new GridLayout(3,1));
        //ricavo da DAO la lista dei materiali
        String[] mats = DAO.getMaterialiDAO().getMateriali();
        text_materiale = new JComboBox(mats);
        materiali_panel.add(text_materiale);
        button_add_materiale = new JButton();
        button_add_materiale.setText("Aggiungi");
        button_add_materiale.addActionListener(new Listener_AddMaterialeButton(this));
        materiali_panel.add(button_add_materiale);
        list_materiali = new JList();
        list_materiali.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_materiali.setLayoutOrientation(JList.VERTICAL);
        list_materiali.setVisibleRowCount(10);
        list_materiali_scroller = new JScrollPane(list_materiali);
        list_materiali_scroller.setPreferredSize(new Dimension(100,100));
        materiali_panel.add(list_materiali_scroller);
        form_panel.add(materiali_panel);
        
        label_prezzo = new JLabel();
        label_prezzo.setText("Prezzo: ");
        form_panel.add(label_prezzo);
        text_prezzo = new JTextField();
        form_panel.add(text_prezzo);
        
        contentPane.add(form_panel, BorderLayout.CENTER);
        
        south_panel = new JPanel();
        south_panel.setLayout(new GridLayout(4,1));
        
        button_add = new JButton();
        button_add.setText("AGGIUNGI ARTICOLO");
        button_add.addActionListener(new Listener_AddArticoloButton(this));
        btn_panel2 = new JPanel();
        btn_panel2.add(button_add);
        btn_panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        south_panel.add(btn_panel2);
        
        label_articoli = new JLabel();
        label_articoli.setText("Catalogo articoli:");
        south_panel.add(label_articoli);
        
        ArrayList<Articolo> art = DAO.getArticoloDAO().getAllArticoli();
        Articolo[] articoli = new Articolo[art.size()];
        articoli = art.toArray(articoli);
        list_articoli = new JList(articoli);
        list_articoli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_articoli.setLayoutOrientation(JList.VERTICAL);
        list_articoli.setVisibleRowCount(10);
        ListSelectionModel list_articoli_model = list_articoli.getSelectionModel();
        list_articoli_model.addListSelectionListener(new Listener_SelectionArticolo(this));
        list_articoli_scroller = new JScrollPane(list_articoli);
        list_articoli_scroller.setPreferredSize(new Dimension(100,100));
        south_panel.add(list_articoli_scroller);
        
        label_statistiche = new JLabel();
        label_statistiche.setText("Statistiche:\n");
        south_panel.add(label_statistiche);
        
        contentPane.add(south_panel, BorderLayout.SOUTH);
        
        this.pack();
    }
    
    public Utente getUser(){
        return this.user;
    }
    
    public Articolo getArticolo(){
        return this.articolo;
    }
    
    public void addMateriale(String materiale){
        boolean presente = false;
        for(String mat : this.articolo.getMateriali()){
            if(mat.equals(materiale)){
                presente = true;
            }
        }
        if(!presente){
            this.articolo.addMateriale(materiale);
            String[] materiali_new = new String[this.articolo.getMateriali().size()];
            materiali_new = this.articolo.getMateriali().toArray(materiali_new);
            this.list_materiali.setListData(materiali_new);
        }
    }
    
    public String getSelectedNome(){
        return this.text_nome.getText();
    }
    
    public String getSelectedDesc(){
        return this.text_desc.getText();
    }
    
    public String getSelectedSport(){
        return (String)this.text_sport.getSelectedItem();
    }
    
    public String getSelectedCategoria(){
        return (String)this.text_categoria.getSelectedItem();
    }
    
    public String getSelectedMateriale(){
        return (String)this.text_materiale.getSelectedItem();
    }
    
    public String getSelectedPrezzo(){
        return this.text_prezzo.getText();
    }
    
    public Articolo getSelectedArticolo(){
        return (Articolo)this.list_articoli.getSelectedValue();
    }
    
    public void setLabelStatistiche(String s){
        this.label_statistiche.setText("Statistiche:\n" + s);
    }
}
