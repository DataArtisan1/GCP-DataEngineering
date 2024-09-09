package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.apache.beam.sdk.transforms.Create;

public class code_06_BeamParDo {
    public static void main(String[] args) {

        // Create a pipeline
        Pipeline pipeline = Pipeline.create();

        // Create a PCollection of integers
        PCollection<Integer> numbers = pipeline.apply(Create.of(1, 2, 3, 4, 5));

        // Apply ParDo to process each element
        PCollection<String> results = numbers.apply(ParDo.of(new DoFn<Integer, String>() {
            @ProcessElement
            public void processElement(ProcessContext c) {
                Integer number = c.element();  // Get the current element (integer)
                String result = "Number: " + number;  // Create a string representation
                c.output(result);  // Output the result
            }
        }));

        // Print the results
        results.apply(MapElements.into(TypeDescriptor.of(Void.class))
                .via((String result) -> {
                    System.out.println(result);  // Print each result
                    return null;
                }));

        // Run the pipeline
        pipeline.run().waitUntilFinish();
    }
}
