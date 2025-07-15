package proje_02;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger instance;
    private final String logFile;
    private final DateTimeFormatter formatter;

    private Logger(String logFilePath) {
        this.logFile = logFilePath;
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public static Logger getInstance(String logFilePath) {
        if (instance == null) {
            instance = new Logger(logFilePath);
        }
        return instance;
    }

    private void log(String entry) {
        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write(entry + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error occurred while logging!");
        }
    }

    public void logCreatedOn(String id, String name, String surname, String job) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = String.format("%s - [%s] ID: %s (%s %s) created an account", timestamp, job, id, name, surname);
        log(logEntry);
    }

    public void logAction(String id, String name, String surname, String job, String action) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = String.format("%s - [%s] ID: %s (%s %s) %s", timestamp, job, id, name, surname, action);
        log(logEntry);
    }

    public void logLogout(String id, String name, String surname, String job) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = String.format("%s - [%s] ID: %s (%s %s) logged out", timestamp, job, id, name, surname);
        log(logEntry);
    }

    public void readLog() {
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading the log file!");
        }
    }

    public void copyLog(String destinationPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile));
             FileWriter writer = new FileWriter(destinationPath, true)) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while copying the log file!");
        }
    }
}
