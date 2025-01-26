package model;

import java.io.BufferedWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logs  {
    private final String catalog = "logs/";
    private static Logs log = null;
    private BufferedWriter writer;

    private Logs() {
        try {
            String filename = catalog + "LOG--"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd--HH-mm-ss"))+".txt";
            writer = new BufferedWriter(new java.io.FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createLog() {
        if (log == null) {
            log = new Logs();
        }
    }

    public static void writeLog(String message) {
        try {
            log.writer.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " - " + message + "\n");
            log.writer.flush(); // flush() - zapisuje dane z bufora do pliku , czy to jest potrzebne ? odpowiedz: tak, bo inaczej nie zapisze
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeLog() {
        try {
            log.writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
