import apache_beam as beam

with beam.Pipeline() as p:
    kv_pairs = [('A', 1), ('A', 2), ('B', 3), ('B', 4)]

    # Group by key and sum the values for each key
    summed = (
        p
        | 'Create key-value pairs' >> beam.Create(kv_pairs)
        | 'Group by key' >> beam.GroupByKey()
        | 'Sum values' >> beam.Map(lambda kv: (kv[0], sum(kv[1])))
        | 'Print results' >> beam.Map(print)
    )
