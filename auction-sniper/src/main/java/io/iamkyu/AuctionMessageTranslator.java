package io.iamkyu;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

public class AuctionMessageTranslator implements MessageListener {

    private AuctionEventListener auctionEventListener;

    public AuctionMessageTranslator(AuctionEventListener auctionEventListener) {
        this.auctionEventListener = auctionEventListener;
    }

    @Override
    public void processMessage(Chat chat, Message message) {
        auctionEventListener.auctionClosed();
    }
}
