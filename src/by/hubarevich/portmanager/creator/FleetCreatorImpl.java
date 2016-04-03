package by.hubarevich.portmanager.creator;

import by.hubarevich.portmanager.entities.CargoShip;
import by.hubarevich.portmanager.exception.WrongInputException;
import by.hubarevich.portmanager.util.ShipDataValidator;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Anton on 08.12.2015.
 */
public class FleetCreatorImpl implements FleetCreator {
    private static final Logger LOGGER = Logger.getLogger(FleetCreatorImpl.class);
    private static final String SHIP_TAG_NAME = "ship";
    public static volatile Deque<CargoShip> cargoShips;

    /**
     * Initializes a list of ships
     * Creates new CargoShip Object
     * Adds CargoShip to list
     */

    private static Deque<CargoShip> parseShipsFromXML(String dataSourceFile) {

        cargoShips = new LinkedList<CargoShip>();
        CargoShipCreatorImpl cargoShipCreator = new CargoShipCreatorImpl();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(dataSourceFile));
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName(SHIP_TAG_NAME);

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    ShipDataValidator.validateData(eElement);

                    cargoShips.add(cargoShipCreator.createShip(eElement));
                }
            }
        } catch (ParserConfigurationException e) {
            LOGGER.error(e);
        } catch (SAXException e) {
            LOGGER.error(e);
        } catch (IOException e) {
            LOGGER.error(e);
        } catch (WrongInputException e) {
            LOGGER.error(e);
        }
        return cargoShips;
    }

    /**
     * creates fleet
     *
     * @param dataSourceFile - the String with the address of ship_source.xml
     * @return - the Queue
     */

    @Override
    public Deque<CargoShip> createFleet(String dataSourceFile) {

        cargoShips = parseShipsFromXML(dataSourceFile);

        return cargoShips;
    }
}
