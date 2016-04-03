package by.hubarevich.portmanager.entities;

import by.hubarevich.portmanager.creator.FleetCreatorImpl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class-entity for Pier.
 * Created by Anton on 08.12.2015.
 */
public class Pier implements Runnable {

    private final Lock LOCK = new ReentrantLock();
    private int pierId;
    private CargoShip cargoShip;

    public Pier(int pierId) {

        this.pierId = pierId;

    }

    public int getPierId() {
        return pierId;
    }

    /**
     * provides the start of new Thread and manage operations performing
     */

    @Override
    public void run() {

        System.out.println("Pier " + String.valueOf(this.getPierId()) + " is ready to get ships");
        while (!FleetCreatorImpl.cargoShips.isEmpty()) {

            try {

                // trying to get Object CargoShip and get Lock

                if (!FleetCreatorImpl.cargoShips.isEmpty() & LOCK.tryLock()) {

                    System.out.println(FleetCreatorImpl.cargoShips.getFirst().getShipName() + " is on the pier" +
                            String.valueOf(this.getPierId()) + "\n");
                    cargoShip = FleetCreatorImpl.cargoShips.removeFirst();

                    while (true) {

                        // starting of operations
                        cargoShip.getState().movingContainer(this.cargoShip);

                        // the condition of process ending

                        if ("exit".equals(cargoShip.getState().toString())) {

                            System.out.println(cargoShip.getShipName() + " is now leaving the harbor. Ship summary: \n"
                                    + cargoShip.toString());


                            break;
                        }

                    }
                }
            } finally {
                LOCK.unlock();
            }
        }
    }


    @Override
    public String toString() {
        return ("This is the pier number " + String.valueOf(pierId));
    }
}
