package Exercise2022.ArrayProblems;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class M_P528_RandomPickWithWeight {

    /**
     * 528. Random Pick with Weight
     * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
     *
     * You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive)
     * and returns it. The probability of picking an index i is w[i] / sum(w).
     *
     * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability
     * of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
     */
    public static class Solution {
        private double[] prob;
        private Random random = new Random(0);
        public Solution(int[] w) {
            prob = new double[w.length];
            double sum = 0;
            for (int i = 0; i < w.length; i++) {
                sum += w[i];
            }
            prob[0] += w[0] / sum;
            for (int i = 1; i < w.length; i++) {
                prob[i] = prob[i-1] + w[i] / sum;
            }
        }


        public int pickIndex() {
            double x = random.nextDouble();
            int left = 0;
            int right = prob.length - 1;
            int mid = (left + right) / 2;
            while (left < right) {
                if (x < prob[mid]) {
                    System.out.println("1");
                    right = mid;
                } else {
                    System.out.println("2");
                    left = (left + 1 != right) ? mid : right;
                }
                mid = (left + right) / 2;
            }
            return mid;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new int[] {1});
        int x = solution.pickIndex();
        assert 0 == x;

        solution = new Solution(new int[] {1, 2, 3, 4});
        int[] count = new int[4];
        for (int i = 0; i < 10000; i++) {
            count[solution.pickIndex()]++;
        }
        for (int i = 0; i < count.length; i++) {
            System.out.println(i + "--->" + count[i]);
        }
    }

}
