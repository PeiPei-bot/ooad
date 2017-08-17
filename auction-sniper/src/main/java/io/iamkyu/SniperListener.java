package io.iamkyu;

/**
 * @author Kj Nam
 */
interface SniperListener {
    void sniperLost();

    void sniperBidding();

    void sniperWinning();

    void auctionClosed();

    void sniperWon();
}
