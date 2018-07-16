package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;

public class MaterialiDAO {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(MaterialiDAO.class);

    private final String SELECT = "SELECT * FROM MATERIALE";

    public String[] getMateriali() {
        String[] sport = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECT);
            ResultSet resultset = pst.executeQuery();
            sport = mapRowToArrayMateriali(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return sport;
    }

    private String[] mapRowToArrayMateriali(ResultSet resultset) {
        String[] materiali = null;
        try {
            ArrayList<String> array = new ArrayList();
            while (resultset.next()) {
                array.add(resultset.getString("nome"));
            }
            materiali = new String[array.size()];
            array.toArray(materiali);
        } catch (Exception ex) {
            log.error(ex);
        }
        return materiali;
    }

    private String mapRowToMateriali(ResultSet resultset) {
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
