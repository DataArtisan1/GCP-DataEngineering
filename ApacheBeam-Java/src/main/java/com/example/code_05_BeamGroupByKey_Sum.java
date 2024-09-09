package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.GroupByKey;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.apache.beam.sdk.transforms.Create;

public class code_05_BeamGroupByKey_Sum {
    public static void main(String[] args) {

        Pipeline pipeline = Pipeline.create();

        // Create a PCollection of key-value pairs
        pipeline.apply(Create.of(
                        KV.of("Category1", 1),
                        KV.of("Category1", 2),
                        KV.of("Category2", 3),
                        KV.of("Category2", 4)))

                // Creating transformation - GroupByKey
                .apply(GroupByKey.create())

                // Applying transformation to sum values for each key
                .apply(MapElements.into(TypeDescriptor.of(String.class))
                        .via((KV<String, Iterable<Integer>> grouped) -> {
                            // Sum the values associated with each key
                            int sum = 0;
                            for (Integer value : grouped.getValue()) {
                                sum += value;
                            }
                            return grouped.getKey() + ": " + sum;
                        }))

                // Print the elements of the result
                .apply(MapElements.into(TypeDescriptor.of(Void.class))
                        .via((String result) -> {
                            System.out.println(result);
                            return null;
                        }));

        // Run the pipeline
        pipeline.run().waitUntilFinish();
    }
}
