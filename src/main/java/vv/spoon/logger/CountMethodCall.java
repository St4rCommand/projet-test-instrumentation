package vv.spoon.logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by romain on 28/03/17.
 */
public class CountMethodCall {

    private static PrintWriter fileWriter;
    protected static Map<String, Integer> counters = new HashMap();


    public static void increment(String key) {

        Integer counterValue = 1;

        if (counters.containsKey(key)) {
            counterValue = counters.get(key)+1;
        }

        counters.put(key, counterValue);

    }

    public static void writeLog() {

        try {
            PrintWriter writer = getWriter();

            for (Map.Entry<String, Integer> counter : counters.entrySet()){
                writer.write(counter.getKey()+": "+counter.getValue()+"\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        fileWriter.close();
    }

    protected static PrintWriter getWriter() throws FileNotFoundException {
        if(fileWriter == null) {
            vv.spoon.logger.ShutdownHookCounter shutdownHook = new vv.spoon.logger.ShutdownHookCounter();
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            fileWriter = new PrintWriter("log");
        }
        return fileWriter;
    }
}
