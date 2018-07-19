package view;

import control.Listener_BackToHomeMagazziniereButton;
import control.Listener_SpostaButton;
import control.Main;
import dao.DAOSettings;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
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
import model.ArticoloMagazzino;
import model.Utente;

public class View_SpostaArticoli extends JFrame{
    private Utente user = null;
    
    private Box north_box;
    private Box central_box;
    private Box south_box;
    private JLabel label_title;
    private JButton button_back;
    private JPanel sel_articolo_panel;
    private JLabel label_sel_articolo;
    private JList list_articoli;
    private JScrollPane list_articoli_scroller;
    private JPanel scaffale_panel;
    private JLabel label_scaffale;
    private JSpinner text_scaffale;
    private JPanel ripiano_panel;
    private JLabel label_ripiano;
    private JSpinner text_ripiano;
    private JButton button_sposta;
    
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
        contentPane.setLayout(new BorderLayout());
        
        north_box = Box.createHorizontalBox();
        north_box.add(Box.createRigidArea(new Dimension(10,50)));
        label_title = new JLabel();
        label_title.setText("SPOSTA ARTICOLI");
        north_box.add(label_title);
        
        contentPane.add(north_box, BorderLayout.NORTH);

        central_box = Box.createVerticalBox();
        
        sel_articolo_panel = new JPanel();
        sel_articolo_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label_sel_articolo = new JLabel();
        label_sel_articolo.setText("Seleziona articolo:");
        sel_articolo_panel.add(label_sel_articolo);
        central_box.add(sel_articolo_panel);
        
        //ricavo da DAO (tramite query) la lista di tutti gli ArticoloMagazzino
        ArrayList<ArticoloMagazzino> artmag = DAO.getArticoloMagazzinoDAO().getArticoliMagazzino();
        ArticoloMagazzino[] articoli_mag = new ArticoloMagazzino[artmag.size()];
        articoli_mag = artmag.toArray(articoli_mag);
        list_articoli = new JList(articoli_mag);
        list_articoli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_articoli.setLayoutOrientation(JList.VERTICAL);
        list_articoli.setVisibleRowCount(10);
        list_articoli_scroller = new JScrollPane(list_articoli);
        list_articoli_scroller.setPreferredSize(new Dimension(100,100));
        central_box.add(list_articoli_scroller);
        
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
        
        contentPane.add(central_box, BorderLayout.CENTER);
        
        south_box = Box.createHorizontalBox();
        south_box.add(Box.createRigidArea(new Dimension(10,50)));
        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToHomeMagazziniereButton(this));
        south_box.add(button_back);
        south_box.add(Box.createHorizontalGlue());
        button_sposta = new JButton();
        button_sposta.setText("Sposta");
        button_sposta.addActionListener(new Listener_SpostaButton(this));
        south_box.add(button_sposta);
        south_box.add(Box.createRigidArea(new Dimension(10,50)));
        
        contentPane.add(south_box, BorderLayout.SOUTH);
        
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
