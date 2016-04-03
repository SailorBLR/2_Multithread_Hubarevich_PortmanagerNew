package by.hubarevich.portmanager.creator;

import by.hubarevich.portmanager.entities.CargoShip;
import org.w3c.dom.Element;


/**
 * Interface of Ship creator
 * Created by Anton on 07.12.2015.
 */
public interface ShipCreator {

    CargoShip createShip (Element eElement);

}
