package by.hubarevich.portmanager.action;

import by.hubarevich.portmanager.entities.CargoShip;
import by.hubarevich.portmanager.entities.Harbor;
import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class provides Releasing State operations for Object CargoShip
 * Created by Anton on 11.12.2015.
 */
public class ReleasingToStoreState implements IState {

    private static final Logger LOG = Logger.getLogger(ReleasingToStoreState.class);
    /**
     * moves containers to Harbor store and/or to buffer
     *
     * @param cargoShip
     */
    @Override
    public void movingContainer(CargoShip cargoShip) {



        cargoShip.setState(new LoadingState());
        containersToOtherShip(cargoShip);
        do {


            if (checkFreeSpaceIsExist(cargoShip) & checkContainerIsExist(cargoShip)) {
                cargoShip.setContainerQuantity(new AtomicInteger(cargoShip.getContainerQuantity().decrementAndGet()));
                Harbor.containersInStore++;
            }
            System.out.println(cargoShip.getShipName() + " moves 1 container to store \n" +
                    "Store capacity " + String.valueOf(Harbor.containersInStore)  + "\n" +
            "Ship store capacity " + String.valueOf(cargoShip.getContainerQuantity()));

        } while (checkContainerIsExist(cargoShip));

    }

    @Override
    public boolean checkContainerIsExist(CargoShip cargoShip) {

        return cargoShip.getContainerQuantity().get() > 0;
    }

    @Override
    public boolean checkFreeSpaceIsExist(CargoShip cargoShip) {

        return Harbor.STORE_CAPACITY > Harbor.containersInStore;
    }


    /**
     * moves containers to buffer
     *
     * @param cargoShip
     */
    @Override
    public void containersToOtherShip(CargoShip cargoShip) {
        int counter = 0;

        while (true) {
            counter++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                LOG.error(e);
            }
            if ((cargoShip.getContainersToOtherShip() >= counter) && (cargoShip.getContainerQuantity().get() > 0)) {
                Harbor.containersInBuffer++;
                cargoShip.setContainerQuantity(new AtomicInteger(cargoShip.getContainerQuantity().decrementAndGet()));
                System.out.println(cargoShip.getShipName() + " moves " + String.valueOf(counter)
                        + " containers to buffer \n" + "buffer size is " + String.valueOf(Harbor.containersInBuffer));
            } else {
                break;
            }
        }

    }
}
