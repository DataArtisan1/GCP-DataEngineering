package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptor;

public class code_01_BeamCreate_String {
    public static void main(String[] args) {
        // Create a pipeline
        Pipeline p = Pipeline.create();

        // Apply a transform to create a PCollection
        PCollection<String> words = p.apply("Creating Strings", Create.of("Hello", "World"));

        // Apply a transform to convert words to uppercase
        PCollection<String> uppercasedWords = words.apply("Applying Transformations",
                MapElements
                        .into(TypeDescriptor.of(String.class))
                        .via((String word) -> {
                            assert word != null;
                            return word.toUpperCase();
                        })
        );

        // Apply a transform to print each word
        uppercasedWords.apply("Printing Strings",
                MapElements
                        .into(TypeDescriptor.of(Void.class))
                        .via((String word) -> {
                            System.out.println(word);
                            return null;
                        })
        );

        // Run the pipeline
        p.run().waitUntilFinish();
    }
}
