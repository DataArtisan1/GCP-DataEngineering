import apache_beam as beam

class MultiplyByTwo(beam.DoFn):
    def process(self, element):
        yield element * 2

with beam.Pipeline() as p:
    numbers = [1, 2, 3]

    # Use ParDo with a custom DoFn
    result = (
        p
        | 'Create numbers' >> beam.Create(numbers)
        | 'Multiply by 2' >> beam.ParDo(MultiplyByTwo())
        | 'Print results' >> beam.Map(print)
    )
