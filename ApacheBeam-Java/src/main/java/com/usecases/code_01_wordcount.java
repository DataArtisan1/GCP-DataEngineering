package com.usecases;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

import java.util.Arrays;


public class code_01_wordcount {

    public static void main(String args[]){

        System.out.println("Create an object of the Beam Pipeline");
        Pipeline pipeline = Pipeline.create();

        System.out.println("Reading the Input File");
        PCollection<String> lines = pipeline.apply("Text File read",TextIO.read().from("src/main/java/datasets/word_count_file.txt"));

        System.out.println("Printing Each Line");
        lines.apply(MapElements
                .into(TypeDescriptors.strings())
                .via((String line) -> {
                    System.out.println("Read line: " + line);
                    return line;
                }));

        System.out.println("Splitting Each line into words");
        PCollection<String> words = lines.apply(FlatMapElements
                .into(TypeDescriptors.strings())
                .via((String line) ->
                        Arrays.asList(line.split("\\W+")) //Returns array of words.
        ));

        System.out.println("Printing Each word");
        words.apply(MapElements
                .into(TypeDescriptors.strings())
                .via((String word) -> {
                    System.out.println("Word: "+word);
                    return word;
                })
        );

        PCollection<KV<String, Long>> wordCounts = words.apply(Count.perElement());

        System.out.println("Printing Each word and Count");
        PCollection<String> keyValueString = wordCounts.apply(MapElements
                .into(TypeDescriptors.strings())
                .via((wordCount) -> {
                    String result = wordCount.getKey() + ": " + wordCount.getValue();
                    System.out.println(result);
                    return result;
                 }));

        keyValueString.apply(
                TextIO.write()
                    .to("src/main/java/datasets/output/word_count_output/output.txt")
                    .withNumShards(1)  // Control the number of output files
        );

        pipeline.run().waitUntilFinish();

    }

}
