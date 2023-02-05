package edu.neu.coe.info6205.util;
import com.google.common.collect.Lists;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
public class ArraysBenchmarksSort {
    private static final int REPETATION = 100;
    private static final int FROM = -1000;
    private static final int TO = 1000;
    private static final Random RANDOM = new Random();


    public static void main(String[] args) {
        final List<Integer[]> randNumList = Lists.newArrayList(

                RandomNoArrayGeneration(400),
                RandomNoArrayGeneration(800),
                RandomNoArrayGeneration(1600),
                RandomNoArrayGeneration(3200),
                RandomNoArrayGeneration(6400),
                RandomNoArrayGeneration(12800),
                RandomNoArrayGeneration(25600)

        );

        for(Integer[] randomlyGenNo : randNumList) {
            System.out.println("----------------------------------");
            System.out.println("No of element, N: " + randomlyGenNo.length);
            benchmarkCallTimerFunction("Array In Random Order", randomlyGenNo);
            Arrays.sort(randomlyGenNo, Collections.reverseOrder());
            benchmarkCallTimerFunction("Array In Reverse Order", randomlyGenNo);
            Arrays.sort(randomlyGenNo, 0, (randomlyGenNo.length)/2);
            benchmarkCallTimerFunction("Array In Partial Order", randomlyGenNo);
            Arrays.sort(randomlyGenNo);
            benchmarkCallTimerFunction("Array In Order",randomlyGenNo);
            System.out.println("----------------------------------");
        }
    }

    private static Integer[] RandomNoArrayGeneration(int a) {
        Integer[] RndNo = new Integer[a];

        for(int z=0; z<a; z++){
            RndNo[z] = RANDOM.nextInt(TO + 1 - FROM) + FROM;
        }
        return RndNo;
    }

    public static void benchmarkCallTimerFunction(final String description, final Integer[] inArr){
        final Consumer<Integer[]> sortUsingInsertion = InsertionSort::sort;
        final Supplier<Integer[]> supplier = () -> Arrays.copyOf(inArr, inArr.length);

        final Benchmark_Timer<Integer[]> benchmark_timer = new Benchmark_Timer<>(description,null, sortUsingInsertion,null);
        final double meanOfArraySortingTimeInMilliSeconds = benchmark_timer.runFromSupplier(supplier, REPETATION);

        System.out.println(description + " : mean sort time (in ms): " + meanOfArraySortingTimeInMilliSeconds);
    }


}
