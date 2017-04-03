package vv.spoon;

import vv.spoon.InstruTree;
import vv.spoon.processor.TreeProcessor;

import java.io.IOException;

/**
 * Created by romain on 03/04/17.
 */
public class TreeCallMain {

    public static void main(String[] args) throws IOException {
        InstruTree instru = new InstruTree(args[0], args[1], new TreeProcessor());

        //copy the project (args[0]) in the output directory (args[1])
        instru.initOutputDirectory();

        //instrumentalize the java code of output directory with LogProcessor
        instru.instru();
    }
}
