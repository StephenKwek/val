package Exercise2022.ArrayProblems;

/**
 * 900. RLE Iterator
 * We can use run-length encoding (i.e., RLE) to encode a sequence of integers. In a run-length encoded array of even
 * length encoding (0-indexed), for all even i, encoding[i] tells us the number of times that the non-negative integer
 * value encoding[i + 1] is repeated in the sequence.
 *
 * For example, the sequence arr = [8,8,8,5,5] can be encoded to be encoding = [3,8,2,5]. encoding = [3,8,0,9,2,5] and
 * encoding = [2,8,1,8,2,5] are also valid RLE of arr.
 * Given a run-length encoded array, design an iterator that iterates through it.
 *
 * Implement the RLEIterator class:
 *
 * RLEIterator(int[] encoded) Initializes the object with the encoded array encoded.
 * int next(int n) Exhausts the next n elements and returns the last element exhausted in this way. If there is no
 * element left to exhaust, return -1 instead.
 */
public class M_P900_RLEIterator {
    public static class RLEIterator {
        private int blockIndex;
        private int remaining;
        private final int[] encoding;

        public RLEIterator(int[] encoding) {
            blockIndex = 0;
            remaining = encoding[0];
            this.encoding = encoding;
        }

        public int next(int n) {
                while (2 * blockIndex < encoding.length && remaining < n) {
                    n -= remaining;
                    blockIndex++;
                    if (blockIndex * 2 >= encoding.length) {
                        return - 1;
                    }
                    remaining = encoding[2 * blockIndex];
                }
                if (2 * blockIndex >= encoding.length) {
                    return -1;
                }
                int result = encoding[2 * blockIndex + 1];
                remaining = remaining - n;
                if (remaining == 0) {
                    blockIndex++;
                    remaining = encoding[2 * blockIndex];
                }
                return result;
        }
    }

    public static void main(String[] args) {
        RLEIterator p = new RLEIterator(new int[] {3, 8, 0, 9, 2, 5});
        assert 8 == p.next(2);
        assert 8 == p.next(1);
        assert 5 == p.next(1);
        assert -1 == p.next(2);
    }
}
