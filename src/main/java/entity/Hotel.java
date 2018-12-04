package entity;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Hotel {
    public final static int LIMIT_REQUESTS = 15;
    private final static int MAXIMUM_QUEUE_SIZE = 5;
    private final static int MINIMUM_QUEUE_SIZE = 1;

    private AtomicInteger orderNumber = new AtomicInteger(0);
    private Queue<String> queue = new LinkedList<>();
    private volatile boolean stopBooker = false;

    public synchronized void put(String request) {
        while (queue.size() >= MAXIMUM_QUEUE_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        queue.offer(request);
        notifyAll();
    }

    public synchronized String get() {
        while (queue.size() < MINIMUM_QUEUE_SIZE) {
            if (orderNumber.get() >= LIMIT_REQUESTS) {
                stopBooker = true;
                break;
            }
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        String request = queue.poll();
        notifyAll();
        return request;
    }

    public int getOrderNumber() {
        return orderNumber.getAndIncrement();
    }

    public boolean isStopBooker() {
        return stopBooker;
    }
}
