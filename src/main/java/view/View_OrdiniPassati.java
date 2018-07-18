package view;

import control.Listener_BackToHomeResponsabileButton;
import control.Listener_DettagliOrdineButton;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import model.Negozio;
import model.Ordine;
import model.Utente;

public class View_OrdiniPassati extends JFrame{
    
    private Utente user = null;
    
    private Box north_box;
    private Box south_box;
    private JLabel label_title;
    private JButton button_back;
    private JButton button_details;
    private JList list;
    private JScrollPane list_scroller;
    
    private static DAOSettings DAO = Main.getDAO();
    
    public View_OrdiniPassati(Utente user) {
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
        label_title.setText("Lista ordini:");
        north_box.add(label_title);
        
        contentPane.add(north_box, BorderLayout.NORTH);

        //ricavo da DAO (tramite query) il Negozio dell'utente
        //ricavo da DAO la lista di Ordini del Negozio
        Negozio negozio = DAO.getNegozioDAO().getNegozioByResponsabile(user);
        ArrayList<Ordine> ord = DAO.getOrdineDAO().getOrdiniByNegozio(negozio);
        Ordine[] ordini = new Ordine[ord.size()];
        ordini = ord.toArray(ordini);
        list = new JList(ordini); //passare come parametro l'array di oggetti da DB
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(10);
        list_scroller = new JScrollPane(list);
        list_scroller.setPreferredSize(new Dimension(100,100));
        contentPane.add(list_scroller, BorderLayout.CENTER);
        
        south_box = Box.createHorizontalBox();
        south_box.add(Box.createRigidArea(new Dimension(10,50)));
        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToHomeResponsabileButton(this));
        south_box.add(button_back);
        south_box.add(Box.createHorizontalGlue());
        button_details = new JButton();
        button_details.setText("Dettagli ordine");
        button_details.addActionListener(new Listener_DettagliOrdineButton(this));
        south_box.add(button_details);
        south_box.add(Box.createRigidArea(new Dimension(10,50)));
        
        contentPane.add(south_box, BorderLayout.SOUTH);
        
        this.pack();
    }
    
    public Utente getUser(){
        return this.user;
    }
    
    public Ordine getSelectedOrdine(){
        return (Ordine)this.list.getSelectedValue(); //null se non è selezionato niente
    }
}
