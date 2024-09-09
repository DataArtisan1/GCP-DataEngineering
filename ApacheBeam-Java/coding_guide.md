# Apache Beam Java Basics

- **Pipeline Basics**
   - **Concept**: The Pipeline represents a series of data processing steps. It is used to define and execute your data processing workflow.
   - **Code**:
     ```java
     Pipeline p = Pipeline.create();
     ```
   - **Explanation**:  
     `Pipeline.create()` initializes a new pipeline. This is the entry point for all Beam operations. You apply transforms to this pipeline to process data.


- **Creating a PCollection**
   - **Concept**: PCollection is a distributed dataset that can be processed in parallel. You can create a PCollection from an in-memory collection or other data sources.
   - **Code**:
     ```java
     PCollection<String> words = p.apply("Creating Strings", Create.of("Hello", "World"));
     ```
   - **Explanation**:  
     `p.apply("Creating Strings", Create.of("Hello", "World"))` creates a PCollection named "Creating Strings" from an in-memory list of strings: "Hello" and "World".  
     "Creating Strings" is a label used for debugging and visualization purposes.


- **Applying Transformations**
   - **Concept**: Transformations modify the data in a PCollection. Common transformations include `MapElements`, `FlatMapElements`, and `Filter`.
   - **Code**:
     ```java
     PCollection<String> uppercasedWords = words.apply("Applying Transformations",
         MapElements
         .into(TypeDescriptor.of(String.class))
         .via((String word) -> word.toUpperCase())
     );
     ```
   - **Explanation**:  
     `MapElements` applies a function to each element of a PCollection.  
     `TypeDescriptor.of(String.class)` specifies the output type of the transformation.  
     `.via((String word) -> word.toUpperCase())` defines the transformation function, converting each word to uppercase.


- **Printing Elements**
   - **Concept**: You can print elements of a PCollection using `MapElements` with a `Void` return type.
   - **Code**:
     ```java
     uppercasedWords.apply("Printing Strings",
         MapElements
         .into(TypeDescriptor.of(Void.class))
         .via((String word) -> {
             System.out.println(word);
             return null;
         })
     );
     ```
   - **Explanation**:  
     `MapElements.into(TypeDescriptor.of(Void.class))` is used to apply a function where the result type is `Void` (i.e., no output from the transformation).  
     `.via((String word) -> { System.out.println(word); return null; })` prints each element to the console and returns `null` since the result type is `Void`.

- **Running the Pipeline**
   - **Concept**: To execute the pipeline, you need to call the `run` method.
   - **Code**:
     ```java
     p.run().waitUntilFinish();
     ```
   - **Explanation**:  
     `p.run()` starts the execution of the pipeline.  
     `.waitUntilFinish()` waits for the pipeline to complete.



# Additional Explanations for Code Snippets

- **apply()**
   - **Concept**: `apply()` is a method used to apply a transformation to a `PCollection`. It accepts a transform and returns a new `PCollection` with the results of the transformation.
   - **Usage**: In the snippet `p.apply("Creating Strings", Create.of("Hello", "World"))`, the `apply` method is used to apply the `Create` transform to the pipeline, which creates a `PCollection`.


- **via()**
   - via() defines the process (input transformation).
   - **Concept**: `via()` defines the transformation logic for how elements in the `PCollection` are processed.
   - **Usage**: In the snippet `.via((String word) -> word.toUpperCase())`, the `via()` method is used to specify the transformation logic, in this case converting each string to uppercase.


- **TypeDescriptor.of()**
   - **Concept**: `TypeDescriptor.of()` is used to specify the type of elements in the `PCollection` after a transformation.
   - **Usage**: In the snippet `TypeDescriptor.of(String.class)`, it defines the type of the output of the transformation, indicating that the elements will be `String` objects.


- **into()**
   - into() defines the output type (result structure).
   - **Concept**: `into()` is a method used to specify the target type of the transformation’s output.
   - **Usage**: In the snippet `MapElements.into(TypeDescriptor.of(String.class))`, `into()` specifies that the transformation will output a `PCollection` of strings.


