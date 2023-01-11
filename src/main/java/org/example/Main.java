package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * A simple http://logging.apache.org/log4j/2.x demo,
 * see file src/main/resources/log4j2.xml for configuration options
 * and A1.log containing debugging output.
 */

public class Main {
    private static Logger log = LogManager.getLogger(Main.class);

    /**
     * Your application's main entry point.
     *
     * @param args Yet unused
     */

    public static void main( String[] args ) {
        GameManager manager = new GameManager();

        final int size = 10, mines = 15;
        int x,y;
        Field [][] fieldArray = new Field[size][size];
        manager.fillArray(fieldArray, size, mines);
        manager.fieldOutput(fieldArray, size);
        manager.getUserAction();

        log.debug("You may configure 'src/main/resources/log4j2.xml' ");
        log.debug("for adapting both console and 'A1.log' file output");


        // X: covered
        // space = uncovered
        // o = bomb
 }

 //TODO updateFieldArray method
}
