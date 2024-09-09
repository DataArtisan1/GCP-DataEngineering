package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.apache.beam.sdk.transforms.Create;

import java.util.Arrays;
import java.util.List;

public class code_04_BeamFlatMapElements_ListToIntegers {
    public static void main(String[] args) {

        Pipeline pipeline = Pipeline.create();

        // Create a PCollection of lists of integers
        PCollection<List<Integer>> listOfIntegers = pipeline.apply(Create.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9)
        ));

        // Apply FlatMapElements to transform List<Integer> to individual integers
        PCollection<Integer> individualIntegers = listOfIntegers.apply(
                FlatMapElements
                        .into(TypeDescriptor.of(Integer.class))
                        .via((List<Integer> list) -> list)  // Flatten each list into individual integers
        );

        // Print each integer
        individualIntegers.apply(
                MapElements
                        .into(TypeDescriptor.of(Void.class))
                        .via((Integer number) -> {
                            System.out.println(number);
                            return null;
                        })
        );

        // Run the pipeline
        pipeline.run().waitUntilFinish();
    }
}
