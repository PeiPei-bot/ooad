package io.iamkyu;

import org.jivesoftware.smack.XMPPException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Kj Nam
 */
public class AuctionSniperEndToEndTest {
    private FakeAuctionServer auctionServer;
    private ApplicationRunner applicationRunner;

    @Before
    public void setUp() {
        auctionServer = new FakeAuctionServer("item-54321");
        applicationRunner = new ApplicationRunner();
    }

    @Test
    public void sniperJoinsAuctionUntilAuctionCloses() throws XMPPException, InterruptedException {
        //given
        auctionServer.startSellingItem();
        
        //when
        applicationRunner.startBiddingIn(auctionServer);
        
        //then
        auctionServer.hasReceivedJoinRequestFromSniper();
        
        
        //when
        auctionServer.announceClosed();
        
        //then
        applicationRunner.showSniperHasLostAuction();
    }

    @After
    public void tearDown() {
        auctionServer.stop();
        applicationRunner.stop();
    }
}
