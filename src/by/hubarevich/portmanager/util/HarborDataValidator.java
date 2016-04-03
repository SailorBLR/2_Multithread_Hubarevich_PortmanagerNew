package by.hubarevich.portmanager.util;

import by.hubarevich.portmanager.entities.Harbor;
import by.hubarevich.portmanager.exception.WrongInputException;

import java.util.Properties;

/**
 * Class provides data verifying for harbor initializing from harbor_properties.xml
 * Created by Anton on 12.12.2015.
 */

public class HarborDataValidator {

    private static final String PROPERTY_HARBOR_NAME = "harborName";
    private static final String PROPERTY_START_CONTAINERS_QUANTITY = "startContainersQuantity";
    private static final String PROPERTY_PIERS_QUANTITY = "piersQuantity";
    private static final String PROPERTY_START_CONTAINERS_IN_BUFFER = "startContainersInBuffer";

    /**
     * Validates data
     * @param properties
     * @throws WrongInputException
     */

    public static void validateData(Properties properties) throws WrongInputException {

        if ((properties.getProperty(PROPERTY_HARBOR_NAME) == null) ||
                (Integer.valueOf(properties.getProperty(PROPERTY_START_CONTAINERS_QUANTITY)) == null) ||
                (Integer.valueOf(properties.getProperty(PROPERTY_PIERS_QUANTITY)) == null) ||
                (Integer.valueOf(properties.getProperty(PROPERTY_START_CONTAINERS_IN_BUFFER)) == null)) {

            throw new WrongInputException("No data inputed");

        }

        if ((Integer.valueOf(properties.getProperty(PROPERTY_START_CONTAINERS_QUANTITY)) > Harbor.STORE_CAPACITY ) ||
                (Integer.valueOf(properties.getProperty(PROPERTY_START_CONTAINERS_IN_BUFFER)) > Harbor.STORE_CAPACITY )) {

            throw new WrongInputException("It can't be container quantity more than ship store capacity");

        }

        if ((Integer.valueOf(properties.getProperty(PROPERTY_START_CONTAINERS_QUANTITY)) < 0 ) |
                (Harbor.STORE_CAPACITY < 0 )|
                (Integer.valueOf(properties.getProperty(PROPERTY_START_CONTAINERS_IN_BUFFER)) < 0 )|
                (Integer.valueOf(properties.getProperty(PROPERTY_PIERS_QUANTITY)) < 0 )) {

            throw new WrongInputException("Harbor's properties can't be less then 0");

        }

    }
}
