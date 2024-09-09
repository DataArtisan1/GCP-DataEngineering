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
