package view;

import control.Listener_BackToOrdiniPassati;
import control.Main;
import dao.DAOSettings;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import model.ArticoloOrdinato;
import model.Ordine;
import model.Uscita;

public class View_DettagliOrdine extends JFrame{
    private Ordine ordine;
    
    private Box north_box;
    private Box central_box;
    private Box south_box;
    private JButton button_back;
    private JLabel label_title;
    private JLabel label_codice;
    private JLabel label_data;
    private JLabel label_codFiscale;
    private JLabel label_nomeNegozio;
    private JLabel label_indirizzoNegozio;
    private JLabel label_articoliOrdinati;
    private JList list;
    private JScrollPane list_scroller;
    private JLabel label_prezzoOrdine;
    private JLabel label_stato;
    private JLabel label_dettagli_uscita;
    private JLabel label_bolla;
    private JLabel label_data_uscita;
    private JLabel label_spedizioniere;
    
    private static DAOSettings DAO = Main.getDAO();

    public View_DettagliOrdine(Ordine ordine) {
        this.ordine = ordine;
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
        label_title.setText("DETTAGLI ORDINE");
        north_box.add(label_title);
        
        contentPane.add(north_box, BorderLayout.NORTH);
        
        central_box = Box.createVerticalBox();
        
        label_codice = new JLabel();
        label_codice.setText("Codice: " + ordine.getCodice());
        central_box.add(label_codice);
        
        label_data = new JLabel();
        label_data.setText("Data dell'ordine: " + ordine.getData().toString());
        central_box.add(label_data);
        
        label_codFiscale = new JLabel();
        label_codFiscale.setText("Codice fiscale negozio: " + ordine.getNegozio().getCodiceFiscale());
        central_box.add(label_codFiscale);
        
        label_nomeNegozio = new JLabel();
        label_nomeNegozio.setText("Nome negozio: " + ordine.getNegozio().getNome());
        central_box.add(label_nomeNegozio);
        
        label_indirizzoNegozio = new JLabel();
        label_indirizzoNegozio.setText("Indirizzo negozio: " + ordine.getNegozio().getIndirizzo().toString());
        central_box.add(label_indirizzoNegozio);
        central_box.add(Box.createRigidArea(new Dimension(0,10)));

        label_articoliOrdinati = new JLabel();
        label_articoliOrdinati.setText("Articoli ordinati");
        central_box.add(label_articoliOrdinati);
        
        //ottengo la lista di ArticoloOrdinato
        ArticoloOrdinato[] articoli = new ArticoloOrdinato[ordine.getArticoli().size()];
        articoli = ordine.getArticoli().toArray(articoli);
        list = new JList(articoli);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(10);
        list_scroller = new JScrollPane(list);
        central_box.add(list_scroller);
        
        label_prezzoOrdine = new JLabel();
        label_prezzoOrdine.setText("Prezzo totale: " + ordine.getPrezzoTot());
        central_box.add(label_prezzoOrdine);
        
        //ricavo l'uscita dell'ordine da DAO
        Uscita usc = DAO.getUscitaDAO().getUscitaByOrdine(ordine);
        
        if(usc != null){
            
            label_stato = new JLabel();
            label_stato.setText("Stato ordine: Uscito");
            central_box.add(label_stato);
            
            label_dettagli_uscita = new JLabel();
            label_dettagli_uscita.setText("DETTAGLI USCITA");
            central_box.add(label_dettagli_uscita);
            
            label_bolla = new JLabel();
            label_bolla.setText("Numero di bolla: " + usc.getNumBolla());
            central_box.add(label_bolla);

            label_data_uscita = new JLabel();
            label_data_uscita.setText("Data uscita: " + usc.getData().toString());
            central_box.add(label_data_uscita);

            label_spedizioniere = new JLabel();
            label_spedizioniere.setText("Spedizioniere: " + usc.getSpedizioniere().toString());
            central_box.add(label_spedizioniere);
        }
        else{
            label_stato = new JLabel();
            label_stato.setText("Stato ordine: In elaborazione");
            central_box.add(label_stato);
        }
        
        contentPane.add(central_box, BorderLayout.CENTER);
        
        south_box = Box.createHorizontalBox();
        south_box.add(Box.createRigidArea(new Dimension(10,50)));
        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToOrdiniPassati(this));
        south_box.add(button_back);
        
        contentPane.add(south_box, BorderLayout.SOUTH);
        
        this.pack();
    }
    
    public Ordine getOrdine(){
        return this.ordine;
    }
}
