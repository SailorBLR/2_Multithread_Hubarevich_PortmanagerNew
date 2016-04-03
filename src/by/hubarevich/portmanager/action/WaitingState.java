package by.hubarevich.portmanager.action;

import by.hubarevich.portmanager.entities.CargoShip;
import by.hubarevich.portmanager.entities.Harbor;
import org.apache.log4j.Logger;

/**
 * Class provides the Wait state operation for Object CargoShip
 * Created by Anton on 11.12.2015.
 */
public class WaitingState implements IState {

    private static final Logger LOG = Logger.getLogger(WaitingState.class);

    /**
     * increasing CargoShip State to Loading or Releasing
     * @param cargoShip
     */

    @Override
    public void movingContainer(CargoShip cargoShip) {


        while (true) {

            if (checkContainerIsExist(cargoShip) & (Harbor.STORE_CAPACITY > Harbor.containersInStore)) {

                cargoShip.setState(new ReleasingToStoreState());
                break;

            } else {
                if (!checkContainerIsExist(cargoShip) & (Harbor.containersInStore > 0)) {

                    cargoShip.setState(new LoadingState());
                    break;

                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        LOG.error(e);
                    }

                }
            }
        }
    }

    @Override
    public boolean checkContainerIsExist(CargoShip cargoShip) {


        return cargoShip.getContainerQuantity().get() > 0;
    }

    @Override
    public boolean checkFreeSpaceIsExist(CargoShip cargoShip) {


        return cargoShip.getMaxContainerQuantity() > cargoShip.getContainerQuantity().get();
    }

    @Override
    public void containersToOtherShip(CargoShip cargoShip) {


    }
}
