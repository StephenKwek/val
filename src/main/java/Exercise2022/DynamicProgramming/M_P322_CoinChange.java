package Exercise2022.DynamicProgramming;

import java.util.Arrays;


/**
 * 316. Remove Duplicate Letters
 *
 * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure
 * your result is
 * the smallest in lexicographical order
 *  among all possible results.
 */
public class M_P322_CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] numCoins = new int[amount + 1];
        Arrays.fill(numCoins, Integer.MAX_VALUE);
        numCoins[0] = 0;

        for (int i = 0; i < amount; i++) {
            if (numCoins[i] != Integer.MAX_VALUE) {
                for (int coin : coins) {
                    if (i + coin <= amount) {
                        if (numCoins[i] + 1 < numCoins[i + coin]) {
                            numCoins[i + coin] = numCoins[i] + 1;
                        }
                    }
                }
            }
        }
        return numCoins[amount] == Integer.MAX_VALUE ? -1 : numCoins[amount];
    }

    public static void main(String[] args) {
        M_P322_CoinChange p = new M_P322_CoinChange();
        //assert 3 == p.coinChange(new int[]{1,2,5}, 11);
        assert -1 == p.coinChange(new int[]{2}, 3);
        assert 0 == p.coinChange(new int[]{1}, 0);
    }
}
