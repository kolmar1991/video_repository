package pl.edu.agh;

import java.io.File;
import java.io.FileWriter;

// for testing purposes
public class MainClass {
    
    public static void main(String argv[]) throws Exception {
        Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                System.err.println("Exception in thread: " + thread.getName());
                ex.printStackTrace();
            }
        };
        for (File file : new File("xmls").listFiles()) {
            if (file.isFile()) {
                Thread thread = new Thread(new Converter("xmls/" + file.getName(),
                        new FileWriter("xmls/results/result_" + file.getName())));
                thread.setUncaughtExceptionHandler(handler);
                thread.start();
                thread.join();
            }
        }
    }
}
