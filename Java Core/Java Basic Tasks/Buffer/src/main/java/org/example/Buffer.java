package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Buffer {
    private final Queue<Object> dataQueue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public void produce(Object data) throws InterruptedException {
        lock.lock();
        try {
            int maxSize = 10;
            while (dataQueue.size() == maxSize) {
                notFull.await();
            }
            dataQueue.add(data);
            System.out.println("Produced " + data);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object consume() throws InterruptedException {
        lock.lock();
        try {
            while (dataQueue.isEmpty()) {
                notEmpty.await();
            }
            Object data = dataQueue.poll();
            System.out.println("Consumed " + data);
            notFull.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }
}

class Producer implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private final Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Object data = generateData();
                buffer.produce(data);
                synchronized (buffer) {
                    buffer.notify(); // Notify the buffer after producing data
                }
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            logger.error("Producer thread interrupted: {}", e.getMessage());
            Thread.currentThread().interrupt(); // Preserve interruption status
        }
    }

    private Object generateData() {
        return "Data";
    }
}

class Consumer implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (buffer) {
                    while (buffer.consume() == null) {
                        buffer.wait(); // Wait for data in the buffer
                    }
                    Object data = buffer.consume();
                    processData(data);
                }
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            logger.error("Consumer thread interrupted: {}", e.getMessage());
            Thread.currentThread().interrupt(); // Preserve interruption status
        }
    }

    private void processData(Object data) {
        logger.info("Processed {}", data);
    }
}
