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
       log.debug("You may configure 'src/main/resources/log4j2.xml' ");
       log.debug("for adapting both console and 'A1.log' file output");


        // X: covered
        // space = uncovered
        // o = bomb


        System.out.println("    0  1  2  3  4  5  6  7  8  9 ");
        System.out.println("------------------------------");
        System.out.println("0 |" );
        System.out.println("1 |");
        System.out.println("2 |");
        System.out.println("3 |");
        System.out.println("4 |");
        System.out.println("5 |");
        System.out.println("6 |");
        System.out.println("7 |");
        System.out.println("8 |");
        System.out.println("9 |");

    }

    /**
     * This method purely exists for providing Junit tests.
     *
     * @param a first parameter
     * @param b second parameter
     * @return the sum of both parameters.
     */
    public static int add(final int a, final int b) {
        return a + b;
    }
}
