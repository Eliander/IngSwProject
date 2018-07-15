package dao;

import control.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import model.Uscita;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Eliander
 */
public class UscitaDAO {
    
    /*
    
    create table USCITA(
    bolla int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    dataUscita date,
    spedizioniere varchar(20),
    idOrdine int,
    PRIMARY KEY(bolla),
    FOREIGN KEY (spedizioniere) REFERENCES SPEDIZIONIERE(nome),
    FOREIGN KEY (idOrdine) REFERENCES ORDINE(id));
    
    */
    
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(SpedizioniereDAO.class);

    private final String SELECTBYID = "SELECT * FROM USCITA WHERE ID = ?";
    private final String SELECT = "SELECT * FROM USCITA";

    public Uscita getUscitaById(int id) {
        Uscita uscita = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYID);
            pst.setInt(1, id);
            ResultSet resultset = pst.executeQuery();
            uscita = mapRowToUscita(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return uscita;
    }

    private Uscita mapRowToUscita(ResultSet resultset) {
        Uscita uscita = null;
        try {
            if (resultset.next()) {
                uscita = new Uscita(resultset.getInt("bolla"), new Date(resultset.getDate("dataUscita").getTime()), null, null, null);
                //setto gli articoli usciti
                uscita.setArticoli(Main.getDAO().getArticoloMagazzinoDAO().getArticoliMagazzinoByCodiceUscita(resultset.getInt("bolla")));
                //setto l'ordine
                uscita.setOrdine(Main.getDAO().getOrdineDAO().getOrdineByID(resultset.getInt("idOrdine")));
                //setto lo spedizioniere
                uscita.setSpedizioniere(Main.getDAO().getSpedizioniereDAO().getSpedizioniereByNome(resultset.getString("spedizioniere")));
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return uscita;
    }
    
}
