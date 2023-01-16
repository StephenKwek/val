package Exercise2022.Applications;


import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 362. Design Hit Counter
 *
 * Design a hit counter which counts the number of hits received in the past 5 minutes (i.e., the past 300 seconds).
 *
 * Your system should accept a timestamp parameter (in seconds granularity), and you may assume that calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing). Several hits may arrive roughly at the same time.
 *
 * Implement the HitCounter class:
 *
 * HitCounter() Initializes the object of the hit counter system.
 * void hit(int timestamp) Records a hit that happened at timestamp (in seconds). Several hits may happen at the same timestamp.
 * int getHits(int timestamp) Returns the number of hits in the past 5 minutes from timestamp (i.e., the past 300 seconds).
 *
 * Not submitted
 */
public class M_P362_DesignHitCounter {
    static class HitCounter {
        private static final int CAP = 300;
        int[] count = new int[CAP];
        int[] timestampKey = new int[CAP];
        AtomicInteger total = new AtomicInteger(0);
        Current curStatus;
        private Semaphore mutex;
        private ReadWriteLock lock = new ReentrantReadWriteLock();
        private Lock readLock = lock.readLock();
        private Lock writeLock = lock.writeLock();


        class Current {
            public int timestamp;
            public int count;

            Current(int timestamp, int hit) {
                this.timestamp = timestamp;
                this.count = hit;
            }
        }
        public HitCounter() {
            mutex = new Semaphore(1);
        }

        private int updateTotal(int timestamp, int hit) {
            writeLock.lock();
            if (timestamp == curStatus.timestamp) {
                return ++curStatus.count;
            }
            int curIndex = curStatus.timestamp % CAP;
            timestampKey[curIndex] = curStatus.timestamp;
            int delta = curStatus.count - count[curIndex];
            total.addAndGet(delta);
            count[curIndex] = curStatus.count;
            for (int i = 1; i < CAP; i++) {
                int j = curIndex - i;
                if (j < 0) {
                    j = CAP - 1;
                }
                if (timestampKey[j] == curStatus.timestamp - i) {
                    break;
                }
                total.addAndGet(-1 * count[j]);
                timestampKey[j] = curStatus.timestamp - i;
                count[j] = 0;
            }
            curStatus = new Current(timestamp, hit);
            int result = total.get();
            writeLock.unlock();
            return result;
        }

        public void hit(int timestamp) {
            if (curStatus == null) {
                curStatus = new Current(timestamp, 1);
            } else if (timestamp == curStatus.timestamp) {
                curStatus.count++;
            } else {
                updateTotal(timestamp, 1);
            }
        }

        public int getHits(int timestamp) {
            readLock.lock();
            if (timestamp != timestampKey[timestamp % CAP]) {
                readLock.unlock();
                return updateTotal(timestamp, 0);
            } else {
                int result = total.get();
                readLock.unlock();
                return result;
            }
        }
    }

    public static void main(String[] args) {
        HitCounter hitCounter = new HitCounter();
        hitCounter.hit(1);       // hit at timestamp 1.
        hitCounter.hit(2);       // hit at timestamp 2.
        hitCounter.hit(3);       // hit at timestamp 3.
        System.out.println(hitCounter.getHits(4));
        assert 3 == hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
        hitCounter.hit(300);     // hit at timestamp 300.
        assert 4 == hitCounter.getHits(300); // get hits at timestamp 300, return 4.
        assert 3 == hitCounter.getHits(301); // get hits at timestamp 301, return 3.
    }
}
