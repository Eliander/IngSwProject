package view;

import control.Listener_BackToHomeSegretarioButton;
import control.Listener_DettagliUscitaButton;
import control.Main;
import dao.DAOSettings;
import java.awt.BorderLayout;
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
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import model.Uscita;
import model.Utente;

/**
 *
 * @author Bosky
 */
public class View_MovimentiMagazzino extends JFrame{
    private Utente user = null;
    private JPanel head_panel;
    private JLabel label_title;
    private JPanel btn_panel;
    private JButton button_back;
    private JPanel btn_panel2;
    private JButton button_details;
    private JList list;
    private JScrollPane list_scroller;
    
    private static DAOSettings DAO = Main.getDAO();
    
    public View_MovimentiMagazzino(Utente user) {
        this.user = user;
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        setResizable(false);
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        label_title = new JLabel();
        label_title.setText("Lista uscite:");

        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToHomeSegretarioButton(this));
        
        head_panel = new JPanel();
        head_panel.setLayout(new GridLayout(2,1));
        btn_panel = new JPanel();
        btn_panel.add(button_back);
        btn_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        head_panel.add(btn_panel);
        head_panel.add(label_title);
        contentPane.add(head_panel, BorderLayout.NORTH);
        
        //ricavo da DAO la lista delle uscite
        ArrayList<Uscita> usc = DAO.getUscitaDAO().getUscite();
        Uscita[] uscite = new Uscita[usc.size()];
        uscite = usc.toArray(uscite);
        list = new JList(uscite);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(10);
        list_scroller = new JScrollPane(list);
        list_scroller.setPreferredSize(new Dimension(100,100));
        contentPane.add(list_scroller, BorderLayout.CENTER);
        
        button_details = new JButton();
        button_details.setText("Dettagli uscita");
        button_details.addActionListener(new Listener_DettagliUscitaButton(this));
        
        btn_panel2 = new JPanel();
        btn_panel2.add(button_details);
        btn_panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contentPane.add(btn_panel2, BorderLayout.SOUTH);
        
        this.pack();
    }
    
    public Utente getUser(){
        return this.user;
    }
    
    public Uscita getSelectedUscita(){
        return (Uscita)this.list.getSelectedValue();
    }
}
