package maker.model;

import maker.Pollable;
import maker.api.CoffeeMakerAPI;

/**
 * @author Kj Nam
 * @since 2017-03-25
 */
public class M4ContainmentVessel extends ContainmentVessel implements Pollable {
    private CoffeeMakerAPI api;
    private int lastPotStatus;

    public M4ContainmentVessel(CoffeeMakerAPI api) {
        this.api = api;
        lastPotStatus = api.POT_EMPTY;
    }

    @Override
    public boolean isReady() {
        int plateStatus = api.getWarmerPlateStatus();
        return plateStatus == api.POT_EMPTY;
    }

    @Override
    public void poll() {
        int potStatus = api.getWarmerPlateStatus();
        if (potStatus != lastPotStatus) {
            if (isBrewing) {
                handleBrewingEvent(potStatus);
            } else if (isComplete == false) {
                handleCompleteEvent(potStatus);
            }
            lastPotStatus = potStatus;
        }

    }

    private void handleCompleteEvent(int potStatus) {
        if (potStatus == api.POT_NOT_EMPTY) {
            containerAvailable();
            api.setWarmerState(api.WARMER_ON);
        } else if (potStatus == api.WARMER_EMPTY) {
            containerUnavailable();
            api.setWarmerState(api.WARMER_OFF);
        } else {
            containerAvailable();
            api.setWarmerState(api.WARMER_OFF);
        }
    }

    private void handleBrewingEvent(int potStatus) {
        if (potStatus == api.POT_NOT_EMPTY) {
            api.setWarmerState(api.WARMER_ON);
        } else if (potStatus == api.WARMER_EMPTY) {
            api.setWarmerState(api.WARMER_OFF);
        } else {
            api.setWarmerState(api.WARMER_OFF);
            declareComplete();
        }
    }
}
