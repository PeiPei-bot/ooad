package io.iamkyu;

import io.iamkyu.AuctionEventListener.PriceSource;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

import java.util.HashMap;
import java.util.Map;

import static io.iamkyu.AuctionEventListener.PriceSource.FromOtherBidder;
import static io.iamkyu.AuctionEventListener.PriceSource.FromSniper;

public class AuctionMessageTranslator implements MessageListener {

    private String sniperId;
    private AuctionEventListener auctionEventListener;

    public AuctionMessageTranslator(String sniperId, AuctionEventListener auctionEventListener) {
        this.sniperId = sniperId;
        this.auctionEventListener = auctionEventListener;
    }

    @Override
    public void processMessage(Chat chat, Message message) {
        AuctionEvent auctionEvent = AuctionEvent.from(message.getBody());
        String type = auctionEvent.type();
        if ("CLOSE".equals(type)) {
            auctionEventListener.auctionClosed();
        } else if ("PRICE".equals(type)) {
            auctionEventListener.currentPrice(
                    auctionEvent.currentPrice(),
                    auctionEvent.increment(),
                    auctionEvent.isFrom(sniperId));
        }
    }

    public static class AuctionEvent {
        private Map<String, String> fields = new HashMap<>();

        public static AuctionEvent from(String messageBody) {
            AuctionEvent event = new AuctionEvent();
            for (String field : fieldsIn(messageBody)) {
                event.addField(field);
            }

            return event;
        }

        private void addField(String field) {
            String[] pair = field.split(":");
            fields.put(pair[0].trim(), pair[1].trim());
        }

        static String[] fieldsIn(String messageBody) {
            return messageBody.split(";");
        }

        public String type() {
            return fields.get("Event");
        }

        public int currentPrice() {
            return getInt("CurrentPrice");
        }

        public int increment() {
            return getInt("Increment");
        }

        public String get(String fieldName) {
            return fields.get(fieldName);
        }

        public int getInt(String fieldName) {
            return Integer.parseInt(get(fieldName));
        }

        public PriceSource isFrom(String sniperId) {
            return sniperId.equals(bidder()) ? FromSniper : FromOtherBidder;
        }

        private String bidder() {
            return get("Bidder");
        }
    }
}
