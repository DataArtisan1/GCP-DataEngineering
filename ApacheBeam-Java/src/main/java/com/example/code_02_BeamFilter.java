package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.apache.beam.sdk.transforms.Create;

public class code_02_BeamFilter {
    public static void main(String[] args) {

        Pipeline pipeline = Pipeline.create();

        // Create a PCollection with integers
        pipeline.apply("Creating Pcollection with Integers",Create.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))

                // Filter numbers less than 5
                .apply("Filter less than 5",Filter.lessThan(5))

                // Convert each result to string
                // .apply(MapElements.into(TypeDescriptors.strings())
                //.via(Object::toString))

                //Print each Integer Element
                .apply("Printing Each Integer",MapElements.into(TypeDescriptors.voids())
                        .via((Integer result) -> {
                            System.out.println(result);
                            return null;
                        }));

        // Run the pipeline
        pipeline.run().waitUntilFinish();
    }
}
