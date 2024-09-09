package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptor;

public class code_01_BeamCreate_Integer {
    public static void main(String[] args) {
        Pipeline pipeline = Pipeline.create();

        // Create a PCollection of Integers
        PCollection<Integer> numbers = pipeline.apply(Create.of(1, 2, 3, 4, 5));

        // Apply a transformation or print the elements (for demonstration)
        numbers.apply(MapElements.into(TypeDescriptor.of(Void.class))
                .via((Integer number) -> {
                    System.out.println(number);
                    return null;
                }));

        pipeline.run().waitUntilFinish();
    }
}
