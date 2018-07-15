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
public class SportDAO {
    
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(SportDAO.class);

    private final String SELECT = "SELECT * FROM SPORT";
    
    
    public String[] getSport(){
        String [] sport = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECT);
            ResultSet resultset = pst.executeQuery();
            sport = mapRowToArraySport(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return sport;
    }
    
    private String[] mapRowToArraySport(ResultSet resultset) {
        String[] sport = null;
        try {
            ArrayList<String> array = new ArrayList();
            while (resultset.next()) {
                array.add(resultset.getString("nome"));
            }
            sport = new String[array.size()];
            array.toArray(sport);
        } catch (Exception ex) {
            log.error(ex);
        }
        return sport;
    }
    
    private String mapRowToSport(ResultSet resultset) {
        String categoria = null;
        try {
            if (resultset.next()) {
                categoria = resultset.getString("nome");
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return categoria;
    }
}
