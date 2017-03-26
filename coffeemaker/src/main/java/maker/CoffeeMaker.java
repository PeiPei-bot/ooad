package maker;

import maker.api.CoffeeMakerAPI;
import maker.model.M4ContainmentVessel;
import maker.model.M4HotWaterSource;
import maker.model.M4UserInterface;

/**
 * @author Kj Nam
 * @since 2017-03-25
 */
public class CoffeeMaker {
    public static void main(String[] args) {
        CoffeeMakerAPI api = new M4CoffeeMakerAPIImplementation();
        M4UserInterface ui = new M4UserInterface(api);
        M4HotWaterSource hws = new M4HotWaterSource(api);
        M4ContainmentVessel cv = new M4ContainmentVessel(api);

        ui.init(hws, cv);
        hws.init(ui, cv);
        cv.init(ui, hws);

        while (true) {
            ui.poll();
            hws.poll();
            cv.poll();
        }
    }


    /*
     * Dummy
     */
    private static class M4CoffeeMakerAPIImplementation implements CoffeeMakerAPI {
        @Override
        public int getWarmerPlateStatus() {
            return 0;
        }

        @Override
        public int getBoilerStatus() {
            return 0;
        }

        @Override
        public int getBrewButtonStatus() {
            return 0;
        }

        @Override
        public void setBoilerState(int boilerStatus) {

        }

        @Override
        public void setWarmerState(int warmetState) {

        }

        @Override
        public void setIndicatorState(int indicatorState) {

        }

        @Override
        public void setReliefValveState(int reliefValveState) {

        }
    }
}
