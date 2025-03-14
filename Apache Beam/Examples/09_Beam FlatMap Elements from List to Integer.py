import apache_beam as beam

with beam.Pipeline() as p:
    lists_of_numbers = [[1, 2], [3, 4, 5]]

    # Flatten list of lists into individual integers
    flattened = (
        p
        | 'Create lists of numbers' >> beam.Create(lists_of_numbers)
        | 'Flatten lists' >> beam.FlatMap(lambda lst: lst)
        | 'Print integers' >> beam.Map(print)
    )
