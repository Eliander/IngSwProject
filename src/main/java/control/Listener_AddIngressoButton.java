package control;

import dao.DAOSettings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Ingresso;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_RegistraIngresso;

public class Listener_AddIngressoButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_AddIngressoButton.class);
    private View_RegistraIngresso frame;
    
    private static DAOSettings DAO = Main.getDAO();

    public Listener_AddIngressoButton(View_RegistraIngresso frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Ingresso ing = this.frame.getIngresso();
        if(ing.getArticoli().size() > 0){
            //aggiungere da DAO l'ingresso alla lista ingressi
            if(DAO.getIngressoeDAO().addIngresso(ing)){
                Utente user = this.frame.getUser();
                this.frame.dispose();
                View_RegistraIngresso view_RegistraIngresso = new View_RegistraIngresso(user);
                view_RegistraIngresso.setVisible(true);
                log.info("Ingresso creato");
                Main.showPopup(view_RegistraIngresso, "Ingresso creato");
            }
        }
    }
}
