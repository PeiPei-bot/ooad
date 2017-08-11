package io.iamkyu;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.packet.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
        translator = new AuctionMessageTranslator(auctionEventListener);
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
    public void notifiesBidDetailWhenPriceMessageReceived() {
        //given
        message.setBody("SQLVersion: 1.1; Event: PRICE; CurrentPrice: 192; Increment: 7; Bidder: Someone else");

        //when
        translator.processMessage(UNUSED_CHAT, message);

        //then
        // verify(auctionEventListener, times(1)).currentPrice(192, 7);
        verify(auctionEventListener).currentPrice(192, 7);
    }
}