package maker.api;

/**
 * @author Kj Nam
 * @since 2017-03-25
 */
public interface CoffeeMakerAPI {
   int getWarmerPlateStatus();
   static final int WARMER_EMPTY = 0;
   static final int POT_EMPTY = 1;
   static final int POT_NOT_EMPTY = 2;

   int getBoilerStatus();
   static final int BOILER_EMPTY = 0;
   static final int BOILER_NOT_EMPTY = 1;

   int getBrewButtonStatus();
   static final int BREW_BUTTON_PUSHED = 0;
   static final int BREW_BUTTON_NOT_PUSHED = 1;

   void setBoilerState(int boilerStatus);
   static final int BOILER_ON = 0;
   static final int BOILER_OFF = 1;

   void setWarmerState(int warmerState);
   static final int WARMER_ON = 0;
   static final int WARMER_OFF = 1;

   void setIndicatorState(int indicatorState);
   static final int INDICATOR_ON = 0;
   static final int INDICATOR_OFF = 1;

   void setReliefValveState(int reliefValveState);
   static final int VALVE_OPEN = 0;
   static final int VALVE_CLOSED = 1;

}
