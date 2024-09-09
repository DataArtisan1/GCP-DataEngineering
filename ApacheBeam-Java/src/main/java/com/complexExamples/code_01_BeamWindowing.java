package com.complexExamples;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.transforms.windowing.*;
import org.apache.beam.sdk.values.TimestampedValue;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.joda.time.Duration;
import org.apache.beam.sdk.transforms.Create;

public class code_01_BeamWindowing {
    public static void main(String[] args) {

        Pipeline pipeline = Pipeline.create();

        // Create a PCollection with timestamps
        pipeline.apply(Create.timestamped(
                        TimestampedValue.of(1, org.joda.time.Instant.now()),
                        TimestampedValue.of(2, org.joda.time.Instant.now().plus(Duration.standardSeconds(10))),
                        TimestampedValue.of(3, org.joda.time.Instant.now().plus(Duration.standardSeconds(20)))))

                .apply(Window.<Integer>into(FixedWindows.of(Duration.standardSeconds(15)))) // Fixed windowing of 15 seconds

                .apply(MapElements.into(TypeDescriptors.strings())
                        .via(Object::toString))

                .apply(MapElements.into(TypeDescriptors.voids())
                        .via((String result) -> {
                            System.out.println(result);
                            return null;
                        }));

        // Run the pipeline
        pipeline.run().waitUntilFinish();
    }
}
