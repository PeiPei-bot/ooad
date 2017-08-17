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
    public void reportsLostIfAuctionClosesImmediately() {
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
        auctionSniper.currentPrice(price, increment, AuctionEventListener.PriceSource.FromOtherBidder);

        //then
        verify(auction).bid(price + increment);
        verify(sniperListener).sniperBidding();
    }

    @Test
    public void reportsIsWinningWhenCurrentPriceComesFromSniper() {
        //given
        int price = 123;
        int increment = 45;

        //when
        auctionSniper.currentPrice(price, increment, AuctionEventListener.PriceSource.FromSniper);

        //then
        verify(sniperListener).sniperWinning();
    }

    @Test
    public void reportsWonIfAuctionClosesWhenWinning() {
        //given
        int price = 123;
        int increment = 45;

        //when
        auctionSniper.currentPrice(price, increment, AuctionEventListener.PriceSource.FromSniper);
        sniperListener.auctionClosed();

        //then
        verify(sniperListener).sniperWinning();
    }
}
