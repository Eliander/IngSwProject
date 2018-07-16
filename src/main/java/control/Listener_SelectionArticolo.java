package control;

import dao.DAOSettings;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Articolo;
import model.Statistica;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_InserisciArticolo;

public class Listener_SelectionArticolo implements ListSelectionListener{
    private final static Logger log = LogManager.getLogger(Listener_SelectionArticolo.class);
    private View_InserisciArticolo frame;
    
    private static DAOSettings DAO = Main.getDAO();
    
    public Listener_SelectionArticolo(View_InserisciArticolo frame){
        this.frame = frame;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        Articolo art = this.frame.getSelectedArticolo();
        if(art != null){
            String s = "";
            //ricavo le statistiche dell'articolo
            ArrayList<Statistica> stats = DAO.getStatisticaDAO().getStatisticheByArticolo(art);
            for(Statistica sta : stats){
                s += sta.toString()+"\n";
            }
            this.frame.setLabelStatistiche(s);
        }
    }
}
