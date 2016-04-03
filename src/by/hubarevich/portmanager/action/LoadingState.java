package by.hubarevich.portmanager.action;

import by.hubarevich.portmanager.entities.CargoShip;
import by.hubarevich.portmanager.entities.Harbor;
import org.apache.log4j.Logger;
import org.apache.log4j.pattern.IntegerPatternConverter;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * Class provides Loading State operations for Object CargoShip
 * Created by Anton on 11.12.2015.
 */
public class LoadingState implements IState {

    public static final String STATE = "loading";
    private static final Logger LOG = Logger.getLogger(LoadingState.class);

    /**
     * moves containers from buffer and/or from Harbor store to ship
     *
     * @param cargoShip
     */
    @Override
    public void movingContainer(CargoShip cargoShip) {

        if ("waiting".equals(cargoShip.getState().toString())) {
            cargoShip.setState(new ReleasingToStoreState());
        } else {
            cargoShip.setState(new ExitHarborState());
        }

        containersToOtherShip(cargoShip);

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                LOG.error(e);
            }

            if (checkContainerIsExist(cargoShip) && checkFreeSpaceIsExist(cargoShip)) {
                Harbor.containersInStore--;
                cargoShip.setContainerQuantity(new AtomicInteger(cargoShip.getContainerQuantity().incrementAndGet()));
                System.out.println(cargoShip.getShipName() + " got 1 container from store \n" +
                        "Store capacity " + String.valueOf(Harbor.containersInStore) + "\n" +
                        "Ship store capacity " + String.valueOf(cargoShip.getContainerQuantity()));
            } else {
                break;
            }

        }
    }


    @Override
    public boolean checkContainerIsExist(CargoShip cargoShip) {

        return Harbor.containersInStore > 0;
    }

    @Override
    public boolean checkFreeSpaceIsExist(CargoShip cargoShip) {

        return cargoShip.getMaxContainerQuantity() > cargoShip.getContainerQuantity().get();
    }

    /**
     * performs loading from buffer
     *
     * @param cargoShip
     */

    @Override
    public void containersToOtherShip(CargoShip cargoShip) {
        int counter = 0;
        AtomicInteger increaser = new AtomicInteger(1);

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                LOG.error(e);
            }

            if (Harbor.containersInBuffer > 0 & counter < cargoShip.getContainersToOtherShip()) {
                Harbor.containersInBuffer--;
                counter++;
                cargoShip.setContainerQuantity(new AtomicInteger(cargoShip.getContainerQuantity().incrementAndGet()));
                System.out.println(cargoShip.getShipName() + " got " + String.valueOf(counter)
                        + " containers from buffer \n" + "buffer size is " + String.valueOf(Harbor.containersInBuffer));
            } else {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return STATE;
    }
}
