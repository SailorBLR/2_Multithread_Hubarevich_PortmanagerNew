package by.hubarevich.portmanager.main;


import by.hubarevich.portmanager.creator.FleetCreator;
import by.hubarevich.portmanager.creator.FleetCreatorImpl;
import by.hubarevich.portmanager.creator.HarborCreator;
import by.hubarevich.portmanager.entities.Harbor;
import by.hubarevich.portmanager.entities.Pier;
import by.hubarevich.portmanager.util.InitializeDataAccessor;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Anton on 06.12.2015.
 * It's the runner class for an application
 */
public class HarborLiveRunner {


    private static final Logger LOG = Logger.getLogger(HarborLiveRunner.class);
    private static final String HARBOR_PROPERTIES_ADDRESS = "harbor_properties.xml";
    private static final String SHIPS_PROPERTIES_ADDRESS = "ships_properties.xml";


    public static void main(String[] args) {


        FleetCreator fleetCreator = new FleetCreatorImpl();
        HarborCreator harborCreator = new HarborCreator();
        harborCreator.initiateHarbor(InitializeDataAccessor.dataFileReader(HARBOR_PROPERTIES_ADDRESS));

        try {


            fleetCreator.createFleet(SHIPS_PROPERTIES_ADDRESS);

            for (int i = 1; i <= Harbor.getPiersQuantity() ; i++) {
                new Thread(new Pier(i)).start();

            }
        } catch (ParserConfigurationException e) {
            LOG.error(e);
        } catch (IOException e) {
            LOG.error(e);
        } catch (SAXException e) {
            LOG.error(e);
        }
    }
}






