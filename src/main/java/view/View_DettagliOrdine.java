package view;

import control.Listener_BackToOrdiniPassati;
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

/**
 *
 * @author Bosky
 */
public class View_DettagliOrdine extends JFrame{
    private Ordine ordine;
    private JPanel dati_panel;
    private JPanel lista_panel;
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

    public View_DettagliOrdine(Ordine ordine) {
        this.ordine = ordine;
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        setResizable(false);
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(1,2));
        
        button_back = new JButton();
        button_back.setText("INDIETRO");
        button_back.addActionListener(new Listener_BackToOrdiniPassati(this));
        
        label_title = new JLabel();
        label_title.setText("DETTAGLI ORDINE");
        label_codice = new JLabel();
        label_codice.setText("Codice: " + ordine.getCodice());
        label_data = new JLabel();
        label_data.setText("Data dell'ordine: " + ordine.getData().toString());
        label_codFiscale = new JLabel();
        label_codFiscale.setText("Codice fiscale negozio: " + ordine.getNegozio().getCodiceFiscale());
        label_nomeNegozio = new JLabel();
        label_nomeNegozio.setText("Nome negozio: " + ordine.getNegozio().getNome());
        label_indirizzoNegozio = new JLabel();
        label_indirizzoNegozio.setText("Indirizzo negozio: " + ordine.getNegozio().getIndirizzo().toString());
        
        dati_panel = new JPanel();
        dati_panel.setLayout(new GridLayout(7,1));
        btn_panel = new JPanel();
        btn_panel.add(button_back);
        btn_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        dati_panel.add(btn_panel);
        dati_panel.add(label_title);
        dati_panel.add(label_codice);
        dati_panel.add(label_data);
        dati_panel.add(label_codFiscale);
        dati_panel.add(label_nomeNegozio);
        dati_panel.add(label_indirizzoNegozio);
        contentPane.add(dati_panel);
        
        label_articoliOrdinati = new JLabel();
        label_articoliOrdinati.setText("Articoli ordinati:");
        
        //ottengo la lista di ArticoloOrdinato
        ArticoloOrdinato[] articoli = new ArticoloOrdinato[ordine.getArticoli().size()];
        articoli = ordine.getArticoli().toArray(articoli);
        list = new JList(articoli);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(10);
        list_scroller = new JScrollPane(list);
        list_scroller.setPreferredSize(new Dimension(100,100));
        
        lista_panel = new JPanel();
        lista_panel.setLayout(new GridLayout(2,1));
        lista_panel.add(label_articoliOrdinati);
        lista_panel.add(list_scroller);
        contentPane.add(lista_panel);
        
        this.pack();
    }
    
    public Ordine getOrdine(){
        return this.ordine;
    }
}
