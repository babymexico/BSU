package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BufferTest {

    private static final Logger logger = LoggerFactory.getLogger(BufferTest.class);

    static {
        System.setProperty("org.slf4j.simpleLogger.defaultLogback", "true");
    }

    @Test
    void testProduceAndConsume() throws InterruptedException {
        Buffer buffer = new Buffer();

        Thread producerThread = new Thread(() -> {
            try {
                buffer.produce("Data1");
                buffer.produce("Data2");
            } catch (InterruptedException e) {
                logger.error("Producer thread interrupted: {}", e.getMessage());
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                Object data1 = buffer.consume();
                Object data2 = buffer.consume();
                assertEquals("Data1", data1);
                assertEquals("Data2", data2);
            } catch (InterruptedException e) {
                logger.error("Consumer thread interrupted: {}", e.getMessage());
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }

    @Test
    void testBufferLimit() throws InterruptedException {
        Buffer buffer = new Buffer();

        Thread producerThread = new Thread(() -> {
            try {
                buffer.produce("Data1");
                buffer.produce("Data2");
                buffer.produce("Data3");
                buffer.produce("Data4");
            } catch (InterruptedException e) {
                logger.error("Producer thread interrupted: {}", e.getMessage());
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                Object data1 = buffer.consume();
                Object data2 = buffer.consume();
                Object data3 = buffer.consume();
                assertEquals("Data1", data1);
                assertEquals("Data2", data2);
                assertEquals("Data3", data3);
            } catch (InterruptedException e) {
                logger.error("Consumer thread interrupted: {}", e.getMessage());
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }

    public static void main(String[] args) {
        logger.info("Starting BufferTest...");

        BufferTest bufferTest = new BufferTest();
        try {
            bufferTest.testProduceAndConsume();
            bufferTest.testBufferLimit();
        } catch (InterruptedException e) {
            logger.error("Exception occurred: {}", e.getMessage());
        }

        logger.info("BufferTest completed.");
    }
}
