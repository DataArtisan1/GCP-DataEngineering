package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.apache.beam.sdk.transforms.Create;

import java.util.Arrays;

public class code_04_BeamFlatMap {
    public static void main(String[] args) {

        Pipeline pipeline = Pipeline.create();

        // Create a PCollection with sentences and split them into words
        pipeline.apply(Create.of("Hello World", "Apache Beam", "Java SDK"))

                // Transformation - FlatMapElements to convert list of sentences to words
                .apply(FlatMapElements
                        .into(TypeDescriptors.strings())
                        .via((String sentence) -> Arrays.asList(sentence.split(" "))))

                //Print the element
                .apply(MapElements.into(TypeDescriptors.voids())
                        .via((String word) -> {
                            System.out.println(word);
                            return null;
                        }));

        // Run the pipeline
        pipeline.run().waitUntilFinish();
    }
}
