package Exercise2022.Combinatorics;

/**
 * 793. Preimage Size of Factorial Zeroes Function
 * Let f(x) be the number of zeroes at the end of x!. Recall that x! = 1 * 2 * 3 * ... * x and by convention, 0! = 1.
 *
 * For example, f(3) = 0 because 3! = 6 has no zeroes at the end, while f(11) = 2 because 11! = 39916800 has two zeroes at the end.
 * Given an integer k, return the number of non-negative integers x have the property that f(x) = k.
 */
public class H_P793_PreimageSizeOfFactorialZerosFunction {
    private int numZeros(int intx, int x) {
        int count = 0;
        while (intx > 1) {
            intx = intx / x;
            if (intx > 0)  {
                count++;
            }
        }
        return count;
    }
    public int preimageSizeFZF(int k) {
        int t = 0;
        int int5 = 5;
        int int10 = 10;
        for (int i = 0; i < k; i++) {
            t += numZeros(int5, 5);
            int5 += 10;
            if (t == k) {
                return 5;
            } else if (t > k){
                return 0;
            }

            t += numZeros(int10, 10);
            int10 += 10;
            if (t == k) {
                return 5;
            } else if (t > k){
                return 0;
            }
        }
        return 5; // only possible when k == 0;
    }

    public static void main(String[] args) {
        H_P793_PreimageSizeOfFactorialZerosFunction p = new H_P793_PreimageSizeOfFactorialZerosFunction();
        assert 0 == p.preimageSizeFZF(5);
        assert 5 == p.preimageSizeFZF(0);
        assert 5 == p.preimageSizeFZF(3);
    }
}
