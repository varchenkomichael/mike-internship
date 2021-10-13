package com.varchenko.concurrency;


public class LoggingIntoFile implements Runnable {

    private final Printer printer;
    private final int max;
    private final boolean isEven;

    public LoggingIntoFile(Printer printer, int max, boolean isEven) {
        this.printer = printer;
        this.max = max;
        this.isEven = isEven;
    }

    @Override
    public void run() {
        int num = isEven ? 2 : 1;
        while (num <= max) {
            if (isEven) {
                printer.printEven(num);
            } else {
                printer.printOdd(num);
            }
            num += 2;
        }
    }
}
