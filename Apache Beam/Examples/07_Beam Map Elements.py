import apache_beam as beam

with beam.Pipeline() as p:
    numbers = [1, 2, 3]

    # Multiply each number by 2
    multiplied = (
        p
        | 'Create numbers' >> beam.Create(numbers)
        | 'Multiply by 2' >> beam.Map(lambda x: x * 2)
        | 'Print results' >> beam.Map(print)
    )
