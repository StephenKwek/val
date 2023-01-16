package Exercise2022.ArrayProblems;

/**
 * 121. Best Time to Buy and Sell Stock
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to
 * sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 */
public class EasyP121BestTimeToSellAndBuyStocks {
    public int maxProfit(int[] prices) {
        int curLow = prices[0];
        int curHigh = prices[0];
        int best = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < curLow) {
                curLow = i;
                curHigh = i;
            } else if (prices[i] > curHigh) {
                curHigh = i;
                best = Math.max(curHigh - curLow, best);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        EasyP121BestTimeToSellAndBuyStocks easyP121BestTimeToSellAndBuyStocks = new EasyP121BestTimeToSellAndBuyStocks();
        assert 5 == easyP121BestTimeToSellAndBuyStocks.maxProfit(new int[]{7,1,5,3,6,4});
        assert 0 == easyP121BestTimeToSellAndBuyStocks.maxProfit(new int[]{7,6,4,3,1});
    }
}