- **Create.of()**
   - **Concept**: `Create.of()` is a Beam transform used to create a `PCollection` from an in-memory data source like a list or array.
   - **Usage**: In the snippet `Create.of("Hello", "World")`, it creates a `PCollection` with the elements "Hello" and "World".


- **waitUntilFinish()**
   - **Concept**: `waitUntilFinish()` waits for the pipeline to finish executing before proceeding further.
   - **Usage**: In the snippet `p.run().waitUntilFinish()`, it ensures the pipeline completes its execution before the program continues.


# Type Descriptors

- **`TypeDescriptors.strings()`**: For `String` type.
- **`TypeDescriptors.integers()`**: For `Integer` type.
- **`TypeDescriptors.longs()`**: For `Long` type.
- **`TypeDescriptors.doubles()`**: For `Double` type.
- **`TypeDescriptors.floats()`**: For `Float` type.
- **`TypeDescriptors.booleans()`**: For `Boolean` type.
- **`TypeDescriptors.bytes()`**: For `Byte` type.
- **`TypeDescriptors.kvs(TypeDescriptor<K>, TypeDescriptor<V>)`**: For `KV<K, V>` type.
- **`TypeDescriptors.iterables(TypeDescriptor<T>)`**: For `Iterable<T>` type.
- **`TypeDescriptors.maps(TypeDescriptor<K>, TypeDescriptor<V>)`**: For `Map<K, V>` type.
- **`TypeDescriptors.pcollections(TypeDescriptor<T>)`**: For `PCollection<T>` type.
- **`TypeDescriptors.list(TypeDescriptor<T>)`**: For `List<T>` type.
- **`TypeDescriptors.sets(TypeDescriptor<T>)`**: For `Set<T>` type.
- **`TypeDescriptor.of(Class<T>)`**: For any custom or complex type.
- **`TypeDescriptor.of(ParameterizedType)`**: For parameterized types like `List<String>` or `Map<String, Integer>`.


## Type descriptors with their class notations

- **`TypeDescriptors.strings()`**: For `String` type.  
  Equivalent to `TypeDescriptor.of(String.class)`.

- **`TypeDescriptors.integers()`**: For `Integer` type.  
  Equivalent to `TypeDescriptor.of(Integer.class)`.

- **`TypeDescriptors.longs()`**: For `Long` type.  
  Equivalent to `TypeDescriptor.of(Long.class)`.

- **`TypeDescriptors.doubles()`**: For `Double` type.  
  Equivalent to `TypeDescriptor.of(Double.class)`.

- **`TypeDescriptors.floats()`**: For `Float` type.  
  Equivalent to `TypeDescriptor.of(Float.class)`.

- **`TypeDescriptors.booleans()`**: For `Boolean` type.  
  Equivalent to `TypeDescriptor.of(Boolean.class)`.

- **`TypeDescriptors.bytes()`**: For `Byte` type.  
  Equivalent to `TypeDescriptor.of(Byte.class)`.

- **`TypeDescriptors.kvs(TypeDescriptor<K>, TypeDescriptor<V>)`**: For `KV<K, V>` type.

- **`TypeDescriptors.iterables(TypeDescriptor<T>)`**: For `Iterable<T>` type.

- **`TypeDescriptors.maps(TypeDescriptor<K>, TypeDescriptor<V>)`**: For `Map<K, V>` type.

- **`TypeDescriptors.pcollections(TypeDescriptor<T>)`**: For `PCollection<T>` type.

- **`TypeDescriptors.list(TypeDescriptor<T>)`**: For `List<T>` type.

- **`TypeDescriptors.sets(TypeDescriptor<T>)`**: For `Set<T>` type.

- **`TypeDescriptor.of(Class<T>)`**: For any custom or complex type.

- **`TypeDescriptor.of(ParameterizedType)`**: For parameterized types like `List<String>` or `Map<String, Integer>`.


### PCollectionView and View.asSingleton()

#### **PCollectionView**

- **What It Is:**
    - A `PCollectionView` is a special type of `PCollection` used to share static data across multiple elements in a `PCollection`.
    - Think of it as a way to pass small, read-only data to your processing steps. It’s like having a shared reference that multiple parts of your pipeline can access.

- **Usage:**
    - It’s typically used when you want to use a small amount of data (like configuration values or constants) in your transformations.

