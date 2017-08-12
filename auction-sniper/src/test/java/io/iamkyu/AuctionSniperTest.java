package io.iamkyu;

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
    private AuctionSniper auctionSniper;

    @Test
    public void reportsLostWhenAuctionCloses() {
        //given
        auctionSniper = new AuctionSniper(sniperListener);

        //when
        auctionSniper.auctionClosed();

        //then
        verify(sniperListener).sniperLost();
    }

}
