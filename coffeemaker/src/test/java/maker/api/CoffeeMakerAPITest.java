package maker.api;

import maker.model.M4ContainmentVessel;
import maker.model.M4HotWaterSource;
import maker.model.M4UserInterface;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2017-03-25
 */
public class CoffeeMakerAPITest {
    private M4UserInterface ui;
    private M4HotWaterSource hws;
    private M4ContainmentVessel cv;
    private CoffeeMakerStub api;

    @Before
    public void setUp() {
        api = new CoffeeMakerStub();
        ui = new M4UserInterface(api);
        hws = new M4HotWaterSource(api);
        cv = new M4ContainmentVessel(api);
        ui.init(hws, cv);
        hws.init(ui, cv);
        cv.init(ui, hws);
    }

    private void poll() {
        ui.poll();
        hws.poll();
        cv.poll();
    }

    @Test
    public void initialConditions() {
        poll();
        assertFalse(api.boilerOn);
        assertFalse(api.lightOn);
        assertFalse(api.plateOn);
        assertTrue(api.valveClosed);
    }

    @Test
    public void startNoWater() {
        poll();
        api.buttonPressed = true;
        api.boilerEmpty = true;
        poll();
        assertFalse(api.boilerOn);
        assertFalse(api.lightOn);
        assertFalse(api.plateOn);
        assertTrue(api.valveClosed);
    }

    @Test
    public void goodStart() {
        normalStart();
        assertTrue(api.boilerOn);
        assertFalse(api.lightOn);
        assertFalse(api.plateOn);
        assertTrue(api.valveClosed);
    }

    private void normalStart() {
        poll();
        api.boilerEmpty = false;
        api.buttonPressed = true;
        poll();
    }

    class CoffeeMakerStub implements CoffeeMakerAPI {
        public boolean buttonPressed;
        public boolean lightOn;
        public boolean boilerOn;
        public boolean valveClosed;
        public boolean plateOn;
        public boolean boilerEmpty;
        public boolean potPresent;
        public boolean potNotEmpty;

        public CoffeeMakerStub() {
            buttonPressed = false;
            lightOn = false;
            boilerOn = false;
            valveClosed = true;
            plateOn = false;
            boilerEmpty = true;
            potPresent = true;
            potNotEmpty = false;
        }

        @Override
        public int getWarmerPlateStatus() {
            if (!potPresent) {
                return WARMER_EMPTY;
            } else if (potNotEmpty) {
                return POT_NOT_EMPTY;
            } else {
                return POT_EMPTY;
            }
        }

        @Override
        public int getBoilerStatus() {
            return boilerEmpty ? BOILER_EMPTY : BOILER_NOT_EMPTY;
        }

        @Override
        public int getBrewButtonStatus() {
            if (buttonPressed) {
                buttonPressed = false;
                return BREW_BUTTON_PUSHED;
            } else {
                return BREW_BUTTON_NOT_PUSHED;
            }
        }

        @Override
        public void setBoilerState(int boilerStatus) {
            boilerOn = (boilerStatus == BOILER_ON);
        }

        @Override
        public void setWarmerState(int warmerState) {
            plateOn = (warmerState == WARMER_ON);
        }

        @Override
        public void setIndicatorState(int indicatorState) {
            lightOn = (indicatorState == INDICATOR_ON);
        }

        @Override
        public void setReliefValveState(int reliefValveState) {
            valveClosed = (reliefValveState == VALVE_CLOSED);
        }
    }
}