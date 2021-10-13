package com.varchenko.concurrency;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Printer {

    private volatile boolean isOdd;
    FileWriter writer;
    File file;

    public Printer(File file) {
        this.file = file;
    }

    synchronized void printOdd(int num) {
        while (isOdd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        printNum(num);
        isOdd = true;
        notifyAll();
    }

    synchronized void printEven(int num) {
        while (!isOdd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        printNum(num);
        isOdd = false;
        notifyAll();
    }

    private void printNum(Integer num) {
        try {
            writer = new FileWriter(file, true);
            writer.write(num.toString());
            writer.write('\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}