package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.apache.beam.sdk.transforms.Create;

public class code_01_BeamCreate_Integers {
    public static void main(String[] args) {

        Pipeline pipeline = Pipeline.create();

        // Create a PCollection with integers
        pipeline.apply(Create.of(1, 2, 3, 4, 5))
                .apply(MapElements.into(TypeDescriptors.integers())
                        .via((Integer number) -> number * 2))  // Multiply each number by 2
                .apply(MapElements.into(TypeDescriptors.strings())
                        .via(Object::toString))  // Convert each result to string
                .apply(MapElements.into(TypeDescriptors.voids())
                        .via((String result) -> {
                            System.out.println(result);
                            return null;
                        }));

        // Run the pipeline
        pipeline.run().waitUntilFinish();
    }
}
