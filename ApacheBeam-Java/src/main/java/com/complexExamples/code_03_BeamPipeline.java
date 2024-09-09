/*
this program creates a data processing pipeline that prints a collection of strings.
The collection includes "Hello", "World!", and a third string that can be specified as a command-line argument
(defaulting to "My input text" if not specified).

Cmd to run - mvn exec:java -D"exec.mainClass"="com.complexExamples.code_03_BeamPipeline"

java -cp your_classpath com.complexExamples.code_03_BeamPipeline --inputText="Your custom text"


mvn exec:java -D"exec.mainClass=com.complexExamples.code_03_BeamPipeline" -D"exec.args=--inputText='Your custom text'"

*/

package com.complexExamples;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.StreamingOptions;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

import java.util.Arrays;

public class code_03_BeamPipeline {

    public interface Options extends StreamingOptions {
        @Description("Input text to print.")
        @Default.String("My input text")
        String getInputText();

        void setInputText(String value);
    }

    public static PCollection<String> buildPipeline(Pipeline pipeline, String inputText) {
        return pipeline
                .apply("Create elements", Create.of(Arrays.asList("Hello", "World!", inputText)))
                .apply("Print elements",
                        MapElements.into(TypeDescriptors.strings()).via(x -> {
                            System.out.println(x);
                            return x;
                        }));
    }

    public static void main(String[] args) {
        Options options = PipelineOptionsFactory.fromArgs(args).withValidation().as(Options.class);
        Pipeline pipeline = Pipeline.create(options);
        buildPipeline(pipeline, options.getInputText());
        pipeline.run().waitUntilFinish();
    }
}