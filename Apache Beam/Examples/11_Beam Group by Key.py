import apache_beam as beam

with beam.Pipeline() as p:
    kv_pairs = [('A', 1), ('A', 2), ('B', 3), ('B', 4)]

    # Group by key without additional operations
    grouped = (
        p
        | 'Create key-value pairs' >> beam.Create(kv_pairs)
        | 'Group by key' >> beam.GroupByKey()
        | 'Print grouped' >> beam.Map(print)
    )
