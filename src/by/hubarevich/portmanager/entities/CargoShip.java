package by.hubarevich.portmanager.entities;

import by.hubarevich.portmanager.action.IState;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Anton on 07.12.2015.
 */
public class CargoShip{

    private int shipId;
    private String shipName;
    private AtomicInteger containerQuantity;
    private volatile IState state;
    private int maxContainerQuantity;
    private int containersToOtherShip;




    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public AtomicInteger getContainerQuantity() {
        return containerQuantity;
    }

    public void setContainerQuantity(AtomicInteger containerQuantity) {
        this.containerQuantity = containerQuantity;
    }

    public int getMaxContainerQuantity() {
        return maxContainerQuantity;
    }

    public void setMaxContainerQuantity(int maxContainerQuantity) {
        this.maxContainerQuantity = maxContainerQuantity;
    }

    public int getContainersToOtherShip() {
        return containersToOtherShip;
    }

    public void setContainersToOtherShip(int containersToOtherShip) {
        this.containersToOtherShip = containersToOtherShip;
    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }



    @Override
    public String toString()
    {
        return "Ship [id=" + shipId + ", shipName=" + shipName + "]\n" +
                "containers after harbor operations =  " + containerQuantity;
    }
}
