package by.hubarevich.portmanager.creator;

import by.hubarevich.portmanager.entities.CargoShip;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Deque;

/**
 * Interface of FleeCreator
 *
 * Fleet creator creates the Queue of ships, going to visit the harbor
 *
 * Created by Anton on 08.12.2015.
 */
public interface FleetCreator {

    Deque<CargoShip> createFleet(String dataSourceFile)throws ParserConfigurationException, SAXException, IOException;

}
