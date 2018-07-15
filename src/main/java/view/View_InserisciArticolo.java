package view;

import control.Listener_AddArticoloButton;
import control.Listener_AddMaterialeButton;
import control.Listener_BackToHomeSegretarioButton;
import control.Main;
import dao.DAOSettings;
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

/**
 *
 * @author Bosky
 */
public class View_InserisciArticolo extends JFrame{
    private Utente user = null;
    private Articolo articolo; //articolo da creare
    
    private JLabel label_title;
    private JPanel btn_panel;
    private JButton button_back;
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
    
    private static DAOSettings DAO = Main.getDAO();
    
    public View_InserisciArticolo(Utente user) {
        this.user = user;
        articolo = new Articolo("","","","",new ArrayList<>(),0);
        initComponents();
    }
                     
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        setResizable(false);
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(8,2));

        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToHomeSegretarioButton(this));
        btn_panel = new JPanel();
        btn_panel.add(button_back);
        btn_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(btn_panel);
        
        label_title = new JLabel();
        label_title.setText("AGGIUNGI ARTICOLO");
        contentPane.add(label_title);
        
        label_nome = new JLabel();
        label_nome.setText("Nome: ");
        contentPane.add(label_nome);
        text_nome = new JTextField();
        contentPane.add(text_nome);
        
        label_desc = new JLabel();
        label_desc.setText("Descrizione: ");
        contentPane.add(label_desc);
        text_desc = new JTextField();
        contentPane.add(text_desc);
        
        label_sport = new JLabel();
        label_sport.setText("Sport: ");
        contentPane.add(label_sport);
        //ricavo da DAO la lista degli sport
        String[] sports = DAO.getSportDAO().getSport();
        text_sport = new JComboBox(sports);
        contentPane.add(text_sport);
        
        label_categoria = new JLabel();
        label_categoria.setText("Categoria: ");
        contentPane.add(label_categoria);
        //ricavo da DAO la lista delle categorie
        String[] categorie = DAO.getCategoriaDAO().getCategorie();
        text_categoria = new JComboBox(categorie);
        contentPane.add(text_categoria);
        
        label_materiali = new JLabel();
        label_materiali.setText("Materiali: ");
        contentPane.add(label_materiali);
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
        contentPane.add(materiali_panel);
        
        label_prezzo = new JLabel();
        label_prezzo.setText("Prezzo: ");
        contentPane.add(label_prezzo);
        text_prezzo = new JTextField();
        contentPane.add(text_prezzo);
        
        button_add = new JButton();
        button_add.setText("AGGIUNGI ARTICOLO");
        button_add.addActionListener(new Listener_AddArticoloButton(this));
        btn_panel2 = new JPanel();
        btn_panel2.add(button_add);
        btn_panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contentPane.add(btn_panel2);
        
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
}
