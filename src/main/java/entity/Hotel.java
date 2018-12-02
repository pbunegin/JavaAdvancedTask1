package entity;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Hotel {
    private AtomicInteger orderNumber = new AtomicInteger(0);
    private Queue<String> queue = new LinkedList<>();
    private boolean stop = false;

    public synchronized void put(String request) {
        while (queue.size() >= 5) {
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
        while (queue.size() < 1) {
            if (orderNumber.get() >= 15) {
                stop = true;
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

    public boolean isStop() {
        return stop;
    }
}
