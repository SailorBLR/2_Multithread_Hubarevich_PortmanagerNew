package by.hubarevich.portmanager.util;

import by.hubarevich.portmanager.exception.WrongInputException;
import org.w3c.dom.Element;

/**
 * Created by Anton on 12.12.2015.
 * Class validates data to input from ship_properties.xml
 */

public class ShipDataValidator {

    private static final String SHIP_ID = "id";
    private static final String SHIP_NAME = "shipName";
    private static final String CONTAINERS_QUANTITY = "containerQuantity";
    private static final String MAX_CONTAINER_QUANTITY = "maxContainerQuantity";
    private static final String CONTAINERS_TO_OTHER_SHIP = "containersToOtherShips";

    /**
     * validates data
     * @param eElement - parsing result
     * @throws WrongInputException
     */

    public static void validateData(Element eElement) throws WrongInputException {

        //verifying for empty input

        if ((eElement.getAttribute(SHIP_ID) == null) ||
                (eElement.getElementsByTagName(SHIP_NAME).item(0).getTextContent()== null) ||
                (eElement.getElementsByTagName(CONTAINERS_QUANTITY).item(0).getTextContent() == null) ||
                (eElement.getElementsByTagName(MAX_CONTAINER_QUANTITY).item(0).getTextContent() == null) ||
                (eElement.getElementsByTagName(CONTAINERS_TO_OTHER_SHIP).item(0).getTextContent()== null)) {

            throw new WrongInputException("No data inputed");

        }


        if ((Integer.valueOf(eElement.getElementsByTagName(CONTAINERS_QUANTITY).item(0).getTextContent()) >
                Integer.valueOf(eElement.getElementsByTagName(MAX_CONTAINER_QUANTITY).item(0).getTextContent())) ||
                (Integer.valueOf(eElement.getElementsByTagName(CONTAINERS_TO_OTHER_SHIP).item(0).getTextContent()) >
                Integer.valueOf(eElement.getElementsByTagName(MAX_CONTAINER_QUANTITY).item(0).getTextContent()))) {

            throw new WrongInputException("It can't be container quantity more than ship store capacity");

        }

        if ((Integer.valueOf(eElement.getElementsByTagName(CONTAINERS_QUANTITY).item(0).getTextContent()) < 0 ) ||
             (Integer.valueOf(eElement.getElementsByTagName(MAX_CONTAINER_QUANTITY).item(0).getTextContent()) < 0 )||
             (Integer.valueOf(eElement.getElementsByTagName(CONTAINERS_TO_OTHER_SHIP).item(0).getTextContent()) < 0 )) {

            throw new WrongInputException("Ship's properties can't be less then 0");

        }

    }
}