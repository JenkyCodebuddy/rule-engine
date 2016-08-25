package jenky.codebuddy;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Joost on 17-8-2016.
 */
public class Log4j {

    static final Category log = Category.getInstance(Log4j.class);
    static final String LOG_PROPERTIES = "src/main/resources/log4j.properties";
    //static final Logger log = Logger.getLogger(Log4j.class.getName());

    public static void main(String[] args) throws FileNotFoundException, IOException{
        Properties props = new Properties();

        try
        {
            // load our log4j properties / configuration file
            props.load(new FileInputStream(LOG_PROPERTIES));
            PropertyConfigurator.configure(props);
            log.info("Logging initialized.");
        }
        catch(IOException e)
        {
            throw new RuntimeException("Unable to load logging property " + LOG_PROPERTIES);
        }
    }

}
