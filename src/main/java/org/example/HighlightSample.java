package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>The sole purpose of this class is about demonstrating
 * <a target="_blank" href="https://highlightjs.org">highlight.js</a> syntax highlighting support in Javadoc.</p>
 *
 */
public class HighlightSample {

    /**
     * <p>Executing {@link #printCurrentDay()} according to its documentation.</p>
     *
     * @param args Unused
     */
    public static void main(String[] args) {

        System.out.println("Current date is:");

        HighlightSample.printCurrentDay();
    }


    /**
     * <p>Printing current date and time.</p>
     *
     * <p>Usage sample:</p>
     *
     * <pre><code class="java"> final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
     * LocalDateTime now = LocalDateTime.now();
     * System.out.println(dtf.format(now));</code></pre>
     *
     * <p>Illustrating syntax highlighting of different programming languages using a totally unrelated SQL example:</p>
     *
     * <pre><code class="sql"> CREATE TABLE CUSTOMERS(
     *    ID   INT              NOT NULL,
     *    NAME VARCHAR (20)     NOT NULL,
     *    AGE  INT              NOT NULL,
     *    ADDRESS  CHAR (25) ,
     *    SALARY   DECIMAL (18, 2),
     *    PRIMARY KEY (ID)
     * )</code></pre>
     *
     */
    public static void printCurrentDay() {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }
}
