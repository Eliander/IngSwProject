package dao;

import control.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import model.Ingresso;
import model.Uscita;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Eliander
 */
public class IngressoDAO {
    
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(IngressoDAO.class);

    private final String SELECTBYID = "SELECT * FROM INGRESSO WHERE ID = ?";
    
    public Ingresso getIngressoById(int id) {
        Ingresso ingresso = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYID);
            pst.setInt(1, id);
            ResultSet resultset = pst.executeQuery();
            ingresso = mapRowToIngresso(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return ingresso;
    }

    private Ingresso mapRowToIngresso(ResultSet resultset) {
        Ingresso ingresso = null;
        try {
            if (resultset.next()) {
                ingresso = new Ingresso(resultset.getInt("bolla"), new Date(resultset.getDate("dataIngresso").getTime()), null);
                //setto gli articoli entrati
                ingresso.setArticoli(Main.getDAO().getArticoloMagazzinoDAO().getArticoliMagazzinoByCodiceIngresso(resultset.getInt("bolla")));
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return ingresso;
    }
}
