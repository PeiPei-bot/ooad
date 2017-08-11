package io.iamkyu;

interface AuctionEventListener {

    void auctionClosed();

    void currentPrice(int price, int increment);
}
