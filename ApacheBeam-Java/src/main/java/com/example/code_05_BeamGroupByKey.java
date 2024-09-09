package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.GroupByKey;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.apache.beam.sdk.transforms.Create;

public class code_05_BeamGroupByKey {
    public static void main(String[] args) {

        Pipeline pipeline = Pipeline.create();

        // Create a PCollection of key-value pairs
        pipeline.apply(Create.of(
                        KV.of("Category1", 1),
                        KV.of("Category1", 2),
                        KV.of("Category2", 3),
                        KV.of("Category2", 4)))

                // Creating transformation - GroupbyKey
                .apply(GroupByKey.create())

                // Applying transformation to consolidate each key-value pair to key-listofvalues
                .apply(MapElements.into(TypeDescriptors.strings())
                        .via((KV<String, Iterable<Integer>> grouped) -> {
                            return grouped.getKey() + ": " + grouped.getValue();
                        }))

                // Print the elements of the result
                .apply(MapElements.into(TypeDescriptors.voids())
                        .via((String result) -> {
                            System.out.println(result);
                            return null;
                        }));

        // Run the pipeline
        pipeline.run().waitUntilFinish();
    }
}
