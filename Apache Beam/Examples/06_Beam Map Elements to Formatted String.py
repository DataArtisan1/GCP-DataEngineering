import apache_beam as beam

with beam.Pipeline() as p:
    numbers = [1, 2, 3]

    # Map numbers to a formatted string
    formatted_output = (
        p
        | 'Create numbers' >> beam.Create(numbers)
        | 'Format to string' >> beam.Map(lambda x: f'The number is {x}')
        | 'Print formatted output' >> beam.Map(print)
    )
