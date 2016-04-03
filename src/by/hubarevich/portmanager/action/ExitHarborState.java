package by.hubarevich.portmanager.action;

import by.hubarevich.portmanager.entities.CargoShip;

/**
 * Class provides Exit State for Object CargoShip
 * Created by Anton on 11.12.2015.
 */
public class ExitHarborState implements IState {

    public static final String STATE = "exit";


    @Override
    public void movingContainer(CargoShip cargoShip) {

        System.out.println(cargoShip.getShipName() + " is now going out of the harbor");
    }

    @Override
    public boolean checkContainerIsExist(CargoShip cargoShip) {

        return true;
    }

    @Override
    public boolean checkFreeSpaceIsExist(CargoShip cargoShip) {

        return true;
    }

    @Override
    public String toString() {
        return STATE;
    }

    @Override
    public void containersToOtherShip(CargoShip cargoShip) {

    }
}
