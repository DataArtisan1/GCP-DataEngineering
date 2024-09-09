package com.complexExamples;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.*;
import org.apache.beam.sdk.transforms.Create;

public class code_02_BeamSideInputs {
    public static void main(String[] args) {

        // Create a Pipeline object to define and run the data processing pipeline
        Pipeline pipeline = Pipeline.create();

        // Create a side input: a PCollection containing a single value (multiplier)
        final PCollectionView<Integer> multiplier = pipeline.apply("CreateMultiplier", Create.of(2))
                .apply(View.asSingleton());  // Converts the PCollection into a singleton view

        // Create a main input: a PCollection of integers
        pipeline.apply(Create.of(1, 2, 3, 4, 5))

                // Apply a ParDo transformation to process each element
                .apply(ParDo.of(new DoFn<Integer, String>() {

                    // Define the processing logic
                    @ProcessElement
                    public void processElement(ProcessContext c) {
                        // Retrieve the side input (multiplier) and multiply it with the current element
                        int multipliedValue = c.element() * c.sideInput(multiplier);

                        // Output the result in the format "element * multiplier = result"
                        c.output(c.element() + " * " + c.sideInput(multiplier) + " = " + multipliedValue);
                    }

                }).withSideInputs(multiplier))  // Specify that the DoFn has a side input
                .apply(MapElements.into(TypeDescriptors.voids())
                        .via((String result) -> {
                            // Print the result to the console
                            System.out.println(result);
                            return null;  // Return null because MapElements is expected to output Void
                        }));

        // Run the pipeline and wait for it to finish
        pipeline.run().waitUntilFinish();
    }
}
