package io.iamkyu;

import java.util.EventListener;

interface AuctionEventListener extends EventListener {
    enum PriceSource {
        FromSniper, FromOtherBidder
    }

    void auctionClosed();

    void currentPrice(int price, int increment, PriceSource bidder);
}
