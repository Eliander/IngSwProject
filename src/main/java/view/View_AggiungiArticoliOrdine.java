package view;

import control.MouseListener_ScambiaDatiTabelleOrdine;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.*;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eliander
 */
public class View_AggiungiArticoliOrdine extends JFrame {

    private JButton button_confermaOrdine;
    private JLabel label_ID_info;
    private JLabel label_nome_info;
    private JLabel label_informazioni_info;
    private JLabel label_descrizione_info;
    private JLabel label_codiceArticolo_info;
    private JLabel label_nomeArticolo_info;
    private JLabel label_ordine;
    private JLabel label_numeroOrdine;
    private JPanel panel_sfondo;
    private JPanel panel_info;
    private JScrollPane scrollPanel_inMagazzino;
    private JScrollPane scrollPanel_inOrdinazione;
    private JTabbedPane tabbedPanel;
    private JTable table_inOrdinazione;
    private JTable table_inMagazzino;
    //per aggiungere e togliere righe alle tabelle
    private DefaultTableModel model_inOrdinazione;
    private DefaultTableModel model_inMagazzino;
    private JTextField textField_descrizione_info;

    /**
     * Creates new form ProductInfo
     */
    public View_AggiungiArticoliOrdine() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panel_sfondo = new JPanel();
        label_ordine = new JLabel();
        label_numeroOrdine = new JLabel();//da riempire coi dati
        tabbedPanel = new JTabbedPane();
        scrollPanel_inMagazzino = new JScrollPane();
        table_inMagazzino = new JTable();
        scrollPanel_inOrdinazione = new JScrollPane();
        table_inOrdinazione = new JTable();
        button_confermaOrdine = new JButton();
        //sezione informazioni sul prodotto
        //to do: alla selezione, cambiare la schermata di informazioni
        panel_info = new JPanel();
        label_ID_info = new JLabel();
        label_nome_info = new JLabel();
        label_informazioni_info = new JLabel();
        label_descrizione_info = new JLabel();
        label_codiceArticolo_info = new JLabel();//da riempire coi dati
        label_nomeArticolo_info = new JLabel();//da riemprire coi dati
        textField_descrizione_info = new JTextField();//da riemprire coi dati

        model_inMagazzino = (DefaultTableModel) table_inMagazzino.getModel();
        model_inOrdinazione = (DefaultTableModel) table_inOrdinazione.getModel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        textField_descrizione_info.setEditable(false);

        panel_info.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        label_ID_info.setText("ID: ");

        label_nome_info.setText("Nome prodotto: ");

        label_informazioni_info.setText("INFORMAZIONI SUL PRODOTTO");

        label_descrizione_info.setText("Descrizione: ");

        label_codiceArticolo_info.setText("__id__");

        label_nomeArticolo_info.setText("__nome__");

        textField_descrizione_info.setText("__descrizione__");
        textField_descrizione_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        GroupLayout jPanel2Layout = new GroupLayout(panel_info);
        panel_info.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(label_nome_info)
                                                        .addComponent(label_ID_info)
                                                        .addComponent(label_descrizione_info))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(label_codiceArticolo_info)
                                                        .addComponent(label_nomeArticolo_info)
                                                        .addComponent(textField_descrizione_info, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)))
                                        .addComponent(label_informazioni_info))
                                .addContainerGap(256, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label_informazioni_info)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_ID_info)
                                        .addComponent(label_codiceArticolo_info))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_nome_info)
                                        .addComponent(label_nomeArticolo_info))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(label_descrizione_info)
                                        .addComponent(textField_descrizione_info, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addContainerGap(104, Short.MAX_VALUE))
        );

        table_inMagazzino.setModel(new DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null}
                },
                new String[]{
                    "Aggiungi", "Codice", "Nome", "Prezzo", "Data produzione"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        scrollPanel_inMagazzino.setViewportView(table_inMagazzino);
        
        table_inMagazzino.addMouseListener(new MouseListener_ScambiaDatiTabelleOrdine(this, table_inMagazzino, table_inOrdinazione));
        table_inOrdinazione.addMouseListener(new MouseListener_ScambiaDatiTabelleOrdine(this, table_inOrdinazione, table_inMagazzino));

        tabbedPanel.addTab("In magazzino", scrollPanel_inMagazzino);

        table_inOrdinazione.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Rimuovi", "Codice", "Nome", "Prezzo", "Data produzione"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        scrollPanel_inOrdinazione.setViewportView(table_inOrdinazione);

        tabbedPanel.addTab("In ordinazione", scrollPanel_inOrdinazione);

        button_confermaOrdine.setText("jButton1");
        button_confermaOrdine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        label_ordine.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        label_ordine.setText("ORDINE: ");

        label_numeroOrdine.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        label_numeroOrdine.setText("__numeroOrdine__");

        GroupLayout jPanel1Layout = new GroupLayout(panel_sfondo);
        panel_sfondo.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button_confermaOrdine)
                                .addGap(35, 35, 35))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(label_ordine)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(label_numeroOrdine))
                                        .addComponent(panel_info, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(tabbedPanel, PREFERRED_SIZE, 601, PREFERRED_SIZE))
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_ordine)
                                        .addComponent(label_numeroOrdine))
                                .addGap(20, 20, 20)
                                .addComponent(panel_info, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tabbedPanel, PREFERRED_SIZE, 130, PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(button_confermaOrdine)
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel_sfondo, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panel_sfondo, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        int nRows = this.table_inOrdinazione.getRowCount();
        for (int i = 0; i < nRows; i++) {
            if (table_inOrdinazione.getValueAt(i, 0) != null) {
                if ((boolean) table_inOrdinazione.getValueAt(i, 0)) {
                    System.out.println(table_inMagazzino.getValueAt(i, 2));
                } else {
                    //to do: quando si inizializza la lista, settare il boolean a false
                    System.out.println("non selezionata");
                }
            }
        }
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View_AggiungiArticoliOrdine().setVisible(true);
            }
        });
    }
}
