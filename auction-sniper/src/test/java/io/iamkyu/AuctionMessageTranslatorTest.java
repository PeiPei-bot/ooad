package io.iamkyu;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.packet.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static io.iamkyu.ApplicationRunner.SNIPER_ID;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class AuctionMessageTranslatorTest {

    private static final Chat UNUSED_CHAT = null;
    private AuctionMessageTranslator translator;

    @Mock private AuctionEventListener auctionEventListener;
    private Message message;

    @Before
    public void setUp() {
        message = new Message();
        translator = new AuctionMessageTranslator(SNIPER_ID, auctionEventListener);
    }

    @Test
    public void notifiesAuctionClosedWhenCloseMessageReceived() {
        //given
        message.setBody("SQLVersion: 1.1; Event: CLOSE;");

        //when
        translator.processMessage(UNUSED_CHAT, message);

        //then
        verify(auctionEventListener).auctionClosed();
    }

    @Test
    public void notifiesBidDetailWhenPriceMessageReceivedFromOtherBidder() {
        //given
        message.setBody("SQLVersion: 1.1; Event: PRICE; CurrentPrice: 192; Increment: 7; Bidder: Someone else;");

        //when
        translator.processMessage(UNUSED_CHAT, message);

        //then
        verify(auctionEventListener).currentPrice(192, 7,
                AuctionEventListener.PriceSource.FromOtherBidder);
    }

    @Test
    public void notifiesBidDetailWhenPriceMessageReceivedFromSniper() {
        //given
        message.setBody(String.format("SQLVersion: 1.1; Event: PRICE; CurrentPrice: 192; Increment: 7; Bidder: %s;", SNIPER_ID));

        //when
        translator.processMessage(UNUSED_CHAT, message);

        //then
        verify(auctionEventListener).currentPrice(192, 7,
                AuctionEventListener.PriceSource.FromSniper);
    }
}