package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Eliander
 */
public class MaterialiPerArticoloDAO {
    
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(MaterialiPerArticoloDAO.class);

    private final String SELECT = "SELECT * FROM MATERIALIPERARTICOLO WHERE NOMEARTICOLO = ?";
    
    public ArrayList<String> getMaterialiFromArticolo(String nomeArticolo){
        ArrayList<String> materiali = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECT);
            pst.setString(1, nomeArticolo);
            ResultSet resultset = pst.executeQuery();
            materiali = mapRowToMaterialiPerArticolo(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return materiali;
    
    }

    private ArrayList<String> mapRowToMaterialiPerArticolo(ResultSet resultset) {
        ArrayList<String> materiali = new ArrayList();
        try {
            while (resultset.next()) {
                //Aggiungo se presenti alla lista i materiali
                materiali.add(resultset.getString("nomeMateriale"));
            }

        } catch (Exception ex) {
            log.error(ex);
        }
        return materiali;
    }
    
}
