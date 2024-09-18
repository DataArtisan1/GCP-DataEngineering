package com.usecases;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.PCollection;

import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.Mean;
import org.apache.beam.sdk.values.TypeDescriptors;


public class code_03_average_numbers {
    public static void main(String args[]) {
        Pipeline pipeline = Pipeline.create();

        PCollection<Integer> values  = pipeline.apply(Create.of(1,2,3,4,5,6,7,8,9,10));

        PCollection<Double> result = values.apply(Mean.globally());

        result.apply(MapElements
                .into(TypeDescriptors.doubles())
                .via((Double value)   -> {
                    System.out.println(value);
                    return value;
                })
        );

        pipeline.run();
    }
}


//import org.apache.beam.sdk.Pipeline;
//import org.apache.beam.sdk.transforms.Combine;
//import org.apache.beam.sdk.transforms.MapElements;
//import org.apache.beam.sdk.transforms.SimpleFunction;
//import org.apache.beam.sdk.values.PCollection;
//import org.apache.beam.sdk.values.TypeDescriptor;
//
//public class AverageCalculator {
//    public static void main(String[] args) {
//        Pipeline pipeline = Pipeline.create();
//
//        PCollection<Integer> numbers = pipeline.apply(Create.of(1, 2, 3, 4, 5));
//
//        PCollection<Double> average = numbers.apply(Combine.globally((Iterable<Integer> input) -> {
//            int sum = 0;
//            int count = 0;
//            for (Integer number : input) {
//                sum += number;
//                count++;
//            }
//            return sum / (double) count;
//        }));
//
//        average.apply(MapElements.into(TypeDescriptor.of(String.class))
//                        .via((Double avg) -> "Average: " + avg))
//                .apply(MapElements.via(new SimpleFunction<String, Void>() {
//                    public Void apply(String input) {
//                        System.out.println(input);
//                        return null;
//                    }
//                }));
//
//        pipeline.run();
//    }
//}
