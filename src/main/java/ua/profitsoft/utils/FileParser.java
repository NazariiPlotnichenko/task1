package ua.profitsoft.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ua.profitsoft.model.Book;

public class FileParser implements Runnable {
    private final File file;
    private final List<Book> books;

    public FileParser(File file, List<Book> books) {
        this.file = file;
        this.books = books;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Gson gson = new Gson();
            Type bookListType = new TypeToken<List<Book>>() {}.getType();
            List<Book> fileBooks = gson.fromJson(reader, bookListType);
            synchronized (books) {
                books.addAll(fileBooks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}