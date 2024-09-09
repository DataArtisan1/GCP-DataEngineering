package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptor;

public class code_01_BeamCreate_KV {
    public static void main(String[] args) {
        Pipeline pipeline = Pipeline.create();

        // Create a PCollection of Key-Value pairs
        PCollection<KV<String, Integer>> keyValuePairs = pipeline.apply(Create.of(
                KV.of("Category1", 1),
                KV.of("Category1", 2),
                KV.of("Category2", 3),
                KV.of("Category2", 4)));

        // Apply a transformation or print the elements (for demonstration)
        keyValuePairs.apply(MapElements.into(TypeDescriptor.of(Void.class))
                .via((KV<String, Integer> kv) -> {
                    System.out.println("Key: " + kv.getKey() + ", Value: " + kv.getValue());
                    return null;
                }));

        pipeline.run().waitUntilFinish();
    }
}
