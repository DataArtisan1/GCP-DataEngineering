import apache_beam as beam

with beam.Pipeline() as p:
    # Create a PCollection of strings
    strings = p | 'Create strings' >> beam.Create(['apple', 'banana', 'cherry'])
    
    # Print each string
    strings | 'Print strings' >> beam.Map(print)