#### **View.asSingleton()**

- **What It Is:**
    - `View.asSingleton()` is a method that converts a `PCollection` into a `PCollectionView` that contains exactly one element.
    - This is useful when you know your `PCollection` will have only one element, and you need to use that single value across your entire pipeline.


### **User Defined Functions**

#### **DoFn**

- **What It Is:**
    - `DoFn` stands for "Do Function." It is a base class in Apache Beam used to define custom transformations applied to elements of a `PCollection`.
    - You subclass `DoFn` and override its `processElement` method to specify how each element should be transformed.

- **Usage:**
    - Use `DoFn` when you need custom logic for processing each element in a `PCollection`.

#### **@ProcessElement**

- **What It Is:**
    - `@ProcessElement` is an annotation that marks a method in a `DoFn` subclass as the method that processes individual elements of the `PCollection`.

- **Usage:**
    - This method contains the logic for processing each element and is called once for each element in the `PCollection`.

#### **ProcessContext**

- **What It Is:**
    - `ProcessContext` is an object provided to the `@ProcessElement` method. It gives access to the current element being processed and provides methods to output results.

- **Key Methods:**
    - `c.element()`: Retrieves the current element from the input `PCollection`.
    - `c.output(...)`: Outputs one or more results from the `@ProcessElement` method.

#### **ParDo**

- **What It Is:**
    - `ParDo` is a transform that applies a `DoFn` to each element of a `PCollection`. It is used for parallel processing of elements.

- **Usage:**
    - Use `ParDo` when you want to apply custom processing logic to each element in a `PCollection`. It can output zero or more results for each input element.

### **c.output()**

- **What It Is:**

  - `c.output()` is a method provided by the `ProcessContext` object inside the `@ProcessElement` method of a `DoFn`.
  - It allows you to emit results from the `DoFn` for each element processed.

- **Usage:**

  - **Emitting Results**: Use `c.output()` to send processed data to the next stage in the pipeline.
  - **Multiple Outputs**: You can call `c.output()` multiple times to emit multiple results for a single input element.

- **Parameters:**

  - **Single Element**: Pass a single element to `c.output()` to emit it as part of the `PCollection` being processed.
  - **Iterable**: You can also pass an `Iterable` to `c.output()` if you need to emit multiple elements at once.


### **Example Code**

```java
package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.apache.beam.sdk.transforms.Create;

public class code_06_BeamParDo {
    public static void main(String[] args) {

        // Create a pipeline
        Pipeline pipeline = Pipeline.create();

        // Create a PCollection of integers
        PCollection<Integer> numbers = pipeline.apply(Create.of(1, 2, 3, 4, 5));

        // Apply ParDo to process each element
        PCollection<String> results = numbers.apply(ParDo.of(new DoFn<Integer, String>() {
            @ProcessElement
            public void processElement(ProcessContext c) {
                Integer number = c.element();  // Get the current element (integer)
                String result = "Number: " + number;  // Create a string representation
                c.output(result);  // Output the result
            }
        }));

        // Print the results
        results.apply(MapElements.into(TypeDescriptor.of(Void.class))
                .via((String result) -> {
                    System.out.println(result);  // Print each result
                    return null;
                }));

        // Run the pipeline
        pipeline.run().waitUntilFinish();
    }
}
```

```java
ParDo.of(new DoFn<input, output>() {
    @ProcessElement
    public void processElement(ProcessContext c) {
        // c.element() returns an element of type input
        // c.output(outputElement) emits an element of type output
    }
});

```

### **Code Explanation**

- **`DoFn<Integer, String>`**: Defines a custom transformation where each `Integer` input element is transformed into a `String`.

- **`@ProcessElement` Method**:
    - **`processElement(ProcessContext c)`**:
        - **`c.element()`**: Retrieves the current integer.
        - **`c.output(result)`**: Outputs the transformed string.

- **`ParDo.of(...)`**: Applies the custom `DoFn` to each element of the `PCollection`.

- **`MapElements`**: Converts the resulting `PCollection<String>` to print each result.

- **`pipeline.run().waitUntilFinish()`**: Executes the pipeline and waits for it to complete.
