package com.usecases;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.apache.beam.sdk.values.TypeDescriptors;


import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.Filter;

public class code_02_even_odd {
    public static void main(String[] args) {

        Pipeline pipeline = Pipeline.create();

        PCollection<Integer> values = pipeline.apply(Create.
                of(1,2,3,4,5,6,7,8,9,10));

        PCollection<Integer> even_values = values.apply(Filter
                .by((Integer number) -> number%2==0
        ));

        PCollection<Integer> odd_values = values.apply(Filter
                .by((Integer number) -> number%2!=0
                ));

        even_values.apply(MapElements
                .into(TypeDescriptors.integers())
                .via((Integer value)   -> {
                    System.out.println(value);
                    return value;
                })
        );

        odd_values.apply(MapElements
                .into(TypeDescriptors.integers())
                .via((Integer value)   -> {
                    System.out.println(value);
                    return value;
                })
        );

        pipeline.run();
    }
}
