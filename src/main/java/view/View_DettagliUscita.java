package view;

import control.Listener_BackToMovimentiMagazzino;
import control.Main;
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
import model.ArticoloMagazzino;
import model.ArticoloOrdinato;
import model.Uscita;
import model.Utente;

public class View_DettagliUscita extends JFrame{
    private Utente user = null;
    private Uscita uscita;
    
    private JPanel dati_panel;
    private JPanel lista_panel;
    private JPanel btn_panel;
    private JButton button_back;
    private JLabel label_title;
    private JLabel label_codice;
    private JLabel label_data;
    private JLabel label_spedizioniere;
    private JLabel label_codOrdine;
    private JLabel label_dataOrdine;
    private JLabel label_codNegozio;
    private JLabel label_nomeNegozio;
    private JLabel label_indirizzoNegozio;
    private JLabel label_responsabileNegozio;
    private JLabel label_prezzoOrdine;
    private JLabel label_articoliOrdinati;
    private JList list;
    private JScrollPane list_scroller;
    private JLabel label_articoliUsciti;
    private JList list_art_usc;
    private JScrollPane list_art_usc_scroller;
    
    public View_DettagliUscita(Utente user, Uscita uscita) {
        this.user = user;
        this.uscita = uscita;
        initComponents();
    }
                     
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(Main.getWindowsSize());
        setResizable(false);
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(1,2));
        
        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToMovimentiMagazzino(this));
        
        label_title = new JLabel();
        label_title.setText("DETTAGLI USCITA");
        label_codice = new JLabel();
        label_codice.setText("Num bolla: " + uscita.getNumBolla());
        label_data = new JLabel();
        label_data.setText("Data dell'uscita: " + uscita.getData().toString());
        label_spedizioniere = new JLabel();
        label_spedizioniere.setText("Spedizioniere: " + uscita.getSpedizioniere().toString());
        label_codOrdine = new JLabel();
        label_codOrdine.setText("Codice ordine: " + uscita.getOrdine().getCodice());
        label_dataOrdine = new JLabel();
        label_dataOrdine.setText("Data dell'ordine: " + uscita.getOrdine().getData().toString());
        label_codNegozio = new JLabel();
        label_codNegozio.setText("Codice fiscale negozio: " + uscita.getOrdine().getNegozio().getCodiceFiscale());
        label_nomeNegozio = new JLabel();
        label_nomeNegozio.setText("Nome negozio: " + uscita.getOrdine().getNegozio().getNome());
        label_indirizzoNegozio = new JLabel();
        label_indirizzoNegozio.setText("Indirizzo negozio: " + uscita.getOrdine().getNegozio().getIndirizzo().toString());
        label_responsabileNegozio = new JLabel();
        label_responsabileNegozio.setText("Responsabile negozio: " + uscita.getOrdine().getNegozio().getResponsabile().toString());
        label_prezzoOrdine = new JLabel();
        label_prezzoOrdine.setText("Prezzo totale: " + uscita.getOrdine().getPrezzoTot());
        
        dati_panel = new JPanel();
        dati_panel.setLayout(new GridLayout(12,1));
        btn_panel = new JPanel();
        btn_panel.add(button_back);
        btn_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        dati_panel.add(btn_panel);
        dati_panel.add(label_title);
        dati_panel.add(label_codice);
        dati_panel.add(label_data);
        dati_panel.add(label_spedizioniere);
        dati_panel.add(label_codOrdine);
        dati_panel.add(label_dataOrdine);
        dati_panel.add(label_codNegozio);
        dati_panel.add(label_nomeNegozio);
        dati_panel.add(label_indirizzoNegozio);
        dati_panel.add(label_responsabileNegozio);
        dati_panel.add(label_prezzoOrdine);
        contentPane.add(dati_panel);
        
        label_articoliOrdinati = new JLabel();
        label_articoliOrdinati.setText("Articoli ordinati:");
        
        //ottengo la lista di ArticoloOrdinato
        ArticoloOrdinato[] articoli_ord = new ArticoloOrdinato[uscita.getOrdine().getArticoli().size()];
        articoli_ord = uscita.getOrdine().getArticoli().toArray(articoli_ord);
        list = new JList(articoli_ord);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(10);
        list_scroller = new JScrollPane(list);
        list_scroller.setPreferredSize(new Dimension(100,100));
        
        label_articoliUsciti = new JLabel();
        label_articoliUsciti.setText("Articoli usciti:");
        
        //ottengo la lista di ArticoloMagazzino usciti
        ArticoloMagazzino[] articoli_usc = new ArticoloMagazzino[uscita.getArticoli().size()];
        articoli_usc = uscita.getArticoli().toArray(articoli_usc);
        list_art_usc = new JList(articoli_usc);
        list_art_usc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_art_usc.setLayoutOrientation(JList.VERTICAL);
        list_art_usc.setVisibleRowCount(10);
        list_art_usc_scroller = new JScrollPane(list_art_usc);
        list_art_usc_scroller.setPreferredSize(new Dimension(100,100));
        
        lista_panel = new JPanel();
        lista_panel.setLayout(new GridLayout(4,1));
        lista_panel.add(label_articoliOrdinati);
        lista_panel.add(list_scroller);
        lista_panel.add(label_articoliUsciti);
        lista_panel.add(list_art_usc_scroller);
        contentPane.add(lista_panel);
        
        this.pack();
    }
    
    public Utente getUser(){
        return this.user;
    }
    
    public Uscita getUscita(){
        return this.uscita;
    }
}
