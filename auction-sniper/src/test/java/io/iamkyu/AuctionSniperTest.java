package io.iamkyu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * @author Kj Nam
 */
@RunWith(MockitoJUnitRunner.class)
public class AuctionSniperTest {

    @Mock private SniperListener sniperListener;
    @Mock private Auction auction;
    private AuctionSniper auctionSniper;

    @Before
    public void setUp() {
        auctionSniper = new AuctionSniper(auction, sniperListener);
    }

    @Test
    public void reportsLostWhenAuctionCloses() {
        //given

        //when
        auctionSniper.auctionClosed();

        //then
        verify(sniperListener).sniperLost();
    }

    @Test
    public void bidsHigherAndReportsBiddingWhenNewPriceArrives() {
        //given
        int price = 1001;
        int increment = 25;

        //when
        auctionSniper.currentPrice(price, increment);

        //then
        verify(auction).bid(price + increment);
        verify(sniperListener).sniperBidding();
    }
}
