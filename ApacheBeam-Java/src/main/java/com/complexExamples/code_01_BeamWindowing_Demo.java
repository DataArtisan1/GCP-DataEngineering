package com.complexExamples;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.transforms.windowing.*;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.TimestampedValue;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.joda.time.Duration;
import org.joda.time.Instant;

public class code_01_BeamWindowing_Demo {
    public static void main(String[] args) {

        Pipeline pipeline = Pipeline.create();

        // Create a PCollection with timestamps and different values
        pipeline.apply(Create.timestamped(
                        TimestampedValue.of(1, Instant.now().plus(Duration.standardSeconds(1))),
                        TimestampedValue.of(2, Instant.now().plus(Duration.standardSeconds(5))),
                        TimestampedValue.of(3, Instant.now().plus(Duration.standardSeconds(10))),
                        TimestampedValue.of(4, Instant.now().plus(Duration.standardSeconds(16))),
                        TimestampedValue.of(5, Instant.now().plus(Duration.standardSeconds(20))),
                        TimestampedValue.of(6, Instant.now().plus(Duration.standardSeconds(25)))))

                // Apply fixed windowing of 10 seconds
                .apply(Window.<Integer>into(FixedWindows.of(Duration.standardSeconds(10))))

                // Group by key to summarize the results
                .apply(ParDo.of(new DoFn<Integer, KV<String, Integer>>() {
                    @ProcessElement
                    public void processElement(ProcessContext c) {
                        // For demonstration, we use each element as its own "key"
                        c.output(KV.of("Window", c.element()));
                    }
                }))

                // Group by key (which is just "Window" in this case)
                .apply(GroupByKey.create())

                // Summarize results for each window
                .apply(MapElements.into(TypeDescriptor.of(String.class))
                        .via((KV<String, Iterable<Integer>> grouped) -> {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Window Results:\n");
                            for (Integer value : grouped.getValue()) {
                                sb.append(value).append(" ");
                            }
                            return sb.toString();
                        }))

                // Print the results
                .apply(MapElements.into(TypeDescriptor.of(Void.class))
                        .via((String result) -> {
                            System.out.println(result);
                            return null;
                        }));

        // Run the pipeline
        pipeline.run().waitUntilFinish();
    }
}
