package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.transforms.Create;

public class code_07_BeamParDo_KeyValue {
    public static void main(String[] args) {

        // Create a pipeline
        Pipeline pipeline = Pipeline.create();

        // Create a PCollection of key-value pairs
        PCollection<KV<String, Integer>> keyValuePairs = pipeline.apply(Create.of(
                KV.of("Key1", 10),
                KV.of("Key2", 20),
                KV.of("Key3", 30)));

        // Apply ParDo to process each key-value pair
        PCollection<String> results = keyValuePairs.apply(ParDo.of(new DoFn<KV<String, Integer>, String>() {
            @ProcessElement
            public void processElement(ProcessContext c) {
                KV<String, Integer> kv = c.element();  // Get the current key-value pair
                String result = "Key: " + kv.getKey() + ", Value: " + kv.getValue();  // Create a string representation
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
