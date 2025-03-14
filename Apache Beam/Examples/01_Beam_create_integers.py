import apache_beam as beam

with beam.Pipeline() as p:
    # Create a PCollection of integers
    integers = p | 'Create integers' >> beam.Create([1, 2, 3, 4, 5])
    
    # Print each element in the PCollection
    integers | 'Print elements' >> beam.Map(print)
