package entity;

import java.util.LinkedList;
import java.util.Queue;

public class Hotel {
    private final static int LIMIT_REQUEST = 15;
    private final static int MAXIMUM_QUEUE_SIZE = 5;
    private final static int MINIMUM_QUEUE_SIZE = 1;

    private volatile int orderNumber = 0;
    private Queue<String> queue = new LinkedList<>();
    private boolean stopBooker = false;
    private boolean stopProducer = false;

    public synchronized void put(String request) {
        while (queue.size() >= MAXIMUM_QUEUE_SIZE) {
            if (orderNumber >= LIMIT_REQUEST) {
                stopProducer = true;
                notifyAll();
                return;
            }
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        queue.offer(request);
        notify();
    }

    public synchronized String get() {
        while (queue.size() < MINIMUM_QUEUE_SIZE) {
            if (orderNumber >= LIMIT_REQUEST) {
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
        notify();
        return request;
    }

    public synchronized int getOrderNumber() {
        int oldOrderNumber = orderNumber;
        orderNumber = oldOrderNumber + 1;
        return oldOrderNumber;
    }

    public boolean isStopBooker() {
        return stopBooker;
    }

    public boolean isStopProducer() {
        return stopProducer;
    }
}
