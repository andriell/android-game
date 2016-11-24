package com.andriell.mygame;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestThread {

    public static void main(String[] args) {
        new TestThread().go();
    }

    public void go() {
        Printer printer = new Printer();
        Thread1 thread1 = new Thread1(printer);
        Thread2 thread2 = new Thread2(printer);
        thread1.start();
        thread2.start();
    }

    class Thread1 extends Thread {
        Printer printer;

        public Thread1(Printer printer) {
            this.printer = printer;
        }

        @Override
        public void run() {
            printer.print1();
        }
    }

    class Thread2 extends Thread {
        Printer printer;

        public Thread2(Printer printer) {
            this.printer = printer;
        }

        @Override
        public void run() {
            printer.print2();
        }
    }

    class Printer {
        public void print1() {
            synchronized (this) {
                for (int i = 0; i < 100; i += 2) {
                    System.out.print(i);
                }
                System.out.println();
            }
        }

        public void print2() {
            synchronized (this) {
                for (int i = 1; i < 100; i += 2) {
                    System.out.print(i);
                }
                System.out.println();
            }
        }
    }
}