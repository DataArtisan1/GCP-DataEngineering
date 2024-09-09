1. Pipeline Basics
Concept: The Pipeline represents a series of data processing steps. It is used to define and execute your data processing workflow.

Code:

java
Copy code
Pipeline p = Pipeline.create();
Explanation:

Pipeline.create() initializes a new pipeline. This is the entry point for all Beam operations. You apply transforms to this pipeline to process data.
2. Creating a PCollection
Concept: PCollection is a distributed dataset that can be processed in parallel. You can create a PCollection from an in-memory collection or other data sources.

Code:

java
Copy code
PCollection<String> words = p.apply("Creating Strings", Create.of("Hello", "World"));
Explanation:

p.apply("Creating Strings", Create.of("Hello", "World")) creates a PCollection named "Creating Strings" from an in-memory list of strings: "Hello" and "World".
"Creating Strings" is a label used for debugging and visualization purposes.
3. Applying Transformations
Concept: Transformations modify the data in a PCollection. Common transformations include MapElements, FlatMapElements, and Filter.

Code:

java
Copy code
PCollection<String> uppercasedWords = words.apply("Applying Transformations",
    MapElements
        .into(TypeDescriptor.of(String.class))
        .via((String word) -> word.toUpperCase())
);
Explanation:

MapElements applies a function to each element of a PCollection.
TypeDescriptor.of(String.class) specifies the output type of the transformation.
.via((String word) -> word.toUpperCase()) defines the transformation function, converting each word to uppercase.
4. Printing Elements
Concept: You can print elements of a PCollection using MapElements with a Void return type.

Code:

java
Copy code
uppercasedWords.apply("Printing Strings",
    MapElements
        .into(TypeDescriptor.of(Void.class))
        .via((String word) -> {
            System.out.println(word);
            return null;
        })
);
Explanation:

MapElements.into(TypeDescriptor.of(Void.class)) is used to apply a function where the result type is Void (i.e., no output from the transformation).
.via((String word) -> { System.out.println(word); return null; }) prints each element to the console and returns null since the result type is Void.
5. Running the Pipeline
Concept: To execute the pipeline, you need to call the run method.

Code:

java
Copy code
p.run().waitUntilFinish();
Explanation:

p.run() starts the execution of the pipeline.
.waitUntilFinish() waits for the pipeline to complete.
