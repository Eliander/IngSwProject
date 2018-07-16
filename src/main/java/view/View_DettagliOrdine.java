package view;

import control.Listener_BackToOrdiniPassati;
import control.Main;
import dao.DAOSettings;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
    
    private JPanel btn_panel;
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
        contentPane.setLayout(new GridLayout(15,1));
        
        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToOrdiniPassati(this));
        btn_panel = new JPanel();
        btn_panel.add(button_back);
        btn_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(btn_panel);
        
        label_title = new JLabel();
        label_title.setText("DETTAGLI ORDINE");
        contentPane.add(label_title);
        
        label_codice = new JLabel();
        label_codice.setText("Codice: " + ordine.getCodice());
        contentPane.add(label_codice);
        
        label_data = new JLabel();
        label_data.setText("Data dell'ordine: " + ordine.getData().toString());
        contentPane.add(label_data);
        
        label_codFiscale = new JLabel();
        label_codFiscale.setText("Codice fiscale negozio: " + ordine.getNegozio().getCodiceFiscale());
        contentPane.add(label_codFiscale);
        
        label_nomeNegozio = new JLabel();
        label_nomeNegozio.setText("Nome negozio: " + ordine.getNegozio().getNome());
        contentPane.add(label_nomeNegozio);
        
        label_indirizzoNegozio = new JLabel();
        label_indirizzoNegozio.setText("Indirizzo negozio: " + ordine.getNegozio().getIndirizzo().toString());
        contentPane.add(label_indirizzoNegozio);

        label_articoliOrdinati = new JLabel();
        label_articoliOrdinati.setText("Articoli ordinati:");
        contentPane.add(label_articoliOrdinati);
        
        //ottengo la lista di ArticoloOrdinato
        ArticoloOrdinato[] articoli = new ArticoloOrdinato[ordine.getArticoli().size()];
        articoli = ordine.getArticoli().toArray(articoli);
        list = new JList(articoli);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(10);
        list_scroller = new JScrollPane(list);
        list_scroller.setPreferredSize(new Dimension(100,100));
        contentPane.add(list_scroller);
        
        label_prezzoOrdine = new JLabel();
        label_prezzoOrdine.setText("Prezzo totale: " + ordine.getPrezzoTot());
        contentPane.add(label_prezzoOrdine);
        
        //ricavo l'uscita dell'ordine da DAO
        Uscita usc = DAO.getUscitaDAO().getUscitaByOrdine(ordine);
        
        if(usc != null){
            
            label_stato = new JLabel();
            label_stato.setText("Stato ordine: Uscito");
            contentPane.add(label_stato);
            
            label_dettagli_uscita = new JLabel();
            label_dettagli_uscita.setText("DETTAGLI USCITA");
            contentPane.add(label_dettagli_uscita);
            
            label_bolla = new JLabel();
            label_bolla.setText("Numero di bolla: " + usc.getNumBolla());
            contentPane.add(label_bolla);

            label_data_uscita = new JLabel();
            label_data_uscita.setText("Data uscita: " + usc.getData().toString());
            contentPane.add(label_data_uscita);

            label_spedizioniere = new JLabel();
            label_spedizioniere.setText("Spedizioniere: " + usc.getSpedizioniere().toString());
            contentPane.add(label_spedizioniere);
        }
        else{
            label_stato = new JLabel();
            label_stato.setText("Stato ordine: In elaborazione");
            contentPane.add(label_stato);
        }
        
        this.pack();
    }
    
    public Ordine getOrdine(){
        return this.ordine;
    }
}
