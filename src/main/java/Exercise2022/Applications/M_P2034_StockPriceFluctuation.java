package Exercise2022.Applications;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 2034. Stock Price Fluctuation
 * You are given a stream of records about a particular stock. Each record contains a timestamp and the corresponding price of the stock at that timestamp.
 *
 * Unfortunately due to the volatile nature of the stock market, the records do not come in order. Even worse, some records may be incorrect. Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.
 *
 * Design an algorithm that:
 *
 * Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
 * Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
 * Finds the maximum price the stock has been based on the current records.
 * Finds the minimum price the stock has been based on the current records.
 * Implement the StockPrice class:
 *
 * StockPrice() Initializes the object with no price records.
 * void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
 * int current() Returns the latest price of the stock.
 * int maximum() Returns the maximum price of the stock.
 * int minimum() Returns the minimum price of the stock.
 *
 * NOT TESTED
 */
public class M_P2034_StockPriceFluctuation {

    public class StockPrice {
        private Map<Integer, Integer> actualPrice = new HashMap<>();
        private TreeMap<Integer, Integer> priceDistribution = new TreeMap<>();
        private int min = Integer.MAX_VALUE;
        private int max = 0;
        private int currentTime = 0;
        public void update(int timestamp, int price) {
            if (timestamp > currentTime) {
                currentTime = timestamp;
            }
            if (actualPrice.containsKey(timestamp)) {
                int oldPrice = actualPrice.get(timestamp);
                Integer count = priceDistribution.get(oldPrice);
                count--;
                if (count == 0) {
                    priceDistribution.remove(oldPrice);
                } else {
                    priceDistribution.put(oldPrice, count);
                }
            }
            actualPrice.put(timestamp, price);
            int newCount = priceDistribution.getOrDefault(price, 0) + 1;
            priceDistribution.put(price, newCount);
        }

        public int current() {
            return actualPrice.get(currentTime);
        }

        public int maximum() {
            return priceDistribution.lastKey();
        }

        public int minimum() {
            return priceDistribution.firstKey();
        }
    }
}
