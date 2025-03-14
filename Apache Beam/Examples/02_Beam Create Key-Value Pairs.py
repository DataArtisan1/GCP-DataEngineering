import apache_beam as beam

with beam.Pipeline() as p:
    # Create a PCollection of key-value pairs (tuples)
    kv_pairs = p | 'Create key-value pairs' >> beam.Create([('key1', 1), ('key2', 2), ('key1', 3)])
    
    # Print each key-value pair
    kv_pairs | 'Print key-value pairs' >> beam.Map(print)
