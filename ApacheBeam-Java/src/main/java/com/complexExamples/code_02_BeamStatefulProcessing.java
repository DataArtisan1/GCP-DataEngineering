package com.complexExamples;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.state.*;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.apache.beam.sdk.transforms.Create;

public class code_02_BeamStatefulProcessing {
    public static void main(String[] args) {

        Pipeline pipeline = Pipeline.create();

        pipeline.apply(Create.of(KV.of("key1", 1), KV.of("key1", 2), KV.of("key2", 3), KV.of("key2", 4)))
                .apply(ParDo.of(new StatefulDoFn()))
                .apply(MapElements.into(TypeDescriptors.voids())
                        .via((String result) -> {
                            System.out.println(result);
                            return null;
                        }));

        pipeline.run().waitUntilFinish();
    }

    static class StatefulDoFn extends DoFn<KV<String, Integer>, String> {
        @StateId("sum")
        private final StateSpec<ValueState<Integer>> sumSpec = StateSpecs.value();

        @ProcessElement
        public void processElement(@Element KV<String, Integer> element, @StateId("sum") ValueState<Integer> sum, OutputReceiver<String> out) {
            Integer currentSum = sum.read();
            if (currentSum == null) {
                currentSum = 0;
            }
            currentSum += element.getValue();
            sum.write(currentSum);
            out.output(element.getKey() + ": " + currentSum);
        }
    }
}
