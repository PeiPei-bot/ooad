package io.iamkyu;

import org.jivesoftware.smack.XMPPException;

import java.lang.reflect.InvocationTargetException;

import static io.iamkyu.FakeAuctionServer.XMPP_HOSTNAME;
import static io.iamkyu.Main.STATUS_BIDDING;
import static io.iamkyu.Main.STATUS_JOINING;
import static io.iamkyu.Main.STATUS_LOST;
import static io.iamkyu.Main.STATUS_WINNING;

/**
 * @author Kj Nam
 */
public class ApplicationRunner {
    public static final String SNIPER_ID = "sniper";
    public static final String SNIPER_PASSWORD = "sniper";
    public static final String SNIPER_XMPP_ID = SNIPER_ID + "@" + XMPP_HOSTNAME + "/Auction";

    private AuctionSniperDriver driver;

    public void startBiddingIn(FakeAuctionServer auctionServer) {
        Thread thread  = new Thread("Test Application") {
            @Override
            public void run() {
                try {
                    Main.main(XMPP_HOSTNAME,
                            SNIPER_ID,
                            SNIPER_PASSWORD,
                            auctionServer.getItemId());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (XMPPException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.setDaemon(true);
        thread.start();

        /**
         * @see https://stackoverflow.com/questions/23316432/windowlicker-is-not-working-on-os-x
         */
        System.setProperty("com.objogate.wl.keyboard", "Mac-GB");

        driver = new AuctionSniperDriver(1000);
        driver.showsSniperStatus(STATUS_JOINING);
    }

    public void showSniperHasLostAuction() {
        driver.showsSniperStatus(STATUS_LOST);
    }

    public void hasShownSniperIsBidding() {
        driver.showsSniperStatus(STATUS_BIDDING);
    }

    public void stop() {
        if (driver != null) {
            driver.dispose();
        }
    }

    public void hasShownSniperIsWinning() {
        driver.showsSniperStatus(STATUS_WINNING);
    }

    public void showsSniperHasWonAuction() {
        // TODO
    }
}
