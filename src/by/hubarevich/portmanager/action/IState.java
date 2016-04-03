package by.hubarevich.portmanager.action;

import by.hubarevich.portmanager.entities.CargoShip;

/**
 * Interface-Class for states of object CargoShip
 * Created by Anton on 11.12.2015.
 */
public interface IState {

    /**
     * moves containers
     * @param cargoship
     */
    public void movingContainer(CargoShip cargoship);

    /**
     * checks if container is exist
     * @param cargoship
     * @return true if containers is exist
     */
    public boolean checkContainerIsExist(CargoShip cargoship);

    /**
     * checks free space
     * @param cargoship
     * @return true if the aim store has a free space
     */
    public boolean checkFreeSpaceIsExist(CargoShip cargoship);

    /**
     * moves container to other ship from buffer, or from ship to buffer
     * @param cargoShip
     */
    public void containersToOtherShip (CargoShip cargoShip);


}
