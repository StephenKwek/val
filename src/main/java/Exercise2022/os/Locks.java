package Exercise2022.os;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

public class Locks {
    static class MySemaphore {
        private Semaphore semaphore;
        private int limit;
        public MySemaphore(int limit) {
            semaphore = new Semaphore(limit);
            this.limit = limit;
        }

        boolean tryAcquire() {
            System.out.println("acquire: " + semaphore.availablePermits());
            return semaphore.tryAcquire();
        }

        void release() {
            synchronized(this) {
                System.out.println("release: " + semaphore.availablePermits());
                if (semaphore.availablePermits() < this.limit) {
                    semaphore.release();
                }
            }
        }

        int availableSlots() {
            return semaphore.availablePermits();
        }

        int queueLength() {
            return semaphore.getQueueLength();
        }

        static void testSemaphore() throws InterruptedException {
            int slots = 10;
            ExecutorService executorService = Executors.newFixedThreadPool(slots);
            MySemaphore ms = new MySemaphore(4);

            System.out.println("--------------");
            IntStream.range(0, slots)
                    .forEach(i -> executorService.execute(ms::tryAcquire));
            System.out.println("--------------");
            IntStream.range(0, 10)
                    .forEach(i -> executorService.execute(ms::release));
            System.out.println("--------------");
            Thread.sleep(100);
            IntStream.range(0, 10)
                    .forEach(i -> executorService.execute(ms::tryAcquire));
            executorService.shutdown();

            System.out.println(ms.availableSlots());
        }
    }

    static class MyMutexInteger {
        private Semaphore mutex;
        private int val;
        MyMutexInteger(int val) {
            mutex = new Semaphore(1);
            this.val = val;
        }

        void set(int val) throws InterruptedException {
            mutex.acquire();
            this.val = val;
            Thread.sleep(1000);
            System.out.println("set:" + this.val);
            mutex.release();
        }

        int getVal() {
            return this.val;
        }

        int getQueueLength() {
            return this.mutex.getQueueLength();
        }
        static void testMutex() throws InterruptedException {
            ExecutorService executor = Executors.newFixedThreadPool(10);
            MyMutexInteger myMutexInteger = new MyMutexInteger(1);
            IntStream.range(0, 20).forEach(
                    i -> executor.execute(() -> {
                        try {
                            myMutexInteger.set(i);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }));
            System.out.println("val:" + myMutexInteger.getVal());
            Thread.sleep(1000);
            System.out.println("val:" + myMutexInteger.getVal());
            System.out.println("queue:" + myMutexInteger.getQueueLength());
        }
    }

    static class MyReadWriteLockCounter {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock writeLock = lock.writeLock();
        Lock readLock = lock.writeLock();
        int val;

        public MyReadWriteLockCounter() {
            this.val = 0;
        }

        public void inc() {
            try {
                writeLock.lock();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                this.val++;
            } finally {
                writeLock.unlock();
                System.out.println("inc: " + read());
            }
        }

        public int read() {
            try {
                readLock.lock();
                System.out.println("read: " + val);
                return val;
            } finally {
                readLock.unlock();
            }
        }

        public static void testReadWriteLock() {
            Random random = new Random();
            ExecutorService executor = Executors.newFixedThreadPool(10);
            MyReadWriteLockCounter myReadWriteLockCounter = new MyReadWriteLockCounter();
            IntStream.range(0, 10).forEach(i -> executor.execute(() -> {
                if (random.nextInt(10) < 3) {
                    System.out.println("inc");
                    myReadWriteLockCounter.inc();
                } else {
                    System.out.println("read");
                    myReadWriteLockCounter.read();
                }
            }));
        }
    }

    static class ConditionalBarb {
        private static final int NUM_BARBERS = 3;
        private static final int MAX_QUEUE_LENGTH = 7;
        Semaphore availablerBabers = new Semaphore(MAX_QUEUE_LENGTH);
        Semaphore queueCapactiy = new Semaphore(NUM_BARBERS);
        Lock modifySystem = new ReentrantLock();

        void barber() throws InterruptedException {
            while (true) {
                if (queueCapactiy.availablePermits() < MAX_QUEUE_LENGTH && availablerBabers.availablePermits() > 0) {
                    modifySystem.lock();
                    queueCapactiy.release();
                    availablerBabers.acquire();
                    // cur hair
                    availablerBabers.release();
                    modifySystem.unlock();
                }
            }
        }

        void customer() throws InterruptedException {
            while (true) {
                modifySystem.lock();
                if (queueCapactiy.availablePermits() > 0) {
                    queueCapactiy.wait();
                }
                modifySystem.unlock();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        //MySemaphore.testSemaphore();
        //MyMutexInteger.testMutex();
        //MyReadWriteLockCounter.testReadWriteLock();
        Semaphore t = new Semaphore(10);
        System.out.println(t.availablePermits());
        for (int i = 0; i < 12; i++) {
            t.acquire();
            System.out.println("----------------------");
            System.out.println(t.availablePermits());
            System.out.println(t.getQueueLength());
        }
    }
}
