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
import javax.swing.Box;
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
    
    private Box north_box;
    private Box central_box;
    private Box south_box;
    private JLabel label_title;
    private JButton button_back;
    private JPanel nome_panel;
    private JLabel label_nome;
    private JTextField text_nome;
    private JPanel desc_panel;
    private JLabel label_desc;
    private JTextField text_desc;
    private JPanel sport_panel;
    private JLabel label_sport;
    private JComboBox text_sport;
    private JPanel categoria_panel;
    private JLabel label_categoria;
    private JComboBox text_categoria;
    private JLabel label_materiali;
    private JPanel materiali_panel;
    private JComboBox text_materiale;
    private JButton button_add_materiale;
    private JList list_materiali;
    private JScrollPane list_materiali_scroller;
    private JPanel prezzo_panel;
    private JLabel label_prezzo;
    private JTextField text_prezzo;
    private JLabel label_euro;
    private JButton button_add;
    private JPanel btn_panel2;
    private JLabel label_articoli;
    private JList list_articoli;
    private JScrollPane list_articoli_scroller;
    private JPanel stat_panel;
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
        
        north_box = Box.createHorizontalBox();
        north_box.add(Box.createRigidArea(new Dimension(10,50)));
        label_title = new JLabel();
        label_title.setText("AGGIUNGI ARTICOLO");
        north_box.add(label_title);
        
        contentPane.add(north_box, BorderLayout.NORTH);
        
        central_box = Box.createVerticalBox();
        
        nome_panel = new JPanel();
        nome_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label_nome = new JLabel();
        label_nome.setText("Nome: ");
        nome_panel.add(label_nome);
        text_nome = new JTextField(20);
        nome_panel.add(text_nome);
        central_box.add(nome_panel);
        
        desc_panel = new JPanel();
        desc_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label_desc = new JLabel();
        label_desc.setText("Descrizione: ");
        desc_panel.add(label_desc);
        text_desc = new JTextField(50);
        desc_panel.add(text_desc);
        central_box.add(desc_panel);
        
        sport_panel = new JPanel();
        sport_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label_sport = new JLabel();
        label_sport.setText("Sport: ");
        sport_panel.add(label_sport);
        //ricavo da DAO la lista degli sport
        String[] sports = DAO.getSportDAO().getSport();
        text_sport = new JComboBox(sports);
        sport_panel.add(text_sport);
        central_box.add(sport_panel);
        
        categoria_panel = new JPanel();
        categoria_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label_categoria = new JLabel();
        label_categoria.setText("Categoria: ");
        categoria_panel.add(label_categoria);
        //ricavo da DAO la lista delle categorie
        String[] categorie = DAO.getCategoriaDAO().getCategorie();
        text_categoria = new JComboBox(categorie);
        categoria_panel.add(text_categoria);
        central_box.add(categoria_panel);
        
        materiali_panel = new JPanel();
        materiali_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label_materiali = new JLabel();
        label_materiali.setText("Materiali: ");
        materiali_panel.add(label_materiali);
        Box materiali_box = Box.createVerticalBox();
        //ricavo da DAO la lista dei materiali
        String[] mats = DAO.getMaterialiDAO().getMateriali();
        text_materiale = new JComboBox(mats);
        materiali_box.add(text_materiale);
        list_materiali = new JList();
        list_materiali.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_materiali.setLayoutOrientation(JList.VERTICAL);
        list_materiali.setVisibleRowCount(10);
        list_materiali_scroller = new JScrollPane(list_materiali);
        list_materiali_scroller.setPreferredSize(new Dimension(100,100));
        materiali_box.add(list_materiali_scroller);
        button_add_materiale = new JButton();
        button_add_materiale.setText("Aggiungi");
        button_add_materiale.addActionListener(new Listener_AddMaterialeButton(this));
        materiali_box.add(button_add_materiale);
        materiali_panel.add(materiali_box);
        central_box.add(materiali_panel);
        
        prezzo_panel = new JPanel();
        prezzo_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label_prezzo = new JLabel();
        label_prezzo.setText("Prezzo: ");
        prezzo_panel.add(label_prezzo);
        text_prezzo = new JTextField(10);
        prezzo_panel.add(text_prezzo);
        label_euro = new JLabel();
        label_euro.setText(" euro");
        prezzo_panel.add(label_euro);
        central_box.add(prezzo_panel);
        
        button_add = new JButton();
        button_add.setText("AGGIUNGI ARTICOLO");
        button_add.addActionListener(new Listener_AddArticoloButton(this));
        btn_panel2 = new JPanel();
        btn_panel2.add(button_add);
        btn_panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        central_box.add(btn_panel2);
        
        label_articoli = new JLabel();
        label_articoli.setText("Catalogo articoli");
        central_box.add(label_articoli);
        
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
        central_box.add(list_articoli_scroller);
        
        label_statistiche = new JLabel();
        label_statistiche.setText("Statistiche: ");
        stat_panel = new JPanel();
        stat_panel.add(label_statistiche);
        stat_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        central_box.add(stat_panel);
        
        contentPane.add(central_box, BorderLayout.CENTER);
        
        south_box = Box.createHorizontalBox();
        south_box.add(Box.createRigidArea(new Dimension(10,50)));
        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToHomeSegretarioButton(this));
        south_box.add(button_back);
        
        contentPane.add(south_box, BorderLayout.SOUTH);
        
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
        this.label_statistiche.setText("Statistiche: " + s);
    }
}
