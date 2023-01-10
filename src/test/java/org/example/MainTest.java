package org.example;

import org.junit.Assert;
import org.junit.Test;

/**
 * Sample unit test of class {@link Main}.
 */
public class MainTest {
    /**
     * Test for correct sum of two arguments.
     */
    @Test
    public void testApp() {
        Assert.assertEquals( 33, Main.add(30, 3));

    }
}
