package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.apache.beam.sdk.transforms.Create;

public class code_03_BeamMapElements_formattedStringOutput {
    public static void main(String[] args) {

        Pipeline pipeline = Pipeline.create();

        // Create a PCollection with integers
        pipeline.apply(Create.of(1, 2, 3, 4, 5))

                // Apply Transformations to convert each integer into "elem_XX" format using MapElements
                .apply(MapElements.into(TypeDescriptor.of(String.class))
                        .via((Integer number) -> String.format("elem_%02d", number)))

                // Print each result
                .apply(MapElements.into(TypeDescriptors.voids())
                        .via((String result) -> {
                            System.out.println(result);
                            return null;
                        }));

        // Run the pipeline
        pipeline.run().waitUntilFinish();
    }
}
