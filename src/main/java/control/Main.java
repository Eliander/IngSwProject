package control;

import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Eliander
 */
public class Main {
    
    private static Logger log = LogManager.getLogger(Main.class);
    
    private Properties config = new Properties();
    
    public static void main(String[] args) {
        System.out.println("ok");
        log.info("prova");
    }
    
}
