package org.example;


/**
 * <p>The sole purpose of this class is about demonstrating
 * <a target="_blank" href="https://www.latex-project.org">LaTeX</a> math formular support in Javadoc.</p>
 *
 */
public class Statistics {

    /**
     * <p>Computing the average of a given array's values</p>
     *
     * <p>Consider a series of values:</p>
     *
     * \[ \textbf{v} = \{v_1, v_2, \dots v_n\} \]
     *
     * <p>Its average is being defined as:</p>
     *
     * \[ \overline{\textbf{v}} = {1\over n}\sum_{i=1}^n {v_i} \]
     *
     * @param values A non-empty array of values.
     * @return The average of all array values.
     */
    public static double average(double[] values) {

        double result = 0;
        for (final double v: values) {
            result += v;
        }

        return result / values.length;

    }
}
