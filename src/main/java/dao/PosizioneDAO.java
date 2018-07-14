package dao;

import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Eliander
 */
public class PosizioneDAO {
    
    //to do: fare una query che ritorna i posti liberi in magazzino
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(PosizioneDAO.class);

    private final String CHEKFREE = "SELECT * FROM ARTICOLOMAGAZZINO WHERE NOME = ? ";
    private final String SELECTFREE = "SELECT * FROM ARTICOLOMAGAZZINO WHERE NOME = ? ";
}
