package control;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Eliander
 */
public class MouseListener_ScambiaDatiTabelleOrdine extends MouseAdapter {

    private final static Logger log = LogManager.getLogger(MouseListener_ScambiaDatiTabelleOrdine.class);
    private JFrame frame;
    private JTable from;
    private JTable to;

    public MouseListener_ScambiaDatiTabelleOrdine(JFrame frame, JTable from, JTable to) {
        this.frame = frame;
        this.from = from;
        this.to = to;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        int row = from.rowAtPoint(evt.getPoint());
        int col = from.columnAtPoint(evt.getPoint());
        //controllo se il click e stato fatto sul checkbox
        if (col == 0) {
            if (from.getValueAt(row, 0) != null) {
                if ((boolean) from.getValueAt(row, 0)) {
                    DefaultTableModel modelTo = (DefaultTableModel) to.getModel();
                    modelTo.addRow(getData(row));
                    DefaultTableModel modelFrom = (DefaultTableModel) from.getModel();
                    modelFrom.removeRow(row);
                    //aggiorno il costo totale
                    System.out.println("Riga spostata");
                } else {
                    //to do: quando si inizializza la lista, settare il boolean a false
                    System.out.println("non selezionata");
                }
            }
        }
    }

    private Object[] getData(int row) {
        Object[] values = new Object[this.from.getColumnCount()];
        values[0] = false;
        for (int i = 1; i < this.from.getColumnCount(); i++) {
            values[i] = from.getValueAt(row, i);
        }
        return values;
    }
}
