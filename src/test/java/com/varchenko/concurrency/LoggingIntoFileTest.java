package com.varchenko.concurrency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class LoggingIntoFileTest {

    @BeforeEach
    void run() {
        File file = new File("m.log");
        Printer printer = new Printer(file);
        Thread t1 = new Thread(new LoggingIntoFile(printer, 10, true));
        Thread t2 = new Thread(new LoggingIntoFile(printer, 10, false));
        t1.start();
        t2.start();
    }

    @Test
    void isFileExists() {
        File file = new File("m.log");
        assertTrue(file.exists());
    }

    @Test
    void fileContent() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("m.log"));
        List<String> list = new ArrayList<>();
        List<String> expected = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        while (sc.hasNextLine()) {
            list.add(sc.nextLine());
        }
        assertEquals("5", list.get(4));
        assertEquals(expected, list);
    }
}