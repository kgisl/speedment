/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.internal.core.stream.parallelstrategy;

import java.util.HashSet;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Supplier;
import static java.util.stream.Collectors.toSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
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
public class ArraySpliteratorTest extends BaseSpliteratorTest {

    private static final int SIZE = 2048;

    private static final Supplier<Stream<Integer>> STREAM_SUPPLIER
            = () -> IntStream.range(0, SIZE)
            .boxed();

    private Integer[] array;
    private Set<Integer> expectedSet;

    public ArraySpliteratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        array = STREAM_SUPPLIER.get().toArray(Integer[]::new);
        instance = new ArraySpliterator(array, 0);
        expectedSet = STREAM_SUPPLIER.get().collect(toSet());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testTrySplit() {
        printTestName();
        Set<String> threadNames = new HashSet<>();
        final Set<Integer> set = StreamSupport.stream(instance, true)
                .peek(i -> threadNames.add(Thread.currentThread().getName()))
                .collect(toSet());
        assertEquals(expectedSet, set);
        System.out.println("Threads used:" + threadNames);
        assertTrue(threadNames.size() > Runtime.getRuntime().availableProcessors()/2-1);
    }

    @Test
    public void testForEachRemaining() {
        printTestName();
        final Set<Integer> set = new HashSet<>();
        assertTrue(instance.tryAdvance(set::add));
        instance.forEachRemaining(set::add);
        assertEquals(expectedSet, set);
    }

    @Test
    public void testTryAdvance() {
        printTestName();
        IntStream.range(0, SIZE).forEach(i -> assertTrue("error for:" + i, instance.tryAdvance(DO_NOTHING)));
        assertFalse(instance.tryAdvance(DO_NOTHING));
    }

    @Test
    public void testEstimateSize() {
        printTestName();
        int remains = SIZE;
        for (int i = 0; i < SIZE; i++) {
            assertEquals(remains--, instance.estimateSize());
            instance.tryAdvance(DO_NOTHING);
        }
    }

}
