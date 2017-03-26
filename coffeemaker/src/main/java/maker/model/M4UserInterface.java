package maker.model;

import maker.Pollable;
import maker.api.CoffeeMakerAPI;

/**
 * @author Kj Nam
 * @since 2017-03-25
 */
public class M4UserInterface extends UserInterface implements Pollable {
    private CoffeeMakerAPI api;

    public M4UserInterface(CoffeeMakerAPI api) {
        this.api = api;
    }

    @Override
    public void poll() {
        int buttonStatus = api.getBrewButtonStatus();
        if (buttonStatus == api.BREW_BUTTON_PUSHED) {
            startBrewing();
        }
    }

    @Override
    public void done() {
        api.setIndicatorState(api.INDICATOR_ON);
    }

    @Override
    public void completeCycle() {
        api.setIndicatorState(api.INDICATOR_OFF);
    }
}
