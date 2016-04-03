package by.hubarevich.portmanager.entities;

/**
 * Entity class for Harbor.
 * Harbor - singleton
 * Created by Anton on 07.12.2015.
 */
public class Harbor {

    public static final int STORE_CAPACITY = 10 ;
    public static volatile int containersInStore;
    public static volatile int containersInBuffer;
    private static String harborName;
    private static int piersQuantity;



    private Harbor () {}


    /**
     * SingleTone Holder
     */
    private static class SingletonHolder { // nested class
        private final static Harbor INSTANCE = new Harbor();
    }

    /**
     * Initializes Harbor Instance
     * @return
     */
    public static Harbor getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public static void setHarborName(String harborName) {
        Harbor.harborName = harborName;
    }

    public static void setPiersQuantity(int piersQuantity) {
        Harbor.piersQuantity = piersQuantity;
    }

    public static int getPiersQuantity() {
        return piersQuantity;
    }
}
