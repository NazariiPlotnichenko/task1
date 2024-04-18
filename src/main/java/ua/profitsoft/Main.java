package ua.profitsoft;

import ua.profitsoft.logic.StatisticsCalculator;
import ua.profitsoft.model.Book;
import ua.profitsoft.utils.FileParser;
import ua.profitsoft.utils.XmlWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        if (args.length < 2) {
            System.out.println("Usage: java Main <folder_path> <attribute>");
            return;
        }

        String folderPath = args[0];
        String attribute = args[1];

        List<Book> books = parseBooks(folderPath);

        Map<String, Integer> stat = StatisticsCalculator.generateStatistics(books, attribute);

        XmlWriter.writeStatisticsToXml(stat, attribute);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;

        System.out.println("Програма виконана за " + duration + " мілісекунд");
    }

    private static List<Book> parseBooks(String folderPath) {
        List<Book> books = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(8);

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    executor.execute(new FileParser(file, books));
                }
            }
        }

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return books;
    }
}