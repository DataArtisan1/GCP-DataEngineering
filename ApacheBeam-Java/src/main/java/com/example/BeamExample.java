package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.PCollection;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.SimpleFunction;

public class BeamExample {
    public static void main(String[] args) {

        Pipeline pipeline = Pipeline.create();

        PCollection<String> input = pipeline.apply("Create Input",
                Create.of("Hello", "World"));

        PCollection<String> output = input.apply("To Uppercase",
                MapElements.via(new SimpleFunction<String, String>() {
                    public String apply(String input) {
                        return input.toUpperCase();
                    }
                }));

        output.apply("Print Output", ParDo.of(new DoFn<String, Void>() {
            @ProcessElement
            public void processElement(ProcessContext context) {
                System.out.println(context.element());
            }
        }));

        pipeline.run().waitUntilFinish();
    }
}
