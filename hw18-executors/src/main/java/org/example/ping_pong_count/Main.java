package org.example.ping_pong_count;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private String last = "2";

    private synchronized void action(String message) {
        int i = 1;
        int inc = 1;
        while(!Thread.currentThread().isInterrupted()) {
            try {
                while (last.equals(message)) {
                    this.wait();
                }
                logger.info("Поток {}: {}", message, i);

                if (i == 10) {
                    inc = -1;
                } else if (i == 1) {
                    inc = 1;
                }
                i += inc;

                last = message;
                sleep();
                notifyAll();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        Main pingPong = new Main();
        new Thread(() -> pingPong.action("1")).start();
        new Thread(() -> pingPong.action("2")).start();
    }

    private static void sleep() {
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
