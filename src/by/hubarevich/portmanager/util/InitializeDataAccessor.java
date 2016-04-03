package by.hubarevich.portmanager.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Anton on 06.12.2015.
 * Class provides getting data from properties files
 */
public class InitializeDataAccessor {

    private static Properties properties;
    static final Logger LOG = Logger.getLogger(InitializeDataAccessor.class);

    /**
     * Method performs reading from properties file
     *
     * @return object Properties
     */

    public static Properties dataFileReader(String propertyFile) {

        try {
            properties = new Properties();
            FileInputStream inputStream = new FileInputStream(propertyFile);
            properties.loadFromXML(inputStream);
        } catch (FileNotFoundException e) {
            LOG.error(e);
        } catch (IOException e) {
            LOG.error(e);
        }
        return properties;
    }
}
