package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Articolo;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Eliander
 */
public class MaterialiPerArticoloDAO {
    
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(MaterialiPerArticoloDAO.class);

    private final String SELECT = "SELECT * FROM MATERIALIPERARTICOLO WHERE NOMEARTICOLO = ?";
    private final String INSERT = "INSERT INTO MATERIALIPERARTICOLO(nomeArticolo, nomeMateriale) VALUES (?, ?)";
    
    public ArrayList<String> getMaterialiByArticolo(String nomeArticolo){
        ArrayList<String> materiali = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECT);
            pst.setString(1, nomeArticolo);
            ResultSet resultset = pst.executeQuery();
            materiali = mapRowToArrayListMaterialiPerArticolo(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return materiali;
    
    }
    
    public boolean insertMaterialiByArticolo(Articolo articolo){
        boolean esito = true;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(INSERT);
            pst.setString(1, articolo.getNome());
            for(String materiale : articolo.getMateriali()){
                pst.setString(2, materiale);
                pst.executeUpdate();
            }
            con.close();
        } catch (Exception ex) {
            log.error(ex);
            esito = false;
        }
        return esito;
    
    }

    private ArrayList<String> mapRowToArrayListMaterialiPerArticolo(ResultSet resultset) {
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
