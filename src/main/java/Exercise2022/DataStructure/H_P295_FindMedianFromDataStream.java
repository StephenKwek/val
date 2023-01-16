package Exercise2022.DataStructure;

import java.util.Collections;
import java.util.PriorityQueue;

public class H_P295_FindMedianFromDataStream {
    public static class MedianFinder {
        final PriorityQueue<Double> left = new PriorityQueue<>(Collections.reverseOrder());
        final PriorityQueue<Double> right = new PriorityQueue<>();

        public void addNum(int num){
            if (left.size() < right.size()) {
                if (left.isEmpty() || num <= right.peek()) {
                    left.add((double) num);
                } else if (num > right.peek()){
                    Double minRight = right.poll();
                    left.add(minRight);
                    right.add((double)num);
                }
            } else {
                if (right.isEmpty() || num >= left.peek()) {
                    right.add((double) num);
                } else {
                    Double maxLeft = left.poll();
                    right.add(maxLeft);
                    left.add((double) num);
                }
            }
        }

        public double findMedian () {
            if (left.size() < right.size()) {
                return right.peek();
            } else {
                return (left.peek() + right.peek())/2.0;
            }
        }

        }

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(2);
        assert 1.5 == finder.findMedian();
        finder.addNum(3);
        assert 2.0 == finder.findMedian();
    }
}