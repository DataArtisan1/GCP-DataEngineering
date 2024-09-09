package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.SerializableCoder;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class code_01_BeamCreate_Objects {

    public static class MyClass implements Serializable {
        public int id;
        public String name;

        public MyClass() {} // Default constructor for serialization

        public MyClass(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "ID: "+id + " and Name: " + name;
        }
    }

    public static void main(String[] args) {
        Pipeline p = Pipeline.create();

        List<MyClass> myObjects = Arrays.asList(
                new MyClass(1, "Alice"),
                new MyClass(2, "Bob"),
                new MyClass(3, "Charlie")
        );

        // Apply the Create-transform with SerializableCoder
        PCollection<MyClass> myClassPCollection = p.apply(Create.of(myObjects)
                .withCoder(SerializableCoder.of(MyClass.class)));

        // Process each element of the PCollection
        myClassPCollection.apply(ParDo.of(new DoFn<MyClass, Void>() {
            @ProcessElement
            public void processElement(ProcessContext c) {
                MyClass obj = c.element();
                System.out.println(obj); // Using overridden toString method
            }
        }));

        p.run().waitUntilFinish();
    }
}
