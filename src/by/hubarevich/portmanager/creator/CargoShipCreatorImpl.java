package by.hubarevich.portmanager.creator;

import by.hubarevich.portmanager.entities.CargoShip;
import by.hubarevich.portmanager.action.WaitingState;
import org.w3c.dom.Element;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class creator for CargoShips
 * Created by Anton on 07.12.2015.
 */
public class CargoShipCreatorImpl implements ShipCreator {

    private static final String SHIP_ID = "id";
    private static final String SHIP_NAME = "shipName";
    private static final String CONTAINERS_QUANTITY = "containerQuantity";
    private static final String MAX_CONTAINER_QUANTITY = "maxContainerQuantity";
    private static final String CONTAINERS_TO_OTHER_SHIP = "containersToOtherShips";

    /**
     * creates the employee of CargoShip
     * @param eElement - gets the result of XML-document parsing
     * @return - CargoShip instance
     */

    @Override
    public CargoShip createShip(Element eElement) {

        CargoShip cargoShip = new CargoShip();

        cargoShip.setShipId(Integer.parseInt(eElement.getAttribute(SHIP_ID)));

        cargoShip.setShipName(eElement.getElementsByTagName(SHIP_NAME).item(0).getTextContent());

        cargoShip.setContainerQuantity(new AtomicInteger(Integer
                .parseInt(eElement.getElementsByTagName(CONTAINERS_QUANTITY).item(0).getTextContent())));

        cargoShip.setMaxContainerQuantity(Integer.parseInt(eElement.getElementsByTagName(MAX_CONTAINER_QUANTITY)
                .item(0).getTextContent()));

        cargoShip.setContainersToOtherShip(Integer.parseInt(eElement.getElementsByTagName(CONTAINERS_TO_OTHER_SHIP)
                .item(0).getTextContent()));

        cargoShip.setState(new WaitingState());


        return cargoShip;
    }
}
