package io.iamkyu;

import org.jivesoftware.smack.XMPPException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.iamkyu.ApplicationRunner.SNIPER_XMPP_ID;

/**
 * @author Kj Nam
 */
public class AuctionSniperEndToEndTest {
    private FakeAuctionServer auction;
    private ApplicationRunner application;

    @Before
    public void setUp() {
        auction = new FakeAuctionServer("item-54321");
        application = new ApplicationRunner();
    }

    @Test
    public void sniperJoinsAuctionUntilAuctionCloses() throws XMPPException, InterruptedException {
        //given
        auction.startSellingItem();
        
        //when
        application.startBiddingIn(auction);
        
        //then
        auction.hasReceivedJoinRequestFromSniper();
        
        
        //when
        auction.announceClosed();
        
        //then
        application.showSniperHasLostAuction();
    }

    @Test
    public void sniperMakesAHigherBidButLoses() throws XMPPException, InterruptedException {
        //given
        auction.startSellingItem();

        //when
        application.startBiddingIn(auction);

        //then
        auction.hasReceivedJoinRequestFrom(SNIPER_XMPP_ID);

        //when
        auction.reportPrice(1000, 98, "other bidder");

        //then
        application.hasShownSniperIsBidding();

        //when
        auction.hasReceivedBid(1098, SNIPER_XMPP_ID);
        auction.announceClosed();

        //then
        application.showSniperHasLostAuction();
    }

    @After
    public void tearDown() {
        auction.stop();
        application.stop();
    }
}
