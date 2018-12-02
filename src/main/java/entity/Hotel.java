package entity;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Hotel {
    private AtomicInteger orderNumber = new AtomicInteger(0);
    private Queue<String> requests = new PriorityQueue<>();
    private boolean stop = false;

    public synchronized void put(String request) {
        while (requests.size() >= 5) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        requests.offer(request);
        notifyAll();
    }

    public synchronized String get() {
        while (requests.size() < 1) {
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
        String request = requests.poll();
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
