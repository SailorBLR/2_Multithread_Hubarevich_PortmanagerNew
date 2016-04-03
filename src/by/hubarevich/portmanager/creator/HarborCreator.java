package by.hubarevich.portmanager.creator;

import by.hubarevich.portmanager.entities.Harbor;
import by.hubarevich.portmanager.exception.WrongInputException;
import by.hubarevich.portmanager.util.HarborDataValidator;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * Class-creator for Harbor Instance
 * Created by Anton on 14.12.2015.
 */
public class HarborCreator {

    private static final Logger LOG = Logger.getLogger(HarborCreator.class);
    private final String PROPERTY_HARBOR_NAME = "harborName";
    private final String PROPERTY_START_CONTAINERS_QUANTITY = "startContainersQuantity";
    private final String PROPERTY_PIERS_QUANTITY = "piersQuantity";
    private final String PROPERTY_START_CONTAINERS_IN_BUFFER = "startContainersInBuffer";


    /**
     * Initiates The one Instance of Harbor
     * @param properties
     */

    public void initiateHarbor (Properties properties) {

        Harbor.getInstance();
        try {
        HarborDataValidator.validateData(properties);
        } catch (WrongInputException e) {
            LOG.error(e);
        }
        Harbor.getInstance();
        Harbor.setHarborName(properties.getProperty(PROPERTY_HARBOR_NAME));
        Harbor.setPiersQuantity(Integer.valueOf(properties.getProperty(PROPERTY_PIERS_QUANTITY)));
        Harbor.containersInBuffer = Integer.valueOf(properties.getProperty(PROPERTY_START_CONTAINERS_IN_BUFFER));
        Harbor.containersInStore = Integer.valueOf(properties.getProperty(PROPERTY_START_CONTAINERS_QUANTITY));


    }

}
