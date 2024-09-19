package com.usecases;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.values.PCollection;

public class code_03_average_numbers_combineApproach {

    public static void main(String args[]) {

        Pipeline pipeline = Pipeline.create();

        PCollection<Integer> values = pipeline.apply(Create.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        PCollection<Double> average = numbers.apply(Combine.globally((Iterable<Integer> input) -> {
            int sum = 0;
            int count = 0;
            for (Integer number : input) {
                sum += number;
                count++;
            }
            return sum / (double) count;
        }));

    }

}
