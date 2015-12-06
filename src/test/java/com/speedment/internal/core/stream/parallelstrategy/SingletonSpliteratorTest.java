/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.internal.core.stream.parallelstrategy;

import static com.speedment.internal.core.stream.parallelstrategy.BaseSpliteratorTest.DO_NOTHING;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pemi
 */
public class SingletonSpliteratorTest extends BaseSpliteratorTest {

    private static final int VALUE = 43;

    public SingletonSpliteratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new SingletonSpliterator<>(VALUE, 0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testTrySplit() {
        printTestName();
        assertNull(instance.trySplit());
    }

    @Test
    public void testForEachRemaining() {
        printTestName();
        final List<Integer> expected = Arrays.asList(VALUE);
        final List<Integer> list = new ArrayList<>();
        instance.forEachRemaining(list::add);
        assertEquals(expected, list);
        instance.forEachRemaining(list::add);
        assertEquals(expected, list);
    }

    @Test
    public void testTryAdvance() {
        printTestName();
        assertTrue(instance.tryAdvance(DO_NOTHING));
        assertFalse(instance.tryAdvance(DO_NOTHING));
    }

    @Test
    public void testEstimateSize() {
        printTestName();
        assertEquals(1, instance.estimateSize());
        instance.tryAdvance(DO_NOTHING);
        assertEquals(0, instance.estimateSize());
        instance.tryAdvance(DO_NOTHING);
        assertEquals(0, instance.estimateSize());
    }

}
