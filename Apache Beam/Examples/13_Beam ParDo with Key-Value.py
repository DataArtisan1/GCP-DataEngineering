import apache_beam as beam

class MultiplyValues(beam.DoFn):
    def process(self, element):
        key, value = element
        yield (key, value * 2)

with beam.Pipeline() as p:
    kv_pairs = [('A', 1), ('B', 2)]

    # Use ParDo on key-value pairs
    result = (
        p
        | 'Create' >>  beam.Create(kv_pairs)
        | 'Multiply' >> beam.ParDo(MultiplyValues())
        | 'print' >> beam.Map(print)
    )

